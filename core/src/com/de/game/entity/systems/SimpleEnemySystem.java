package com.de.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.PlayerComponent;
import com.de.game.entity.components.StateComponent;

public class SimpleEnemySystem extends IteratingSystem{

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<B2dBodyComponent> bodm;
    private ComponentMapper<StateComponent> sm;

    public SimpleEnemySystem(Family family){
		super(Family.all(PlayerComponent.class).get());
        pm = ComponentMapper.getFor(PlayerComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        B2dBodyComponent b2body = bodm.get(entity);
		StateComponent state = sm.get(entity);
		PlayerComponent player = pm.get(entity);
    }
    
}
