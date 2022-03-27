package hw4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle {
   int x, y; // top-left corner

   boolean leftKey = false, rightKey = false;

   final static int WIDTH = 6;
   final static int HEIGHT = 40;
   final static int SPEED = 10;

   // Set x1 as the middle of the paddle
   public Paddle(int x1){
      x = x1;
      y = Game.HEIGHT-50;
   }

   public void move(){
	   if (leftKey && getTop()>0)
	       x -= SPEED;
	   if (rightKey && getBottom()<Game.HEIGHT)
	       x += SPEED;
   }

   public void render(GraphicsContext gc){
	   gc.setFill(Color.RED);
	   gc.fillRect(x, y, HEIGHT, WIDTH);
   }

   public void setUpKey(Boolean val){
	   leftKey = val;
   }

   public void setDownKey(Boolean val){
	   rightKey = val;
   }

   // return the x-coord of middle of paddle
   public int getX(){
	   return x+(WIDTH/2);
   }
   public int getLeft(){
	   return x;
   }
   public int getRight(){
	   return x+WIDTH;
   }
   
   public int getTop(){
	   return y;
   }

   public int getBottom(){
	   return y+HEIGHT;
   }
}