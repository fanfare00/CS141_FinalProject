package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.*;

public class MovingState extends TextUIState {
	
	public MovingState(Application app) {
		keys.add(new Key('W', "W - Move UP", new MoveCommand(app, 1, 0)));
		keys.add(new Key('A', "A - Move LEFT", new MoveCommand(app, 0, -1)));
		keys.add(new Key('S', "S - Move DOWN", new MoveCommand(app, -1, 0)));
		keys.add(new Key('D', "D - Move RIGHT", new MoveCommand(app, 0, 1)));
		keys.add(new Key('C', "C - Look", new ToggleLookCommand(app)));
		keys.add(new Key('Z', "Z - Shoot", new ToggleShootCommand(app)));
		keys.add(new Key('M', "M - Menu", new MenuCommand(app)));
	}
}
