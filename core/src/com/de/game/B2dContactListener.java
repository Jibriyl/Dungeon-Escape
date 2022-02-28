package com.de.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class B2dContactListener implements ContactListener {

	private LVL1_Model parent;
	
	public B2dContactListener(LVL1_Model parent){
		this.parent = parent;
	}

	@Override
	public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
		if(fa.getBody().getUserData() == "IAMTHESEA"){
			System.out.println("WATERRRRRR");
			return;
		}else if(fb.getBody().getUserData() == "IAMTHESEA"){
			System.out.println("WATERRRRRR");
			return;
		}
	}

	@Override
	public void endContact(Contact contact) {	
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {		
	}
}