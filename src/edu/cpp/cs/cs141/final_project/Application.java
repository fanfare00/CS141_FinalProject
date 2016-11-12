package edu.cpp.cs.cs141.final_project;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUserInterface;

public class Application {
	private TextUserInterface UI;
	private Game game;
	
	public Application(TextUserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
	}

	public void startNewGame() {
		UI.createGrid(Game.GAME_ROWS, Game.GAME_COLS);
		
		for (GameObject o : game.getActiveEntities()) {
			UI.addToGrid(o.getXPos(), o.getYPos(), o.getSymbol());
		}
		
		UI.beginGame();
	}
	
	public void playerMove() {
	}
	
	public void playerLook() {
		
	}
	
	public void playerShoot() {
		
	}
	
	
}
