package com.de.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.controller.KeyboardController;


public class B2dModel {
	public World world;
    private KeyboardController controller;
    public Body playerbody;
    Player player;
    double zeit;

	public B2dModel(KeyboardController cont){
		world = new World(new Vector2(0,0), true);
        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        world.setContactListener(new B2dContactListener(this));
        controller = cont;
        player = new Player(10, 100, 500, 20);
        zeit = 0;


        Body water = bodyFactory.makeBox(0, -24, 128, 2, BodyFactory.STEEL, BodyType.StaticBody);
        water.setUserData("IAMTHESEA");
        bodyFactory.makeAllFixturesSensors(water);

        //Erstellen des PlayerBody
        playerbody =  bodyFactory.makeBox(0, 0, 7, 16, BodyFactory.STEEL, BodyType.DynamicBody);
        bodyFactory.makeCircle(0, 0, 2, BodyFactory.RUBBER);

        //Erstellen des Leveldesign
        //Raumstruktur
        //Außenwand Oben
        bodyFactory.makeBox(0, 38, 192, 1, BodyFactory.STEEL, BodyType.StaticBody);
        //Außenwand Links
        bodyFactory.makeBox(-93.5f, 0, 1, 108, BodyFactory.STEEL, BodyType.StaticBody);
        //Außenwand Rechts
        bodyFactory.makeBox(93.5f, 0, 1, 108, BodyFactory.STEEL, BodyType.StaticBody);
        //Außenwand Unten
        bodyFactory.makeBox(0, -51.8f, 192, 1, BodyFactory.STEEL, BodyType.StaticBody);



	}

    public void logicStep(float delta){
        if(controller.left){
            playerbody.applyForceToCenter(-player.getSpeed(), 0,true);
        }
        if(controller.right){
            playerbody.applyForceToCenter(player.getSpeed(), 0,true);
        }
        if(controller.up){
            playerbody.applyForceToCenter(0, player.getSpeed(),true);
        }
        if(controller.down){
            playerbody.applyForceToCenter(0, -player.getSpeed(),true);
        }
        if(controller.space){
            if(zeit >= 1){
                System.out.println("Space");
                zeit =- 0;
            }
        }
        zeit = zeit + delta;
        world.step(delta , 3, 3);
    }
}

