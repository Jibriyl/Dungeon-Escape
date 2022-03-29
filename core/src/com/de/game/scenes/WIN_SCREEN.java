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
    private Texture background;
    private KeyboardController controller;
    

    public WIN_SCREEN (Main game){
        this.game = game;
        //Deklariert den Controller, nutzt die Tastatur
        controller = new KeyboardController();
        //Lädt den Hintergrund
        background = game.assetManager.manager.get("Input/game/Gameoverbackground.png");
    }

    @Override
    public void show(){
        //Setzen der Tastatur als Kontroller
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){
        //Vorbereitung des ladens der Assets
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);

        //Laden/Zeichnen der Assets
        game.batch.draw(background, 0, 0, 1920, 1080);
        game.batch.end();

        //Wenn enter gedrückt wird, wird das spiel beendet
        if (controller.enter){
            System.exit(0);
        }
    }

}
