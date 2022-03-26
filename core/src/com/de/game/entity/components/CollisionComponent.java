package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
//Speichert die letzte collision einer Entity, wird im Contactlistener gesetzt
public class CollisionComponent implements Component {
	private Entity collisionEntity;; 

	public Entity getCollisionEntity() {
		return collisionEntity;
	}public void setCollisionEntity(Entity collisionEntity) {
		this.collisionEntity = collisionEntity;
	}
}
