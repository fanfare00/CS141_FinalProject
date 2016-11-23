package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.Room;
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
	private boolean hasDiedRecently;
	
	private int startingRow;
	private int startingCol;
	
	private int remainingLives;
	private int remainingAmmo;
	private int remainingTurnsInvincible = 0;
	
	private boolean isAlive;

	private boolean hasRadar;
	
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
	
	public void look(List<GameObject> activeEntities) {
		revealNearby(activeEntities);
		
		for (GameObject obj : activeEntities) {
			if(obj.equals(this)) continue;
			
			if (checkCollision(obj, lookDir.row()*2, lookDir.col()*2)) { 
				obj.setVisible(true);
				
				if (obj instanceof Enemy) {
					this.nearbyActors.add((Enemy)obj);
					this.canAttack = true;
				}
				
			}
		}
		
		canLook = false;
	}
	
	public void attack() {
		this.canAttack = false;
		
	    if(remainingAmmo > 0) {
			nearbyActors.get(0).setActive(false);
			nearbyActors.clear();
			
			this.remainingAmmo -= 1;
	    }
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
		}
	}
	
	public void updatePowerup() {
		
		if (remainingTurnsInvincible == 0) isInvincible = false;
		
		if (this.currentPowerup == null) return;
		
		currentPowerup.consume(this);
		((GameObject) currentPowerup).setActive(false);
		currentPowerup = null;
	}
	
	
	@Override
	public void update(List<GameObject> activeEntities) {
		canLook = true;
		hasDiedRecently = false;
		hasRadar = false;
		if(isInvincible) remainingTurnsInvincible-=1;
		setVisible(true);
		updateState(activeEntities);
		move();
		updatePowerup();
		setLookDir(null);
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

	public void setHasRadar(boolean b) {
		this.hasRadar = true;
	}

}