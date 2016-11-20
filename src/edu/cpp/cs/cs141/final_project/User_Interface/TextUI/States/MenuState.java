package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.LookCommand;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class MenuState extends TextUIState{
	
	public MenuState(Application app) {
		
		keys.add(new Key('1', "W - Move UP", new LookCommand(app, 1, 0)));
		keys.add(new Key('2', "A - Move LEFT", new LookCommand(app, 0, -1)));
		keys.add(new Key('3', "S - Move DOWN", new LookCommand(app, -1, 0)));
	}
}
