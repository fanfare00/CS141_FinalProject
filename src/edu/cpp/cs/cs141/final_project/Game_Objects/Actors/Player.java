package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

/**
 * The {@link Actor} that the user controls.
 */
public class Player extends Actor{
	private boolean isInvincible;

	private static final String PLAYER_NAME = "Player";
	private static final char PLAYER_SYMBOL = 'P';
	
	public Player(int row, int col) {
		super(row, col);
		this.symbol = PLAYER_SYMBOL;
		this.name = PLAYER_NAME;
	}
}