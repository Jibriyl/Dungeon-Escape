package com.de.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Gameworld world1 = new Gameworld();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Grass.jpg");
		}

	@Override
	public void render () {
		long start = System.nanoTime();
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();


		long frametime = (System.nanoTime() - start);
		System.out.println(frametime);
		//Updated alle Objekte die an die Welt gebunten sind.
		//world1.doPhysicsStep(frametime);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
