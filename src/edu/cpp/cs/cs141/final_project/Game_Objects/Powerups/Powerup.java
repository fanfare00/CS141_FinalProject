package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;


/**
 * The abstract class for Powerups. Powerups can be consumed by the player and have different effects.
 */
public abstract class Powerup extends GameObject {

    /**
     * Instantiates a new powerup.
     *
     * @param row the row
     * @param col the col
     */
    public Powerup(int row, int col) {
		super(row, col);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Makes the specified {@link Actor} consume the {@link Powerup}.
	 *
	 * @param consumer the consumer
	 */
	public abstract void consume(Actor consumer);
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public abstract String getDescription();
}
