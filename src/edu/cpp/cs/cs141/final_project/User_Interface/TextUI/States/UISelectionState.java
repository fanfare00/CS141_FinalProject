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

// TODO: Auto-generated Javadoc
/**
 * The Class UISelectionState.
 */
public class UISelectionState extends TextUIState {

	/**
	 * Instantiates a new UI selection state.
	 *
	 * @param app the app
	 */
	public UISelectionState(Application app) {
		super(app);
		
		key_1.setText("Run game in text mode.");
		key_1.setCommand(new SelectTextUICommand());
		key_1.setActive(true);
		
		key_2.setText("Run game in GUI mode.");
		key_2.setCommand(new SelectGUICommand());
		key_2.setActive(true);
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States.TextUIState#update(boolean[])
	 */
	public void update(boolean[] activeDirections) {	
		key_3.setActive(false);
		key_4.setActive(false);
		
		keys.get(keys.indexOf(key_W)).setActive(false);
		keys.get(keys.indexOf(key_A)).setActive(false);
		keys.get(keys.indexOf(key_S)).setActive(false);
		keys.get(keys.indexOf(key_D)).setActive(false);
			
		keys.get(keys.indexOf(key_C)).setActive(false);
		keys.get(keys.indexOf(key_Z)).setActive(false);
		keys.get(keys.indexOf(key_M)).setActive(false);
	}

}
