package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

public class Invincibility implements Powerup{

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		consumer.setInvincibility(true);
	}

}
