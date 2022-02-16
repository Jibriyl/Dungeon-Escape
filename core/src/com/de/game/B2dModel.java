package com.de.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class B2dModel {
	public World world;

	public B2dModel(){
		world = new World(new Vector2(0,-10f), true);
        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        bodyFactory.makeCircle(0, 0, 2, BodyFactory.RUBBER);
        bodyFactory.makeBox(0, -30, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
	}


        // our game logic here
    public void logicStep(float delta){
        world.step(delta , 3, 3);
    }
}

