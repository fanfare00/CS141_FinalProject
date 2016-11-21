package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class ShootingState extends TextUIState {
	public ShootingState(Application app) {
		super(app);

		key_W.setText("Shoot UP");
		key_W.setCommand(new ShootCommand(Direction.UP));
		
		key_A.setText("Shoot LEFT");
		key_A.setCommand(new ShootCommand(Direction.LEFT));
		
		key_S.setText("Shoot DOWN");
		key_S.setCommand(new ShootCommand(Direction.DOWN));
		
		key_D.setText("Shoot RIGHT");
		key_D.setCommand( new ShootCommand(Direction.RIGHT));
		
		key_C.setText("Look");
		key_C.setCommand(new ToggleLookCommand());
		
		key_Z.setText("Move");
		key_Z.setCommand(new ToggleMoveCommand());
	}
}
