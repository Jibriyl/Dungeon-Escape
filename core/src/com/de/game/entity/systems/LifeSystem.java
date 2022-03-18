package com.de.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.de.game.Main;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.StatComponent;
import com.de.game.entity.components.TypeComponent;

public class LifeSystem extends IteratingSystem{

    ComponentMapper<StatComponent> sm;
    ComponentMapper<TypeComponent> tm;
    ComponentMapper<B2dBodyComponent> bodm;
    private World world;
    private Main main;
    private Engine engine;

    public LifeSystem(World world, Main main, Engine engine) {
        super(Family.all(StatComponent.class).get());
        this.main = main;
        this.engine = engine;
        this.world = world;
        sm = ComponentMapper.getFor(StatComponent.class);
        tm = ComponentMapper.getFor(TypeComponent.class);		 
        bodm = ComponentMapper.getFor(B2dBodyComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
		StatComponent stats = sm.get(entity);		
        TypeComponent type = tm.get(entity);
        B2dBodyComponent body = bodm.get(entity); 

        if(stats.getLeben() <= 0 && type.type == TypeComponent.PLAYER){
            main.screenset("GAME_OVER");
        }
        if(stats.getLeben() <= 0 && type.type == TypeComponent.ENEMY){
            engine.removeEntity(entity);
            world.destroyBody(body.body);
        }
    }
    
}
