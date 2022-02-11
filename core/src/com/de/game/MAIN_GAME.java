package com.de.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class MAIN_GAME extends ScreenAdapter{

    Main game;
    Vector2 worldvector = new Vector2(0, 0);
    World world = new World(worldvector, true);
    private float accumulator = 0;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();


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
            Gdx.gl.glClearColor(0, .25f, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.begin();
            ScreenUtils.clear(0, 0, 0, 1);
            //Abteil indem die Assets geladen werden
            


            game.batch.end();
            //Ermessen der Zeit die ein Frame benötigt hat
			long frametime = (System.nanoTime() - start);
			System.out.println(frametime);
            
            doPhysicsStep(frametime);
    }

    public void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime/1000000000, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/120f) {
            world.step(1/120f, 6, 2);
            accumulator -= 1/120f;
        }
    }

    
}
