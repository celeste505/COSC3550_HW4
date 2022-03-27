package hw4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
	int x, y; // center of ball
	int vx,vy; // velocity vector

	final static int RADIUS = 12;

	public Ball() {
		reset();
	}

	public void render(GraphicsContext gc){
		gc.setFill(Color.BLUE);
		gc.fillOval(x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
	}

	// start the ball in mid-screen with a somewhat
	// random direction
	public void reset() {
	    x = Game.WIDTH/2;
	    y = Game.HEIGHT/4;
	    if (Math.random() < 0.5)
	        vx = -7;
	    else
	        vx = 7;
	    vy = (int)(2+3*Math.random());
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void checkHit(Paddle p) {
		int py = p.getTop();
		if( y+RADIUS < py )
			return;
		int px1 = p.getLeft();
		int px2 = p.getRight();
		if ((x < px1-10)||(x > px2-10))
			return;
		vy = -vy;
	}
	
	//Checks if emoji is hit by ball
	//if yes, return true
	public boolean checkEm(Emojis e) {
		int ex = e.getX();
		int ey = e.getY();
		
		if ((x-50 < ex) && (ex < x+50) && (y-50 < ey) && (ey < y+50)){
			return true;
		}
		return false;
		
	}
	

	public void move() {
		x += vx;
		y += vy;
		// Bounce off all walls
		if (y < RADIUS || y+RADIUS > Game.HEIGHT )
		    vy = -vy;
		if (x < RADIUS || x+RADIUS > Game.WIDTH )
		    vx = -vx;
	}
}
