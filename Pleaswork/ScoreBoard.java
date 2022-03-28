import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScoreBoard {

   int left = 0;  // score for left player
   int right = 0; // score for right player
   int level = 1;

   Font font;

   public ScoreBoard(){
	   font = Font.font("SansSerif", FontWeight.BOLD, 24);
   }

   public void render(GraphicsContext gc){
	   gc.setFill(Color.WHITE);
	   gc.setFont(font);
	   gc.fillText("Level: "+level, 2*Game.EDGE, 2*Game.EDGE);
	   
   }

   public void nextLevel(){
      level++;
   }

   public void bumpRight(){
	   right++;
   }

   public void bumpLeft(){
	   left++;
   }

}