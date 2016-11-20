package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.LookCommand;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class MenuState extends TextUIState{
	
	public MenuState(Application app) {
		super(app);
		key_1.setText("1 - Save Game");
		//key_1.setCommand(command);
		
		key_2.setText("2 - Load Game");
		key_3.setText("3 - New Game");
		key_4.setText("4 - Quit Game");
	}
}
