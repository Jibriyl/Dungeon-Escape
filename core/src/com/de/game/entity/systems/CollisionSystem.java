package com.de.game.entity.systems;

import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.CollisionComponent;
import com.de.game.entity.components.StatComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

public class CollisionSystem  extends IteratingSystem {
	 ComponentMapper<CollisionComponent> cm;
	 ComponentMapper<StatComponent> sm;
	 ComponentMapper<TypeComponent> tm;
	 ComponentMapper<B2dBodyComponent> bodm;
	 ComponentMapper<TransformComponent> trm;

	public CollisionSystem() {
		// only need to worry about player collisions
		super(Family.all(CollisionComponent.class,StatComponent.class).get());
		
		 cm = ComponentMapper.getFor(CollisionComponent.class);
		 sm = ComponentMapper.getFor(StatComponent.class);
		 tm = ComponentMapper.getFor(TypeComponent.class);
		 bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		 trm = ComponentMapper.getFor(TransformComponent.class);

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// get player collision component
		CollisionComponent cc = cm.get(entity);
		TypeComponent thistype = tm.get(entity);
		StatComponent stats = sm.get(entity);
		B2dBodyComponent spielerbody = bodm.get(entity); 
		TransformComponent playerposi = trm.get(entity);

		Entity collidedEntity = cc.collisionEntity;
		//Wenn der Spieler mit einer anderen Entity Kollidiert
		if(thistype.type == TypeComponent.PLAYER){
			if(collidedEntity != null){
				TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
				if(type != null){
					switch(type.type){
					//Spieler berührt einen Gegner
					case TypeComponent.ENEMY:
						StatComponent gegnerstats = collidedEntity.getComponent(StatComponent.class);
						TransformComponent gegnerpos = collidedEntity.getComponent(TransformComponent.class);
						//Berechnen der richtung in der der gegner zum spieler ist
						Vector2 difference = new Vector2(playerposi.position.x - gegnerpos.position.x, playerposi.position.y - gegnerpos.position.y);
						//Einfügen des Rückstoßes entgegengesetzt der richtung dem Gegner
						spielerbody.body.applyForceToCenter(difference.x * 3000, difference.y * 3000,true);
						//Fügt dem Spieler den schaden zu der im gegner als schaden gespeichert ist
						stats.nehmeschaden(gegnerstats.getDamage());
						System.out.println("player hit enemy" + stats.getLeben());
						break;
					case TypeComponent.OTHER:
						//do player hit other thing
						System.out.println("player hit other");
						break; //technically this isn't needed				
					}
					cc.collisionEntity = null; // collision handled reset component
				}
			}
		}
		//Wenn ein gegner mit einer entity Kollidiert
		if(thistype.type == TypeComponent.ENEMY){

		}



		
	}

}
