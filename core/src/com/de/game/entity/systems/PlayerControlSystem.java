package com.de.game.entity.systems;

import com.de.game.controller.KeyboardController;
import com.de.game.entity.components.PlayerComponent;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.StateComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class PlayerControlSystem extends IteratingSystem{

	ComponentMapper<PlayerComponent> pm;
	ComponentMapper<B2dBodyComponent> bodm;
	ComponentMapper<StateComponent> sm;
	KeyboardController controller;
	
	public PlayerControlSystem(KeyboardController keyCon) {
		super(Family.all(PlayerComponent.class).get());
		controller = keyCon;
		pm = ComponentMapper.getFor(PlayerComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		sm = ComponentMapper.getFor(StateComponent.class);
	}
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		B2dBodyComponent b2body = bodm.get(entity);
		StateComponent state = sm.get(entity);
		
		if(b2body.body.getLinearVelocity().y == 0 && b2body.body.getLinearVelocity().x == 0){
			if(state.get() == StateComponent.STATE_IN_DASH){
				state.set(StateComponent.STATE_NORMAL);
			}
			if(b2body.body.getLinearVelocity().x != 0 || b2body.body.getLinearVelocity().y != 0){
				state.set(StateComponent.STATE_MOVING);
			}
		}
		

        if(controller.up){
			b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 0, 5f),b2body.body.getLinearVelocity().y);
		}
        if(controller.down){
			b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 0, 5f),b2body.body.getLinearVelocity().y);
		}
        if(controller.left){
			b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, -5f, 0),b2body.body.getLinearVelocity().y);
		}
		if(controller.right){
			b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 5f, 0),b2body.body.getLinearVelocity().y);
		}
	}
}