package com.de.game.entity.systems;

import com.de.game.entity.components.TextureComponent;
import com.de.game.entity.components.TypeComponent;
import com.de.game.entity.components.StateComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class AnimationSystem extends IteratingSystem {

    ComponentMapper<TextureComponent> tm;
    ComponentMapper<StateComponent> sm;
    ComponentMapper<TypeComponent> typem;
    float t1;
    float t2;
    float t3;
    float t4;
    float t5;

	public AnimationSystem(){
        super(Family.all(TextureComponent.class, StateComponent.class).get());

        tm = ComponentMapper.getFor(TextureComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        typem = ComponentMapper.getFor(TypeComponent.class);
        t1 = 0;
        t2 = 0;
        t3 = 0;
        t4 = 0;
        t5 = 0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = sm.get(entity);
        TextureComponent texture = tm.get(entity);
        TypeComponent type = typem.get(entity);
        if(type.getType() == TypeComponent.PLAYER){
            if(state.getstate() == StateComponent.STATE_MOVING_DOWN){
                if(t1 <= 0.125){
                    t2 = 0;
                    t3 = 0;
                    t4 = 0;
                    texture.setRegion(texture.getAtlas().findRegion("front_standing"));
                    t1 += deltaTime;
                }
                else if(t1 <= 0.250){
                    texture.setRegion(texture.getAtlas().findRegion("front_waltking_a"));
                    t1 += deltaTime;
                }
                else if(t1 <= 0.375){
                    texture.setRegion(texture.getAtlas().findRegion("front_waltking_b"));
                    t1 += deltaTime;
                }
                else{
                    t1 = 0;
                }
            }
            else if(state.getstate() == StateComponent.STATE_MOVING_UP){
                if(t2 <= 0.125){
                    t1 = 0;
                    t3 = 0;
                    t4 = 0;
                    texture.setRegion(texture.getAtlas().findRegion("back_standing"));
                    t2 += deltaTime;
                }
                else if(t2 <= 0.250){
                    texture.setRegion(texture.getAtlas().findRegion("back_waltking_a"));
                    t2 += deltaTime;
                }
                else if(t2 <= 0.375){
                    texture.setRegion(texture.getAtlas().findRegion("back_waltking_b"));
                    t2 += deltaTime;
                }
                else{
                    t2 = 0;
                }
            }
            else if(state.getstate() == StateComponent.STATE_MOVING_LEFT){
                if(t3 <= 0.125){
                    t1 = 0;
                    t2 = 0;
                    t4 = 0;
                    texture.setRegion(texture.getAtlas().findRegion("left_standing"));
                    t3 += deltaTime;
                }
                else if(t3 <= 0.250){
                    texture.setRegion(texture.getAtlas().findRegion("left_waltking_a"));
                    t3 += deltaTime;
                }
                else if(t3 <= 0.375){
                    texture.setRegion(texture.getAtlas().findRegion("left_waltking_b"));
                    t3 += deltaTime;
                }
                else{
                    t3 = 0;
                }
            }
            else if(state.getstate() == StateComponent.STATE_MOVING_RIGHT){
                if(t4 <= 0.125){
                    t1 = 0;
                    t2 = 0;
                    t3 = 0;
                    texture.setRegion(texture.getAtlas().findRegion("right_standing"));
                    t4 += deltaTime;
                }
                else if(t4 <= 0.250){
                    texture.setRegion(texture.getAtlas().findRegion("right_waltking_b"));
                    t4 += deltaTime;
                }
                else if(t4 <= 0.375){
                    texture.setRegion(texture.getAtlas().findRegion("right_waltking_a"));
                    t4 += deltaTime;
                }
                else{
                    t4 = 0;
                }
            }
            else if(state.getstate() == StateComponent.STATE_NORMAL){
                if (state.getLaststate() == "DOWN"){
                    texture.setRegion(texture.getAtlas().findRegion("front_standing"));
                }
                if (state.getLaststate() == "UP"){
                    texture.setRegion(texture.getAtlas().findRegion("back_standing"));
                }
                if (state.getLaststate() == "LEFT"){
                    texture.setRegion(texture.getAtlas().findRegion("left_standing"));
                }
                if (state.getLaststate() == "RIGHT"){
                    texture.setRegion(texture.getAtlas().findRegion("right_standing"));
                }
            }
        }
        else if(type.getType() == TypeComponent.ENEMY){
            if(t5 <= 1){
                texture.setRegion(texture.getAtlas().findRegion("gound"));
                t5 += deltaTime;
            }
            else if(t5 <= 2){
                texture.setRegion(texture.getAtlas().findRegion("jump1"));
                t5 += deltaTime;
            }
            else if(t5 <= 3){
                texture.setRegion(texture.getAtlas().findRegion("jump2"));
                t5 += deltaTime;
            }
            else if(t5 <= 4){
                texture.setRegion(texture.getAtlas().findRegion("jump1"));
                t5 += deltaTime;
            }
            if(t5 > 4){
                t5 = 0;
            }
        }
    }
}
