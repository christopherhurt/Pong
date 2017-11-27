package main;

import java.awt.Graphics2D;

public class Controller {
	
	private static State state = Game.INIT_STATE;
	
	private static Menu mainMenu = null;
	private static Menu playMenu = null;
	private static Menu aboutMenu = null;
	
	private static Handler splitScreenHandler = null;
	
	public static void init(){
		mainMenu = new MainMenu();
		playMenu = new PlayMenu();
		aboutMenu = new AboutMenu();
		
		splitScreenHandler = new Handler();
		
		SplitScreen.init();
	}
	
	public static void update(){
		switch(state){
			case MAIN_MENU:
				mainMenu.update();
				break;
			case PLAY_MENU:
				playMenu.update();
				break;
			case ABOUT_MENU:
				aboutMenu.update();
				break;
			case SPLIT_SCREEN:
				SplitScreen.update();
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
			case PLAY_MENU:
				playMenu.render(g);
				break;
			case ABOUT_MENU:
				aboutMenu.render(g);
				break;
			case SPLIT_SCREEN:
				SplitScreen.render(g);
				break;
			default:
				System.err.println("Invalid game state.");
				System.exit(-1);
		}
	}
	
	public static void setState(State state){
		Controller.state = state;
	}
	
	public static Handler getSplitScreenHandler(){
		return splitScreenHandler;
	}
	
}
