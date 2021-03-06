package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
	//Speichert um was es sich handelt, spieler gegner etc.
	public static final int PLAYER = 0;
	public static final int ENEMY = 1;
	public static final int PLAYERATTACK = 2;
	public static final int SCENERY = 3;
	public static final int OTHER = 4;
	public static final int ATTACK = 5;
	public static final int SLIME = 6;
	private int type = OTHER;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}

