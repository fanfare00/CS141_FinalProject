package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.*;

public class MovingState extends TextUIState {
	
	public MovingState(Application app) {
		super(app);
		
		key_W.setText("Move UP");
		key_W.setCommand(new LookCommand(1, 0));
		
		key_A.setText("Move LEFT");
		key_A.setCommand(new LookCommand(0, -1));
		
		key_S.setText("Move DOWN");
		key_S.setCommand(new LookCommand(-1, 0));
		
		key_D.setText("Move RIGHT");
		key_D.setCommand( new LookCommand(0, 1));
	}
}
