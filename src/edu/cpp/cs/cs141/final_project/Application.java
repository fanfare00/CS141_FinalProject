package edu.cpp.cs.cs141.final_project;

import java.io.IOException;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.SaveFileManager;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;

public class Application {
	private IUserInterface UI;
	private Game game;
	
	public Application(IUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
		
		UI.addApplication(this);
	}

	public void startNewGame() {
		
		loadGame(game.getActiveEntities());
		
		UI.beginGame();
		gameLoop();
	}
	
	@SuppressWarnings("unchecked")
	public void loadGameData() {		
		loadGame((List<GameObject>) SaveFileManager.load());
	}
	
	public void loadGame(List<GameObject> activeEntities) {
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		
		updateUI(activeEntities);
		
		game.setActiveEntities(activeEntities);
	}
	
	public void saveGameData() {
		SaveFileManager.save(game.getActiveEntities());
	}
	
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
	
	public void playerLook() {
		
	}
	
	public void playerShoot() {
		
	}
	
	
}
