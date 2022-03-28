package com.de.game.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class B2dAssetManager {
    public final AssetManager manager = new AssetManager();
	
	// Textures 
	public final String lvl1background = "Input/game/lvl1background.png";
	public final String gameoverbackground = "Input/game/Gameoverbackground.png";
    public final String gameover = "Input/game/Gameover.png";
    public final String player = "Output/joshuachar1.atlas";
    public final String charback = "Input/charauswahl/Back.png";

    public void queueAddImages(){
        manager.load(lvl1background, Texture.class);
        manager.load(gameoverbackground, Texture.class);
        manager.load(player, TextureAtlas.class);
        manager.load(gameover, Texture.class);
        manager.load(charback, Texture.class);
    }
}
