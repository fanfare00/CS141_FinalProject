package edu.cpp.cs.cs141.final_project;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;
import edu.cpp.cs.cs141.final_project.Utilities.SaveFileManager;

/**
 * The {@link Application} class receives commands from the UI and controls the game.
 */
public class Application {
	
	private IUserInterface UI;
	private Game game;
	private boolean close;
	
	public Application(IUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
		
		UI.init(this);
		startNewGame();
	}

	public void run() {
		UI.update();
		game.update();
		updateUIGrid();
		
		if (!close) run();
	}
	
	/**
	 * Initializes a new game.
	 */
	public void startNewGame() {	
		loadGame(game.getActiveEntities());
		run();
	}
	
	public void playerMove(Direction dir) {
		
		game.getPlayer().setMoveDirection(dir);
	}
	
	public void playerLook(Direction dir) {
		game.playerLook(dir);
		updateUIGrid();
		UI.toggleMoveState();
		UI.update();
		
	}
	
	public void playerAttack(Direction dir) {
		game.getPlayer().attack(dir);
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
		updateUIGrid();
		
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
	public void updateUIGrid()
	{  
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
	    for (GameObject o : game.getActiveEntities()) {
	    	if (o.isVisible()) UI.addToGrid(o.getRow(), o.getCol(), o.getSymbol());
	    }
	}
	
	public void getNewCommand() {
		UI.setStatusText("Invalid Entry - See the above list for valid commands.");
		UI.getAndHandleInput();
	}
	
	public void close() {
		this.close = true;
	}
	
	public boolean[] getDirectionalConditions() {
		return game.getPlayer().getState().getMoveConditions();
	}
	
	public boolean[] getProximityConditions() {
		return game.getPlayer().getState().getProximityConditions();
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
		updateUIGrid();
		UI.update();
	}

	public boolean getLookStatus() {
		return game.getPlayer().getCanLook();
	}

	public boolean getShootStatus() {
		return game.getPlayer().getCanAttack();
	}




}
