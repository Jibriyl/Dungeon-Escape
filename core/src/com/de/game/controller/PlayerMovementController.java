package com.de.game.controller;

import com.de.game.entity.components.StatComponent;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TypeComponent;
import com.de.game.inputs.KeyboardController;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class PlayerMovementController extends IteratingSystem{

	ComponentMapper<StatComponent> pm;
	ComponentMapper<B2dBodyComponent> bodm;
	ComponentMapper<StateComponent> sm;
	ComponentMapper<TypeComponent> tm;
	KeyboardController controller;
	
	public PlayerMovementController(KeyboardController keyCon) {
		super(Family.all().get());
		controller = keyCon;
		pm = ComponentMapper.getFor(StatComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		sm = ComponentMapper.getFor(StateComponent.class);
		tm = ComponentMapper.getFor(TypeComponent.class);
	}
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TypeComponent type = tm.get(entity);

		//stellt fest ob es sich um einen play handelt 
		if (type.getType() == TypeComponent.PLAYER){
			StateComponent state = sm.get(entity);
			//Spieler kann sich nur bewegen wenn er nicht angreift
			if(state.getstate() != StateComponent.STATE_IN_HIT){
				B2dBodyComponent b2body = bodm.get(entity);
				StatComponent player = pm.get(entity);
				
				if(b2body.getBody().getLinearVelocity().x <= 0.5f && b2body.getBody().getLinearVelocity().y <= 0.5f){
					//Wenn der spieler still steht wird das erfasst
					state.setstate(StateComponent.STATE_NORMAL);
				}
				
				if(controller.up){
					//Versetzt den spieler in bewegung
					b2body.getBody().applyForceToCenter(0, player.getSpeed(),true);
					//Setzt die aktuelle state
					state.setstate(StateComponent.STATE_MOVING_UP);
					//Setzt die letzte bewegung, diese wird auch nach nicht mehr bewegen gespeichert und ist dazu da das der spieler weiterhin in diese richtugn schaut
					state.setLaststate("UP");
				}
				if(controller.down){
					b2body.getBody().applyForceToCenter(0, -player.getSpeed(),true);
					state.setstate(StateComponent.STATE_MOVING_DOWN);
					state.setLaststate("DOWN");
				}
				if(controller.left){
					b2body.getBody().applyForceToCenter(-player.getSpeed(), 0,true);
					state.setstate(StateComponent.STATE_MOVING_LEFT);
					state.setLaststate("LEFT");
				}
				if(controller.right){
					b2body.getBody().applyForceToCenter(player.getSpeed(), 0,true);
					state.setstate(StateComponent.STATE_MOVING_RIGHT);
					state.setLaststate("RIGHT");
				}
			}
		}
	}
}