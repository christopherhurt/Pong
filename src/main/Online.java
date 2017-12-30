package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class Online {
	
	private static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 50;
	private static final int P1_PADDLE_INIT_X = 0;
	private static final int P2_PADDLE_INIT_X = Game.WIDTH - PADDLE_WIDTH;
	private static final int PADDLE_INIT_Y = (Game.HEIGHT - PADDLE_HEIGHT) / 2;
	
	public static final int BALL_SIZE = 25;
	private static final int BALL_INIT_X = (Game.WIDTH - BALL_SIZE) / 2;
	private static final int BALL_INIT_Y = (Game.HEIGHT - BALL_SIZE) / 2;
	private static final int BALL_SPEED_X = 3;
	
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
	
	private static final int PING_OFFSET = 5;
	private static final Font PING_FONT = new Font("arial", Font.PLAIN, 15);
	
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
	
	private static Menu waitingMenu = null;
	private static Handler handler = null;
	private static boolean matchStarted = false;
	private static int ping = 0;
	private static int p1Score = 0;
	private static int p2Score = 0;
	
	private static OnlineBall ball = null;
	private static OnlinePaddle player1 = null;
	private static OnlinePaddle player2 = null;
	
	public static void init(String ip){
		waitingMenu = new OnlineWaitingMenu();
		handler = new Handler();
		matchStarted = false;
		ping = 0;
		
		p1Score = 0;
		p2Score = 0;
		
		resetAfterScore();
		
		// TODO: open socket (default port if none given)
	}
	
	private static void resetAfterScore(){
		handler.clear();
		
		ball = new OnlineBall(BALL_INIT_X, BALL_INIT_Y, BALL_SIZE, BALL_SIZE, 0, 0, handler);
		player1 = new OnlinePaddle(P1_PADDLE_INIT_X, PADDLE_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 0, 0, ID.ONLINE_P1_PADDLE, handler);
		player2 = new OnlinePaddle(P2_PADDLE_INIT_X, PADDLE_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 0, 0, ID.ONLINE_P2_PADDLE, handler);
	}
	
	public static void update(){
		if(matchStarted){
			String data = retrievePacket();
			
			if(data.length() != 27){
				throw new IllegalStateException("Received data packet is incorrect size");
			}
			
			boolean disconnect = parseBool(data.substring(0, 1));
			int playerWin = parsePlayerWin(data.substring(1, 2));
			int p1Score = Integer.parseInt(data.substring(2, 3));
			int p2Score = Integer.parseInt(data.substring(3, 4));
			boolean paused = parseBool(data.substring(4, 5));
			boolean ballVelXChanged = parseBool(data.substring(5, 6));
			int ballVelX = parseBool(data.substring(6, 7)) ? -BALL_SPEED_X : BALL_SPEED_X;
			boolean ballVelYChanged = parseBool(data.substring(7, 8));
			int ballVelY = Integer.parseInt(data.substring(9, 10)) * (parseBool(data.substring(8, 9)) ? -1 : 1);
			boolean updateBallPos = parseBool(data.substring(10, 11));
			int ballPosX = Integer.parseInt(data.substring(11, 14));
			int ballPosY = Integer.parseInt(data.substring(14, 17));
			boolean p1VelChanged = !data.substring(17, 18).equals("0");
			int p1Vel = parsePlayerVel(data.substring(17, 18));
			boolean p2VelChanged = !data.substring(18, 19).equals("0");
			int p2Vel = parsePlayerVel(data.substring(18, 19));
			boolean updateP1Pos = parseBool(data.substring(19, 20));
			int p1PosY = Integer.parseInt(data.substring(20, 23));
			boolean updateP2Pos = parseBool(data.substring(23, 24));
			int p2PosY = Integer.parseInt(data.substring(24, 27));
			int yourID = Integer.parseInt(data.substring(27, 28));
			ping = Integer.parseInt(data.substring(28));
			
			if(disconnect){
				JOptionPane.showMessageDialog(null, "Disconnected from server.");
				Controller.setState(State.MAIN_MENU);
			}
			
			if(playerWin == 1){
				JOptionPane.showMessageDialog(null, "Player 1 Wins!");
				Controller.setState(State.MAIN_MENU);
			}else if(playerWin == 2){
				JOptionPane.showMessageDialog(null, "Player 2 Wins!");
				Controller.setState(State.MAIN_MENU);
			}
			
			if(p1Score > Online.p1Score || p2Score > Online.p2Score){
				Online.p1Score = p1Score;
				Online.p2Score = p2Score;
				resetAfterScore();
			}
			
			if(ballVelXChanged){
				ball.setvX(ballVelX);
			}
			
			if(ballVelYChanged){
				ball.setvY(ballVelY);
			}
			
			if(updateBallPos){
				ball.setX(ballPosX);
				ball.setY(ballPosY);
			}
			
			if(p1VelChanged){
				player1.setvY(p1Vel);
			}
			
			if(p2VelChanged){
				player2.setvY(p2Vel);
			}
			
			if(updateP1Pos){
				player1.setY(p1PosY);
			}
			
			if(updateP2Pos){
				player2.setY(p2PosY);
			}
			
			String inputData;
			
			if(paused){
				inputData = "0";
			}else{
				if(yourID == 0){
					inputData = player1.getInputData();
				}else if(yourID == 1){
					inputData = player2.getInputData();
				}else{
					throw new IllegalStateException("Tried to parse invalid paddle ID");
				}
			}
			
			String sendData = yourID + inputData + System.currentTimeMillis();
			sendPacket(sendData);
		}else{
			waitingMenu.update();
			// TODO: try to connect to server, wait for game start confirmation
		}
	}
	
	private static String retrievePacket(){
		return null; // TODO
		// Time out connection with server if waiting too long? (DO IT SOMEWHERE)
	}
	
	private static void sendPacket(String data){
		// TODO
		// Time out connection with server if waiting too long?
	}
	
	private static boolean parseBool(String bit){
		if(bit.equals("0")){
			return false;
		}else if(bit.equals("1")){
			return true;
		}else{
			throw new IllegalArgumentException("Tried to parse boolean from illegal String");
		}
	}
	
	private static int parsePlayerWin(String value){
		int number = Integer.parseInt(value);
		
		if(number < 0 || number > 2){
			throw new IllegalArgumentException("Tried to parse illegal player win value");
		}
		
		return number;
	}
	
	private static int parsePlayerVel(String value){
		if(value.equals("0") || value.equals("1")){
			return 0;
		}else if(value.equals("2")){
			return -OnlinePaddle.SPEED;
		}else if(value.equals("3")){
			return OnlinePaddle.SPEED;
		}else{
			throw new IllegalArgumentException("Tried to parse invalid player velocity");
		}
	}
	
	public static void render(Graphics2D g){
		if(matchStarted){
			drawCenterLine(g);
			drawPing(g);
			drawScore(p2Score, Game.WIDTH - SCORE_OFFSET_X - SCORE_WIDTH, SCORE_OFFSET_Y, SCORE_WIDTH / 3, g);
			drawScore(p1Score, SCORE_OFFSET_X, SCORE_OFFSET_Y, SCORE_WIDTH / 3, g);
			handler.render(g);
		}else{
			waitingMenu.render(g);
		}
	}
	
	private static void drawCenterLine(Graphics2D g){
		for(int i = 0; i < NUM_CENTER_LINE_SEGMENTS; i++){
			int y = i * CENTER_LINE_INCREMENT + CENTER_LINE_OFFSET;
			g.fillRect(CENTER_LINE_X, y, CENTER_LINE_WIDTH, CENTER_LINE_SEGMENT_HEIGHT);
		}
	}
	
	private static void drawPing(Graphics2D g){
		if(ping >= 200){
			g.setColor(Color.RED);
		}else if(ping >= 100){
			g.setColor(Color.YELLOW);
		}else{
			g.setColor(Color.GREEN);
		}
		
		g.setFont(PING_FONT);
		g.drawString(ping + "", PING_OFFSET, Game.HEIGHT - PING_OFFSET);
		g.setFont(Game.MENU_FONT);
		g.setColor(Game.GAME_COLOR);
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
