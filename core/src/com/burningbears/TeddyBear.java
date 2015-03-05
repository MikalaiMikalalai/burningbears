package com.burningbears;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TeddyBear {

	// Drawing support
	Texture sprite;
	Rectangle drawRectangle;

	// Velocity information
	Vector2 velocity = new Vector2(0, 0);

	// bouncing support
	int windowHeight;
	int windowWidth;

	// Class constructors

	public TeddyBear(String spriteName, int x, int y, int windowWidth,
			int windowHeight) {

		// TODO -
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		loadContent(spriteName, x, y);
		Random rand = new Random();
		int speed = rand.nextInt(5) + 3;
		double angle = 2 * Math.PI * rand.nextDouble();
		velocity.x = (float) (Math.cos(angle) * speed);
		velocity.y = -1 * (float) (Math.sin(angle) * speed);

	}

	public void update() {
		drawRectangle.x += (int) (velocity.x);
		drawRectangle.y += (int) (velocity.y);

		// bounce as necessary
		bounceTopBottom();
		bounceLeftRight();
	}

	public void draw(SpriteBatch batch) {
		batch.draw(sprite, drawRectangle.x, drawRectangle.y,
				drawRectangle.width, drawRectangle.height);
	}

	private void loadContent(String spriteName, int x, int y) {
		sprite = new Texture(Gdx.files.internal(spriteName));
		drawRectangle = new Rectangle(x - sprite.getWidth() / 2, y
				- sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
	}

	private void bounceTopBottom() {
		if (drawRectangle.y < 0) {
			// bounce off top
			drawRectangle.y = 0;
			velocity.y *= -1;
		} else if ((drawRectangle.y + drawRectangle.height) > windowHeight) {
			// bounce off bottom
			drawRectangle.y = windowHeight - drawRectangle.height;
			velocity.y *= -1;
		}
	}

	private void bounceLeftRight() {
		if (drawRectangle.x < 0) {
			// bounce off left
			drawRectangle.x = 0;
			velocity.x *= -1;
		} else if ((drawRectangle.x + drawRectangle.width) > windowWidth) {
			// bounce off right
			drawRectangle.x = windowWidth - drawRectangle.width;
			velocity.x *= -1;
		}
	}

}
