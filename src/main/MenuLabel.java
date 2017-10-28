package main;

import java.awt.Graphics2D;

public class MenuLabel {
	
	public static final int LABEL_X_OFFSET = 0; // TODO: update these
	public static final int LABEL_Y_OFFSET = 0;
	
	private String label;
	private int x;
	private int y;
	
	public MenuLabel(String label, int x, int y){
		this.label = label;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics2D g) {
		g.drawString(label, x + LABEL_X_OFFSET, y + LABEL_Y_OFFSET);
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
