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
		gc.setFill(Color.YELLOW);
		gc.fillOval(x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
	}

	// start the ball in mid-screen with a somewhat
	// random direction
	public void reset() {
	    x = AnimationTest.WIDTH/2;
	    y = AnimationTest.HEIGHT/4;
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

	public boolean checkBH(Character bh){
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
		if (y < RADIUS || y+RADIUS > AnimationTest.HEIGHT) //if the Y is less than radius, or if the y+radius is greater than the height
		    vy = -vy; //invert Y
	}
}