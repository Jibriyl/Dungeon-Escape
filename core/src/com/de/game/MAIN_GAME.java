package com.de.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MAIN_GAME extends ScreenAdapter{

    Main game;
    private B2dModel model;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;


    public MAIN_GAME (Main game){
        this.game = game;
        model = new B2dModel();
        cam = new OrthographicCamera(128,72);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){

        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);

    }
}
