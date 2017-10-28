package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
	
	private List<MenuLabel> labels;
	private List<MenuButton> buttons;
	
	public Menu(){
		labels = new ArrayList<>(); // TODO: copyonwrite?
		buttons = new ArrayList<>();
	}
	
	public void addLabel(MenuLabel label){
		labels.add(label);
	}
	
	public void addButton(MenuButton button){
		buttons.add(button);
	}
	
	public void update(){
		checkButtons();
	}
	
	public void render(Graphics2D g){
		for(MenuLabel label : labels){
			label.render(g);
		}
		for(MenuButton button : buttons){
			button.render(g);
		}
	}
	
	public abstract void checkButtons();
	
}
