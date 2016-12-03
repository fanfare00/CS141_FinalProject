/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodr�guez's CS141 class at 
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
 * The Class LookingState is the state the UI will be in when the player is selecting a direction to shoot..
 */
public class LookingState extends TextUIState {

	/**
	 * Instantiates a new looking state.
	 *
	 * @param app the app
	 */
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
		
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States.TextUIState#update(boolean[])
	 */
	@Override
	public void update(boolean[] activeDirections) {	
		key_1.setActive(false);
		key_2.setActive(false);
		key_3.setActive(false);
		key_4.setActive(false);
		
		keys.get(keys.indexOf(key_W)).setActive(activeDirections[Direction.UP.ordinal()]);
		keys.get(keys.indexOf(key_A)).setActive(activeDirections[Direction.LEFT.ordinal()]);
		keys.get(keys.indexOf(key_S)).setActive(activeDirections[Direction.DOWN.ordinal()] | app.getRoomCheckCondition());
		keys.get(keys.indexOf(key_D)).setActive(activeDirections[Direction.RIGHT.ordinal()]);
			
		keys.get(keys.indexOf(key_C)).setActive(app.getLookStatus());
		keys.get(keys.indexOf(key_Z)).setActive(app.getShootStatus());
		
	}

}
