package server;

import java.awt.Rectangle;

public class PhysicsObject {
	
	private int x, y;
	private int w, h;
	private int vX, vY;
	
	public PhysicsObject(int x, int y, int w, int h, int vX, int vY){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.vX = vX;
		this.vY = vY;
	}
	
	public void update(){
		x += vX;
		y += vY;
	}
	
	public boolean collidesWith(PhysicsObject obj){
		Rectangle thisBounds = new Rectangle(x, y, w, h);
		Rectangle objBounds = new Rectangle(obj.x, obj.y, obj.w, obj.h);
		return thisBounds.intersects(objBounds);
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
	
	public int getvX(){
		return vX;
	}
	
	public void setvX(int vX){
		this.vX = vX;
	}
	
	public int getvY(){
		return vY;
	}
	
	public void setvY(int vY){
		this.vY = vY;
	}
	
}
