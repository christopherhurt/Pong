package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private static boolean leftClicked = false;
	
	@Override
	public void mousePressed(MouseEvent e){
		leftClicked = true;
	}
	
	public static void reset(){
		leftClicked = false;
	}
	
	public static boolean getLeftClicked(){
		return leftClicked;
	}
	
}
