package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Direction;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.*;

public class MovingState extends TextUIState {
	
	public MovingState(Application app) {
		super(app);
		
		key_W.setText("Move UP");
		key_W.setCommand(new LookCommand(Direction.UP));
		
		key_A.setText("Move LEFT");
		key_A.setCommand(new LookCommand(Direction.LEFT));
		
		key_S.setText("Move DOWN");
		key_S.setCommand(new LookCommand(Direction.DOWN));
		
		key_D.setText("Move RIGHT");
		key_D.setCommand( new LookCommand(Direction.RIGHT));
	}
}
