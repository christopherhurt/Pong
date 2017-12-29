package main;

public class OnlineWaitingMenu extends Menu {
	
	private MenuLabel waitingMessage1;
	private MenuLabel waitingMessage2;
	private MenuButton back;
	
	public OnlineWaitingMenu(){
		waitingMessage1 = new MenuLabel("Searching for", Game.WIDTH / 2 - 165, Game.HEIGHT / 2 - 30);
		waitingMessage2 = new MenuLabel("opponent...", Game.WIDTH / 2 - 135, Game.HEIGHT / 2 + 30);
		back = new MenuButton("Back", Game.WIDTH / 2 - 62, Game.HEIGHT - 33, MainMenu.BUTTON_X, Game.HEIGHT - MainMenu.BUTTON_HEIGHT - 20, MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT);
		
		addLabel(waitingMessage1);
		addLabel(waitingMessage2);
		addButton(back);
	}
	
	@Override
	public void checkButtons() {
		if(back.isClicked()){
			Controller.setState(State.MAIN_MENU);
		}
	}
	
}
