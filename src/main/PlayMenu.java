package main;

public class PlayMenu extends Menu {
	
	private MenuButton splitScreen;
	private MenuButton online;
	private MenuButton back;
	
	public PlayMenu(){
		/* TODO
		 * splitscreen = 
		 * online = 
		 * back = 
		 */
		
		addButton(splitScreen);
		addButton(online);
		addButton(back);
	}
	
	@Override
	public void checkButtons() {
		if(splitScreen.isClicked()){
			// TODO: start game
		}else if(online.isClicked()){
			// TODO: connect to server
		}else if(back.isClicked()){
			Controller.setState(State.MAIN_MENU);
		}
	}

}
