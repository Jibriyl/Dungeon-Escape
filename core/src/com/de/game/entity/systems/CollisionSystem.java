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
	private TransformComponent lastplayerpos;

	public CollisionSystem() {
		//Der Kontrukter gibt an welche Entitys angefragt werden sollen, in diesem fall muss die Entität eine CollisionComponent haben
		super(Family.all(CollisionComponent.class).get());
		 cm = ComponentMapper.getFor(CollisionComponent.class);
		 sm = ComponentMapper.getFor(StatComponent.class);
		 tm = ComponentMapper.getFor(TypeComponent.class);
		 bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		 trm = ComponentMapper.getFor(TransformComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		CollisionComponent cc = cm.get(entity);
		TypeComponent thistype = tm.get(entity);
		StatComponent stats = sm.get(entity);
		B2dBodyComponent spielerbody = bodm.get(entity); 
		TransformComponent playerposi = trm.get(entity);

		Entity collidedEntity = cc.collisionEntity;

		//Wenn der Spieler mit einer anderen Entity Kollidiert
		if(thistype.type == TypeComponent.PLAYER){
			//Speicher jeden tick wo der spieler ist
			lastplayerpos = playerposi;
			if(collidedEntity != null){
				TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
				//Spieler berührt einen Gegner
				if(type.type == TypeComponent.ENEMY){
					StatComponent gegnerstats = collidedEntity.getComponent(StatComponent.class);
					TransformComponent gegnerpos = collidedEntity.getComponent(TransformComponent.class);
					//Berechnen der richtung in der der gegner zum spieler ist
					Vector2 difference = new Vector2(playerposi.position.x - gegnerpos.position.x, playerposi.position.y - gegnerpos.position.y);
					//Einfügen des Rückstoßes entgegengesetzt der richtung dem Gegner
					spielerbody.body.applyForceToCenter(difference.x * 3000, difference.y * 3000,true);
			
					//Fügt dem Spieler den schaden zu der im gegner als schaden gespeichert ist
					stats.nehmeschaden(gegnerstats.getDamage());
					System.out.println("player hit enemy" + stats.getLeben());
				}
				cc.collisionEntity = null; // collision handled reset component
			}
		}
		//Wenn ein gegner mit einer entity Kollidiert
		if(thistype.type == TypeComponent.ATTACK){
			if(collidedEntity != null){
				TypeComponent type = collidedEntity.getComponent(TypeComponent.class);
				//Wenn ein gegner von einem Angriff des Spielers getroffen wird
				if(type.type == TypeComponent.ENEMY){
					TransformComponent gegnerpos = collidedEntity.getComponent(TransformComponent.class);
					Vector2 difference = new Vector2(gegnerpos.position.x - lastplayerpos.position.x, gegnerpos.position.y - lastplayerpos.position.y);
					StatComponent gegnerstats = collidedEntity.getComponent(StatComponent.class);
					B2dBodyComponent gegnerbody = collidedEntity.getComponent(B2dBodyComponent.class); 
					//Berechnen der richtung in der der gegner zum spieler ist
					System.out.println("Attack hit Enemy");
					gegnerstats.nehmeschaden(stats.getDamage());
					gegnerbody.body.applyForceToCenter(difference.x * 300, difference.y * 300,true);
				}
				cc.collisionEntity = null; // collision handled reset component
			}
		}
	}
}
