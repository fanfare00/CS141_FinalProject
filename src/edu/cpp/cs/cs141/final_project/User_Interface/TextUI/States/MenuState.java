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
 * The Class MenuState.
 */
public class MenuState extends TextUIState{
	
	/**
	 * Instantiates a new menu state.
	 *
	 * @param app the app
	 */
	public MenuState(Application app) {
		super(app);
		
		key_1.setText("New Game");
		key_1.setCommand(new MenuNewCommand());
		key_1.setActive(true);
		
		key_2.setText("Load Game");
		key_2.setCommand(new MenuLoadCommand());
		key_2.setActive(true);
		
		key_3.setText("Save Game");
		key_3.setCommand(new MenuSaveCommand());
		key_3.setActive(true);
		
		key_4.setText("Quit Game");
		key_4.setCommand(new MenuQuitCommand());
		key_4.setActive(true);
		
		key_5.setText("Toggle UI");
		key_5.setCommand(new ToggleUICommand());
		key_5.setActive(true);
		
		key_M.setText("Back");
		key_M.setCommand(new ToggleMoveCommand());
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States.TextUIState#update(boolean[])
	 */
	@Override
	public void update(boolean[] activeDirections) {
		keys.get(keys.indexOf(key_W)).setActive(false);
		keys.get(keys.indexOf(key_A)).setActive(false);
		keys.get(keys.indexOf(key_S)).setActive(false);
		keys.get(keys.indexOf(key_D)).setActive(false);
			
		keys.get(keys.indexOf(key_C)).setActive(false);
		keys.get(keys.indexOf(key_Z)).setActive(false);
		
		keys.get(keys.indexOf(key_3)).setActive(!app.getGameOverStatus());
		keys.get(keys.indexOf(key_5)).setActive(!app.getGameOverStatus());
		keys.get(keys.indexOf(key_M)).setActive(!app.getGameOverStatus());
	}
}
