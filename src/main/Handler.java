package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Handler {
	
	private List<GameObject> list;
	
	public Handler(){
		list = new ArrayList<>(); // TODO: make copyonwrite?
	}
	
	public void add(GameObject obj){
		list.add(obj);
	}
	
	public void remove(GameObject obj){
		list.remove(obj);
	}
	
	public void clear(){
		list.clear();
	}
	
	public void update(){
		for(GameObject obj : list){
			obj.update();
		}
	}
	
	public void render(Graphics2D g){
		for(GameObject obj : list){
			obj.render(g);
		}
	}
	
	public GameObject getGameObject(ID id){
		for(GameObject obj : list){
			if(id == obj.getID()){
				return obj;
			}
		}
		return null;
	}
	
	public boolean checkCollision(GameObject collider1, GameObject collider2){
		return collider1.getBounds().intersects(collider2.getBounds());
	}
	
}
