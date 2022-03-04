package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {
    public TextureAtlas atlas = null;
    public TextureRegion region = null;
    private ComponentMapper<TypeComponent> typecom;
    private ComponentMapper<StateComponent> statecom;
    
    public TextureComponent(){
        typecom = ComponentMapper.getFor(TypeComponent.class);
        statecom = ComponentMapper.getFor(StateComponent.class);
    }

    public TextureRegion getRegion(Entity entity) {
        TypeComponent type = typecom.get(entity);
        StateComponent state = statecom.get(entity);
        if (type.getTypenumber() == TypeComponent.PLAYER){
            if(state.get() == StateComponent.STATE_NORMAL){
                region = atlas.findRegion("standing");
            }
            if(state.get() == StateComponent.STATE_MOVING_UP){
                region = atlas.findRegion("back");
            }
            if(state.get() == StateComponent.STATE_MOVING_DOWN){
                region = atlas.findRegion("standing");
            }
            if(state.get() == StateComponent.STATE_MOVING_LEFT){
                region = atlas.findRegion("standing_left");
            }
            if(state.get() == StateComponent.STATE_MOVING_RIGHT){
                region = atlas.findRegion("standing_right");
            }
        }
        return region;
    }
}

