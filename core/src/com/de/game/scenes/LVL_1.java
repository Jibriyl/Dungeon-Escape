package com.de.game.scenes;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.de.game.B2dContactListener;
import com.de.game.BodyFactory;
import com.de.game.Main;
//Eigene Klassen importieren
import com.de.game.controller.KeyboardController;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.CollisionComponent;
import com.de.game.entity.components.PlayerComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TextureComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;
import com.de.game.entity.systems.CollisionSystem;
import com.de.game.entity.systems.PhysicsDebugSystem;
import com.de.game.entity.systems.PhysicsSystem;
import com.de.game.entity.systems.PlayerControlSystem;
import com.de.game.entity.systems.RenderingSystem;
import com.de.game.manager.Enemymanager;
import com.de.game.manager.Playermanager;

public class LVL_1 extends ScreenAdapter{

    Main game;
    private OrthographicCamera cam;
    private KeyboardController controller;

    private Texture lvl1background;

    private SpriteBatch sb;

    private PooledEngine engine;
    private World world;
    private BodyFactory bodyFactory;

    public LVL_1 (Main game){
        this.game = game;
        controller = new KeyboardController();
		world = new World(new Vector2(0,0), true);
        world.setContactListener(new B2dContactListener());
        bodyFactory = BodyFactory.getInstance(world);
        cam = new OrthographicCamera(192,108);


        game.assetManager.queueAddImages();
        game.assetManager.manager.finishLoading();

        lvl1background = game.assetManager.manager.get("Input/game/lvl1background.png");


        sb = new SpriteBatch();
        // Create our new rendering system
        RenderingSystem renderingSystem = new RenderingSystem(sb);
        cam = renderingSystem.getCamera();
        sb.setProjectionMatrix(cam.combined);
        renderingSystem.setBackground(lvl1background);

        engine = new PooledEngine();

        Playermanager pm = new Playermanager(bodyFactory, engine, game);
        Enemymanager em = new Enemymanager();

        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));

        pm.createbasicPlayer();
        createWall(96, 2, 192, 1);
        createWall(96, 92, 192, 1);
        createWall(2, 54, 1, 108);
        createWall(190, 54, 1, 108);

    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
    }


    private void createWall(float x, float y, float width, float height){
        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        b2dbody.body = bodyFactory.makeBox(x, y, width, height, BodyFactory.STONE, BodyType.StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;
        
        b2dbody.body.setUserData(entity);
     
        entity.add(b2dbody);
        entity.add(texture);
        entity.add(type);
        
        engine.addEntity(entity);
    }
}

