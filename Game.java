package assign2;
import assign2.Ball;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

		final String appName = "Emoji Game";
		final int FPS = 30; // frames per second
		final static int WIDTH = 600;
		final static int HEIGHT = 500;
		final static int EDGE = 30;
		Font font = Font.font("SansSerif", FontWeight.BOLD, 50);
		
		Paddle paddle;
		Ball ball;
		Emojis emoji1,emoji2,emoji3,emoji4;
		String win = "";
		
		//This is semi-inspired by Brickbreaker, User uses paddle to 
		//guide ball to hit "angry Emojis" and turn them into "heart" emojis.
		//Once all four emojis are turned, the user wins

		
		void initialize()
		{
			paddle = new Paddle(WIDTH/2);
			ball = new Ball();
			emoji1 = new Emojis(10,70);
			emoji2 = new Emojis(180, 70);
			emoji3 = new Emojis(350, 70);
			emoji4 = new Emojis(490, 70);

		}
		
		void setHandlers(Scene scene)
		{
			scene.setOnKeyPressed(
					e -> {
						KeyCode c = e.getCode();
						switch (c) {
							case LEFT: paddle.setUpKey(true);
										break;
							case RIGHT: paddle.setDownKey(true);
										break;
							
							default:
										break;
						}
					}
				);
				
				scene.setOnKeyReleased(
						e -> {
							KeyCode c = e.getCode();
							switch (c) {
								case LEFT: paddle.setUpKey(false);
											break;
								case RIGHT: paddle.setDownKey(false);
											break;
								default:
											break;
							}
						}
					);
		}

		/**
		 *  Update variables for one time step
		 */
		public void update()
		{
			paddle.move();
			ball.move();
			ball.checkHit(paddle);
			
			//if emoji hit, change to other emoji
			
			if(ball.checkEm(emoji1)) {
				emoji1.changeEmoji(ball,emoji1);
	

			}
			else if(ball.checkEm(emoji2)) {
				emoji2.changeEmoji(ball,emoji2);
	

			}
			else if(ball.checkEm(emoji3)) {
				emoji3.changeEmoji(ball,emoji3);
	

			}
			else if(ball.checkEm(emoji4)) {
				emoji4.changeEmoji(ball,emoji4);


			}
			
			//if all emojis are hit, game won
			
			if(emoji1.current== emoji1.heartemoji && emoji2.current== emoji2.heartemoji && emoji3.current== 
					emoji3.heartemoji && emoji4.current== emoji4.heartemoji) {
				win="WIN!";
				ball.reset();
			}
			


		}
		
		void render(GraphicsContext gc) {
			gc.setFill(Color.DARKGREY);
			gc.fillRect(0, 0, WIDTH, HEIGHT);
			
			gc.setFont(font);
			gc.setFill(Color.BLACK);
			gc.fillText(win, WIDTH/2, HEIGHT/2);
			
			paddle.render(gc);
			ball.render(gc);
			emoji1.render(gc);
			emoji2.render(gc);	
			emoji3.render(gc);	
			emoji4.render(gc);	
		}

		/*
		 * Begin boiler-plate code...
		 * [Animation and events with initialization]
		 */
		public static void main(String[] args) {
			launch(args);
		}
		
		@Override
		public void start(Stage theStage) {
			theStage.setTitle(appName);

			Group root = new Group();
			Scene theScene = new Scene(root);
			theStage.setScene(theScene);

			Canvas canvas = new Canvas(WIDTH, HEIGHT);
			root.getChildren().add(canvas);

			GraphicsContext gc = canvas.getGraphicsContext2D();

			// Initial setup
			initialize();
			setHandlers(theScene);
			
			// Setup and start animation loop (Timeline)
			KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
					e -> {
						// update position
						update();
						// draw frame
						render(gc);
					}
				);
			Timeline mainLoop = new Timeline(kf);
			mainLoop.setCycleCount(Animation.INDEFINITE);
			mainLoop.play();

			theStage.show();
		}
		/*
		 * ... End boiler-plate code
		 */
	}

