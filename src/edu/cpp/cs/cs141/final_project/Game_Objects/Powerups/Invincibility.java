package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

/**
 * The invincibility powerup. This makes the consumer invincible for 5 turns.
 */
public class Invincibility extends GameObject implements Powerup{
	
	private static final char INVINCIBILITY_SYMBOL = 'i';

	public Invincibility(int row, int col) {
		super(row, col);
		this.symbol = INVINCIBILITY_SYMBOL;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		System.out.println("TEST INVC CONSUME");
		// need to notify the Application to disable invincibility on this Actor in 5 turns.
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		// TODO Auto-generated method stub
		
	}

}
