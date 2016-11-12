package edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

public class Room extends GameObject {

	private boolean hasIntel;
	
	
	public Room(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = 'R';
		hasIntel = false;
	}
	
	public void placeIntel() {
		hasIntel = true;
		this.symbol = 'I';
	}


}
