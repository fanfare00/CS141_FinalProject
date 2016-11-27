package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

/**
 * The interface for Powerups. Powerups can be consumed by the player and have different effects.
 */
public abstract class Powerup extends GameObject {

    public Powerup(int row, int col) {
		super(row, col);
		// TODO Auto-generated constructor stub
	}

	/**
     * Makes the specified {@link Actor} consume the {@link Powerup}.
     * @param consumer
     */
	public abstract void consume(Actor consumer);
	
	public abstract String getDescription();
}
