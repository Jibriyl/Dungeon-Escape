package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
	public static final int PLAYER = 0;
	public static final int ENEMY = 1;
	public static final int PLAYERATTACK = 2;
	public static final int SCENERY = 3;
	public static final int OTHER = 4;
	public static final int ATTACK = 5;
	
	public int type = OTHER;

	public int getTypenumber() {
		return type;
	}
}

