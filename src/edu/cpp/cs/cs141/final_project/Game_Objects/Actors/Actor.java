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

	protected static final int MAX_DIRECTIONS = Direction.values().length;
	
	protected List<Actor> nearbyActors = new ArrayList<Actor>();
	
	boolean isAlive;
	boolean invincible;
	boolean canAttack = false;
	int ammo;
	
	boolean[] moveConditions;
	
	protected boolean aboveRoom;
	
	//protected ActorState state;
	
	protected Direction moveDir;
	
	protected Powerup currentPowerup = null;
	
	public Actor(int row, int col) {
		super(row, col);
		
	}
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
	
	public void setMoveDirection(Direction dir){
		this.moveDir = dir;
	}
	
	public void attack(Direction dir){
		
	}
	
	public void init(List<GameObject> activeEntities) {
		updateState(activeEntities);
	}
	
	public void move(){
		if (moveDir == null) return;
		
		row += moveDir.row();
		col += moveDir.col();
		
		moveDir = null;
	}
	
	protected void updateAttackStatus() {
		//canAttack = !(nearbyActors.isEmpty());
		//nearbyActors.clear();
		
//		canAttack = false;
//		
//		for (int i = 0; i < state.getProximityConditions().length; i++) {
//			if (state.getProximityConditions()[i]) canAttack = true;
//		}
	}
	
	public boolean getCanAttack() {	
		return canAttack;
	}
	
	public boolean[] getMoveConditions() {
		return moveConditions;
	}
	
	public boolean getAboveRoom() {
		boolean _aboveRoom = aboveRoom;
		aboveRoom = false;
		
		return _aboveRoom;
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		//move();
		//updateState(activeEntities);
		//updateShootStatus();
	}



}
