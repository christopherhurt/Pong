package main;

import java.awt.Graphics2D;

public class Controller {
	
	private static State state = Game.INIT_STATE;
	
	private static Menu mainMenu = null;
	
	public static void init(){
		mainMenu = new MainMenu();
		
		SplitScreen.init();
	}
	
	public static void update(){
		switch(state){
			case MAIN_MENU:
				mainMenu.update();
				break;
			case SPLIT_SCREEN:
				SplitScreen.update();
				break;
			case ONLINE:
				Online.update();
				break;
			default:
				System.err.println("Invalid game state.");
				System.exit(-1);
		}
	}
	
	public static void render(Graphics2D g){
		switch(state){
			case MAIN_MENU:
				mainMenu.render(g);
				break;
			case SPLIT_SCREEN:
				SplitScreen.render(g);
				break;
			case ONLINE:
				Online.render(g);
				break;
			default:
				System.err.println("Invalid game state.");
				System.exit(-1);
		}
	}
	
	public static void setState(State state){
		Controller.state = state;
	}
	
}
