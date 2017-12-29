package main;

import java.awt.Graphics2D;

public class MenuLabel {
	
	private String label;
	private int x;
	private int y;
	
	public MenuLabel(String label, int x, int y){
		this.label = label;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics2D g) {
		g.drawString(label, x, y);
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
