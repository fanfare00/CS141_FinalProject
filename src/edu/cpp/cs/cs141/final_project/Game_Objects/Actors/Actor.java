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
package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.Room;
import edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

/**
 * A {@link GameObject} that is capable of moving around.
 */
public abstract class Actor extends GameObject{

	/** The Constant MAX_DIRECTIONS. */
	protected static final int MAX_DIRECTIONS = Direction.values().length;
	
	/** The nearby actors. */
	protected List<Actor> nearbyActors = new ArrayList<Actor>();
	
	/** The is alive. */
	boolean isAlive;
	
	/** The invincible. */
	boolean invincible;
	
	/** The can attack. */
	boolean canAttack = false;
	
	/** The ammo. */
	int ammo;
	
	/** The move conditions. */
	boolean[] moveConditions;
	
	/** The above room. */
	protected boolean aboveRoom;
	
	//protected ActorState state;
	
	/** The move dir. */
	protected Direction moveDir;
	
	/** The current powerup. */
	protected Powerup currentPowerup = null;
	
	/**
	 * Instantiates a new actor.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Actor(int row, int col) {
		super(row, col);
		
	}
	
	/**
	 * Update state.
	 *
	 * @param activeEntities the active entities
	 */
	public void updateState(List<GameObject> activeEntities) {

		canAttack = false;
		boolean[] _moveConditions = new boolean[MAX_DIRECTIONS];
		
		
		for(Direction dir : Direction.values()) {
			_moveConditions[dir.ordinal()] = !checkOutOfBounds(MAX_ROW, MAX_COL, dir);
		}
		
		for (GameObject obj : activeEntities) {
			if (obj.equals(this)) continue;
			
			if ((obj instanceof Room) && (checkCollision(obj, Direction.DOWN))) this.aboveRoom = true;
			
			for(Direction dir : Direction.values()) {
				if (checkCollision( obj, dir) && obj instanceof Actor) { 
					_moveConditions[dir.ordinal()] = false;
					nearbyActors.add((Actor) obj);
					this.updateAttackStatus();
				}
				else if (checkCollision( obj, dir) && !(obj instanceof Powerup)) _moveConditions[dir.ordinal()] = false;
				
			}
		}
		
		this.moveConditions = _moveConditions;
	}
	
	/**
	 * Changes the {@link Actor}'s invincibility status.
	 * @param invincible If true, makes the {@link Actor} invincible. If false, makes it vulnerable.
	 */
	public void setInvincibility(boolean invincible)
	{
	    this.invincible = invincible;
	}
	
	/**
	 * Sets the move direction.
	 *
	 * @param dir the new move direction
	 */
	public void setMoveDirection(Direction dir){
		this.moveDir = dir;
	}
	
	/**
	 * Attack.
	 *
	 * @param dir the dir
	 */
	public void attack(Direction dir){
		
	}
	
	/**
	 * Inits the.
	 *
	 * @param activeEntities the active entities
	 */
	public void init(List<GameObject> activeEntities) {
		updateState(activeEntities);
	}
	
	/**
	 * Move.
	 */
	public void move(){
		if (moveDir == null) return;
		
		row += moveDir.row();
		col += moveDir.col();
		
		moveDir = null;
	}
	
	/**
	 * Update attack status.
	 */
	protected void updateAttackStatus() {
		//canAttack = !(nearbyActors.isEmpty());
		//nearbyActors.clear();
		
//		canAttack = false;
//		
//		for (int i = 0; i < state.getProximityConditions().length; i++) {
//			if (state.getProximityConditions()[i]) canAttack = true;
//		}
	}
	
	/**
	 * Gets the can attack.
	 *
	 * @return the can attack
	 */
	public boolean getCanAttack() {	
		return canAttack;
	}
	
	/**
	 * Gets the move conditions.
	 *
	 * @return the move conditions
	 */
	public boolean[] getMoveConditions() {
		return moveConditions;
	}
	
	/**
	 * Gets the above room.
	 *
	 * @return the above room
	 */
	public boolean getAboveRoom() {
		boolean _aboveRoom = aboveRoom;
		aboveRoom = false;
		
		return _aboveRoom;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.GameObject#update(java.util.List)
	 */
	@Override
	public void update(List<GameObject> activeEntities) {
		//move();
		//updateState(activeEntities);
		//updateShootStatus();
	}



}
