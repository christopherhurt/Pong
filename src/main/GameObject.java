package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected final int w, h;
	protected float vX, vY;
	protected final ID id;
	
	public GameObject(int x, int y, final int w, final int h, float vX, float vY, final ID id){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.vX = vX;
		this.vY = vY;
		this.id = id;
	}
	
	public void update(){
		x += vX;
		y += vY;
	}
	
	public abstract void render(Graphics2D g);
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public float getvX() {
		return vX;
	}

	public void setvX(float vX) {
		this.vX = vX;
	}

	public float getvY() {
		return vY;
	}

	public void setvY(float vY) {
		this.vY = vY;
	}

	public ID getId() {
		return id;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, w, h);
	}
	
}
