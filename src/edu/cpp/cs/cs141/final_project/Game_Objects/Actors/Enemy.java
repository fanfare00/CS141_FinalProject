package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.cpp.cs.cs141.final_project.Direction;
import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

/**
 * A hostile {@link Actor} that tries to attack the {@link Player}.
 * @author oadug
 *
 */
public class Enemy extends Actor {
	private static final String ENEMY_NAME = "Ninja";
	private static final char ENEMY_SYMBOL = 'N';
	
	public Enemy(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = ENEMY_SYMBOL;
		this.name = ENEMY_NAME;
	}

	private void getRandomMovement() {
		List<Direction> availableDirs = new ArrayList<Direction>();
		boolean[] moveConditions = this.getState().getMoveConditions();
		
		for (Direction dir : Direction.values()) {
			if (moveConditions[dir.ordinal()]) availableDirs.add(dir);
		}
		
		this.setMoveDirection(availableDirs.get(new Random().nextInt(availableDirs.size())));
	}
	
	@Override
	public void update(List<GameObject> activeEntities) {
		updateState(activeEntities);
		getRandomMovement();
		move();
	}

}
