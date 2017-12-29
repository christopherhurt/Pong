package main;

import java.awt.Graphics2D;

public class OnlinePaddle extends GameObject {
	
	public static final int SPEED = 5;
	
	public OnlinePaddle(int x, int y, int w, int h, int vX, int vY, ID id, Handler handler) {
		super(x, y, w, h, vX, vY, id, handler);
	}
	
	public String getInputData(){
		boolean upDown = KeyInput.isKeyDown(KeyInput.UP);
		boolean downDown = KeyInput.isKeyDown(KeyInput.DOWN);
		boolean neitherDown = !upDown && !downDown;
		boolean bothDown = upDown && downDown;
		
		if(neitherDown || bothDown){
			return "0";
		}else if(upDown){
			return "1";
		}else{
			return "2";
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		g.fillRect(x, y, w, h);
	}
	
}
