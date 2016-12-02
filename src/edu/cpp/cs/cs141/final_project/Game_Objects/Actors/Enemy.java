package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

// TODO: Auto-generated Javadoc
/**
 * A hostile {@link Actor} that tries to attack the {@link Player}.
 * @author oadug
 *
 */
public class Enemy extends Actor {
	
	/** The Constant ENEMY_NAME. */
	private static final String ENEMY_NAME = "Ninja";
	
	/** The Constant ENEMY_SYMBOL. */
	private static final char ENEMY_SYMBOL = 'N';
	
	/**
	 * Instantiates a new enemy.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Enemy(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = ENEMY_SYMBOL;
		this.name = ENEMY_NAME;
	}

	/**
	 * Gets the random movement.
	 *
	 * @return the random movement
	 */
	public void getRandomMovement() {
		List<Direction> availableDirs = new ArrayList<Direction>();
		boolean[] moveConditions = this.getMoveConditions();
		
		for (Direction dir : Direction.values()) {
			if (moveConditions[dir.ordinal()]) availableDirs.add(dir);
		}
		
		if (availableDirs.isEmpty()) setMoveDirection(null);
		else this.setMoveDirection(availableDirs.get(new Random().nextInt(availableDirs.size())));
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor#updateAttackStatus()
	 */
	protected void updateAttackStatus() {

		if (nearbyActors.isEmpty()) return;
		
		for (Actor actor : nearbyActors){
			if ((actor instanceof Player) && !((Player)actor).getHasDiedRecently()) {
				canAttack = true;
				isLookedAt = true;
			}
		}
		
		nearbyActors.clear();
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor#update(java.util.List)
	 */
	@Override
	public void update(List<GameObject> activeEntities) {

		getRandomMovement();
		move();

	}

}
