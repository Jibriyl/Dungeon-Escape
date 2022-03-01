package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class B2dBodyComponent implements Component{
	public Body body;
	private float width;
	private float height;

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
}