package edu.cpp.cs.cs141.final_project.User_Interface.TextUI;

import java.util.Scanner;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.UIState;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;


// TODO: Auto-generated Javadoc
/**
 * The Class TextUserInterface.
 */
public class TextUserInterface implements IUserInterface
{
    
    /** The scan. */
    private Scanner scan;
    
    /** The app. */
    private Application app;
    
    /** The grid. */
    private char grid[][];
    
    /** The state. */
    private TextUIState state;
    
    /** The moving. */
    private static MovingState moving;
    
    /** The looking. */
    private static LookingState looking;
    
    /** The in menus. */
    private static MenuState inMenus;
    
    /** The shooting. */
    private static ShootingState shooting;
    
    /** The UI select. */
    private static UISelectionState UISelect;
    
    /** The instruction text. */
    private String instructionText;
    
    /** The status text. */
    private String statusText;
    
    /** The alert text. */
    private String alertText;
    
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#init(edu.cpp.cs.cs141.final_project.Application)
     */
    public void init(Application app) {
    	this.app = app;
    	app.setTurnBased(true);
    	
    	scan = new Scanner(System.in);	
    	
    	UISelect = new UISelectionState(app);
    	moving = new MovingState(app);
    	shooting = new ShootingState(app);
    	inMenus = new MenuState(app);
    	looking = new LookingState(app);
    	
    	if (!app.hasMadeUIChoice()) {
	    	toggleUICommand();
	    	state.update(null);
	    	drawInstructions();
	    	drawCommandList();
	    	state.handleInput(getUserInput());
    	}
    	else {
    		toggleMoveState();
    	}
    	

    }
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#update()
     */
    public void update() {
    	state.update(getGridConditions());
    	
    	
    	drawGrid();
    	drawAlertText();
    	drawStatusText();
    	drawInstructions();
    	drawCommandList();
    	
    	state.handleInput(getUserInput());
    	
    	
    }
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#setStatusText(java.lang.String)
     */
    public void setStatusText(String statusText){
    	this.statusText = statusText;
    }
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#setAlertText(java.lang.String)
     */
    public void setAlertText(String alertText){
    	this.alertText = alertText;
    }
    
    /**
     * Draw status text.
     */
    private void drawStatusText() {
    	if (state != inMenus) System.out.println(statusText);
    }
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#getAndHandleInput()
     */
    public void getAndHandleInput(){
    	drawStatusText();
    	state.handleInput(getUserInput());
    }
    
    /**
     * Draw alert text.
     */
    private void drawAlertText() {
    	
    	if (alertText != "") System.out.println(alertText);
    	alertText = "";
    }
    
    /**
     * Draw instructions.
     */
    private void drawInstructions() {
    	System.out.println(instructionText);
	}

	/**
	 * Draw command list.
	 */
	private void drawCommandList(){
    	System.out.println(keyTextToString());
    }
    
    /**
     * Key text to string.
     *
     * @return the string
     */
    private String keyTextToString() {
		String str = "Commands: ";
    	for (Key key : state.getActiveKeys()){
    		if(!key.getVisible()) continue;
			str+= "\n" + key.getSymbol() + " - " + key.getText();
		}
    	
    	return str;
	}
    
    /**
     * Gets the user input.
     *
     * @return the user input
     */
    public char getUserInput(){
    	return Character.toUpperCase(scan.next().trim().charAt(0));
    }
    
    /**
     * Change state.
     *
     * @param state the state
     */
    public void changeState(UIState state) {
    	this.state = (TextUIState) state;
    }
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#createGrid(int, int)
     */
    public void createGrid(int rows, int cols) {
    	grid = new char[rows][cols];
    }
    
    /* (non-Javadoc)
     * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#addToGrid(int, int, char)
     */
    public void addToGrid(int row, int col, char symbol) {
    	grid[row][col] = symbol;
    }
    
    /**
     * Returns a string representation of the grid.
     *
     * @return the string
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
     * Gets the grid conditions.
     *
     * @return the grid conditions
     */
    private boolean[] getGridConditions() {
    	
    	return app.getDirectionalConditions();
    }
    
    /**
     * Prints a text representation of the game grid.
     */
    public void drawGrid() {
    	System.out.println(gridToString());
    }

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleMenuState()
	 */
	@Override
	public void toggleMenuState() {
		instructionText = "Choose a menu option from the command list below.";
		state = inMenus;
		update();
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleMoveState()
	 */
	@Override
	public void toggleMoveState() {
		instructionText = "Choose a direction to move from the command list below.";
		state = moving;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleLookState()
	 */
	@Override
	public void toggleLookState() {
		instructionText = "Choose a direction to look from the command list below.";
		state = looking;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleShootState()
	 */
	@Override
	public void toggleShootState() {
		instructionText = "Choose a direction to shoot from the command list below.";
		state = shooting;
	}
	
	/**
	 * Toggle UI command.
	 */
	public void toggleUICommand() {
		instructionText = "Choose a menu option from the command list below.";
		state = UISelect;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#dispose()
	 */
	@Override
	public void dispose() {
		scan.nextLine();


	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#resetGrid()
	 */
	@Override
	public void resetGrid() {
		// TODO Auto-generated method stub
		
	}

    

}
