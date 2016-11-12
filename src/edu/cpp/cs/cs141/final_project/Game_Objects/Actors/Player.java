package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

public class Player extends Actor{
	private boolean isInvincible;

	
	public Player(int row, int col) {
		super(row, col);
		this.symbol = 'P';
	}
}