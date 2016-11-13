package edu.cpp.cs.cs141.final_project.User_Interface;

import java.util.Scanner;

import edu.cpp.cs.cs141.final_project.Application;

public class TextUserInterface
{
    
    private Scanner scan;
    private Application app;
    private char grid[][];
    
    
    public TextUserInterface()
    {
    	scan = new Scanner(System.in);
    }
    
    public void createGrid(int rows, int cols) {
    	grid = new char[rows][cols];
    }
    
    public void addApplication(Application app){
    	this.app = app;
    }
    
    public void addToGrid(int xIndex, int yIndex, char symbol) {
    	grid[xIndex][yIndex] = symbol;
    }
    
    private String gridToString() {
    	String str ="";
    	
    	for (int m = 0; m < grid[0].length; m++) {
    		for (int n = 0; n < grid.length; n++) {
    			str += "[ " + grid[m][n] + " ]";
    		}
    		str+= "\n";
    	}
    	return str;
    }
    
    
    /**
     * Runs the game.
     */
    public void beginGame()
    {
	printHelpMessage();
	gameLoop();
    }
    
    /**
     * Prints a list of commands that the user can perform.
     */
    private void printHelpMessage()
    {
	System.out.println("Here are the commands you can use while playing:");
	System.out.println("move (m), shoot (s), look (l), save, load, help (?), quit, reprint (r), hard, debug");
    }
    
    private void gameLoop()
    {
	while (true) // todo: make this a call to Game/Application to check if
		     // the game is not yet over
	{
	    promptCommand();
	}
    }
    
    /**
     * Prompts for, verifies, and executes a command from the user.
     */
    private void promptCommand()
    {
	System.out.println("Please enter a command. Type \"help\" or \"?\" for a list of commands you can perform.");
	
	System.out.println(gridToString());
	
	boolean success = false;
	
	while (!success)
	{
	    String command = scan.nextLine().toLowerCase();
	    success = true;
	    
	    switch (command)
	    {
		case "move":
		case "m":
		    int moveDirection = getMoveDirection(); // call move method
							    // on Game, passing
							    // this direction as
							    // argument
		    break;
		case "shoot":
		case "s":
		    // call shoot method on game
			app.saveGameData();
		    break;
		case "look":
		case "l":
		    break;
		case "save":
		    // todo: prompt for save name and call save method on
		    // SaveData class
		    break;
		case "load":
		    // todo: prompt for save name and call load method on
		    // SaveData class
		    break;
		case "help":
		case "?":
		    printHelpMessage();
		    break;
		case "quit":
		    break;
		case "reprint":
		case "r":
			app.loadGameData();
		    break;
		case "hard":
		    break;
		case "debug":
		    break;
		default:
		    success = false;
		    System.out.println("That was not a valid command. Please try again.");
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
	    direction = scan.nextInt(); //throws InputMismatchException if not an int
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
}
