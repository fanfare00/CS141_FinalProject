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
package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;



/**
 * The Class InputHandler validates user inputs and executes commands.
 */
public class InputHandler {	
	
	/** The keys. */
	private List<Key> keys = new ArrayList<Key>();
	
	/**
	 * Handle input.
	 *
	 * @param app the app
	 * @param input the input
	 */
	public void handleInput(Application app, char input){
		boolean isValid = false;
		
		for (int i = 0; i < keys.size(); i++) {
			if (wasTyped(input, keys.get(i))) { 
				keys.get(i).executeCommand(app);
				isValid = true;
			}
		}
		
		if (!isValid) app.getNewCommand();
	}
	
	/**
	 * Was typed.
	 *
	 * @param input the input
	 * @param key the key
	 * @return true, if successful
	 */
	private static boolean wasTyped(char input, Key key) {
		return (input == key.getSymbol());
	}
	
	/**
	 * Sets the active keys.
	 *
	 * @param activeKeys the new active keys
	 */
	public void setActiveKeys(List<Key> activeKeys){
		this.keys = activeKeys;
	}
}
