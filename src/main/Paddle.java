package main;

import java.awt.Graphics2D;

public class Paddle extends GameObject implements Collider, InputResponder {
	
	private static final int SPEED = 5;
	
	private final int MIN_Y, MAX_Y;
	private final int UP_KEY, DOWN_KEY;
	
	public Paddle(int x, int y, final int w, final int h, int vX, int vY, final ID id, Handler handler) {
		super(x, y, w, h, vX, vY, id, handler);
		
		if(id != ID.P1_PADDLE && id != ID.P2_PADDLE){
			System.err.println("Invalid paddle ID.");
			System.exit(-1);
		}
		
		MIN_Y = 0;
		MAX_Y = Game.HEIGHT - h;
		
		UP_KEY = id == ID.P1_PADDLE ? KeyInput.UP : KeyInput.W;
		DOWN_KEY = id == ID.P1_PADDLE ? KeyInput.DOWN : KeyInput.S;
	}
	
	@Override
	public void collision() {
		if(y < MIN_Y){
			y = MIN_Y;
		}else if(y > MAX_Y){
			y = MAX_Y;
		}
	}
	
	@Override
	public void inputResponse(){
		boolean upDown = KeyInput.isKeyDown(UP_KEY);
		boolean downDown = KeyInput.isKeyDown(DOWN_KEY);
		boolean neitherDown = !upDown && !downDown;
		boolean bothDown = upDown && downDown;
		
		if(neitherDown || bothDown){
			vY = 0;
		}else if(upDown){
			vY = -SPEED;
		}else{
			vY = SPEED;
		}
	}
	
	@Override
	public void update(){
		inputResponse();
		super.update();
		collision();
	}
	
	@Override
	public void render(Graphics2D g){
		g.fillRect(x, y, w, h);
	}
	
}
