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
 * The UI interface is designed to be used for both a text
 * and graphical UI, these methods are shared between both
 * for swapability while running on the application class.
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
