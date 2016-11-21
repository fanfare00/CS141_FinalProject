package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

/**
 * A {@link GameObject} that is capable of moving around.
 */
public class Actor extends GameObject{

	private static final int MAX_DIRECTIONS = Direction.values().length;
	
	boolean isAlive;
	boolean invincible;
	int ammo;
	
	private ActorState state;
	
	private Direction moveDir;
	
	public Actor(int row, int col) {
		super(row, col);
		
	}
	public void updateState(List<GameObject> activeEntities){

		boolean[] proximityConditions = new boolean[MAX_DIRECTIONS];
		boolean[] moveConditions = new boolean[MAX_DIRECTIONS];
		
		for(Direction dir : Direction.values()) {
			moveConditions[dir.ordinal()] = !checkOutOfBounds(MAX_ROW, MAX_COL, dir);
		}
		
		for (GameObject obj : activeEntities) {
			if (obj.equals(this)) continue;
			
			for(Direction dir : Direction.values()) {
				if (checkCollision( obj, dir) && obj instanceof Actor) { 
					proximityConditions[dir.ordinal()] = true;
					moveConditions[dir.ordinal()] = false;
				}
				else if (checkCollision( obj, dir)) moveConditions[dir.ordinal()] = false;
			}
		}
		
		state = new ActorState(moveConditions, proximityConditions);
	}
	
	/**
	 * Changes the {@link Actor}'s invincibility status.
	 * @param invincible If true, makes the {@link Actor} invincible. If false, makes it vulnerable.
	 */
	public void setInvincibility(boolean invincible)
	{
	    this.invincible = invincible;
	}
	
	public void setMoveDirection(Direction dir){
		this.moveDir = dir;
	}
	
	public void attack(Direction dir){
		
	}
	
	public void init(List<GameObject> activeEntities) {
		updateState(activeEntities);
	}
	
	public void move(){
		
		row += moveDir.row();
		col += moveDir.col();
	}
	
	public ActorState getState() {
		return state;
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		//move();
		//updateState(activeEntities);
	}


}
