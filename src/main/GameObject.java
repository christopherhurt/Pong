package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected final int w, h;
	protected int vX, vY;
	protected final ID id;
	protected final Handler handler;
	
	public GameObject(int x, int y, final int w, final int h, int vX, int vY, final ID id, final Handler handler){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.vX = vX;
		this.vY = vY;
		this.id = id;
		this.handler = handler;
		handler.add(this);
	}
	
	public void update(){
		x += vX;
		y += vY;
	}
	
	public abstract void render(Graphics2D g);
	
	public int getX() {
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

	public ID getID() {
		return id;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, w, h);
	}
	
}
