package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.States;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.MoveCommand;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.TextUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.InputHandler;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class MovingState implements TextUIState {
	
	public List<Key> activeKeys = new ArrayList<Key>();
	
	public MovingState(Application app) {
		
		activeKeys.add(new Key('W', "W - Move UP", new MoveCommand(app, 1, 0)));
		activeKeys.add(new Key('A', "A - Move LEFT", new MoveCommand(app, 0, -1)));
		activeKeys.add(new Key('S', "S - Move DOWN", new MoveCommand(app, -1, 0)));
		activeKeys.add(new Key('D', "D - Move RIGHT", new MoveCommand(app, 0, 1)));
		//activeKeys.add(new Key('C', "C - Look", new MoveCommand(app, 1, 0)));;
		//activeKeys.add(new Key('M', "M - Menu", new MoveCommand(app, 1, 0)));
	}

	public void handleInput(char input) {
		InputHandler.ActiveKeys = activeKeys;
		InputHandler.handleInput(input);
	}

	@Override
	public TextUIState update() {
		// TODO Auto-generated method stub
		return null;
	}
}
