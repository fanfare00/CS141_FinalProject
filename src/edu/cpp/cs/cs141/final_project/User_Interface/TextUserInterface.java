package edu.cpp.cs.cs141.final_project.User_Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Key;
import edu.cpp.cs.cs141.final_project.Utilities.Commands.MoveCommand;

/**
 * The text implementation of {@link IUserInterface}. It uses plain text to display the game to the user.
 */
public class TextUserInterface implements IUserInterface
{
    
	private List<Key> allKeys = new ArrayList<Key>();
	private List<Key> activeKeys = new ArrayList<Key>();
	
    private Scanner scan;
    private Application app;
    private char grid[][];
    
    public TextUserInterface() {
		scan = new Scanner(System.in);
		
		allKeys.add(new Key('W'));
		allKeys.add(new Key('A'));
		allKeys.add(new Key('S'));
		allKeys.add(new Key('D'));
		allKeys.add(new Key('Z'));
		allKeys.add(new Key('C'));
		allKeys.add(new Key('M'));
		allKeys.add(new Key('1'));
		allKeys.add(new Key('2'));
		allKeys.add(new Key('3'));
		
    }
    
    public void update() {
    	
    }
    
    public void createGrid(int rows, int cols)
    {
	grid = new char[rows][cols];
    }
    
    public void addApplication(Application app)
    {
	this.app = app;
    }
    
    public void addToGrid(int xIndex, int yIndex, char symbol)
    {
	grid[xIndex][yIndex] = symbol;
    }
    
    /**
     * Returns a string representation of the grid.
     */
    private String gridToString() {
		String str = "";
		
		for (int m = 0; m < grid[0].length; m++) {
		    for (int n = 0; n < grid.length; n++) {
		    	str += "[ " + grid[m][n] + " ]";
		    }
		    	str += "\n";
		}
			return str;
    }
    
    public void beginGame()
    {
	printHelpMessage();
	// gameLoop();
    }
    
    /**
     * Prints a list of commands that the user can perform.
     */
    private void printHelpMessage()
    {
	System.out.println("Here are the commands you can use while playing:");
	System.out.println("move (m), shoot (s), look (l), save, load, help (?), quit, reprint (r), hard, debug");
    }
    
    public void promptCommand()
    {
	System.out.println("Please enter a command. Type \"help\" or \"?\" for a list of commands you can perform.");
	
	boolean success = false;
	
	while (!success)
	{
	    String command = scan.nextLine().toLowerCase();
	    success = true;
	    
	    switch (command)
	    {
		case "move":
		case "m":
		    while (!app.playerMove(getMoveDirection()))
			System.out.println("You can't move in that direction!");
		    break;
		case "shoot":
		case "s":
		    // call shoot method on game
		    break;
		case "look":
		case "l":
		    break;
		case "save":
		    // todo: prompt for save name
		    app.saveGameData();
		    break;
		case "load":
		    // todo: prompt for save name
		    app.loadGameData();
		    break;
		case "help":
		case "?":
		    printHelpMessage();
		    break;
		case "quit":
		    break;
		case "reprint":
		case "r":
		    drawGrid();
		    break;
		case "hard":
		    break;
		case "debug":
		    break;
		default:
		    success = false;
		    System.out.println(
			    "That was not a valid command. Type \"help\" or \"?\" for a list of commands you can perform.");
		    break;
	    }
	}
    }
    
    /**
     * Prompts the user for the direction in which they would like to move.
     * 
     * @return The player's chosen direction
     */
    private int getMoveDirection()
    {
	System.out.println(
		"Please enter the direction in which you would like to move.\n1 = up, 2 = down, 3 = left, 4 = right");
	int direction = 0;
	boolean valid = false;
	
	while (!valid)
	{
	    direction = scan.nextInt(); // throws InputMismatchException if not
					// an int
	    scan.nextLine();
	    if (direction < 5 && direction > 0)
	    {
		valid = true;
	    }
	    else
	    {
		System.out.println("That is not a valid direction. Please try again.");
	    }
	}
	return direction;
    }
    
    /**
     * Prints a text representation of the game grid.
     */
    @Override
    public void drawGrid()
    {
	System.out.println(gridToString());
    }
    
}
