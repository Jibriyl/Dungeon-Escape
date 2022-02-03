package com.de.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Gameworld{

    Vector2 worldvector = new Vector2(0, 0);

    World world = new World(worldvector, false);

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();


}
