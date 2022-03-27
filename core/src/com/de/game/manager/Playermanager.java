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
import com.de.game.entity.components.StatComponent;
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
        //Importieren aller benutzten Charakter texturen
        //Alle texturen eines Charakters sind in einem Atlas gespeichert
        testplayer = game.assetManager.manager.get("Output/joshuachar1.atlas");
    }

    //Funktion zur auswahl welcher player selected wurde
    public void createPlayer(String type, float x, float y){
        if(type == "player1"){
            createplayer1(x, y);
        }
        else if(type == "player2"){

        }
        else if(type == "player3"){
            
        }
    }

    private void createplayer1(float x, float y){
        // Erstellen aller Entitys für den Player
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StatComponent stats = engine.createComponent(StatComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
     
        // Erstellen des player bodys
        b2dbody.setBody(bodyFactory.makeBox(x, y, 7, 16, BodyFactory.PLAYER, BodyType.DynamicBody));
        //Setzen der größe des Bodys, wird nicht benutzt um die tatsächliche größe zu bestimmen sondern um die Texture richtig zu platzieren
        b2dbody.setdimension(7, 16);
        //Setzen der Playerstats Damage, Leben, Maxleben, Speed, Rüstung
        stats.setStats(10, 100, 100, 1000, 20);
        //Koordinanten des Spieler setzten, z wird benutzt um zu entscheiden was zuerst abgebildet werden soll
        position.position.set(7,16,0);
        texture.setAtlas(testplayer);
        type.setType(TypeComponent.PLAYER);
        stateCom.setstate(StateComponent.STATE_NORMAL); 
        b2dbody.getBody().setUserData(entity);
     
        //Alle Kompenenten des Spieler der Spieler Entity hinzufügen
        entity.add(b2dbody);
        entity.add(position);
        entity.add(texture);
        entity.add(stats);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);
     
        //Die Entity in die engine adden
        engine.addEntity(entity);
    }
}
