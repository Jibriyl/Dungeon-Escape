package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.de.game.Main;
import com.de.game.inputs.KeyboardController;

public class MAIN_MENU extends ScreenAdapter{

    Main game;
    private KeyboardController controller;
    private Texture background;
    private float cd;
    private int auswahl;
    private Texture rahmen;
    private Texture start;
    private Texture kommtbald;
    private Texture verlassen;

    //Import des Assets und nötigen Objekte in den MAIN_MENU Screen
    public MAIN_MENU(Main game){
        this.game = game;
        controller = new KeyboardController();

        background = game.assetManager.manager.get("Input/main/Backrond_hauptmenu_richtig.png");
        rahmen = game.assetManager.manager.get("Input/main/Rahmen.png");
        start = game.assetManager.manager.get("Input/main/Start.png");
        kommtbald = game.assetManager.manager.get("Input/main/kommt_bald.png");
        verlassen = game.assetManager.manager.get("Input/main/Verlassen.png");
        cd = 0;
        auswahl = 0;
        //Hier werden die Assets geladen
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

           //Screenlogic
           if (cd <= 0){
            if (controller.up){
                auswahl -= 1;
                cd = 0.2f;
            }
            if (controller.down){
                auswahl +=1;
                cd = 0.2f;
            }
            if (auswahl < 0){
                auswahl = 0;
            }
            if (auswahl > 2){
                auswahl = 2;
            }
        }
        else {
            cd -= delta;
        }

        game.batch.draw(background, 0, 0, 1920, 1080);

        //Hier werden die Assets gemalt 
        if (auswahl == 0){
            game.batch.draw(rahmen, 618, 750, 704, 172);
        }
        else if (auswahl == 1){
            game.batch.draw(rahmen, 618, 500, 704, 172);
        }
        else if (auswahl == 2){
            game.batch.draw(rahmen, 618, 250, 704, 172);
        }

        // Buttens
        game.batch.draw(start, 633, 766, 674, 140);

        game.batch.draw(kommtbald, 633, 516, 674, 140);

        game.batch.draw(verlassen, 633, 266, 674, 140);

        





        //Beenden des laden
        game.batch.end();

        if (controller.enter){
            if (auswahl == 0){
                game.screenset("CHARACTER_SELECT", "");
            }
            else if (auswahl == 1){
                //game.screenset("GAME_OVER");
            }
            else if (auswahl == 2){
                System.exit(0);
            }
        }
    }


    
}
