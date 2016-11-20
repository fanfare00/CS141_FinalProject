package edu.cpp.cs.cs141.final_project;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;

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
		updateUIGrid(game.getActiveEntities());
		
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
		game.getPlayer().setLookDir(dir);
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
		updateUIGrid(activeEntities);
		
		game.setActiveEntities(activeEntities);
	}
	
	/**
	 * Saves the current game state to the disk.
	 */
	public void saveGameData() {
		SaveFileManager.save(game.getActiveEntities());
	}
	
	/**
	 * Updates the UI with the currently active entities.
	 * @param activeEntities The entities to be displayed on the UI.
	 */
	public void updateUIGrid(List<GameObject> activeEntities)
	{  
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
	    for (GameObject o : activeEntities) {
	    	UI.addToGrid(o.getRow(), o.getCol(), o.getSymbol());
	    }
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
	}

	public void toggleMoveMode() {
		UI.toggleMoveState();
	}

	public void toggleLookMode() {
		UI.toggleLookState();
	}

	public void toggleShootMode() {
		UI.toggleShootState();
		
	}

	public boolean getCanLook() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean getShootStatus() {
		boolean canShoot = false;
		
		for (int i = 0; i < game.getPlayer().getState().getProximityConditions().length; i++) {
			if (game.getPlayer().getState().getProximityConditions()[i]) canShoot = true;
		}
		
		return canShoot;
	}




}
