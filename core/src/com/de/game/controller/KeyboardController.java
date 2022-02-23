package com.de.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class KeyboardController implements InputProcessor{

    public boolean left,right,up,down;

    @Override
	public boolean keyDown(int keycode) {
	boolean keyProcessed = false;
	switch (keycode) // switch code base on the variable keycode
        {
	        case Keys.A:
	            left = true;
	            keyProcessed = true;
	            break;
	        case Keys.D:
	            right = true;
	            keyProcessed = true; 
	            break;
	        case Keys.W: 	
	            up = true;	
	            keyProcessed = true;
	            break;
	        case Keys.S: 
	            down = true;
	            keyProcessed = true;
        }
	return keyProcessed;	//  return our peyProcessed flag
    }

    @Override
	public boolean keyUp(int keycode) {
	boolean keyProcessed = false;
	switch (keycode) // switch code base on the variable keycode
        {
	        case Keys.A: 
	            left = false;
	            keyProcessed = true;
	            break;
	        case Keys.D:
	            right = false;
	            keyProcessed = true;
	            break;
	        case Keys.W: 
	            up = false;
	            keyProcessed = true;
	            break;
	        case Keys.S: 
	            down = false;
	            keyProcessed = true;
        }
	return keyProcessed;	//  return our peyProcessed flag
}

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
    
}