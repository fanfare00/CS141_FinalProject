package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.Room;
import edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

// TODO: Auto-generated Javadoc
/**
 * The {@link Actor} that the user controls.
 */
public class Player extends Actor{
	
	/** The is invincible. */
	private boolean isInvincible;

	/** The Constant PLAYER_NAME. */
	private static final String PLAYER_NAME = "Player";
	
	/** The Constant PLAYER_SYMBOL. */
	private static final char PLAYER_SYMBOL = 'P';
	
	/** The look dir. */
	private Direction lookDir;
	
	/** The can look. */
	private boolean canLook = true;
	
	/** The has died recently. */
	private boolean hasDiedRecently = true;
	
	/** The starting row. */
	private int startingRow;
	
	/** The starting col. */
	private int startingCol;
	
	/** The remaining lives. */
	private int remainingLives;
	
	/** The remaining ammo. */
	private int remainingAmmo;
	
	/** The remaining turns invincible. */
	private int remainingTurnsInvincible = 0;

	/** The has radar. */
	private boolean hasRadar;
	
	/** The found intel. */
	private boolean foundIntel;
	
	/** The revealed enemy. */
	private boolean revealedEnemy;
	
	/** The killed enemy. */
	private boolean killedEnemy;
	
	/** The missed enemy. */
	private boolean missedEnemy;
	
	/** The has made first move. */
	public boolean hasMadeFirstMove;
	
	/** The has looked room. */
	public boolean hasLookedRoom;
	
	/** The has used powerup. */
	public boolean hasUsedPowerup;
	
	/** The old powerup. */
	public Powerup oldPowerup;
	
	/** The adjacent enemy direction. */
	public Direction adjacentEnemyDirection;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param row the row
	 * @param col the col
	 * @param maxLives the max lives
	 * @param maxAmmo the max ammo
	 */
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
	
	/**
	 * Sets the look dir.
	 *
	 * @param dir the new look dir
	 */
	public void setLookDir(Direction dir) {
		this.lookDir = dir;
	}
	
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor#updateState(java.util.List)
	 */
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
	
	/**
	 * Look.
	 *
	 * @param activeEntities the active entities
	 */
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
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor#attack(edu.cpp.cs.cs141.final_project.Utilities.Direction)
	 */
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
	
	
	/**
	 * Reveal nearby.
	 *
	 * @param activeEntities the active entities
	 */
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
	
	/**
	 * Sets the remaining lives.
	 *
	 * @param remainingLives the new remaining lives
	 */
	public void setRemainingLives(int remainingLives){
		this.remainingLives = remainingLives;
	}
	
	/**
	 * Gets the can look.
	 *
	 * @return the can look
	 */
	public boolean getCanLook() {
		return this.canLook;
	}
	
	/**
	 * Gets the checks for died recently.
	 *
	 * @return the checks for died recently
	 */
	public boolean getHasDiedRecently() {
		return this.hasDiedRecently;
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		if (!isInvincible){
			this.row = startingRow;
			this.col = startingCol;
			this.remainingLives-=1;
			this.hasDiedRecently = true;
			this.canLook = true;
		}
	}
	
	/**
	 * Update powerup.
	 */
	public void updatePowerup() {
		if (remainingTurnsInvincible == 0) isInvincible = false;
		
		if (this.currentPowerup == null) return;
		
		currentPowerup.consume(this);
		oldPowerup = currentPowerup;
		((GameObject) currentPowerup).setActive(false);
		hasUsedPowerup = true;
	}
	
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor#update(java.util.List)
	 */
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

	/**
	 * Gets the remaining lives.
	 *
	 * @return the remaining lives
	 */
	public int getRemainingLives() {
		return remainingLives;
	}
	
	/**
	 * Gets the remaining ammo.
	 *
	 * @return the remaining ammo
	 */
	public int getRemainingAmmo() {
		return remainingAmmo;
	}

	/**
	 * Sets the remaining ammo.
	 *
	 * @param extraAmmoAmount the new remaining ammo
	 */
	public void setRemainingAmmo(int extraAmmoAmount) {
		remainingAmmo += extraAmmoAmount;
		
	}

	/**
	 * Sets the invincibility turns.
	 *
	 * @param invincibilityTurns the new invincibility turns
	 */
	public void setInvincibilityTurns(int invincibilityTurns) {
		remainingTurnsInvincible += invincibilityTurns;
		isInvincible = true;
	}
	
	/**
	 * Gets the invincibility turns.
	 *
	 * @return the invincibility turns
	 */
	public int getInvincibilityTurns() {
		return remainingTurnsInvincible;
	}

	/**
	 * Sets the checks for radar.
	 *
	 * @param b the new checks for radar
	 */
	public void setHasRadar(boolean b) {
		this.hasRadar = true;
	}
	
	/**
	 * Gets the current powerup.
	 *
	 * @return the current powerup
	 */
	public Powerup getCurrentPowerup() {
		return this.currentPowerup;
	}

	/**
	 * Sets the current powerup.
	 *
	 * @param obj the new current powerup
	 */
	public void setCurrentPowerup(Powerup obj) {
		this.currentPowerup = obj;
		
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor#getCanAttack()
	 */
	@Override
	public boolean getCanAttack() {	
		return remainingAmmo > 0 ;
	}
	
	/**
	 * Found intel.
	 *
	 * @return true, if successful
	 */
	public boolean foundIntel() {
		return foundIntel;
	}
	
	/**
	 * Gets the checks if is invincible.
	 *
	 * @return the checks if is invincible
	 */
	public boolean getIsInvincible() {
		// TODO Auto-generated method stub
		return isInvincible;
	}

	/**
	 * Checks for revealed enemy.
	 *
	 * @return true, if successful
	 */
	public boolean hasRevealedEnemy() {
		return revealedEnemy;
	}

	/**
	 * Sets the revealed enemy.
	 *
	 * @param revealedEnemy the new revealed enemy
	 */
	public void setRevealedEnemy(boolean revealedEnemy) {
		this.revealedEnemy = revealedEnemy;
	}

	/**
	 * Checks for killed enemy.
	 *
	 * @return true, if successful
	 */
	public boolean hasKilledEnemy() {
		return killedEnemy;
	}

	/**
	 * Sets the killed enemy.
	 *
	 * @param killedEnemy the new killed enemy
	 */
	public void setKilledEnemy(boolean killedEnemy) {
		this.killedEnemy = killedEnemy;
	}

	/**
	 * Checks for missed enemy.
	 *
	 * @return true, if successful
	 */
	public boolean hasMissedEnemy() {
		return missedEnemy;
	}

	/**
	 * Sets the missed enemy.
	 *
	 * @param missedEnemy the new missed enemy
	 */
	public void setMissedEnemy(boolean missedEnemy) {
		this.missedEnemy = missedEnemy;
	}

}