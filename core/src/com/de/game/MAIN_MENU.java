package com.de.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MAIN_MENU extends ScreenAdapter{

    Main game;

    public MAIN_MENU(Main game){
        this.game = game;
    }

    @Override
    public void show(){

    }
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);
        game.font.draw(game.batch, "DUNGEON ESCAPE", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f); 
        game.batch.draw(game.img, 0, 0);
        game.batch.end();
    }


    
}
