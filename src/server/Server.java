package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import main.Game;

public class Server {
    
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 50;
    private static final int P1_PADDLE_INIT_X = 0;
    private static final int P2_PADDLE_INIT_X = Game.WIDTH - PADDLE_WIDTH;
    private static final int PADDLE_INIT_Y = (Game.HEIGHT - PADDLE_HEIGHT) / 2;
    
    public static final int BALL_SIZE = 25;
    private static final int BALL_INIT_X = (Game.WIDTH - BALL_SIZE) / 2;
    private static final int BALL_INIT_Y = (Game.HEIGHT - BALL_SIZE) / 2;
    private static final int BALL_SPEED_X = 3;
    private static final int MAX_BALL_SPEED_Y = 5;
    
    private static final short PORT = 6969;
    
    private static DatagramSocket socket = null;
    
    private static List<Client> clients = null;
    
	private static PhysicsObject ball = null;
	private static PhysicsObject player1 = null;
	private static PhysicsObject player2 = null;
	
	private static boolean gameStarted = false;
	
	private static int p1Score = 0;
	private static int p2Score = 0;
	
	private static void init(){
		try{
            socket = new DatagramSocket(PORT);
        }catch(SocketException e){
            e.printStackTrace();
        }
		
		clients = new ArrayList<>();
		
		resetLevel();
		receivePackets();
		startLoop();
	}
	
	private static void startLoop(){
	    while(true){
	        // TODO: check if game started, if not, send 0 to clients, send 1 when started
	        // TODO: Update physics objects, check for win (restart level on win), pause for a bit on start/win!!!!!
	        // TODO: send packets to clients
	    }
	}
	
	private static void resetLevel(){
	    p1Score = 0;
	    p2Score = 0;
	    gameStarted = false;
	    
	    resetObjects();
	}
	
	private static void resetObjects(){
        ball = new PhysicsObject(BALL_INIT_X, BALL_INIT_Y, BALL_SIZE, BALL_SIZE, 0, 0);
        player1 = new PhysicsObject(P1_PADDLE_INIT_X, PADDLE_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 0, 0);
        player2 = new PhysicsObject(P2_PADDLE_INIT_X, PADDLE_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT, 0, 0);
	}
	
	private static void receivePackets(){
	    new Thread("receive"){
	        @Override
	        public void run(){
	            byte[] data = new byte[21];
	            DatagramPacket packet = new DatagramPacket(data, data.length);
	            
	            try{
                    socket.receive(packet);
                }catch(IOException e){
                    e.printStackTrace();
                }
	            
	            byte[] receivedData = packet.getData();
	            String extractedData = new String(receivedData, 0, receivedData.length);
	            parseData(extractedData);
	        }
	    }.start();
    }
	
	private static void parseData(String data){
	    // TODO: connection packets, ping packets (waiting for game to start, same as connection?), game/input/time packets (watch for concurrency issues)
	}
	
	private static void update(){
	    // TODO: check collisions, respond, check for timeouts
	}
	
	public static void main(String args[]){
		init();
	}
	
}
