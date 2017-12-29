package main;

import javax.swing.JOptionPane;

public class MainMenu extends Menu {
	
	public static final int BUTTON_WIDTH = (int)(Game.WIDTH / 1.2f);
	public static final int BUTTON_HEIGHT = Game.HEIGHT / 6;
	public static final int BUTTON_X = (Game.WIDTH - BUTTON_WIDTH) / 2;
	
	private static final int BUTTON_Y_OFFSET = (int)(Game.HEIGHT / 3.75f);
	private static final int BUTTON_SPACING = Game.HEIGHT / 14;
	
	private MenuLabel title;
	private MenuButton splitScreen;
	private MenuButton online;
	private MenuButton quit;
	
	public MainMenu(){
		title = new MenuLabel("Pong", Game.WIDTH / 2 - 62, (int)(Game.HEIGHT / 5.6f));
		splitScreen = new MenuButton("Split Screen", Game.WIDTH / 2 - 147, (int)(Game.HEIGHT / 2.55f), BUTTON_X, BUTTON_Y_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT);
		online = new MenuButton("Online", Game.WIDTH / 2 - 80, (int)(Game.HEIGHT / 1.59f), BUTTON_X, BUTTON_Y_OFFSET + (BUTTON_HEIGHT + BUTTON_SPACING), BUTTON_WIDTH, BUTTON_HEIGHT);
		quit = new MenuButton("Quit", Game.WIDTH / 2 - 50,(int)(Game.HEIGHT / 1.15f), BUTTON_X, BUTTON_Y_OFFSET + 2 * (BUTTON_HEIGHT + BUTTON_SPACING), BUTTON_WIDTH, BUTTON_HEIGHT);
		
		addLabel(title);
		addButton(splitScreen);
		addButton(online);
		addButton(quit);
	}

	@Override
	public void checkButtons() {
		if(splitScreen.isClicked()){
			SplitScreen.init();
			Controller.setState(State.SPLIT_SCREEN);
		}else if(online.isClicked()){
			String ip = JOptionPane.showInputDialog("Enter server IP:");
			Online.init(ip);
			Controller.setState(State.ONLINE);
		}else if(quit.isClicked()){
			System.exit(0);
		}
	}

}
