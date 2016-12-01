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
	
	private IUserInterface UI;
	private Game game;
	private boolean close;
	
	private boolean paused;
	private boolean hasMadeUIChoice;
	
	
	public Application(IUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
		
		//start();
	}

	public void run() {
		if (getGameOverStatus()) { 
			UI.toggleMenuState();
			paused = true;
		}
			
		if (!paused) {
			UI.update();
			game.update();
			redrawUI();	
		}
	}
	
	/**
	 * Initializes the game.
	 */
	public void start() {
//		close = false;
		
		UI.init(this);
		loadGame(game.getActiveEntities());
		//run();
	}
	
	/**
	 * Prepares a new game.
	 */
	public void startNewGame() {
		hasMadeUIChoice = true;
		
		game = new Game();
		loadGame(game.getActiveEntities());
		UI.toggleMoveState();
		redrawUI();
		paused = false;
		
	}
	
	public void loadGameFromFile() {
		hasMadeUIChoice = true;
		
		game = new Game();
		loadGameData();
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		redrawUI();
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
		game.setActiveEntities(activeEntities);
		paused = false;
		//UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		//redrawUI();
	}
	
	/**
	 * Saves the current game state to the disk.
	 */
	public void saveGameData() {
		SaveFileManager.save(game.getActiveEntities());
		UI.setAlertText("Game saved to file.");
		//UI.update();
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
		game.dispose();
		this.close = true;
		//System.exit(0);
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
		if (!game.getPlayer().hasMadeFirstMove) return false;
		return game.getPlayer().getHasDiedRecently();
	}
	
	public boolean getGameOverStatus() {
		return (game.getGameOver() | game.getGameWon());
	}
	
	public int getPlayerLives() {
		return game.getPlayer().getRemainingLives();
	}
	
	public void updateUIAlertText() {
		String alertText = "";
		
		if (game.getPlayer().hasRevealedEnemy()) alertText = "You've spotted an enemy ninja!";
		else if (game.getPlayer().hasLookedRoom) alertText = "The intel is not in this room.";
		else if (game.getPlayer().hasKilledEnemy()) alertText = "Your gun killed an enemy ninja!";
		else if (game.getPlayer().hasMissedEnemy()) alertText = "You shot your gun, but did not hit an enemy ninja.";
		else if (getDeathStatus()) alertText = "An enemy ninja killed you!";
		else if (game.getGameOver()) alertText = "You have no lives left. Game Over.";
		else if (game.getGameWon()) alertText = "Congrats! You've found the intel and won the game!";
		else if (game.getPlayer().hasUsedPowerup) alertText = game.getPlayer().oldPowerup.getDescription();
		
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
		hasMadeUIChoice = true;
		
		UI.dispose();
	
		if (UI instanceof TextUserInterface) {
			UI = new GraphicalUserInterface();
			UI.init(this);
		} else {
			UI = new TextUserInterface();
			UI.init(this);
			redrawUI();
			//UI.update();
		}
	}

	public int getPlayerAmmo() {
		return game.getPlayer().getRemainingAmmo();
	}
	
	public boolean isRunning() {
		return close;
	}

//	public void setClose(boolean flag) {
//		close = flag;
//	}
	
	public boolean getPaused() {
		return paused;
	}

	public void setPaused(boolean flag) {
		
		if (flag) UI.setAlertText("PAUSED");
		else UI.setAlertText(" ");
		
		game.togglePause(flag);
		
		paused = flag;
		
	}

	public void pauseEnemies(boolean p) {
		game.togglePause(p);	
	}

	public boolean hasMadeUIChoice() {
		return hasMadeUIChoice;
	}
	
	public void setGameType(boolean flag) {
		game.setTurnBased(flag);
		game.togglePause(flag);
	}

}
