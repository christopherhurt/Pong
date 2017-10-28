package main;

import java.awt.Graphics2D;

public class Ball extends GameObject implements Collider {

	public Ball(int x, int y, final int w, final int h, float vX, float vY) {
		super(x, y, w, h, vX, vY, ID.BALL);
	}
	
	@Override
	public void collision(){
		// TODO: Check for wall and paddle collisions
	}
	
	@Override
	public void update(){
		super.update();
		collision();
	}
	
	@Override
	public void render(Graphics2D g){
		g.fillOval(x, y, w, h);
	}
		
}
