package com.de.game.manager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.BodyFactory;
import com.de.game.Main;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.CollisionComponent;
import com.de.game.entity.components.PlayerComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TextureComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;

public class Playermanager {

    private TextureAtlas testplayer;
    private PooledEngine engine;
    private BodyFactory bodyFactory;

    public Playermanager(BodyFactory bodyFactory, PooledEngine engine, Main game){
        this.engine = engine;
        this.bodyFactory = bodyFactory;
        testplayer = game.assetManager.manager.get("Output/testchar.atlas");
    }

    public void createbasicPlayer(){
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
        b2dbody.body = bodyFactory.makeBox(50, 50, 7, 16, BodyFactory.PLAYER, BodyType.DynamicBody);
        //Setzen der größe des Bodys, wird nicht benutzt um die tatsächliche größe zu bestimmen sondern um die Texture richtig zu platzieren
        b2dbody.setdimension(7, 16);
        //Setzen der Playerstats Damage, Leben, Speed, Rüstung
        player.setStats(10, 100, 500, 20);
        //Koordinanten des Spieler setzten, z wird benutzt um zu entscheiden was zuerst abgebildet werden soll
        position.position.set(0,0,0);
        texture.region = testplayer.findRegion("player1");
        type.type = TypeComponent.PLAYER;
        stateCom.set(StateComponent.STATE_NORMAL); 
        b2dbody.body.setUserData(entity);
     
        //Alle Kompenenten des Spieler der Spieler Entity hinzufügen
        entity.add(b2dbody);
        entity.add(position);
        entity.add(texture);
        entity.add(player);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);
     
        //Die Entity in die engine adden
        engine.addEntity(entity);
    }
}
