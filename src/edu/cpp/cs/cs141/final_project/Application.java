package edu.cpp.cs.cs141.final_project;

public class Application {
	private UserInterface UI;
	private Game game;
	
	public Application(UserInterface UI, Game game) {
		this.UI = UI;
		this.game = game;
	}

	public void startGame() {
		UI.beginGame();
	}
	
	public void playerMove() {
		///if(game.attemptPlayerMove()) 
	}
	
	public void playerLook() {
		
	}
	
	public void playerShoot() {
		
	}
	
	
}
