package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class B2dBodyComponent implements Component{
	private Body body;
	private float width;
	private float height;
 	//Wird genutzt um die textur der Entität die richtige größe zu geben
	public void setdimension(float width, float height){
		this.height = height;
		this.width = width;
	}

	public float getWidth(){
		return this.width;
	}
	public float getHeight(){
		return this.height;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
}