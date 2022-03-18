package com.de.game.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.StatComponent;
import com.de.game.entity.components.StateComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;

public class SimpleEnemyController extends IteratingSystem{

  private ComponentMapper<StatComponent> pm;
  private ComponentMapper<B2dBodyComponent> bodm;
  private ComponentMapper<StateComponent> sm;
  private	ComponentMapper<TypeComponent> tm;
  private ComponentMapper<TransformComponent> tfm;
  private Vector2 playerpos;

  public SimpleEnemyController(){
    super(Family.all(StatComponent.class).get());
        
    pm = ComponentMapper.getFor(StatComponent.class);
    bodm = ComponentMapper.getFor(B2dBodyComponent.class);
    sm = ComponentMapper.getFor(StateComponent.class);
    tm = ComponentMapper.getFor(TypeComponent.class);
    tfm = ComponentMapper.getFor(TransformComponent.class);
    playerpos = new Vector2(0.0f, 0.0f);
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    TransformComponent position = tfm.get(entity);

    TypeComponent type = tm.get(entity);
    if(type.type == TypeComponent.ENEMY){
      B2dBodyComponent b2body = bodm.get(entity);
      StateComponent state = sm.get(entity);
      StatComponent player = pm.get(entity);
      Vector2 difference = new Vector2(playerpos.x - position.position.x, playerpos.y - position.position.y);
      
      b2body.body.applyForceToCenter(difference.x * player.getSpeed(), difference.y * player.getSpeed(),true);
    }

    if(type.type == TypeComponent.PLAYER){
      playerpos = new Vector2(position.position.x, position.position.y);
    }
  }
    
}
