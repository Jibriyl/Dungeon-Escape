package com.de.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.de.game.controller.KeyboardController;

public class MAIN_GAME extends ScreenAdapter{

    Main game;
    private B2dModel model;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;
    private KeyboardController controller;

    private Texture lvl1background;

    private SpriteBatch sb;

    private Texture playerTex;


    public MAIN_GAME (Main game){
        this.game = game;
        controller = new KeyboardController();
        model = new B2dModel(controller);
        cam = new OrthographicCamera(192,108);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
        sb = new SpriteBatch();
        sb.setProjectionMatrix(cam.combined);

        game.assetManager.queueAddImages();
        game.assetManager.manager.finishLoading();

        lvl1background = game.assetManager.manager.get("lvl1background.png");
        playerTex = game.assetManager.manager.get("player1.png");
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){

        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(lvl1background, -96, -54, 192, 108);
        sb.draw(playerTex, model.playerbody.getPosition().x - 3.5f, model.playerbody.getPosition().y - 8, 7, 16);
        sb.end();
        debugRenderer.render(model.world, cam.combined);


    }
}
