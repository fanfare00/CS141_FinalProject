package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

/**
 * A hostile {@link Actor} that tries to attack the {@link Player}.
 * @author oadug
 *
 */
public class Enemy extends Actor {
	private static final String ENEMY_NAME = "Ninja";
	private static final char ENEMY_SYMBOL = 'N';
	
	public Enemy(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = ENEMY_SYMBOL;
		this.name = ENEMY_NAME;
	}

}
