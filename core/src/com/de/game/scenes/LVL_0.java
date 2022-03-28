package com.de.game.scenes;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import com.de.game.B2dContactListener;
import com.de.game.BodyFactory;
import com.de.game.Main;
import com.de.game.controller.PlayerMovementController;
import com.de.game.controller.BaseAbilityController;
import com.de.game.controller.SimpleEnemyController;
import com.de.game.entity.systems.AnimationSystem;
import com.de.game.entity.systems.CollisionSystem;
import com.de.game.entity.systems.LifeSystem;
import com.de.game.entity.systems.PhysicsDebugSystem;
import com.de.game.entity.systems.PhysicsSystem;
import com.de.game.entity.systems.RenderingSystem;
import com.de.game.inputs.KeyboardController;
import com.de.game.manager.Enemymanager;
import com.de.game.manager.Playermanager;
import com.de.game.manager.Scenerymanager;

public class LVL_0 extends ScreenAdapter{
    //Dieses Level wird nur dazu genutzt damit andere Level hiervon erben können um Redundanz zu verhindern
    protected Main game;
    protected OrthographicCamera cam;
    protected KeyboardController controller;
    protected SpriteBatch sb;
    protected PooledEngine engine;
    protected World world;
    protected BodyFactory bodyFactory;
    protected RenderingSystem renderingSystem;
    protected Playermanager pm;
    protected Enemymanager em;
    protected Scenerymanager sm;

    protected LVL_0 (Main game, boolean debug, String chartype){
        boolean debugmode = debug;
        this.game = game;
        controller = new KeyboardController();
		world = new World(new Vector2(0,0), true);
        world.setContactListener(new B2dContactListener(debugmode));
        bodyFactory = new BodyFactory(world);
        cam = new OrthographicCamera(192,108);

        sb = new SpriteBatch();
        //Erstellen des Rendersystems
        renderingSystem = new RenderingSystem(sb);
        cam = renderingSystem.getCamera();
        sb.setProjectionMatrix(cam.combined);

        engine = new PooledEngine();

        pm = new Playermanager(bodyFactory, engine, game);
        em = new Enemymanager(bodyFactory, engine, game);
        sm = new Scenerymanager(bodyFactory, engine, game);


        engine.addSystem(new PlayerMovementController(controller));
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new SimpleEnemyController());
        engine.addSystem(new LifeSystem(world, game, engine, chartype));
        if(debugmode){
            engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
        }
        engine.addSystem(new BaseAbilityController(world, engine, bodyFactory, controller));
    }

    @Override
    public void show(){
        //Festelegen was zur eingabe genutzt werden soll
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Updaten aller Physik und texturen
        engine.update(delta);
    }
}

