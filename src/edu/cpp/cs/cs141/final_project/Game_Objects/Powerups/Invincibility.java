package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Player;

/**
 * The invincibility powerup. This makes the consumer invincible for 5 turns.
 */
public class Invincibility extends GameObject implements Powerup{
	
	private static final char INVINCIBILITY_SYMBOL = 'i';
	private static final int INVINCIBILITY_TURNS = 5;

	
	Actor consumer;

	public Invincibility(int row, int col) {
		super(row, col);
		this.symbol = INVINCIBILITY_SYMBOL;
		
	}

	@Override
	public void consume(Actor consumer) {
		((Player) consumer).setInvincibilityTurns(INVINCIBILITY_TURNS);
		this.consumer = consumer;
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if ((obj instanceof Player) && (checkCollision (obj))) ((Player)obj).setCurrentPowerup(this);
		}	
	}

	@Override
	public String getDescription() {
		String description = "You have gained invincibility for " + INVINCIBILITY_TURNS + " turns.";
		
		//if (((Player) consumer).getInvincibilityTurns() == INVINCIBILITY_TURNS) description = "You have gained invincibility for " + INVINCIBILITY_TURNS + " turns.";
		//else description = "You are invincible for " + ((Player) consumer).getInvincibilityTurns() + " more turns.";
		
		return description;
	}

}
