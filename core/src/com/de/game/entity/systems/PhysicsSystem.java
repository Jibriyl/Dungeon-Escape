package com.de.game.entity.systems;

import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.TransformComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class PhysicsSystem extends IteratingSystem {

    //Game ist auf max 120 tick pro sek eingestellt
    private static final float MAX_STEP_TIME = 1/120f;
    private static float accumulator = 0f;

    private World world;
    private Array<Entity> bodiesQueue;

    private ComponentMapper<B2dBodyComponent> bm = ComponentMapper.getFor(B2dBodyComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

	public PhysicsSystem(World world) {
        super(Family.all(B2dBodyComponent.class, TransformComponent.class).get());
        this.world = world;
        //Queue in der alle Bodys gespeichert werden
        this.bodiesQueue = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        //System zu berechnung des frame aktualisierung
        float frameTime = Math.min(deltaTime, 1/15f);
        //Addiert auf wie lange ein Rendering zyklus dauert
        accumulator += frameTime;
        //Sollte dioe zeit größer sein als die max frame time, wird die welt eine  schritt weiter gesetzt
        if(accumulator >= MAX_STEP_TIME) {
            //Setzt die welt einen schritt weiter, die schrittweite wird mit den deltatime multipliziert damit das game auf alle PC gleich schnell läuft
            world.step(MAX_STEP_TIME*(deltaTime*200), 6, 2);
            //Zeiht eine schrittweite vom accumlator ab
            accumulator -= MAX_STEP_TIME;

            //Entity Queue
            for (Entity entity : bodiesQueue) {
                //Aktuallisiert die position in der transform component mit der des bodys, damit die texturen an der richtigen stelle angezeigt werden
                TransformComponent tfm = tm.get(entity);
                B2dBodyComponent bodyComp = bm.get(entity);
                Vector2 position = bodyComp.getBody().getPosition();
                tfm.position.x = position.x;
                tfm.position.y = position.y;
                //Rotation eines Bodys, wird in diesem spiel noch nicht genutzt
                tfm.setRotation(bodyComp.getBody().getAngle() * MathUtils.radiansToDegrees);
            }
        }
        bodiesQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime){
        bodiesQueue.add(entity);
    }
}
