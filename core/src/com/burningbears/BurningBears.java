package com.burningbears;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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
	Explosion explosion;

	//
	@Override
	public void create() {
		batch = new SpriteBatch();
		bear0 = new TeddyBear("teddybear0.png", 500, 300, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), new Vector2(5f, 0));
		bear1 = new TeddyBear("teddybear1.png", 300, 300,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Vector2(
						-5f, 0));
		explosion = new Explosion();
	}

	public void update() {
		bear0.update();
		bear1.update();
		explosion.update();
		if (bear0.isActive() && bear1.isActive()
				&& bear0.drawRectangle.overlaps(bear1.drawRectangle)) {
			Gdx.app.log("BurningBears", "bears overlapped");
			bear0.setActive(false);
			bear1.setActive(false);
			Rectangle collisionRectangle = new Rectangle(bear0.DrawRectangle()
					.getX() + bear0.DrawRectangle().getWidth(),
					bear0.DrawRectangle().getY()
							+ bear0.DrawRectangle().getHeight(), bear0
							.DrawRectangle().getWidth(), bear0.DrawRectangle()
							.getHeight());
			explosion
					.play(collisionRectangle.getX(), collisionRectangle.getY());
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0x47, 0xA3, 0xFF, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bear0.draw(batch);
		bear1.draw(batch);
		explosion.draw(batch);
		batch.end();
		update();
	}
}
