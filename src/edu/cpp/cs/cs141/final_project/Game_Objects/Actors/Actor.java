package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

/**
 * A {@link GameObject} that is capable of moving around.
 */
public class Actor extends GameObject{

	boolean isAlive;
	boolean invincible;
	int ammo;
	private ActorState state;
	
	public Actor(int row, int col) {
		super(row, col);
		
	}
	
	public void move(int row, int col){
		
	}
	
	/**
	 * Moves the {@link Actor} by the specified number of rows down.
	 * @param distance The number of rows to move the {@link Actor} down. A negative number moves it up. 
	 */
	public void moveRow(int distance) {
		this.row+=distance;
	}
	
	/**
	 * Moves the {@link Actor} by the specified number of columns to the right.
	 * @param distance The number of columns to move the {@link Actor} to the right. A negative number moves it left. 
	 */
	public void moveCol(int distance) {
		this.col+=distance;
	}
	
	/**
	 * Changes the {@link Actor}'s invincibility status.
	 * @param invincible If true, makes the {@link Actor} invincible. If false, makes it vulnerable.
	 */
	public void setInvincibility(boolean invincible)
	{
	    this.invincible = invincible;
	}

	public void look(int row, int col) {
		// TODO Auto-generated method stub
		
	}
	
	public void setState(ActorState state) {
		this.state = state;
	}
	
	public ActorState getState() {
		return state;
	}


}
