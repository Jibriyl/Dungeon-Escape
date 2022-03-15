package com.de.game.entity.systems;

import com.de.game.controller.KeyboardController;
import com.de.game.entity.components.StatComponent;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TypeComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class PlayerControlSystem extends IteratingSystem{

	ComponentMapper<StatComponent> pm;
	ComponentMapper<B2dBodyComponent> bodm;
	ComponentMapper<StateComponent> sm;
	ComponentMapper<TypeComponent> tm;
	KeyboardController controller;
	
	public PlayerControlSystem(KeyboardController keyCon) {
		super(Family.all(StatComponent.class).get());
		controller = keyCon;
		pm = ComponentMapper.getFor(StatComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		sm = ComponentMapper.getFor(StateComponent.class);
		tm = ComponentMapper.getFor(TypeComponent.class);
	}
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TypeComponent type = tm.get(entity);

		if (type.type == TypeComponent.PLAYER){
			B2dBodyComponent b2body = bodm.get(entity);
			StateComponent state = sm.get(entity);
			StatComponent player = pm.get(entity);

			if(b2body.body.getLinearVelocity().x <= 0.5f && b2body.body.getLinearVelocity().y <= 0.5f){
				state.set(StateComponent.STATE_NORMAL);
			}
			
			if(controller.up){
				b2body.body.applyForceToCenter(0, player.getSpeed(),true);
				state.set(StateComponent.STATE_MOVING_UP);
			}
			if(controller.down){
				b2body.body.applyForceToCenter(0, -player.getSpeed(),true);
				state.set(StateComponent.STATE_MOVING_DOWN);
			}
			if(controller.left){
				b2body.body.applyForceToCenter(-player.getSpeed(), 0,true);
				state.set(StateComponent.STATE_MOVING_LEFT);
			}
			if(controller.right){
				b2body.body.applyForceToCenter(player.getSpeed(), 0,true);
				state.set(StateComponent.STATE_MOVING_RIGHT);
			}
		}


	}
}