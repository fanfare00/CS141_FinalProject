package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;


import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class LookingState extends TextUIState {

	public LookingState(Application app) {
		super(app);
		
		key_W.setText("Look UP");
		key_W.setCommand(new LookCommand(Direction.UP));
		
		key_A.setText("Look LEFT");
		key_A.setCommand(new LookCommand(Direction.LEFT));
		
		key_S.setText("Look DOWN");
		key_S.setCommand(new LookCommand(Direction.DOWN));
		
		key_D.setText("Look RIGHT");
		key_D.setCommand( new LookCommand(Direction.RIGHT));
		
		key_C.setText("Move");
		key_C.setCommand(new ToggleMoveCommand());
		
		
//		keys.add(new Key('W', "W - Move UP", new LookCommand(app, 1, 0)));
//		keys.add(new Key('A', "A - Move LEFT", new LookCommand(app, 0, -1)));
//		keys.add(new Key('S', "S - Move DOWN", new LookCommand(app, -1, 0)));
//		keys.add(new Key('D', "D - Move RIGHT", new LookCommand(app, 0, 1)));
//		//activeKeys.add(new Key('C', "C - Look", new MoveCommand(app, 1, 0)));;
//		//activeKeys.add(new Key('M', "M - Menu", new MoveCommand(app, 1, 0)));
	}

}
