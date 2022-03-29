package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {
    //Speichert position einer Entität, z wird genutzt um zu entscheiden auf welchem Layer die textur abgebildet wird
    public final Vector3 position = new Vector3();
    private float rotation = 0.0f;
    private boolean isHidden = false;

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
    public float getRotation() {
        return rotation;
    }
    public boolean getisHidden(){
        return isHidden;
    }
}
