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

// TODO: Auto-generated Javadoc
/**
 * The Class ShootingState.
 */
public class ShootingState extends TextUIState {
	
	/**
	 * Instantiates a new shooting state.
	 *
	 * @param app the app
	 */
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
//		
		key_C.setText("Look");
		key_C.setCommand(new ToggleLookCommand());
		
		key_Z.setText("Move");
		key_Z.setCommand(new ToggleMoveCommand());
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States.TextUIState#update(boolean[])
	 */
	@Override
	public void update(boolean[] activeDirections) {	
		
		if (app.getEnemyDirection() != null) activeDirections[app.getEnemyDirection().ordinal()] = true;
		
		key_1.setActive(false);
		key_2.setActive(false);
		key_3.setActive(false);
		key_4.setActive(false);
		
		keys.get(keys.indexOf(key_W)).setActive(activeDirections[0]);
		keys.get(keys.indexOf(key_A)).setActive(activeDirections[1]);
		keys.get(keys.indexOf(key_S)).setActive(activeDirections[2]);
		keys.get(keys.indexOf(key_D)).setActive(activeDirections[3]);
			
		keys.get(keys.indexOf(key_C)).setActive(app.getLookStatus());
		keys.get(keys.indexOf(key_Z)).setActive(app.getShootStatus());
		
	}
}
