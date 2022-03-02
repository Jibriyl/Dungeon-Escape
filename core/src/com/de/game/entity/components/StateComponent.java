package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {
	public static final int STATE_NORMAL = 0;
    public static final int STATE_MOVING_LEFT = 1;
    public static final int STATE_MOVING_RIGHT = 2;
    public static final int STATE_MOVING_UP = 3;
    public static final int STATE_MOVING_DOWN = 4;

	public static final int STATE_IN_DASH = 7;
	public static final int STATE_HIT = 8;
	
	private int state = 0;
    public float time = 0.0f;
    public boolean isLooping = false;

    public void set(int newState){
        state = newState;
        time = 0.0f;
    }

    public int get(){
        return state;
    }
}
