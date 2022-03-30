package hw4;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sun.net.www.content.text.plain;

public class PowerUp {
	int x, y; // center of ball
	int vx,vy; // velocity vector

	public boolean captured;

	
	Image orange = new Image( "orangeemoji.png" );
    ImageView imageView1 = new ImageView(orange); 

	final static int RADIUS = 12;

	public PowerUp() {
		spawnIn();
	}


	public void render(GraphicsContext gc){
		gc.drawImage(orange, x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
		if(captured)
			reset();
		
		}

	// start the ball in mid-screen with a somewhat
	// random direction
	public void reset() {
		captured = true;
	    x = 700;
	    y = 700;
	    vx = 0;
	    vy = 0;
	}

	public void spawnIn(){
		Random random = new Random();
		int randomvar = random.nextInt(50);
		captured = false;
		x = 10*randomvar;
		y = 10*randomvar;
	    //x = Game.WIDTH/2 + randomvar;
	    //y = Game.HEIGHT/4 + randomvar;
	   
	}

	public int getX() { //x getter
		return x;
	}

	public void checkHit(Paddle p) { 
		int px = p.getX(); //gets x from paddle
		if (Math.abs(px - x) > RADIUS) //if absolute value of (px-x) > the radius of ball, return
		    return;
		int py1 = p.getTop();  //get top of paddle
		int py2 = p.getBottom(); //get bottom of paddle
		if ((y < py1)||(y > py2)) //if the Y isnt between top and bottom of paddle
			return;
		// reverse direction
		vx = -vx; //invert X
	}

	public boolean checkBH(Blackhole bh){
		int bx = bh.getX();
		int by = bh.getY();
		int br = bh.getRadius()/2;
		int TOPOFBH = by-br;
		int BOTOFBH = by+br;
		int REDGEBR = bx+br;
		int LEDGEBR = bx-br;
		if( (y > TOPOFBH) && (y < BOTOFBH) && (x > LEDGEBR-5) && (x < REDGEBR+5) ){
			reset();
			return true;
		}
		return false;
	}

	public void move() {
		x += vx;
		y += vy;
		// Bounce off top and bottom of screen
		if (y < RADIUS+90 || y+RADIUS > Game.HEIGHT) //if the Y is less than radius, or if the y+radius is greater than the height
		    vy = -vy; //invert Y
	}
}