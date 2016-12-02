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
package edu.cpp.cs.cs141.final_project;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.GraphicalUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.TextUserInterface;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;
import edu.cpp.cs.cs141.final_project.Utilities.SaveFileManager;

// TODO: Auto-generated Javadoc
/**
 * The {@link Application} class receives commands from the UI and controls the game.
 */
public class Application {
	
	/** The UI. */
	private IUserInterface UI;
	
	/** The game. */
	private Game game;
	
	/** a loop flag for closing the game */
	private boolean close;
	
	/** a loop flag for pausing the game. */
	private boolean paused;
	
	/** The has made UI choice. */
	private boolean hasMadeUIChoice;
	
	/**
	 * Instantiates a new application.
	 *
	 * @param UI the ui
	 * @param game the game
	 */
	public Application(IUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
	}

	/**
	 * The main process of the entire program, being looped through over and over in main.
	 */
	public void run() {
		if (getGameOverStatus()) { 
			paused = true;
			UI.toggleMenuState();
		}
			
		if (!paused) {
			UI.update();
			game.update();
			redrawUI();	
		}
	}
	
	/**
	 * Initializes the UI and loads a fresh game.
	 */
	public void start() {
		UI.init(this);
		loadGame(game.getActiveEntities());
	}
	
	/**
	 * Prepares a new game and starts it.
	 */
	public void startNewGame() {
		hasMadeUIChoice = true;
		
		game = new Game();
		loadGame(game.getActiveEntities());
		UI.toggleMoveState();
		UI.resetGrid();
		redrawUI();
		paused = false;
	}
	
	/**
	 * Load game from file and start.
	 */
	public void loadGameFromFile() {
		hasMadeUIChoice = true;
		
		game = new Game();
		loadGameData();
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		redrawUI();
		UI.update();
		
		paused = false;
	}
	
	/**
	 * Resolves a player move, loop will continue from this point and game will update
	 *
	 * @param dir the direction
	 */
	public void playerMove(Direction dir) {
		game.getPlayer().setMoveDirection(dir);
	}
	
	/**
	 * Resolves a player look and updates UI
	 *
	 * @param dir the direction
	 */
	public void playerLook(Direction dir) {
		game.playerLook(dir);
		game.checkGameOver();
		
		redrawUI();
		
		if (getGameOverStatus()) UI.toggleMenuState();
		else UI.toggleMoveState();
		
		UI.update();
		
	}
	
	/**
	 * Resolves a player attack and updates UI
	 *
	 * @param dir the direction
	 */
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
		game.setActiveEntities(activeEntities);
		paused = false;
	}
	
	/**
	 * Saves the current game state to the disk.
	 */
	public void saveGameData() {
		SaveFileManager.save(game.getActiveEntities());
		UI.setAlertText("Game saved to file.");
		UI.update();
	}
	
	/**
	 * Updates the UI with the whats currently happening in the game
	 */
	public void redrawUI()
	{  
		updateUIAlertText();
		updateUIStatusText();
		updateUIGrid();
	}
	
	/**
	 * Update UI grid.
	 */
	public void updateUIGrid(){
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
	    for (GameObject o : game.getActiveEntities()) {
	    	if (o.isVisible()) UI.addToGrid(o.getRow(), o.getCol(), o.getSymbol());
	    }
	    
	    UI.addToGrid(game.getActiveEntities().get(0).getRow(), game.getActiveEntities().get(0).getCol(), game.getActiveEntities().get(0).getSymbol());
	}
	
	/**
	 * Returns a warning to the user if input is invalid and tries again.
	 *
	 * @return the new command
	 */
	public void getNewCommand() {
		UI.setStatusText("Invalid Entry - See the above list for valid commands.");
		UI.getAndHandleInput();
	}
	
	/**
	 * Close.
	 */
	public void close() {
		game.dispose();
		this.close = true;
		//System.exit(0);
	}
	
	/**
	 * Gets the directional conditions.
	 *
	 * @return the directional conditions
	 */
	public boolean[] getDirectionalConditions() {
		return game.getPlayer().getMoveConditions();
	}
	
	/**
	 * Stays in text UI if user neglects to chose GUI
	 */
	public void stayInTextUI() {
		redrawUI();
		UI.toggleMoveState();
	}

	/**
	 * Toggle UI menu mode.
	 */
	public void toggleMenuMode() {
		UI.toggleMenuState();
	}

	/**
	 * Toggle UI move mode.
	 */
	public void toggleMoveMode() {
		
		UI.toggleMoveState();
		UI.update();
	}

	/**
	 * Toggle UI look mode.
	 */
	public void toggleLookMode() {
		UI.toggleLookState();
		UI.update();
	}

	/**
	 * Toggle UI shoot mode.
	 */
	public void toggleShootMode() {
		UI.toggleShootState();
		UI.update();
		
	}
	
	/**
	 * Toggle game/UI debug mode.
	 */
	public void toggleDebugMode() {
		game.setDebugMode(!game.getDebugMode());
		redrawUI();
		UI.update();
	}

	/**
	 * Gets the look status.
	 *
	 * @return the look status
	 */
	public boolean getLookStatus() {
		return game.getPlayer().getCanLook();
	}

	/**
	 * Gets the shoot status.
	 *
	 * @return the shoot status
	 */
	public boolean getShootStatus() {
		return (game.getPlayer().getCanAttack());
	}
	
	/**
	 * Gets the death status.
	 *
	 * @return the death status
	 */
	public boolean getDeathStatus() {
		if (!game.getPlayer().hasMadeFirstMove) return false;
		return game.getPlayer().getHasDiedRecently();
	}
	
	/**
	 * Gets the game over status.
	 *
	 * @return the game over status
	 */
	public boolean getGameOverStatus() {
		return (game.getGameOver() | game.getGameWon());
	}
	
	/**
	 * Gets the player lives.
	 *
	 * @return the player lives
	 */
	public int getPlayerLives() {
		return game.getPlayer().getRemainingLives();
	}
	
	/**
	 * Updates the UI alert text based on different conditions.
	 */
	public void updateUIAlertText() {
		String alertText = "";
		
		if (game.getPlayer().hasRevealedEnemy()) alertText = "You've spotted an enemy ninja!";
		else if (game.getPlayer().hasLookedRoom) alertText = "The intel is not in this room.";
		else if (game.getPlayer().hasKilledEnemy()) alertText = "Your gun killed an enemy ninja!";
		else if (game.getPlayer().hasMissedEnemy()) alertText = "You shot your gun, but did not hit an enemy ninja.";
		else if (getDeathStatus()) alertText = "An enemy ninja killed you!";
		
		if (game.getGameOver()) alertText = "You have no lives left. Game Over.";
		else if (game.getGameWon()) alertText = "Congrats! You've found the intel and won the game!";
		else if (game.getPlayer().hasUsedPowerup) alertText = game.getPlayer().oldPowerup.getDescription();
		
		if (game.getPlayer().getIsInvincible()) alertText += "\nYou are invincible, " + game.getPlayer().getInvincibilityTurns() + " turns remaining.";
		
		UI.setAlertText(alertText);
	}
	
	/**
	 * Sets the alert text.
	 *
	 * @param text the new alert text
	 */
	public void setAlertText(String text) {
		UI.setAlertText(text);
	}

	/**
	 * Update UI status text.
	 */
	public void updateUIStatusText() {
		String statusText = "";
		
		statusText += "You have " + game.getPlayer().getRemainingLives() + " lives left. ";
		statusText += "Your gun has " + game.getPlayer().getRemainingAmmo() + " shots left.";
		
		UI.setStatusText(statusText);
	}

	/**
	 * Gets the room check condition.
	 *
	 * @return the room check condition
	 */
	public boolean getRoomCheckCondition() {
		return game.getPlayer().getAboveRoom();
	}

	/**
	 * Toggle UI type.
	 */
	public void toggleUIType() {
		hasMadeUIChoice = true;
		
		UI.dispose();
	
		if (UI instanceof TextUserInterface) {
			UI = new GraphicalUserInterface();
			UI.init(this);
		} else {
			UI = new TextUserInterface();
			UI.init(this);
			redrawUI();
		}
	}

	/**
	 * Gets the player ammo.
	 *
	 * @return the player ammo
	 */
	public int getPlayerAmmo() {
		return game.getPlayer().getRemainingAmmo();
	}
	
	/**
	 * Checks if is running.
	 *
	 * @return true, if is running
	 */
	public boolean isRunning() {
		return close;
	}
	
	/**
 * Gets the paused.
 *
 * @return the paused
 */
public boolean getPaused() {
		return paused;
	}

	/**
	 * Sets the paused.
	 *
	 * @param flag the new paused
	 */
	public void setPaused(boolean flag) {
		
		if (flag) UI.setAlertText("PAUSED");
		else UI.setAlertText(" ");
		
		game.setPaused(flag);
		
		paused = flag;
	}

	/**
	 * Pause enemies.
	 *
	 * @param p the p
	 */
	public void pauseEnemies(boolean p) {
		game.setPaused(p);	
	}

	/**
	 * Checks if user has made an initial UI choice
	 *
	 * @return true, if successful
	 */
	public boolean hasMadeUIChoice() {
		return hasMadeUIChoice;
	}
	
	/**
	 * Sets the game (enemies) to run as turn based or real time
	 *
	 * @param flag the new turn based
	 */
	public void setTurnBased(boolean flag) {

		game.setTurnBased(flag);
		game.setPaused(flag);
	}

	/**
	 * Gets the enemy direction. A bandaid to solve the problem of not being able
	 * to shoot while having spawn protection
	 *
	 * @return the enemy direction
	 */
	public Enum<Direction> getEnemyDirection() {
		return game.getPlayer().adjacentEnemyDirection;
	}

}
