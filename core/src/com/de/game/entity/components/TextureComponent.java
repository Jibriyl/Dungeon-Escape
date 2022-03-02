package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.de.game.controller.KeyboardController;

public class TextureComponent implements Component {
    public TextureAtlas atlas = null;
    public TextureRegion region = null;
    private ComponentMapper<TypeComponent> typecom;
    private KeyboardController controller;
    
    public TextureComponent(){
        typecom = ComponentMapper.getFor(TypeComponent.class);
        controller = new KeyboardController();
    }

    public TextureRegion getRegion(Entity entity) {
        TypeComponent type = typecom.get(entity);
        if (type.getTypenumber() == TypeComponent.PLAYER){
            if(!controller.up && !controller.down && ! controller.left && !controller.right){
                region = atlas.findRegion("player1");
            }
            if(controller.up){
                region = null;
            }
            if(controller.down){
            }
            if(controller.left){
            }
            if(controller.right){
            }
        }
        return region;
    }
}

