package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.de.game.Main;

public class CHARACTER_SELECT extends ScreenAdapter{

    Main game;
    private Stage stage;

    public CHARACTER_SELECT (Main game){
        this.game = game;

        //Heir werden die Assets geladen
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
    
            //Hier werden die Assets gemalt 
    
            //Beenden des laden
            game.batch.end();
    }

    
}
