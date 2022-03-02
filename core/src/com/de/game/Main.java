package com.de.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.de.game.loader.B2dAssetManager;
import com.de.game.scenes.LVL_1;

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
        
        //Setzen des Start Screens
        //setScreen(new MAIN_MENU(this));
		setScreen(new LVL_1(this));
        }

    @Override
    public void dispose () {
        //Löschen der Objekte wenn sie nicht mehr benötigt werden
        batch.dispose();
    }
}