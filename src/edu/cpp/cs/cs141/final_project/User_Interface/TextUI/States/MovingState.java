/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodríguez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;


/**
 * The Class MovingState  is the state the UI will be in when the player is selecting a direction to shoot..
 */
public class MovingState extends TextUIState {
	
	/**
	 * Instantiates a new moving state.
	 *
	 * @param app the app
	 */
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
		
		key_C.setText("Look");
		key_C.setCommand(new ToggleLookCommand());
	}
}
