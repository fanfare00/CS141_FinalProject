package edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

/**
 * {@link Room}s can hold intel, which the player is searching for.
 */
public class Room extends GameObject {

	private boolean hasIntel;
	
	
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
	


	@Override
	public void update(List<GameObject> activeEntities) {
		// TODO Auto-generated method stub
		
	}


}
