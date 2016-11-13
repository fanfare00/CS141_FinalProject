package edu.cpp.cs.cs141.final_project;

import java.io.IOException;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.SaveFileManager;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUserInterface;

public class Application {
	private TextUserInterface UI;
	private Game game;
	
	public Application(TextUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
		
		UI.addApplication(this);
	}

	public void startNewGame() {
		
		loadGame(game.getActiveEntities());
		
		
		UI.beginGame();
	}
	
	@SuppressWarnings("unchecked")
	public void loadGameData() {		
		loadGame((List<GameObject>) SaveFileManager.load());
	}
	
	public void loadGame(List<GameObject> activeEntities) {
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		
		for (GameObject o : activeEntities) {
			UI.addToGrid(o.getRow(), o.getCol(), o.getSymbol());
		}
		
		game.setActiveEntities(activeEntities);
	}
	
	public void saveGameData() {
		SaveFileManager.save(game.getActiveEntities());
	}
	
	public void playerMove() {
	}
	
	public void playerLook() {
		
	}
	
	public void playerShoot() {
		
	}
	
	
}
