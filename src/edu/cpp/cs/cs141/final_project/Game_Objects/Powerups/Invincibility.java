package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Player;

// TODO: Auto-generated Javadoc
/**
 * The invincibility powerup. This makes the consumer invincible for 5 turns.
 */
public class Invincibility extends Powerup {
	
	/** The Constant INVINCIBILITY_SYMBOL. */
	private static final char INVINCIBILITY_SYMBOL = 'i';
	
	/** The Constant INVINCIBILITY_TURNS. */
	private static final int INVINCIBILITY_TURNS = 5;

	
	/** The consumer. */
	Actor consumer;

	/**
	 * Instantiates a new invincibility.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Invincibility(int row, int col) {
		super(row, col);
		this.symbol = INVINCIBILITY_SYMBOL;
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup#consume(edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor)
	 */
	@Override
	public void consume(Actor consumer) {
		((Player) consumer).setInvincibilityTurns(INVINCIBILITY_TURNS);
		this.consumer = consumer;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.GameObject#update(java.util.List)
	 */
	@Override
	public void update(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if ((obj instanceof Player) && (checkCollision (obj))) ((Player)obj).setCurrentPowerup(this);
		}	
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup#getDescription()
	 */
	@Override
	public String getDescription() {
		String description = "You have gained invincibility for " + INVINCIBILITY_TURNS + " turns.";
		
		//if (((Player) consumer).getInvincibilityTurns() == INVINCIBILITY_TURNS) description = "You have gained invincibility for " + INVINCIBILITY_TURNS + " turns.";
		//else description = "You are invincible for " + ((Player) consumer).getInvincibilityTurns() + " more turns.";
		
		return description;
	}

}
