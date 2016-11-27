package edu.cpp.cs.cs141.final_project;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.GraphicalUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.TextUserInterface;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;
import edu.cpp.cs.cs141.final_project.Utilities.SaveFileManager;

/**
 * The {@link Application} class receives commands from the UI and controls the game.
 */
public class Application {
	
	private int FPS = 30; 
	
	private IUserInterface UI;
	private Game game;
	private boolean close;
	
	public Application(IUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
		
		start();
	}

	public void run() {
		if (getGameOverStatus()) UI.toggleMenuState();
		
		while(!close){ 
			long time = System.currentTimeMillis(); 
			time = (1000 / FPS) - (System.currentTimeMillis()-time); 
			 
			UI.update();
			game.update();
			redrawUI();
			
			if (time > 0) { 
				try { 
					Thread.sleep(time); 
                } 
                	catch(Exception e){} 
             } 
        }
		
//		UI.update();
//		game.update();
//		redrawUI();
		
		//if (!close) run();
	}
	
	/**
	 * Initializes a new game.
	 */
	public void start() {	
		UI.init(this);
		loadGame(game.getActiveEntities());
		run();
	}
	
	public void startNewGame() {
		UI = new TextUserInterface();
		game = new Game();
		
		start();
	}
	
	public void playerMove(Direction dir) {
		game.getPlayer().setMoveDirection(dir);
	}
	
	public void playerLook(Direction dir) {
		game.playerLook(dir);
		game.checkGameOver();
		
		redrawUI();
		
		if (getGameOverStatus()) UI.toggleMenuState();
		else UI.toggleMoveState();
		
		UI.update();
		
	}
	
	public void playerAttack(Direction dir) {
		game.getPlayer().setMoveDirection(null);
		game.playerAttack(dir);
		redrawUI();
		
		UI.toggleMoveState();
		
		UI.update();
	}
	
	/**
	 * Loads game state from the disk.
	 */
	@SuppressWarnings("unchecked")
	public void loadGameData() {		
		loadGame((List<GameObject>) SaveFileManager.load());
	}
	
	/**
	 * Creates the game with the specified entities.
	 * @param activeEntities The list of entities to add to the game.
	 */
	public void loadGame(List<GameObject> activeEntities) {
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		redrawUI();
		
		game.setActiveEntities(activeEntities);
		
		//UI.update();
	}
	
	/**
	 * Saves the current game state to the disk.
	 */
	public void saveGameData() {
		SaveFileManager.save(game.getActiveEntities());
		UI.update();
	}
	
	/**
	 * Updates the UI with the currently active entities.
	 * @param activeEntities The entities to be displayed on the UI.
	 */
	public void redrawUI()
	{  
		updateUIAlertText();
		updateUIStatusText();
		updateUIGrid();

	}
	
	public void updateUIGrid(){
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
	    for (GameObject o : game.getActiveEntities()) {
	    	if (o.isVisible()) UI.addToGrid(o.getRow(), o.getCol(), o.getSymbol());
	    }
	    
	    UI.addToGrid(game.getActiveEntities().get(0).getRow(), game.getActiveEntities().get(0).getCol(), game.getActiveEntities().get(0).getSymbol());
	}
	
	public void getNewCommand() {
		UI.setStatusText("Invalid Entry - See the above list for valid commands.");
		UI.getAndHandleInput();
	}
	
	public void close() {
		this.close = true;
		System.exit(0);
	}
	
	public boolean[] getDirectionalConditions() {
		return game.getPlayer().getMoveConditions();
	}
	
	public void stayInTextUI() {
		redrawUI();
		UI.toggleMoveState();
	}

	public void toggleMenuMode() {
		UI.toggleMenuState();
		UI.update();
	}

	public void toggleMoveMode() {
		
		UI.toggleMoveState();
		UI.update();
	}

	public void toggleLookMode() {
		UI.toggleLookState();
		UI.update();
	}

	public void toggleShootMode() {
		UI.toggleShootState();
		UI.update();
		
	}
	
	public void toggleDebugMode() {
		game.setDebugMode(!game.getDebugMode());
		redrawUI();
		UI.update();
	}

	public boolean getLookStatus() {
		return game.getPlayer().getCanLook();
	}

	public boolean getShootStatus() {
		return (game.getPlayer().getCanAttack());
	}
	
	public boolean getDeathStatus() {
		return game.getPlayer().getHasDiedRecently();
	}
	
	public boolean getGameOverStatus() {
		return (game.getGameOver() | game.getGameWon());
	}
	
	public void updateUIAlertText() {
		String alertText = "";
		
		if (game.getPlayer().hasRevealedEnemy()) alertText = "You've spotted an enemy ninja!";
		if (game.getPlayer().hasKilledEnemy()) alertText = "Your gun killed an enemy ninja!";
		if (game.getPlayer().hasMissedEnemy()) alertText = "You shot your gun, but did not hit an enemy ninja.";
		if (getDeathStatus()) alertText = "An enemy ninja killed you!";
		if (game.getGameOver()) alertText = "You have no lives left. Game Over.";
		if (game.getGameWon()) alertText = "Congratulations! You've found the intel and won the game!";
		if (game.getPlayer().getCurrentPowerup() != null) alertText = game.getPlayer().getCurrentPowerup().getDescription();
		if (game.getPlayer().getIsInvincible()) alertText += "\nYou are invincible, " + game.getPlayer().getInvincibilityTurns() + " turns remaining.";
		UI.setAlertText(alertText);
	}

	public void updateUIStatusText() {
		String statusText = "";
		
		statusText += "You have " + game.getPlayer().getRemainingLives() + " lives left. ";
		statusText += "Your gun has " + game.getPlayer().getRemainingAmmo() + " shots left.";
		
		UI.setStatusText(statusText);
	}

	public boolean getRoomCheckCondition() {
		return game.getPlayer().getAboveRoom();
	}

	public void toggleUIType() {
		game.setDebugMode(true);
		FPS = 2;
		
		UI = new GraphicalUserInterface();
		UI.init(this);
		redrawUI();
	}
}
