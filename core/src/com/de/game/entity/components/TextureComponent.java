package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {
    //Speichert Textur und Atlastextur einer Entität, Atlastextur ist eine datei mit mehreren texturen die für animationen genutzt werden
    private TextureAtlas atlas = null;
    private TextureRegion region = null;

    public TextureAtlas getAtlas() {
        return atlas;
    }
    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }
    public TextureRegion getRegion() {
        return region;
    }
    public void setRegion(TextureRegion region) {
        this.region = region;
    }
}

