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
package edu.cpp.cs.cs141.final_project.User_Interface;

import edu.cpp.cs.cs141.final_project.Application;

/**
 * The interface for user interfaces. The UI displays the details of the game to the user.
 */
public interface IUserInterface {
	
	void init(Application application);

	void update();

	void createGrid(int gameRows, int gameCols);

	void addToGrid(int row, int col, char symbol);

	void drawGrid();

	void toggleMenuState();

	void toggleMoveState();

	void toggleLookState();

	void toggleShootState();

	void getAndHandleInput();

	void setStatusText(String string);

	void setAlertText(String string);

	void dispose();

	void resetGrid();

	
}
