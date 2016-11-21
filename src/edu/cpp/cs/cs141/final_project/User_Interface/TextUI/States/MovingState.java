package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.*;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class MovingState extends TextUIState {
	
	public MovingState(Application app) {
		super(app);
		
		key_W.setText("Move UP");
		key_W.setCommand(new MoveCommand(Direction.UP));
		
		key_A.setText("Move LEFT");
		key_A.setCommand(new MoveCommand(Direction.LEFT));
		
		key_S.setText("Move DOWN");
		key_S.setCommand(new MoveCommand(Direction.DOWN));
		
		key_D.setText("Move RIGHT");
		key_D.setCommand( new MoveCommand(Direction.RIGHT));
		
		key_Z.setText("Shoot");
		key_Z.setCommand(new ToggleShootCommand());
		
		key_C.setText("Look");
		key_C.setCommand(new ToggleLookCommand());
	}
}
