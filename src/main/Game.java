package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game {
	
	public static final int WIDTH = 400, HEIGHT = 400;
	public static final String TITLE = "Pong";
	public static final String WINDOW_ICON_FILE = "res/icon.png";
	public static final int FPS = 60;
	public static final Color GAME_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	public static final Font MENU_FONT = new Font("arial", Font.PLAIN, 54);
	public static final State INIT_STATE = State.MAIN_MENU;
	
	private static final double TIME_BETWEEN_RENDERS = 1.0 / FPS;
	
	private static Canvas canvas = null;
	
	private static void init(){
		canvas = new Canvas();
		canvas.addKeyListener(new KeyInput());
		canvas.addMouseListener(new MouseInput());
		Controller.init();
		createWindow(canvas);
		start();
	}
	
	private static void update(){
		Controller.update();
		MouseInput.reset();
	}
	
	private static void render(){
		BufferStrategy bs = canvas.getBufferStrategy();
		if(bs == null){
			final int BUFFER_COUNT = 3;
			canvas.createBufferStrategy(BUFFER_COUNT);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(GAME_COLOR);
		g.setFont(MENU_FONT);
		Controller.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private static void start(){
		if(canvas == null){
			System.err.println("Program not initialized properly.");
			System.exit(-1);
		}
		
		canvas.requestFocus();
		
		new Thread("Game"){
			@Override
			public void run(){
				long lastTime;
				long currTime = System.nanoTime();
				double delta = 0;
				
				while(true){
					lastTime = currTime;
					currTime = System.nanoTime();
					
					delta += (currTime - lastTime) / 1000000000.0;
					
					if(delta >= TIME_BETWEEN_RENDERS){
						update();
						render();
						delta -= TIME_BETWEEN_RENDERS;
					}
				}
			}
		}.start();
	}
	
	private static JFrame createWindow(Canvas canvas){
		JFrame frame = new JFrame(TITLE);
		frame.setIconImage((new ImageIcon(WINDOW_ICON_FILE)).getImage());
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension canvasSize = new Dimension(WIDTH, HEIGHT);
		canvas.setMinimumSize(canvasSize);
		canvas.setMaximumSize(canvasSize);
		canvas.setPreferredSize(canvasSize);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		
		return frame;
	}
	
	public static boolean isMouseInside(Rectangle bounds){
		Point loc = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(loc, canvas);
		int locX = (int)loc.getX();
		int locY = (int)loc.getY();
		int boundsX = (int)bounds.getX();
		int boundsY = (int)bounds.getY();
		boolean xInside = locX > boundsX && locX < boundsX + (int)bounds.getWidth();
		boolean yInside = locY > boundsY && locY < boundsY + (int)bounds.getHeight();
		return xInside && yInside;
	}
	
	public static void main(String[] args){
		init();
	}
	
}
