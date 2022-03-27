import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class Game extends Application {
	final String appName = "Pong";
	final int FPS = 30; // frames per second
	final static int WIDTH = 600;
	final static int HEIGHT = 500;
	final static int EDGE = 30;
	Image space = new Image( "space2.jpg" );

	Ball ball1;
	Ball ball2;
	Ball ball3;
	Ball ball4;
	Paddle pleft, pright;
	ScoreBoard score;
	Character bh;
	Ball balllist[] = new Ball[4];
	/**
	 * Set up initial data structures/values
	 */
	void initialize()
	{
		balllist[0] = ball1 = new Ball();
		balllist[1] = ball2 = new Ball();
		balllist[2] = ball3 = new Ball();
		balllist[3] = ball4 = new Ball();
		pleft = new Paddle(EDGE);
		pright = new Paddle(WIDTH - EDGE);
		score = new ScoreBoard();
		bh = new Character(FPS);
	}
	
	void setHandlers(Scene scene)
	{
		scene.setOnKeyPressed(
			e -> {
				KeyCode c = e.getCode();
				switch (c) {
					case UP: bh.setUpKey(true);
								break;
					case LEFT: bh.setLeftKey(true);
								break;
					case DOWN: bh.setDownKey(true);
								break;
					case RIGHT: bh.setRightKey(true);
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
						case UP: bh.setUpKey(false);
									break;
						case LEFT: bh.setLeftKey(false);
									break;
						case DOWN: bh.setDownKey(false);
									break;
						case RIGHT: bh.setRightKey(false);
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

		for(int i = 0; i < balllist.length; i++){
			balllist[i].move();
			balllist[i].checkHit(pleft);
			balllist[i].checkHit(pright);
			if(balllist[i].checkBH(bh)){
				score.bumpLeft();
				bh.setRadius(bh.getRadius()+2);
			}
		}
		pleft.move();
		pright.move();
		bh.move();
		checkScore();
		
	
	}
	
	void checkScore() {
		boolean scored = false;

		for(int i = 0; i < balllist.length; i++){
			if (balllist[i].getX() < EDGE) {
				score.bumpRight();
				scored = true;
				bh.setRadius(bh.getRadius() + 2);
			}
			if (balllist[i].getX() > WIDTH - EDGE) {
				score.bumpLeft();
				scored = true;
				bh.setRadius(bh.getRadius() + 2);
			}
			if (scored)
				balllist[i].reset();
		
		}
		

			//
	}

	/**
	 *  Draw the game world
	 */
	void render(GraphicsContext gc) {
		
		gc.fillRect(0, 0, WIDTH, HEIGHT);

		gc.drawImage(space, 0, 0, 600, 500);
	


		// draw game objects
		pleft.render(gc);
		pright.render(gc);
		score.render(gc);
		for(int i = 0; i < balllist.length; i++){
			balllist[i].render(gc);
		}
		bh.render(gc);
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
