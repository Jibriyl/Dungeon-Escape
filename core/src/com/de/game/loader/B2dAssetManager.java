package com.de.game.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class B2dAssetManager {
    public final AssetManager manager = new AssetManager();
	
	// Textures
	public final String lvl1background = "lvl1background.png";
    public final String player = "player1.png";


    public void queueAddImages(){
        manager.load(lvl1background, Texture.class);
        manager.load(player, Texture.class);

    }
}
