package com.de.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.de.game.loader.B2dAssetManager;
import com.de.game.scenes.CHARACTER_SELECT;
import com.de.game.scenes.GAME_OVER;
import com.de.game.scenes.LVL_1;
import com.de.game.scenes.MAIN_MENU;
import com.de.game.scenes.WIN_SCREEN;

public class Main extends Game {
    public SpriteBatch batch;
    public Texture img;
	public BitmapFont font;
    public B2dAssetManager assetManager = new B2dAssetManager();




    @Override
    public void create () {
        //Vorbereitung des Objekte für die Screens
        batch = new SpriteBatch();
        font = new BitmapFont();
        
        assetManager.queueAddImages();
        assetManager.manager.finishLoading();

        //Setzen des Start Screens
        screenset("MAIN_MENU");        
    }

    @Override
    public void dispose () {
        //Löschen der Objekte wenn sie nicht mehr benötigt werden
        batch.dispose();
    }

    public void screenset(String screen){
        if(screen == "LVL_1"){
            setScreen(new LVL_1(this, "player1"));
        }
        else if(screen == "MAIN_MENU"){
            setScreen(new MAIN_MENU(this));
        }
        else if(screen == "GAME_OVER"){
            setScreen(new GAME_OVER(this));
        }
        else if(screen == "CHARACTER_SELECT"){
            setScreen(new CHARACTER_SELECT(this));
        }
        else if(screen == "WIN_SCREEN"){
            setScreen(new WIN_SCREEN(this));
        }
    }
}
