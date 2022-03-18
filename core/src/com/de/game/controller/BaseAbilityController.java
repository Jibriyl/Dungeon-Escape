package com.de.game.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.BodyFactory;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.CollisionComponent;
import com.de.game.entity.components.StatComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;
import com.de.game.inputs.KeyboardController;

public class BaseAbilityController extends IteratingSystem{

  ComponentMapper<TransformComponent> trm;
  ComponentMapper<B2dBodyComponent> bodm;
  ComponentMapper<TypeComponent> tm;
  ComponentMapper<StateComponent> sm;

  private Engine engine;

  private BodyFactory bodyFactory;
  private KeyboardController controller;
  private float cooldown;
  private int dauer;
  private World world;
  boolean attack;


  public BaseAbilityController(World world, Engine engine, BodyFactory bodyFactory, KeyboardController keyCon){
    super(Family.all().get());
    this.world = world;
    this.engine = engine;
    this.bodyFactory = bodyFactory;
    this.controller = keyCon;
    cooldown = 0;
    dauer = 0;
    attack = false;

    tm = ComponentMapper.getFor(TypeComponent.class);
    bodm = ComponentMapper.getFor(B2dBodyComponent.class);
    trm = ComponentMapper.getFor(TransformComponent.class);
    sm = ComponentMapper.getFor(StateComponent.class);

  }

  @Override
  protected void processEntity(Entity entity, float deltaTime){
    TypeComponent type = tm.get(entity);
    B2dBodyComponent body = bodm.get(entity);

    //Erstellen einer Entity die als angriff fungiert
    if (type.type == TypeComponent.PLAYER && controller.space && cooldown <= 0 && !attack){
      simpleattack(sm.get(entity), body, trm.get(entity),11f,2f);
      cooldown = 2;
      attack = true;
    }
    if (cooldown > 0){
      cooldown = cooldown - deltaTime;
    }
    if (!entity.isScheduledForRemoval()){
      if (type.type == TypeComponent.ATTACK){
        dauer = dauer  + 1;
        if (dauer == 20){
          engine.removeEntity(entity);
          world.destroyBody(body.body);
          dauer = 0;
          attack = false;
        }
      }
    }
  }

  public void simpleattack(StateComponent state, B2dBodyComponent body, TransformComponent posi, float heightin, float widhtin){
    //Berechnung wo der Angriff plaziert werden muss
    float x;
    float y;
    float widht;
    float height;
    if (state.getLaststate() == "DOWN"){
      widht = widhtin;
      height = heightin;
      x = posi.position.x;
      y = posi.position.y - height/2 - (body.getHeight()/2);
    }
    else if(state.getLaststate() == "UP"){
      widht = widhtin;
      height = heightin;
      x = posi.position.x;
      y = posi.position.y + height/2 + (body.getHeight()/2);
    }
    else if(state.getLaststate() == "LEFT"){
      widht = heightin;
      height = widhtin;
      x = posi.position.x - widht/2 - (body.getWidth()/2);
      y = posi.position.y;
    }
    else{
      widht = heightin;
      height = widhtin;
      x = posi.position.x + widht/2 + (body.getWidth()/2);
      y = posi.position.y;
    }

    //Erstellen aller Komponenten für die Entität
    Entity entity = engine.createEntity();
    B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
    TransformComponent position = engine.createComponent(TransformComponent.class);
    CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
    TypeComponent type = engine.createComponent(TypeComponent.class);
    StatComponent stats = engine.createComponent(StatComponent.class);
    StateComponent stateCom = engine.createComponent(StateComponent.class);
  
    // create the data for the components and add them to the components
    b2dbody.body = bodyFactory.makeBox(x, y, widht, height, BodyFactory.PLAYER, BodyType.DynamicBody);
    bodyFactory.makeAllFixturesSensors(b2dbody.body);
    //Setzen der größe des Bodys, wird nicht benutzt um die tatsächliche größe zu bestimmen sondern um die Texture richtig zu platzieren
    b2dbody.setdimension(widht, height);
    stats.setStats(10, 100, 1000, 20);
    //Setzen der Playerstats Damage, Leben, Speed, Rüstung
    //Koordinanten des Spieler setzten, z wird benutzt um zu entscheiden was zuerst abgebildet werden soll
    position.position.set(10,10,0);
    type.type = TypeComponent.ATTACK;
    stateCom.setstate(StateComponent.STATE_NORMAL); 
    b2dbody.body.setUserData(entity);
  
    //Alle Kompenenten des Spieler der Spieler Entity hinzufügen
    entity.add(b2dbody);
    entity.add(position);
    entity.add(colComp);
    entity.add(stats);
    entity.add(type);
    entity.add(stateCom);
  
    //Die Entity in die engine adden
    engine.addEntity(entity);
  }
}
