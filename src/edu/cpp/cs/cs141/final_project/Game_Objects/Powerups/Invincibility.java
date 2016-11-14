package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

/**
 * The invincibility powerup. This makes the consumer invincible for 5 turns.
 */
public class Invincibility implements Powerup{

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		consumer.setInvincibility(true);
		// need to notify the Application to disable invincibility on this Actor in 5 turns.
	}

}
