package com.de.game.manager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.BodyFactory;
import com.de.game.Main;
//Importieren den eigener Klassen
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.CollisionComponent;
import com.de.game.entity.components.PlayerComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TextureComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;


public class Enemymanager {

    private TextureAtlas testplayer;
    private PooledEngine engine;
    private BodyFactory bodyFactory;

    public Enemymanager(BodyFactory bodyFactory, PooledEngine engine, Main game){
        this.engine = engine;
        this.bodyFactory = bodyFactory;
    }

    public void createbasicSimpleEnemy(){
        // Create the Entity and all the components that will go in the entity
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
     
        // create the data for the components and add them to the components
        b2dbody.body = bodyFactory.makeCircle(100, 20, 5, BodyFactory.PLAYER, 4f);
        //Setzen der größe des Bodys, wird nicht benutzt um die tatsächliche größe zu bestimmen sondern um die Texture richtig zu platzieren
        b2dbody.setdimension(5, 5);
        //Setzen der Playerstats Damage, Leben, Speed, Rüstung
        player.setStats(10, 100, 1, 20);
        //Koordinanten des Spieler setzten, z wird benutzt um zu entscheiden was zuerst abgebildet werden soll
        position.position.set(10,10,0);
        type.type = TypeComponent.ENEMY;
        stateCom.set(StateComponent.STATE_NORMAL); 
        b2dbody.body.setUserData(entity);
     
        //Alle Kompenenten des Spieler der Spieler Entity hinzufügen
        entity.add(b2dbody);
        entity.add(position);
        entity.add(player);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);
     
        //Die Entity in die engine adden
        engine.addEntity(entity);
    }
}
