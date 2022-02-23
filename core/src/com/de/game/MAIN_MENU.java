package com.de.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MAIN_MENU extends ScreenAdapter{

    Main game;
    private Stage stage;

    //Import des Assets und nötigen Objekte in den MAIN_MENU Screen
    public MAIN_MENU(Main game){
        this.game = game;
    }

    @Override
    public void show(){

    }
    @Override
    public void render(float delta){
        //Vorbereitung des ladens der Assets
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);

        //Createn des Stage um auf User-Inputs zu testen
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Laden der Assets 
        game.font.draw(game.batch, "DUNGEON ESCAPE", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f); 
        game.batch.draw(game.img, 0, 0);

        //Beenden des laden
        game.batch.end();
    }


    
}