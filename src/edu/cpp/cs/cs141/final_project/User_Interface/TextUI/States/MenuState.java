package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;

public class MenuState extends TextUIState{
	
	public MenuState(Application app) {
		super(app);
		
		key_1.setText("Save Game");
		key_1.setCommand(new MenuSaveCommand());
		
		key_2.setText("Load Game");
		key_2.setCommand(new MenuLoadCommand());
		
		key_3.setText("New Game");
		key_3.setCommand(new MenuNewCommand());
		
		key_4.setText("Quit Game");
		key_4.setCommand(new MenuQuitCommand());
		
		key_M.setText("Back");
		key_M.setCommand(new ToggleMoveCommand());
	}
}
