package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

public class Actor extends GameObject{

	boolean isAlive;
	int ammo;
	
	public Actor(int row, int col) {
		super(row, col);
	}
	
	public void moveRow(int distance) {
		this.row+=distance;
	}
	
	public void moveCol(int distance) {
		this.col+=distance;
	}

}
