package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

/**
 * The interface for Powerups. Powerups can be consumed by the player and have different effects.
 */
public interface Powerup {

    /**
     * Makes the specified {@link Actor} consume the {@link Powerup}.
     * @param consumer
     */
	public void consume(Actor consumer);
}
