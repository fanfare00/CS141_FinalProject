package edu.cpp.cs.cs141.final_project.User_Interface;

import edu.cpp.cs.cs141.final_project.Application;

/**
 * The interface for user interfaces. The UI displays the details of the game to the user.
 */
public interface IUserInterface
{
    /**
     * Creates a game grid with the specified number of rows and columns.
     * @param rows The number of rows the grid should have.
     * @param cols The number of columns the grid should have.
     */
    public void createGrid(int rows, int cols);
    
    /**
     * Assigns the {@link Application} that this UI should communicate with.
     */
    public void addApplication(Application app);
    
    /**
     * Adds the specified mark to the grid at the specified location.
     */
    public void addToGrid(int xIndex, int yIndex, char symbol);
    
    /**
     * Prepares the UI for a new game.
     */
    public void beginGame();
    
    /**
     * Prompts for, verifies, and executes a command from the user.
     */
    public void promptCommand();
    
    /**
     * Displays a representation of the game grid to the user.
     */
    public void drawGrid();
    //public void clearGrid();
}
