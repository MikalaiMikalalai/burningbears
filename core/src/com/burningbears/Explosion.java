package com.burningbears;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {

	// Class Fields
	
	// object location
	Rectangle drawRectangle;
	
	// animation strip info
	String STRIP_NAME = "explosion.png";
	Texture strip;	
	int frameWidth;
    int frameHeight;
	
	// hard-coded animation info. There are better ways to do this,
    // we just don't know enough to use them yet
	 private final int FRAMES_PER_ROW = 3;
     private int NUM_ROWS = 3;
     private int NUM_FRAMES = 9;	
     
     // fields used to track and draw animations
     Rectangle sourceRectangle;
     int currentFrame;
     private final int FRAME_TIME = 10;
     private float elapsedFrameTime = 0;
     
     // playing or not
     boolean playing = false;
     
     public Explosion(){
    	// initialize animation to start at frame 0
         currentFrame = 0;

         loadContent();
     }
     
     public void update()
     {
    	 
    	 float gameTime = Gdx.graphics.getDeltaTime();
         if (isPlaying())
         {
             // check for advancing animation frame
             elapsedFrameTime += gameTime * 1000;
             if (elapsedFrameTime > FRAME_TIME)
             {
                 // reset frame timer
                 elapsedFrameTime = 0;

                 // advance the animation
                 if (currentFrame < NUM_FRAMES - 1)
                 {
                     currentFrame++;
                     setSourceRectangleLocation(currentFrame);
                 }
                 else
                 {
                     // reached the end of the animation
                     setPlaying(false);
                 }
             }
             Gdx.app.log("BurningBears", "update method called " + gameTime);
         }         
     }
     
     public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public void draw(SpriteBatch spriteBatch)
     {
         if (isPlaying())
         {        	 
             spriteBatch.draw(strip, sourceRectangle.getX(), sourceRectangle.getY(), sourceRectangle.getWidth(), drawRectangle.getHeight(),
            		 (int)sourceRectangle.getX(), (int)sourceRectangle.getY(),
            		 (int)sourceRectangle.getWidth(), (int)sourceRectangle.getHeight(), 
            		 false, false);
             Gdx.app.log("BurningBears", "draw method called");
         }
     }
     
     // Starts playing the animation for the explosion
     public void play(int x, int y)
     {
    	 Gdx.app.log("BurningBears", "play method called");
         // reset tracking values
         setPlaying(true);
         elapsedFrameTime = 0;
         currentFrame = 0;

         // set draw location and source rectangle
         drawRectangle.x = x - drawRectangle.getWidth() / 2;
         drawRectangle.y = y - drawRectangle.getHeight() / 2;
         setSourceRectangleLocation(currentFrame);
     }
     
     // Loads the content for the explosion
	 private void loadContent() {

		// load the animation strip
		this.strip = new Texture(Gdx.files.internal(STRIP_NAME));
		// calculate frame size
        frameWidth = strip.getWidth() / FRAMES_PER_ROW;
        frameHeight = strip.getHeight() / NUM_ROWS;

        // set initial draw and source rectangles
        drawRectangle = new Rectangle(0, 0, frameWidth, frameHeight);
        sourceRectangle = new Rectangle(0, 0, strip.getWidth(), strip.getHeight());
	 }
	
	 // Sets the source rectangle location to correspond with the given frame
	 private void setSourceRectangleLocation(int frameNumber)
     {
        // calculate X and Y based on frame number
        sourceRectangle.x = (frameNumber % FRAMES_PER_ROW) * frameWidth;
        sourceRectangle.y = (frameNumber / FRAMES_PER_ROW) * frameHeight;
     }
}
