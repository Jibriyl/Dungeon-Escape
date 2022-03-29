package com.de.game.manager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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


public class Enemymanager {

    private TextureAtlas slimetex;
    private PooledEngine engine;
    private BodyFactory bodyFactory;

    public Enemymanager(BodyFactory bodyFactory, PooledEngine engine, Main game){
        this.engine = engine;
        this.bodyFactory = bodyFactory;
        //Lädt die textur in die Klasse
        slimetex = game.assetManager.manager.get("Output/slime.atlas");

    }

    public void createbasicSimpleEnemy(float x, float y){
        //Erstellt alle komponenten für eine Entität
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        StatComponent stats = engine.createComponent(StatComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
     
        b2dbody.setBody(bodyFactory.makeCircle(x, y, 5, BodyFactory.PLAYER, 4f));
        //Setzen der größe des Bodys, wird nicht benutzt um die tatsächliche größe zu bestimmen sondern um die Texture richtig zu platzieren
        b2dbody.setdimension(5, 9);
        //Setzen der Playerstats Damage, Leben, Maxleben, Speed, Rüstung
        stats.setStats(10, 50, 50, 1, 20);
        //Koordinanten des Spieler setzten, z wird benutzt um zu entscheiden was zuerst abgebildet werden soll
        position.position.set(5,8,0);
        texture.setAtlas(slimetex);

        type.setType(TypeComponent.ENEMY);
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
