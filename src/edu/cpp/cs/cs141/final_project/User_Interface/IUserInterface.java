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

}
