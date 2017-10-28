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
	
	public boolean checkCollision(GameObject collider1, ID collider2){
	    for(GameObject obj : list){
            if(collider2 == obj.getId() && collider1.getBounds().intersects(obj.getBounds())){
                return true;
            }
	    }
	    
	    return false;
	}
	
}
