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
        batch = new SpriteBatch();
        img = new Texture("Grass.jpg");
        font = new BitmapFont();
		setScreen(new MAIN_MENU(this));
		//setScreen(new MAIN_GAME(this));
        }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}