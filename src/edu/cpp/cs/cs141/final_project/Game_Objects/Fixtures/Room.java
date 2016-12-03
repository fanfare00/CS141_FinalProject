/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodr�guez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;


/**
 * {@link Room}s can hold intel, which the player is searching for.
 */
public class Room extends GameObject {

	/** The has intel. */
	private boolean hasIntel;
	
	
	/**
	 * Instantiates a new room.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Room(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = 'R';
		hasIntel = false;
	}
	
	/**
	 * Places the intel in this room.
	 */
	public void placeIntel() {
		hasIntel = true;
		//this.symbol = 'I';
	}
	
	/**
	 * Checks if this room has the intel.
	 * @return True if the room has the intel, false otherwise.
	 */
	public boolean hasIntel()
	{
	    return hasIntel;
	}
	


	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.GameObject#update(java.util.List)
	 */
	@Override
	public void update(List<GameObject> activeEntities) {
		// TODO Auto-generated method stub
		
	}


}
