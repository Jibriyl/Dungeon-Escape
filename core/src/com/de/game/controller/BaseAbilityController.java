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
  private float attackcooldown;
  private float dashcooldown;
  private float dauer;
  private World world;
  boolean inAttack;
  private boolean inDash;
  private StateComponent spielerstate;

  public BaseAbilityController(World world, Engine engine, BodyFactory bodyFactory, KeyboardController keyCon){
    super(Family.all(TypeComponent.class, B2dBodyComponent.class).get());
    this.world = world;
    this.engine = engine;
    this.bodyFactory = bodyFactory;
    this.controller = keyCon;
    attackcooldown = 0;
    dashcooldown = 0;
    dauer = 0;
    inAttack = false;
    inDash = false;

    tm = ComponentMapper.getFor(TypeComponent.class);
    bodm = ComponentMapper.getFor(B2dBodyComponent.class);
    trm = ComponentMapper.getFor(TransformComponent.class);
    sm = ComponentMapper.getFor(StateComponent.class);
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime){
    TypeComponent type = tm.get(entity);
    B2dBodyComponent body = bodm.get(entity);
    StateComponent state = sm.get(entity);

    //Erstellen der Fahigkeiten des Charakters
    if (type.getType() == TypeComponent.PLAYER){
        //Erstellen einer Entity die als Angriff fungiert
      if (controller.space && attackcooldown <= 0 && !inAttack){
        simpleAttack(entity,11f,16f);
        attackcooldown = 0.3f;
        //Setzt angriff auf true
        inAttack = true;
        //Setzt den State auf aut angriff damit der spieler sich wärend des angriffs nicht bewegen kann
        state.setstate(StateComponent.STATE_IN_HIT);
        //Speichert die spieler state um diese wieder auf normal zu setzten wenn der angriff endet 
        spielerstate = state;
      }
      //Bei druck von shift wird der dash ausgelöst, kann nicht wärend des hittens benutzt werden
      if (controller.shift && dashcooldown <= 0 && !inDash && state.getstate() != StateComponent.STATE_IN_HIT){
        //Nutzt die methode dash
        simpleDash(entity);
        //Setzt Dash cooldown
        dashcooldown = 3;
        inDash = true;
      }
      //Berechnen der Cooldowns
      if (attackcooldown > 0){
        attackcooldown = attackcooldown - deltaTime;
      }
      if (dashcooldown > 0){
        dashcooldown = dashcooldown - deltaTime;
      }
      if (dashcooldown <= 0){
        dashcooldown = 0;
        inDash = false;
      }
    }


    //Entfernt den Angriff nach 0,3 sek
    if (!entity.isScheduledForRemoval()){
      if (type.getType() == TypeComponent.ATTACK){
        dauer = dauer  + deltaTime;
        if (dauer >= 0.3f){
          engine.removeEntity(entity);
          world.destroyBody(body.getBody());
          dauer = 0;
          inAttack = false;
          spielerstate.setstate(StateComponent.STATE_NORMAL);
        }
      }
    }
  }

  public void simpleDash(Entity entity){
    B2dBodyComponent body = bodm.get(entity);
    StateComponent state = sm.get(entity);
    //Bestimmt in welche richtung der spieler dasht
    if (state.getLaststate() == "DOWN"){
      //Applyed eine große kraft auf den spieler körper, für einen tick des games
      body.getBody().applyForceToCenter(0, -100000f, true);
    }
    else if(state.getLaststate() == "UP"){
      body.getBody().applyForceToCenter(0, 100000f, true);
    }
    else if(state.getLaststate() == "LEFT"){
      body.getBody().applyForceToCenter(-100000f, 0, true);
    }
    else{ //Right
      body.getBody().applyForceToCenter(100000f, 0, true);
    }
  }

  public void simpleAttack(Entity playerentity, float heightin, float widhtin){
    //Setzen der nötigen variablen um die blickrichtung und die position des Angriffs zu berechnen
    B2dBodyComponent body = bodm.get(playerentity);
    TransformComponent posi = trm.get(playerentity);
    StateComponent state = sm.get(playerentity);
    float x;
    float y;
    float widht;
    float height;
    //Berechnung wo der Angriff plaziert werden muss
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
    b2dbody.setBody(bodyFactory.makeBox(x, y, widht, height, BodyFactory.PLAYER, BodyType.DynamicBody));
    bodyFactory.makeAllFixturesSensors(b2dbody.getBody());
    //Setzen der größe des Bodys, wird nicht benutzt um die tatsächliche größe zu bestimmen sondern um die Texture richtig zu platzieren
    b2dbody.setdimension(widht, height);
    stats.setStats(10, 100, 100, 1000, 20);
    //Setzen der Playerstats Damage, Leben, Speed, Rüstung
    //Koordinanten des Spieler setzten, z wird benutzt um zu entscheiden was zuerst abgebildet werden soll
    position.position.set(10,10,0);
    type.setType(TypeComponent.ATTACK);
    stateCom.setstate(StateComponent.STATE_NORMAL); 
    b2dbody.getBody().setUserData(entity);
  
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
