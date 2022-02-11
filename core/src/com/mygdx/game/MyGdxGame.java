package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	ArrayList<GrassFeeding> grassFeedings = new ArrayList<>();
	ArrayList<MeatEating> meatEatings = new ArrayList<>();
	ArrayList<Grass> grasses = new ArrayList<>();

	@Override
	public void create() {
		batch = new SpriteBatch();
		for (int i = 0; i < 10; i++) {
			grassFeedings.add(new GrassFeeding(100 * i, 250));
			meatEatings.add(new MeatEating(100 * i, 1250))
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
