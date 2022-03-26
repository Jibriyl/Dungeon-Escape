package com.de.game.manager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.BodyFactory;
import com.de.game.Main;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.TextureComponent;
import com.de.game.entity.components.TypeComponent;

public class Scenerymanager {

        private TextureAtlas textures;
        private PooledEngine engine;
        private BodyFactory bodyFactory;
        private Main game;
    
        public Scenerymanager(BodyFactory bodyFactory, PooledEngine engine, Main game){
            this.engine = engine;
            this.bodyFactory = bodyFactory;
            this.game = game;
        }
        
    public void createWall(float x, float y, float width, float height, String path, String name){
        textures = game.assetManager.manager.get(path);
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        b2dbody.setBody(bodyFactory.makeBox(x, y, width, height, BodyFactory.STONE, BodyType.StaticBody));
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.setType(TypeComponent.SCENERY);
        texture.setRegion(textures.findRegion(name));
        
        b2dbody.getBody().setUserData(entity);
     
        entity.add(b2dbody);
        entity.add(texture);
        entity.add(type);
        
        engine.addEntity(entity);
    }

    public void createWall(float x, float y, float width, float height){
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        b2dbody.setBody(bodyFactory.makeBox(x, y, width, height, BodyFactory.STONE, BodyType.StaticBody));
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.setType(TypeComponent.SCENERY);

        b2dbody.getBody().setUserData(entity);
     
        entity.add(b2dbody);
        entity.add(texture);
        entity.add(type);
        
        engine.addEntity(entity);
    }
}
