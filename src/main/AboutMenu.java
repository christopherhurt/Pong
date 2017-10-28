package main;

public class AboutMenu extends Menu {
	
	private MenuLabel header;
	private MenuLabel info;
	private MenuButton back;
	
	public AboutMenu(){
		/* TODO
		 * header = 
		 * info = 
		 * back = 
		 */
		
		addLabel(header);
		addLabel(info);
		addButton(back);
	}
	
	@Override
	public void checkButtons() {
		if(back.isClicked()){
			Controller.setState(State.MAIN_MENU);
		}
	}

}
