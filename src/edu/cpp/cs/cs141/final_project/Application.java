package edu.cpp.cs.cs141.final_project;

import java.io.IOException;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;

/**
 * The {@link Application} class receives commands from the UI and controls the game.
 */
public class Application {
	private IUserInterface UI;
	private Game game;
	
	public Application(IUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
		
		UI.addApplication(this);
	}

	/**
	 * Initializes a new game.
	 */
	public void startNewGame() {
		
		loadGame(game.getActiveEntities());
		
		UI.beginGame();
		gameLoop();
	}
	
	public void moveCurrentActor(int row, int col) {
		game.moveCurrentActor(row, col);
	}
	
	public void lookCurrentActor(int row, int col) {
		game.lookCurrentActor(row, col);
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
		
		updateUI(activeEntities);
		
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
	public void updateUI(List<GameObject> activeEntities)
	{
	    for (int i = 0; i < Game.GAME_ROWS; i++)
	    {
		for (int o = 0; o < game.GAME_COLS; o++)
		{
		    UI.addToGrid(i, o, '\0');
		}
	    }
	    for (GameObject o : activeEntities) {
		UI.addToGrid(o.getRow(), o.getCol(), o.getSymbol());
	    }
	    UI.drawGrid();
	}
	
	/**
	 * The main game loop.
	 */
	private void gameLoop()
	{
	    while (!game.isGameOver())
	    {
		UI.promptCommand();
		game.performEnemyActions();
		
		updateUI(game.getActiveEntities());
	    }
	}
	
	/**
	 * Moves the player in the specified direction.
	 * @param direction 1 = up, 2 = down, 3 = left, 4 = right
	 */
	public boolean playerMove(int direction) {
	    return game.movePlayer(direction);
	}
	

	
	/**
	 * Makes the player shoot.
	 */
	public void playerShoot() {
		
	}
	
	
}
