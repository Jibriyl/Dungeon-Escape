package com.de.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Gameworld{

    Vector2 worldvector = new Vector2(0, 0);

    World world = new World(worldvector, true);

    private float accumulator = 0;

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
