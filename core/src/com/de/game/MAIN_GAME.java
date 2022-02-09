package com.de.game;

import com.badlogic.gdx.ScreenAdapter;

public class MAIN_GAME extends ScreenAdapter{

    Main game;

    public MAIN_GAME (Main game){
        this.game = game;
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
            //Messen der Aktuellen Systemzeit um die Zeit, die ein Frame benötigt zu errechnen
			long start = System.nanoTime();
            //Bereich für das Drawen der Main-Game Grafik



            //Ermessen der Zeit die ein Frame benötigt hat
			long frametime = (System.nanoTime() - start);
			System.out.println(frametime);
    }

    
}
