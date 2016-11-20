package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;


import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.LookCommand;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class LookingState extends TextUIState {

	public LookingState(Application app) {
		
		keys.add(new Key('W', "W - Move UP", new LookCommand(app, 1, 0)));
		keys.add(new Key('A', "A - Move LEFT", new LookCommand(app, 0, -1)));
		keys.add(new Key('S', "S - Move DOWN", new LookCommand(app, -1, 0)));
		keys.add(new Key('D', "D - Move RIGHT", new LookCommand(app, 0, 1)));
		//activeKeys.add(new Key('C', "C - Look", new MoveCommand(app, 1, 0)));;
		//activeKeys.add(new Key('M', "M - Menu", new MoveCommand(app, 1, 0)));
	}

}
