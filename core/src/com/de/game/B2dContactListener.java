package com.de.game;

import com.de.game.entity.components.CollisionComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class B2dContactListener implements ContactListener {
	
	private boolean debugmode;

	public B2dContactListener(boolean debugmode){ 
		this.debugmode = debugmode;
	}
	
	//Diese Klasse erkennt und speichert für jede entity was die letzte collision war
	@Override
	public void beginContact(Contact contact) {
		//Ermittelt die beiden Kolidierenden Klassen
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (debugmode){
			//wenn der Debug mode aktiv ist wird in der Konsole ausgegeben welceh art von entitys kollidiert sind
			System.out.println("Contact");
			System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
		}

		//Wenn es sich um einen Entity handelt, wird ausgeführt
		if(fa.getBody().getUserData() instanceof Entity){
			//Übergibt die entitäten an die entitycollision methode
			Entity ent = (Entity) fa.getBody().getUserData();
			entityCollision(ent,fb);
		}
		
		if(fb.getBody().getUserData() instanceof Entity){
			Entity ent = (Entity) fb.getBody().getUserData();
			entityCollision(ent,fa);
		}
	}

	private void entityCollision(Entity ent, Fixture fb) {
		//Setzt bei Collision die entity als collisionsentity in der Collisionscomponent
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
		if (debugmode){
			System.out.println("Contact end");
		}
	}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {		
	}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {		
	}

}