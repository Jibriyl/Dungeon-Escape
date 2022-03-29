package com.de.game.inputs;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class KeyboardController implements InputProcessor{

    //Klasse die feststellt welcher key auf der tastatur gedrückt wurde und speichert damit andere klassen darauf zu greifen können
    public boolean left,right,up,down,space,shift,enter;

    @Override
    //Erkennt wenn Button gedrückt wird
    public boolean keyDown(int keycode) {
        //Variable die Speichert ob ein button gedrückt worden ist
        boolean keyProcessed = false;

        switch (keycode)
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
                break;
            }
        if (Keys.SPACE == keycode){
            space = true;
            keyProcessed = true;
        }
        if (Keys.SHIFT_LEFT == keycode){
            shift = true;
            keyProcessed = true;
        }
        if (Keys.ENTER == keycode){
            enter = true;
            keyProcessed = true;
        }
    
    return keyProcessed;    
    }

    @Override
    //Erkennt wenn Button losgelassen wird
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        switch (keycode) 
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
        if (Keys.SPACE == keycode){
            space = false;
            keyProcessed = true;
        }
        if (Keys.SHIFT_LEFT == keycode){
            shift = false;
            keyProcessed = true;
        }
        if (Keys.ENTER == keycode){
            enter = false;
            keyProcessed = true;
        }

    return keyProcessed;    //  return our peyProcessed flag
    }

    //Diese autogenerierte Methoden werden nicht genutzt
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