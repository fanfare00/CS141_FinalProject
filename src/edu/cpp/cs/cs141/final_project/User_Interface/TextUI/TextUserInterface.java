package edu.cpp.cs.cs141.final_project.User_Interface.TextUI;

import java.util.Scanner;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.UIState;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;


public class TextUserInterface implements IUserInterface
{
    private Scanner scan;
    private Application app;
    private char grid[][];
    
    private TextUIState state;
    
    private static MovingState moving;
    private static LookingState looking;
    private static MenuState inMenus;
    private static ShootingState shooting;
    
    public TextUserInterface() {
		scan = new Scanner(System.in);	
    }
    
    public void init(Application app) {
    	this.app = app;
    	
    	moving = new MovingState(app);
    	inMenus = new MenuState(app);
    	looking = new LookingState(app);
    	
    	
    }
    
    public void update() {
    	
    	toggleMoveState();
    	
    	drawGrid();
    	drawInstructions();

    	
    	state.handleInput(getUserInput());
    	
    }
    
    private void drawInstructions(){
    	System.out.println(keyTextToString());
    }
    
    private String keyTextToString() {
		String str = "Commands: ";
    	for (Key key : state.getActiveKeys()){
			str+= "\n" + key.getSymbol() + " - " + key.getText();
		}
    	
    	return str;
	}
    
    public char getUserInput(){
    	return Character.toLowerCase(scan.next().trim().charAt(0));
    }
    
    public void changeState(UIState state) {
    	this.state = (TextUIState) state;
    }
    
    public void createGrid(int rows, int cols) {
    	grid = new char[rows][cols];
    }
    

    
    public void addToGrid(int xIndex, int yIndex, char symbol) {
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
    
    
    /**
     * Prints a text representation of the game grid.
     */
    public void drawGrid() {
    	System.out.println(gridToString());
    }

	@Override
	public void toggleMenuState() {
		state = inMenus;
	}

	@Override
	public void toggleMoveState() {
		state = moving;
		state.update(app.getDirectionalConditions());
	}

	@Override
	public void toggleLookState() {
		state = looking;
		state.update(app.getDirectionalConditions());
	}

	@Override
	public void toggleShootState() {
		state = shooting;
		state.update(app.getProximityConditions());
	}

    

}
