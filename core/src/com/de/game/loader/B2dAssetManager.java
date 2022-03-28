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
    public final String slime = "Output/slime.atlas";
    //Player auswahl texturen
    public final String charback = "Input/charauswahl/Back.png";
    public final String charbackrahmen = "Input/charauswahl/Backramen.png";
    public final String charbackground = "Input/charauswahl/Hintergrund.png";
    public final String charJplayer = "Input/charauswahl/Jplayer.png";
    public final String charMplayer = "Input/charauswahl/Mplayer.png";
    public final String charplayerrahmen = "Input/charauswahl/Mplayer.png";
    //Main Menu
    public final String mainbald = "Input/main/kommt_bald.png";
    public final String mainRahmen = "Input/main/Rahmen.png";
    public final String mainStart = "Input/main/Start.png";
    public final String mainVerlassen = "Input/main/Verlassen.png";
    public final String mainBackground = "Input/main/Backrond_hauptmenu_richtig.png";


    public void queueAddImages(){
        manager.load(lvl1background, Texture.class);
        manager.load(gameoverbackground, Texture.class);
        manager.load(player, TextureAtlas.class);
        manager.load(gameover, Texture.class);

        manager.load(charback, Texture.class);
        manager.load(charbackrahmen, Texture.class);
        manager.load(charbackground, Texture.class);
        manager.load(charJplayer, Texture.class);
        manager.load(charMplayer, Texture.class);
        manager.load(charplayerrahmen, Texture.class);

        manager.load(mainbald, Texture.class);
        manager.load(mainRahmen, Texture.class);
        manager.load(mainStart, Texture.class);
        manager.load(mainVerlassen, Texture.class);
        manager.load(mainBackground, Texture.class);
        manager.load(slime, TextureAtlas.class);
    }
}
