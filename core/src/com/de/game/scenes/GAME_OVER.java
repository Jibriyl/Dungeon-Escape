package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.de.game.Main;
import com.de.game.inputs.KeyboardController;

public class GAME_OVER extends ScreenAdapter{
    
    Main game;
    private Texture gameoverbackground;
    private Texture gameover;
    private int auswahl;
    private KeyboardController controller;
    private float cd;
    

    public GAME_OVER (Main game){
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
    
            //Screenlogic
            if (cd <= 0){
                if (controller.up){
                    auswahl -= 1;
                    cd = 1;
                }
                if (controller.down){
                    auswahl +=1;
                    cd = 1;
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


            //Laden der Assets
            if (auswahl == 0){
                System.out.println("Retry");
            }
            else if (auswahl == 1){
                System.out.println("Main");
            }
            else if (auswahl == 2){
                System.out.println("Exit");
            }
            game.batch.draw(gameoverbackground, 0, 0, 1920, 1080);
            game.batch.draw(gameover, 760, 760, 400, 140);
            game.batch.end();

            if (controller.enter){
                if (auswahl == 0){
                    game.screenset("LVL_1");
                }
                else if (auswahl == 1){
                    game.screenset("MAIN_MENU");
                }
                else if (auswahl == 2){
                    System.exit(0);
                }
            }
    }

}
