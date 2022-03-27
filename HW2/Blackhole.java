import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Blackhole {
   int x, y; // top-left corner

   boolean upKey = false, downKey = false, rightKey = false, leftKey = false;

   int WIDTH = 30;
   int HEIGHT = 30;
   final static int SPEED = 8;

   // Set x1 as the middle of the paddle
   public Blackhole(int x1){
      x = 100;
      y = 100;
   }

   public void move(){
	   if (upKey && getY()>0)
	       y -= SPEED;
	   if (downKey && getBottom()<AnimationTest.HEIGHT)
	       y += SPEED;
       if(rightKey)
           x += SPEED;
       if(leftKey)
           x -= SPEED;
   }

   public void render(GraphicsContext gc){
	   gc.setFill(Color.BLACK);
	   gc.fillOval(x, y, WIDTH, HEIGHT);
   }

   public void setUpKey(Boolean val){
	   upKey = val;
   }
   public void setDownKey(Boolean val){
	   downKey = val;
   }    
   public void setLeftKey(Boolean val){
       leftKey = val;
   }
   public void setRightKey(Boolean val){
       rightKey = val;
   }

   // return the x-coord of middle of bh
   public int getX(){
	   return x+(WIDTH/2);
   }

   public int getY(){
	   return y;
   }

   public int getBottom(){
	   return y+HEIGHT;
   }

   public int getTop(){
       return y-HEIGHT;
   }

   public int getRadius(){
       return WIDTH;
   }

   public int setRadius(int val){
       this.HEIGHT = val;
       this.WIDTH = val;
       return val;
   }
}