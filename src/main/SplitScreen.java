package main;

import java.awt.Graphics2D;

public class SplitScreen {
	
	private static final int PAUSE_TIME_IN_SECONDS = 3 * Game.FPS;
	
	public static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 50;
	private static final int P1_PADDLE_INIT_X = 0;
	private static final int P2_PADDLE_INIT_X = Game.WIDTH - PADDLE_WIDTH;
	private static final int PADDLE_INIT_Y = (Game.HEIGHT - PADDLE_HEIGHT) / 2;
	
	public static final int BALL_SIZE = 25;
	private static final int BALL_INIT_X = (Game.WIDTH - BALL_SIZE) / 2;
	private static final int BALL_INIT_Y = (Game.HEIGHT - BALL_SIZE) / 2;
	private static final int MAX_INIT_SPEED = 5;
	
	private static final int CENTER_LINE_WIDTH = 6;
	private static final int CENTER_LINE_SEGMENT_HEIGHT = 60;
	private static final int NUM_CENTER_LINE_SEGMENTS = 5;
	private static final int CENTER_LINE_SPACING = 25;
	private static final int CENTER_LINE_OFFSET = 0;
	private static final int SCORE_WIDTH = 36;
	private static final int SCORE_OFFSET_X = 75;
	private static final int SCORE_OFFSET_Y = 50;
	
	private static final int CENTER_LINE_X = (Game.WIDTH - CENTER_LINE_WIDTH) / 2;
	private static final int CENTER_LINE_INCREMENT = CENTER_LINE_SEGMENT_HEIGHT + CENTER_LINE_SPACING;
	private static final int NUM_SCORE_BLOCKS_X = 3;
	private static final int NUM_SCORE_BLOCKS_Y = 5;
	private static final int SCORE_BLOCK_SIZE = SCORE_WIDTH / NUM_SCORE_BLOCKS_X;
	
	private static final int WINNING_SCORE = 10;
	
	private static final short SCORE_0 = 0b0111101101101111;
	private static final short SCORE_1 = 0b0111010010010011;
	private static final short SCORE_2 = 0b0111001111100111;
	private static final short SCORE_3 = 0b0111100111100111;
	private static final short SCORE_4 = 0b0100100111101101;
	private static final short SCORE_5 = 0b0111100111001111;
	private static final short SCORE_6 = 0b0111101111001111;
	private static final short SCORE_7 = 0b0100100100100111;
	private static final short SCORE_8 = 0b0111101111101111;
	private static final short SCORE_9 = 0b0111100111101111;
	
	private static Handler handler = Controller.getSplitScreenHandler();
	private static Ball ball = null;
	private static int p1Score = 0;
	private static int p2Score = 0;
	private static boolean paused = true;
	private static int pauseTimer = PAUSE_TIME_IN_SECONDS;
	
	@SuppressWarnings("all")
	public static void init(){ // TODO: add a place for this to be called (when play button pressed)
		if(SCORE_WIDTH % 3 != 0 || NUM_SCORE_BLOCKS_X != 3 || NUM_SCORE_BLOCKS_Y != 5){
			System.err.println("Invalid scoreboard constants.");
			System.exit(-1);
		}
		
		p1Score = 0;
		p2Score = 0;
		
		resetAfterScore();
	}
	
	private static void initObjects(){
		ball = new Ball(BALL_INIT_X, BALL_INIT_Y, BALL_SIZE, BALL_SIZE, genInitBallVelocity(), genInitBallVelocity(), handler);
		new Paddle(P2_PADDLE_INIT_X, PADDLE_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 0, 0, ID.P1_PADDLE, handler);
		new Paddle(P1_PADDLE_INIT_X, PADDLE_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 0, 0, ID.P2_PADDLE, handler);
	}
	
	private static int genInitBallVelocity(){
		int vel = (int)(Math.random() * 2 * MAX_INIT_SPEED) - MAX_INIT_SPEED;
		
		if(vel == 0){
			vel = MAX_INIT_SPEED;
		}
		
		return vel;
	}
	
	private static void resetAfterScore(){
		handler.clear();
		pause();
		initObjects();
	}
	
	private static void pause(){
		paused = true;
		pauseTimer = PAUSE_TIME_IN_SECONDS;
	}
	
	public static void update(){
		if(paused){
			pauseTimer--;
			
			if(pauseTimer <= 0){
				paused = false;
			}
		}else{
			handler.update();
			
			if(ball.isOffscreenLeft()){
				p1Score++;
				resetAfterScore();
			}else if(ball.isOffscreenRight()){
				p2Score++;
				resetAfterScore();
			}
			
			// TODO: Do a check if score hits its maximum
		}
		// TODO: update score using ball location, do if at least (or more than?) half of ball off-screen?
		// Be sure to stay synced with time!
		
		
	}
	
	public static void render(Graphics2D g){
		drawCenterLine(g);
		drawScore(p1Score, Game.WIDTH - SCORE_OFFSET_X - SCORE_WIDTH, SCORE_OFFSET_Y, SCORE_WIDTH / 3, g);
		drawScore(p2Score, SCORE_OFFSET_X, SCORE_OFFSET_Y, SCORE_WIDTH / 3, g);
		handler.render(g);
	}
	
	private static void drawCenterLine(Graphics2D g){
		for(int i = 0; i < NUM_CENTER_LINE_SEGMENTS; i++){
			int y = i * CENTER_LINE_INCREMENT + CENTER_LINE_OFFSET;
			g.fillRect(CENTER_LINE_X, y, CENTER_LINE_WIDTH, CENTER_LINE_SEGMENT_HEIGHT);
		}
	}
	
	private static void drawScore(int score, int x, int y, int blockSize, Graphics2D g){
		int blocksCode;
		
		switch(score){
			case 0:
				blocksCode = SCORE_0;
				break;
			case 1:
				blocksCode = SCORE_1;
				break;
			case 2:
				blocksCode = SCORE_2;
				break;
			case 3:
				blocksCode = SCORE_3;
				break;
			case 4:
				blocksCode = SCORE_4;
				break;
			case 5:
				blocksCode = SCORE_5;
				break;
			case 6:
				blocksCode = SCORE_6;
				break;
			case 7:
				blocksCode = SCORE_7;
				break;
			case 8:
				blocksCode = SCORE_8;
				break;
			case 9:
				blocksCode = SCORE_9;
				break;
			default:
				System.err.println("Invalid player score.");
				System.exit(-1);
				blocksCode = -1;
		}
		
		for(int i = 0; i < NUM_SCORE_BLOCKS_Y; i++){
			for(int j = 0; j < NUM_SCORE_BLOCKS_X; j++){
				if((blocksCode >> (i * NUM_SCORE_BLOCKS_X + j)) % 2 == 1){
					g.fillRect(x + j * SCORE_BLOCK_SIZE, y + i * SCORE_BLOCK_SIZE, SCORE_BLOCK_SIZE, SCORE_BLOCK_SIZE);
				}
			}
		}
	}
	
}
