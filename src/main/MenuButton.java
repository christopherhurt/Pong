package main;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

public class MenuButton extends MenuLabel {
	
	private static final Stroke BOX_THICKNESS = new BasicStroke(3);
	
	private int boxX, boxY;
	private int boxWidth, boxHeight;
	private Rectangle boxBounds;
	
	public MenuButton(String label, int x, int y, int boxX, int boxY, int boxWidth, int boxHeight) {
		super(label, x, y);
		this.boxX = boxX;
		this.boxY = boxY;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		boxBounds = new Rectangle(boxX, boxY, boxWidth, boxHeight);
	}
	
	@Override
	public void render(Graphics2D g){
		super.render(g);
		g.setStroke(BOX_THICKNESS);
		g.drawRect(boxX, boxY, boxWidth, boxHeight);
	}
	
	public boolean isClicked(){
		return MouseInput.getLeftClicked() && Game.isMouseInside(boxBounds);
	}
	
	public int getBoxX(){
		return boxX;
	}
	
	public int getBoxY(){
		return boxY;
	}
	
	public int getBoxWidth(){
		return boxWidth;
	}
	
	public int getBoxHeight(){
		return boxHeight;
	}
	
}
