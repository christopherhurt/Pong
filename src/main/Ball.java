package main;

import java.awt.Graphics2D;

public class Ball extends GameObject implements Collider {
	
	public Ball(int x, int y, final int w, final int h, int vX, int vY, Handler handler) {
		super(x, y, w, h, vX, vY, ID.BALL, handler);
	}
	
	// TODO: Fix paddle collision, add more bounding boxes to paddles and change speed on contact
	@Override
	public void collision(){
		GameObject p1Paddle = handler.getGameObject(ID.P1_PADDLE);
		GameObject p2Paddle = handler.getGameObject(ID.P2_PADDLE);
		
		if(handler.checkCollision(this, p1Paddle)){
			x = Game.WIDTH - SplitScreen.PADDLE_WIDTH - SplitScreen.BALL_SIZE;
			vX *= -1;
		}else if(handler.checkCollision(this, p2Paddle)){
			x = SplitScreen.PADDLE_WIDTH;
			vX *= -1;
		}
		
		if(y < 0){
			y = 0;
			vY *= -1;
		}else if(y > Game.HEIGHT - h){
			y = Game.HEIGHT - h;
			vY *= -1;
		}
	}
	
	public boolean isOffscreenLeft(){
		return x <= -SplitScreen.BALL_SIZE;
	}
	
	public boolean isOffscreenRight(){
		return x >= Game.WIDTH;
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
