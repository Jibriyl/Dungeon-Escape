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
    private String laststate = "DOWN";

    public void setstate(int newState){
        state = newState;
    }

    public int getstate(){
        return state;
    }
    public String getLaststate() {
        return laststate;
    }
    public void setLaststate(String laststate) {
        this.laststate = laststate;
    }
}
