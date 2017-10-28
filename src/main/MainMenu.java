package main;

public class MainMenu extends Menu {
	
	private MenuLabel title;
	private MenuButton play;
	private MenuButton about;
	private MenuButton quit;
	
	public MainMenu(){
		// TODO: update intializations
		title = new MenuLabel("Pong", 0, 100);
		play = new MenuButton("Play", 0, 200, 50, 100, 200, 50);
		about = new MenuButton("About", 100, 300, 100, 300, 200, 50);
		quit = new MenuButton("Quit", 100, 400, 100, 250, 200, 50);
		
		addLabel(title);
		addButton(play);
		addButton(about);
		addButton(quit);
	}

	@Override
	public void checkButtons() {
		if(play.isClicked()){
			Controller.setState(State.PLAY_MENU);
		}else if(about.isClicked()){
			Controller.setState(State.ABOUT_MENU);
		}else if(quit.isClicked()){
			System.exit(0);
		}
	}

}
