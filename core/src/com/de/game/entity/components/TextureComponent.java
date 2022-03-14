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
}

