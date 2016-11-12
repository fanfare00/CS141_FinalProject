package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

public class Actor extends GameObject{

	boolean isAlive;
	int ammo;
	
	public Actor(int xPos, int yPos) {
		super(xPos, yPos);
	}

}
