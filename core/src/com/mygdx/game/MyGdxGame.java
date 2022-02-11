package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import org.jcp.xml.dsig.internal.dom.ApacheNodeSetData;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;

	ArrayList<GrassFeeding> grassFeedings = new ArrayList<>();
	ArrayList<MeatEating> meatEatings = new ArrayList<>();
	ArrayList<Grass> grasses = new ArrayList<>();

	public void PlayScreen() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		for (int i = 0; i < 10; i++) {
			grassFeedings.add(new GrassFeeding(100 * i, 250));
			meatEatings.add(new MeatEating(100 * i, 1300));
		}
		PlayScreen();
		Gdx.input.setInputProcessor(new InputAdapter() {
			public boolean touchDown(int x, int y, int pointer, int button) {

				return false;
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		for (GrassFeeding gf : grassFeedings) {
			gf.lifecycle(grassFeedings, grasses, meatEatings);
			gf.getSkin().draw(batch);
		}
		for (MeatEating me : meatEatings) {
			me.lifecycle();
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
