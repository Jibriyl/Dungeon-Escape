package com.de.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class B2dModel {
	public World world;

	public B2dModel(){
		world = new World(new Vector2(0,-10f), true);
        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        world.setContactListener(new B2dContactListener(this));
        bodyFactory.makeCircle(0, 0, 2, BodyFactory.RUBBER);
        bodyFactory.makeBox(0, -30, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        Body water = bodyFactory.makeBox(0, -24, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        water.setUserData("IAMTHESEA");
        bodyFactory.makeAllFixturesSensors(water);

        bodyFactory.makeBox(0, 30, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        bodyFactory.makeBox(-40, 0, 2, 72, BodyFactory.STEEL, BodyType.StaticBody);
        bodyFactory.makeBox(40, 0, 2, 72, BodyFactory.STEEL, BodyType.StaticBody);

        bodyFactory.makeBox(0, 20, 2, 2, BodyFactory.RUBBER, BodyType.DynamicBody);
	}

    public void logicStep(float delta){
        world.step(delta , 3, 3);
    }
}

