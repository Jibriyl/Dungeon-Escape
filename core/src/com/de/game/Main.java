package com.de.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.de.game.loader.B2dAssetManager;
import com.de.game.scenes.LVL_1;
import com.de.game.scenes.MAIN_MENU;

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
        // Laden der Assets in des Spiel
        
        //Setzen des Spart Screens
        //setScreen(new MAIN_MENU(this));
		setScreen(new LVL_1(this));
        }

    @Override
    public void dispose () {
        //Löschen der Objekte wenn sie nicht mehr benötigt werden
        batch.dispose();
    }
}