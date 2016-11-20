package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Direction;
import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;

/**
 * The {@link Actor} that the user controls.
 */
public class Player extends Actor{
	private boolean isInvincible;

	private static final String PLAYER_NAME = "Player";
	private static final char PLAYER_SYMBOL = 'P';
	
	private Direction lookDir;
	
	public Player(int row, int col) {
		super(row, col);
		this.symbol = PLAYER_SYMBOL;
		this.name = PLAYER_NAME;
	}
	
	public void setLookDir(Direction dir) {
		this.lookDir = dir;
	}
	
	public void revealNearby(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if(obj.equals(this)) continue;

			for (Direction dir : Direction.values()){
				if (dir.equals(lookDir)) obj.setVisible(checkCollision(obj, lookDir.row()*2, lookDir.col()*2));
				else obj.setVisible(checkCollision(obj, dir.row(), dir.col()));
			}
		}
	}
	
	@Override
	public void update(List<GameObject> activeEntities) {
		updateState(activeEntities);
		move();
		revealNearby(activeEntities);
		setLookDir(null);
	}

}