import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Ball {
	int x, y; // center of ball
	int vx,vy; // velocity vector
	private int entryno;
	public boolean captured;
	private int colorb;
	Image anger = new Image( "angryemoji.png" );
    ImageView imageView1 = new ImageView(anger); 
	Image heart = new Image( "heartemoji.png" );
    ImageView imageView2 = new ImageView(heart); 
	

	final static int RADIUS = 12;

	public Ball(int entryno, int colorb) {
		this.entryno = entryno;
		this.colorb = colorb;
		spawnIn();
	}

	public void render(GraphicsContext gc){
		if(colorb == 1)
			gc.setFill(Color.YELLOW);
		else if(colorb == 2)
			gc.setFill(Color.BLUE);
		else if(colorb == 3)
			gc.setFill(Color.RED);
		else if(colorb == 4)
			gc.setFill(Color.GREEN);

		gc.fillOval(x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
		if(!captured)
			gc.drawImage(anger, x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
		else
			gc.drawImage(heart, x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
	}

	// start the ball in mid-screen with a somewhat
	// random direction
	public void reset() {
		captured = true;
	    x = 420 + (30 * entryno);
	    y = 60;
	    vx = 0;
	    vy = 0;
	}

	public void spawnIn(){
		Random random = new Random();
		int randomvar = random.nextInt(50);
		captured = false;
	    x = Game.WIDTH/2 + randomvar;
	    y = Game.HEIGHT/4 + randomvar;
	    if (Math.random() < 0.5) //decides if ball moves right or left on start
	        vx = -7;
	    else
	        vx = 7;
	    vy = (int)(2+3*Math.random()); //randomly assigns y velocity

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
		if (y < RADIUS || y+RADIUS > Game.HEIGHT) //if the Y is less than radius, or if the y+radius is greater than the height
		    vy = -vy; //invert Y
	}
}