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
    private int win;

    public LifeSystem(World world, Main main, Engine engine) {
        super(Family.all(StatComponent.class).get());
        this.main = main;
        this.engine = engine;
        this.world = world;
        sm = ComponentMapper.getFor(StatComponent.class);
        tm = ComponentMapper.getFor(TypeComponent.class);		 
        bodm = ComponentMapper.getFor(B2dBodyComponent.class);
        win = 0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
		StatComponent stats = sm.get(entity);		
        TypeComponent type = tm.get(entity);
        B2dBodyComponent body = bodm.get(entity); 

        //Wenn das Leben des Spielers auf 0 fällt wird zum Game over screen gewechselt
        if(stats.getLeben() <= 0 && type.getType() == TypeComponent.PLAYER){
            main.screenset("GAME_OVER");
        }
        //Wenn das Leben einer Gegners auf 0 fällt wird er gelöscht
        if(stats.getLeben() <= 0 && type.getType() == TypeComponent.ENEMY){
            //Löschen der Gegner entity
            engine.removeEntity(entity);
            //Löschen des Bodys des Gegners
            world.destroyBody(body.getBody());
        }

        //Stellt fest ob noch gegner Leben und wenn nicht wechselt zum win screen
        if (type.getType() == TypeComponent.PLAYER){
             win += 1;
        }
        if (type.getType() == TypeComponent.ENEMY){
            win = 0;
        }
        if (win > 1){
            main.screenset("WIN_SCREEN");
        }
    }
}
