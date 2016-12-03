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

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;
import edu.cpp.cs.cs141.final_project.User_Interface.UIState;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.InputHandler;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;


/**
 * The Class TextUIState is a base class for all states and contains generic schema.
 */
public class TextUIState implements UIState {
	
	/** The key W. */
	protected Key key_W = new Key('W');
	
	/** The key A. */
	protected Key key_A = new Key('A');
	
	/** The key S. */
	protected Key key_S = new Key('S');
	
	/** The key D. */
	protected Key key_D = new Key('D');
	
	/** The key C. */
	protected Key key_C = new Key('C');
	
	/** The key Z. */
	protected Key key_Z = new Key('Z');
	
	/** The key M. */
	protected Key key_M = new Key('M');
	
	/** The key 1. */
	protected Key key_1 = new Key('1');
	
	/** The key 2. */
	protected Key key_2 = new Key('2');
	
	/** The key 3. */
	protected Key key_3 = new Key('3');
	
	/** The key 4. */
	protected Key key_4 = new Key('4');
	
	/** The key 5. */
	protected Key key_5 = new Key('5');
	
	/** The key 9. */
	protected Key key_9 = new Key('9');
	
	/** The keys. */
	protected List<Key> keys = new ArrayList<Key>();
	
	/** The active keys. */
	protected List<Key> activeKeys = new ArrayList<Key>();
	
	/** The input handler. */
	protected InputHandler inputHandler = new InputHandler();
	
	/** The app. */
	protected Application app;
	

	
	/**
	 * Instantiates a new text UI state.
	 *
	 * @param app the app
	 */
	public TextUIState(Application app) {
		this.app = app;
		
		keys.add(key_W);
		keys.add(key_A);
		keys.add(key_S);
		keys.add(key_D);
		keys.add(key_C);
		keys.add(key_Z);
		keys.add(key_1);
		keys.add(key_2);
		keys.add(key_3);
		keys.add(key_4);
		keys.add(key_5);
		
		key_Z.setText("Shoot");
		key_Z.setCommand(new ToggleShootCommand());
		key_Z.setActive(true);
		
		key_M.setText("Menu");
		key_M.setCommand(new ToggleMenuCommand());
		key_M.setActive(true);
		keys.add(key_M);
		
		key_9.setCommand(new ToggleDebugCommand());
		key_9.setVisible(false);
		key_9.setActive(true);
		keys.add(key_9);
		
	}
	
	/**
	 * Handle input.
	 *
	 * @param input the input
	 */
	public void handleInput(char input) {
		inputHandler.setActiveKeys(this.getActiveKeys());
		inputHandler.handleInput(app, input);
	}
	
	/**
	 * Gets the active keys.
	 *
	 * @return the active keys
	 */
	public List<Key> getActiveKeys() {
		activeKeys.clear();
		
		for (Key key : keys) {
			if (key.getIsActive()) activeKeys.add(key);
		}
		
		return activeKeys;
	}

	/**
	 * Update.
	 *
	 * @param activeDirections the active directions
	 */
	public void update(boolean[] activeDirections) {	
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
