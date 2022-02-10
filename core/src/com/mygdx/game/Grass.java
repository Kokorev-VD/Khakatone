package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Grass {
    private Sprite skin = new Sprite(new Texture(""));
    private boolean eat = false;

    public Grass() {
        skin.setX((float) (Math.random()*(Gdx.graphics.getWidth()-40)+20));
        skin.setY((float) (Math.random()*(Gdx.graphics.getHeight()-40)+20));
    }

    public boolean isEat() {
        return eat;
    }

    public void setEat(boolean eat) {
        this.eat = eat;
    }

    public Sprite getSkin() {
        return skin;
    }

    public void setSkin(Sprite skin) {
        this.skin = skin;
    }

}


