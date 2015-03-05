package com.burningbears;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Crash
 *
 */
public class BurningBears extends ApplicationAdapter {

	/**
	 * @author Crash
	 * 
	 *
	 */
	SpriteBatch batch;
	TeddyBear bear0, bear1;

	//
	@Override
	public void create() {
		batch = new SpriteBatch();
		bear0 = new TeddyBear("teddybear0.png", 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		bear1 = new TeddyBear("teddybear1.png", 300, 0,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void update() {
		bear0.update();
		bear1.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bear0.draw(batch);
		bear1.draw(batch);
		batch.end();
		update();
	}
}
