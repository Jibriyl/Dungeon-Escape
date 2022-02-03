package com.de.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Grass.jpg");

		Vector2 worldvector = new Vector2(0, 0);
		World world = new World(worldvector, false);
		Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		//Updated alle Objekte die an die Welt gebunten sind.
		world.step(1/120f, 6, 2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
