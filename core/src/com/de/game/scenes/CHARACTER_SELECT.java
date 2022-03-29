package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.de.game.Main;
import com.de.game.inputs.KeyboardController;

public class CHARACTER_SELECT extends ScreenAdapter{

    Main game;
    private KeyboardController controller;
    private Texture charbackground;
    private Texture charback;
    private Texture charbackrahmen;
    private Texture charjplayer;
    private Texture charMplayer;
    private int auswahl;
    private float cd;


    public CHARACTER_SELECT (Main game){
        this.game = game;
        controller = new KeyboardController();

        charbackground = game.assetManager.manager.get("Input/charauswahl/Hintergrund.png");
        charback = game.assetManager.manager.get("Input/charauswahl/Back.png");
        charbackrahmen = game.assetManager.manager.get("Input/charauswahl/Backramen.png");
        charjplayer = game.assetManager.manager.get("Input/charauswahl/Jplayer.png");
        charMplayer = game.assetManager.manager.get("Input/charauswahl/Mplayer.png");
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
            if (controller.left){
                auswahl -= 1;
                cd = 0.2f;
            }
            if (controller.right){
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

        game.batch.draw(charbackground, 0, 0, 1920, 1080);
        game.batch.draw(charback, 450, 430, 225, 113);
        game.batch.draw(charMplayer, 800, 380, 234, 215);
        game.batch.draw(charjplayer, 1200, 380, 234, 215);

            //Hier werden die Assets gemalt 
    
            if (auswahl == 0){
                game.batch.draw(charbackrahmen, 410, 402, 305, 167);
            }
            else if (auswahl == 1){
                game.batch.draw(charMplayer, 780, 350, 302, 278);
            }
            else if (auswahl == 2){
                game.batch.draw(charjplayer, 1180, 350, 302, 278);
            }
    
            
    
    

            //Beenden des laden
            game.batch.end();
    
            if (controller.enter){
                if (auswahl == 0){
                    game.screenset("MAIN_MENU", "");
                }
                else if (auswahl == 1){
                    game.screenset("LVL_1", "player2");
                }
                else if (auswahl == 2){
                    game.screenset("LVL_1", "player1");
                    
                }

    }}
}
