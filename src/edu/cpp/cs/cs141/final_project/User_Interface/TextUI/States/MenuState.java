package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;

public class MenuState extends TextUIState{
	
	public MenuState(Application app) {
		super(app);
		
		key_1.setText("Save Game");
		key_1.setCommand(new MenuSaveCommand());
		key_1.setActive(true);
		
		key_2.setText("Load Game");
		key_2.setCommand(new MenuLoadCommand());
		key_2.setActive(true);
		
		key_3.setText("New Game");
		key_3.setCommand(new MenuNewCommand());
		key_3.setActive(true);
		
		key_4.setText("Quit Game");
		key_4.setCommand(new MenuQuitCommand());
		key_4.setActive(true);
		
		key_M.setText("Back");
		key_M.setCommand(new ToggleMoveCommand());
	}
	
	@Override
	public void update(boolean[] activeDirections) {
		keys.get(keys.indexOf(key_W)).setActive(false);
		keys.get(keys.indexOf(key_A)).setActive(false);
		keys.get(keys.indexOf(key_S)).setActive(false);
		keys.get(keys.indexOf(key_D)).setActive(false);
			
		keys.get(keys.indexOf(key_C)).setActive(false);
		keys.get(keys.indexOf(key_Z)).setActive(false);
	}
}
