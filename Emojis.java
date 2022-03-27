package assign2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Emojis {
	
	int x,y;
	
	Image angryemoji = new Image("angryemoji.png");
	Image heartemoji = new Image("heartemoji.png");
	Image current = angryemoji;
	
	final static int WIDTH = 100;
	final static int HEIGHT = 100;
	
	
	 public Emojis(int a, int b){
	      x = a;
	      y = b;
	   }
	
	public void render(GraphicsContext gc){
		gc.drawImage(current,x,y,HEIGHT,WIDTH);

		   }
	
	//changes emoji 
	public void changeEmoji(Ball b, Emojis e) {
		if(b.checkEm(e)==true)
			current = heartemoji;
		else {
			current = angryemoji;
		}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}