package com.de.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    SpriteBatch batch;
    Texture img;
	BitmapFont font;


    @Override
    public void create () {
        //Vorbereitung des Objekte für die Screens
        batch = new SpriteBatch();
        font = new BitmapFont();
        // Laden der Assets in des Spiel
        img = new Texture("Grass.jpg");
        
        //Setzen des Spart Screens
		setScreen(new MAIN_GAME(this));
        }

    @Override
    public void dispose () {
        //Löschen der Objekte wenn sie nicht mehr benötigt werden
        batch.dispose();
        img.dispose();
    }
}