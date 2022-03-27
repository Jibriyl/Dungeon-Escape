package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.de.game.Main;
import com.de.game.inputs.KeyboardController;

public class WIN_SCREEN extends ScreenAdapter{
    
    Main game;
    private Texture gameoverbackground;
    private Texture gameover;
    private int auswahl;
    private KeyboardController controller;
    private float cd;
    

    public WIN_SCREEN (Main game){
        this.game = game;
        controller = new KeyboardController();

        gameoverbackground = game.assetManager.manager.get("Input/game/Gameoverbackground.png");
        gameover = game.assetManager.manager.get("Input/game/Gameover.png");
        cd = 0;
        auswahl = 0;
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

        //Laden der Assets
        game.batch.draw(gameoverbackground, 0, 0, 1920, 1080);
        game.batch.draw(gameover, 760, 760, 400, 140);
        game.batch.end();

        if (controller.enter){
            System.exit(0);
        }
    }

}
