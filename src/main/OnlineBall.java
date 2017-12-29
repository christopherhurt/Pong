package main;

import java.awt.Graphics2D;

public class OnlineBall extends GameObject {
	
	public OnlineBall(int x, int y, int w, int h, int vX, int vY, Handler handler) {
		super(x, y, w, h, vX, vY, ID.ONLINE_BALL, handler);
	}
	
	@Override
	public void render(Graphics2D g) {
		g.fillOval(x, y, w, h);
	}
	
}
