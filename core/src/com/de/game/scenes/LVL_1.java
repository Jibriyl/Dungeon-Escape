package com.de.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.de.game.Main;

public class LVL_1 extends LVL_0{

    Main game;
    protected Texture lvl1background;

    public LVL_1 (Main game){
        super(game, true);

        //Laden der Texturen
        lvl1background = game.assetManager.manager.get("Input/game/lvl1background.png");
        renderingSystem.setBackground(lvl1background);

        //Erstellen der Objekte im Level
        //Spieler
        pm.createPlayer("test");

        //Gegner
    
        em.createbasicSimpleEnemy(30,50);
        em.createbasicSimpleEnemy(30,30);


        //Scenery
        sm.createWall(96, 2, 192, 1);
        sm.createWall(96, 92, 192, 1);
        sm.createWall(2, 54, 1, 108);
        sm.createWall(190, 54, 1, 108);
    }
}

