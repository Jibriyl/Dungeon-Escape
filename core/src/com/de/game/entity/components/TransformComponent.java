package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {
    public final Vector3 position = new Vector3();
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
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
