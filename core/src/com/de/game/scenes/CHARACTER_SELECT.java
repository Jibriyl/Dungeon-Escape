package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import com.de.game.Main;
import com.de.game.inputs.KeyboardController;

public class CHARACTER_SELECT extends ScreenAdapter{

    Main game;
    private KeyboardController controller;

    public CHARACTER_SELECT (Main game){
        this.game = game;
        controller = new KeyboardController();


        //Heir werden die Assets geladen
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){
            //Vorbereitung des ladens der Assets
            Gdx.gl.glClearColor(0, .25f, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.begin();
            ScreenUtils.clear(0, 0, 0, 1);
    
            //Hier werden die Assets gemalt 
    
            //Beenden des laden
            game.batch.end();
    }
}
