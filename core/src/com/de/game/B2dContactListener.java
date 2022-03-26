package com.de.game;

import com.de.game.entity.components.CollisionComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class B2dContactListener implements ContactListener {
	
	public B2dContactListener(){ 
	}
	
	@Override
	public void beginContact(Contact contact) {
		System.out.println("Contact");
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
		
		if(fa.getBody().getUserData() instanceof Entity){
			Entity ent = (Entity) fa.getBody().getUserData();
			entityCollision(ent,fb);
			System.out.println("tsst");
		}
		
		if(fb.getBody().getUserData() instanceof Entity){
			Entity ent = (Entity) fb.getBody().getUserData();
			entityCollision(ent,fa);
			System.out.println("tsst2");
		}
	}

	private void entityCollision(Entity ent, Fixture fb) {
		if(fb.getBody().getUserData() instanceof Entity){
			Entity colEnt = (Entity) fb.getBody().getUserData();
			
			CollisionComponent col = ent.getComponent(CollisionComponent.class);
			CollisionComponent colb = colEnt.getComponent(CollisionComponent.class);
			
			if(col != null){
				col.setCollisionEntity(colEnt);
			}else if(colb != null){
				colb.setCollisionEntity(ent);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		System.out.println("Contact end");
	}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {		
	}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {		
	}

}