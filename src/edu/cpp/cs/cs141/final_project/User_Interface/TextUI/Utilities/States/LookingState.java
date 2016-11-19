package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.States;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.LookCommand;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class LookingState implements TextUIState {

	private List<Key> activeKeys = new ArrayList<Key>();
	private Application app;
	
	public LookingState(Application app) {
		this.app = app;
		
		activeKeys.add(new Key('W', "W - Move UP", new LookCommand(app, 1, 0)));
		activeKeys.add(new Key('A', "A - Move LEFT", new LookCommand(app, 0, -1)));
		activeKeys.add(new Key('S', "S - Move DOWN", new LookCommand(app, -1, 0)));
		activeKeys.add(new Key('D', "D - Move RIGHT", new LookCommand(app, 0, 1)));
		//activeKeys.add(new Key('C', "C - Look", new MoveCommand(app, 1, 0)));;
		//activeKeys.add(new Key('M', "M - Menu", new MoveCommand(app, 1, 0)));
	}
	
	public void handleInput(char input) {
		
	}

	@Override
	public TextUIState update() {
		// TODO Auto-generated method stub
		
		return new MovingState(app);
		
	}

}
