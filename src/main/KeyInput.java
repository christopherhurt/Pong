package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	public static final int W = KeyEvent.VK_W;
	public static final int S = KeyEvent.VK_S;
	
	private static boolean upDown = false;
	private static boolean downDown = false;
	private static boolean wDown = false;
	private static boolean sDown = false;
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == UP){
			upDown = true;
		}else if(key == DOWN){
			downDown = true;
		}else if(key == W){
			wDown = true;
		}else if(key == S){
			sDown = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == UP){
			upDown = false;
		}else if(key == DOWN){
			downDown = false;
		}else if(key == W){
			wDown = false;
		}else if(key == S){
			sDown = false;
		}
	}
	
	public static boolean isKeyDown(int key){
		switch(key){
			case UP:
				return upDown;
			case DOWN:
				return downDown;
			case W:
				return wDown;
			case S:
				return sDown;
			default:
				System.err.println("Invalid key check request.");
				System.exit(-1);
				return false;
		}
	}
	
}
