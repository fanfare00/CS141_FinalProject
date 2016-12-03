/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodríguez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Player;



/**
 * The Class ExtraAmmo is a powerup that gives the player 1 extra shots when consumed.
 */
public class ExtraAmmo extends Powerup {
	
	/** The Constant EXTRA_AMMO_SYMBOL. */
	private static final char EXTRA_AMMO_SYMBOL = 'a';
	
	/** The Constant EXTRA_AMMO_AMMOUNT. */
	private static final char EXTRA_AMMO_AMMOUNT = 1;
	
	/** The Constant EXTRA_AMMO_DESCRIPTION. */
	private static final String EXTRA_AMMO_DESCRIPTION = "A cache of ammo yeilds you 1 extra rounds.";

	/**
	 * Instantiates a new extra ammo.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public ExtraAmmo(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = EXTRA_AMMO_SYMBOL;
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup#consume(edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor)
	 */
	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		((Player) consumer).setRemainingAmmo(EXTRA_AMMO_AMMOUNT);
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
		return EXTRA_AMMO_DESCRIPTION;
	}

}
