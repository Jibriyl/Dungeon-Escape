package com.de.game;

import java.text.DecimalFormat;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.controller.KeyboardController;


public class B2dModel {
	public World world;
    private KeyboardController controller;
    private Body player;

	public B2dModel(KeyboardController cont){
		world = new World(new Vector2(0,0), true);
        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        world.setContactListener(new B2dContactListener(this));
        controller = cont;



        player =  bodyFactory.makeCircle(0, 0, 2, BodyFactory.STEEL);
        bodyFactory.makeBox(0, -30, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        Body water = bodyFactory.makeBox(0, -24, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        water.setUserData("IAMTHESEA");
        bodyFactory.makeAllFixturesSensors(water);

        bodyFactory.makeBox(0, 30, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        bodyFactory.makeBox(-40, 0, 2, 72, BodyFactory.STEEL, BodyType.StaticBody);
        bodyFactory.makeBox(40, 0, 2, 72, BodyFactory.STEEL, BodyType.StaticBody);

	}

    public void logicStep(float delta){
        world.step(delta , 3, 3);
        Vector2 playervector = player.getLinearVelocity();
        double playery = playervector.y;
        double playerx = playervector.x;
        if(controller.left){
            player.applyForceToCenter(-100, 0,true);
        }else if(controller.right){
            player.applyForceToCenter(100, 0,true);
        }else if(controller.up){
            player.applyForceToCenter(0, 100,true);
        }else if(controller.down){
            player.applyForceToCenter(0, -100,true);
        }
        if(controller.left == false){
            player.applyForceToCenter(0, 0,true);
        }else if(controller.right == false){
            player.applyForceToCenter(0, 0,true);
        }else if(controller.up == false){
            player.applyForceToCenter(0, 0,true);
        }else if(controller.down == false){
            if (playery < 0){
                int y = playery * 100;
                player.applyForceToCenter(y, 0,true);
            }
        }
    }
}

