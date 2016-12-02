package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.Room;
import edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

/**
 * The {@link Actor} that the user controls.
 */
public class Player extends Actor{
	private boolean isInvincible;

	private static final String PLAYER_NAME = "Player";
	private static final char PLAYER_SYMBOL = 'P';
	
	
	private Direction lookDir;
	private boolean canLook = true;
	private boolean hasDiedRecently = true;
	
	private int startingRow;
	private int startingCol;
	
	private int remainingLives;
	private int remainingAmmo;
	private int remainingTurnsInvincible = 0;

	private boolean hasRadar;
	
	private boolean foundIntel;
	
	private boolean revealedEnemy;
	
	private boolean killedEnemy;
	
	private boolean missedEnemy;
	
	public boolean hasMadeFirstMove;
	public boolean hasLookedRoom;
	public boolean hasUsedPowerup;
	
	public Powerup oldPowerup;
	
	public Direction adjacentEnemyDirection;
	
	public Player(int row, int col, int maxLives, int maxAmmo) {
		super(row, col);
		this.startingRow = row;
		this.startingCol = col;

		this.remainingLives = maxLives;
		this.remainingAmmo = maxAmmo;
		
		this.symbol = PLAYER_SYMBOL;
		this.name = PLAYER_NAME;
		this.isAlive = true;
		this.setVisible(true);
	}
	
	public void setLookDir(Direction dir) {
		this.lookDir = dir;
	}
	
	
	@Override
	public void updateState(List<GameObject> activeEntities) {

		canAttack = false;
		boolean[] _moveConditions = new boolean[MAX_DIRECTIONS];
		
		for(Direction dir : Direction.values()) {
			_moveConditions[dir.ordinal()] = !checkOutOfBounds(MAX_ROW, MAX_COL, dir);
		}
		
		for (GameObject obj : activeEntities) {
			if (obj.equals(this)) continue;
			
			if ((obj instanceof Room) && (checkCollision(obj, Direction.DOWN))) this.aboveRoom = true;
			if ((obj instanceof Enemy) && (obj.getRow() == this.row)) nearbyActors.add((Actor)obj);
			if ((obj instanceof Enemy) && (obj.getCol() == this.col)) nearbyActors.add((Actor)obj);
			
			for(Direction dir : Direction.values()) {
				if (checkCollision( obj, dir) && obj instanceof Actor) { 
					_moveConditions[dir.ordinal()] = false;
					this.adjacentEnemyDirection = dir;
				}
				else if (checkCollision( obj, dir) && !(obj instanceof Powerup)) _moveConditions[dir.ordinal()] = false;
			}

		}
		
		this.moveConditions = _moveConditions;
	}
	
	public void look(List<GameObject> activeEntities) {
		revealNearby(activeEntities);
		
		if (canLook) {
			for (GameObject obj : activeEntities) {
				if(obj.equals(this)) continue;
				
				if (checkCollision(obj, lookDir.row()*2, lookDir.col()*2)) { 
					obj.setIsLookedAt(true);
					
					if (obj instanceof Enemy) {
						revealedEnemy = true;
					}
				}
				
				if (obj instanceof Room) {
					if (checkCollision(obj, lookDir.row(), lookDir.col())) { 
						hasLookedRoom = true;
						if (((Room)obj).hasIntel()) {
							obj.setSymbol('I');
							foundIntel = true;
							hasLookedRoom = false;
						}
					}
				}
			}
			canLook = false;
			hasDiedRecently = false;
			hasMadeFirstMove = true;
		}
		
	}
	
	public void attack(Direction dir) {
		this.canAttack = false;
		Actor targetedEnemy = null;
		
		if (dir == Direction.UP) {
			for (Actor actor : nearbyActors) {
				if ((actor.getRow() < this.row) && (actor.getCol() == this.col)){
					if (targetedEnemy != null) {
						if (actor.getRow() > targetedEnemy.getRow()) targetedEnemy = actor;
					} else {
						targetedEnemy = actor;
					}
				}
			}
		}
		
		if (dir == Direction.DOWN) {	
			for (Actor actor : nearbyActors) {
				if ((actor.getRow() > this.row)  && (actor.getCol() == this.col)){
					if (targetedEnemy != null) {
						if (actor.getRow() < targetedEnemy.getRow()) targetedEnemy = actor;
					} else {
						targetedEnemy = actor;
					}
				}
			}
		}
			
		if (dir == Direction.LEFT) {	
			for (Actor actor : nearbyActors) {
				if ((actor.getCol() < this.col)  && (actor.getRow() == this.row)){
					if (targetedEnemy != null) {
						if (actor.getCol() > targetedEnemy.getCol()) targetedEnemy = actor;
					} else {
						targetedEnemy = actor;
					}
				}
			}
		}
		
		if (dir == Direction.RIGHT) {	
			for (Actor actor : nearbyActors) {
				if ((actor.getCol() > this.col) && (actor.getRow() == this.row)){
					if (targetedEnemy != null) {
						if (actor.getCol() < targetedEnemy.getCol()) targetedEnemy = actor;
					} else {
						targetedEnemy = actor;
					}
				}
			}
		}

		if (targetedEnemy == null && remainingAmmo > 0) missedEnemy = true;
		
	    if ((remainingAmmo > 0) && (targetedEnemy != null)) {
	    	targetedEnemy.setActive(false);
	    	killedEnemy = true;
			nearbyActors.clear();
	    }
	    
	    this.remainingAmmo -= 1;
	}
	
	
	public void revealNearby(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if(obj.equals(this)) continue;
			if(hasRadar && obj instanceof Room && ((Room) obj).hasIntel()) obj.setVisible(true);

			//obj.setVisible(false);
			
			for (Direction dir : Direction.values()){
				if (checkCollision(obj, dir.row(), dir.col())) {
					obj.setVisible(true);
				}
				
			}
		}
		//hasRadar = false;
	}
	
	public void setRemainingLives(int remainingLives){
		this.remainingLives = remainingLives;
	}
	
	public boolean getCanLook() {
		return this.canLook;
	}
	
	public boolean getHasDiedRecently() {
		return this.hasDiedRecently;
	}
	
	public void reset() {
		if (!isInvincible){
			this.row = startingRow;
			this.col = startingCol;
			this.remainingLives-=1;
			this.hasDiedRecently = true;
			this.canLook = true;
		}
	}
	
	public void updatePowerup() {
		if (remainingTurnsInvincible == 0) isInvincible = false;
		
		if (this.currentPowerup == null) return;
		
		currentPowerup.consume(this);
		oldPowerup = currentPowerup;
		((GameObject) currentPowerup).setActive(false);
		hasUsedPowerup = true;
	}
	
	
	@Override
	public void update(List<GameObject> activeEntities) {
		currentPowerup = null;
		
		if (moveDir != null) {
			canLook = true;
			hasDiedRecently = false;
			hasRadar = false;
			
			killedEnemy = false;
			revealedEnemy = false;
			missedEnemy = false;
			hasMadeFirstMove = true;
			hasLookedRoom = false;
			hasUsedPowerup = false;
		
			if(isInvincible) remainingTurnsInvincible-=1;
			setVisible(true);
		
			//updateState(activeEntities);
			move();
		
			setLookDir(null);

			for (GameObject obj : activeEntities) {
				obj.setIsLookedAt(false);
			}
		
		}
	}

	public int getRemainingLives() {
		return remainingLives;
	}
	
	public int getRemainingAmmo() {
		return remainingAmmo;
	}

	public void setRemainingAmmo(int extraAmmoAmount) {
		remainingAmmo += extraAmmoAmount;
		
	}

	public void setInvincibilityTurns(int invincibilityTurns) {
		remainingTurnsInvincible += invincibilityTurns;
		isInvincible = true;
	}
	
	public int getInvincibilityTurns() {
		return remainingTurnsInvincible;
	}

	public void setHasRadar(boolean b) {
		this.hasRadar = true;
	}
	
	public Powerup getCurrentPowerup() {
		return this.currentPowerup;
	}

	public void setCurrentPowerup(Powerup obj) {
		this.currentPowerup = obj;
		
	}
	
	@Override
	public boolean getCanAttack() {	
		return remainingAmmo > 0 ;
	}
	
	public boolean foundIntel() {
		return foundIntel;
	}
	
	public boolean getIsInvincible() {
		// TODO Auto-generated method stub
		return isInvincible;
	}

	public boolean hasRevealedEnemy() {
		return revealedEnemy;
	}

	public void setRevealedEnemy(boolean revealedEnemy) {
		this.revealedEnemy = revealedEnemy;
	}

	public boolean hasKilledEnemy() {
		return killedEnemy;
	}

	public void setKilledEnemy(boolean killedEnemy) {
		this.killedEnemy = killedEnemy;
	}

	public boolean hasMissedEnemy() {
		return missedEnemy;
	}

	public void setMissedEnemy(boolean missedEnemy) {
		this.missedEnemy = missedEnemy;
	}

}