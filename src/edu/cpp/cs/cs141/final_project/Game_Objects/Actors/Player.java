package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
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
	
	public Player(int row, int col) {
		super(row, col);
		this.symbol = PLAYER_SYMBOL;
		this.name = PLAYER_NAME;
		this.setVisible(true);
	}
	
	public void setLookDir(Direction dir) {
		this.lookDir = dir;
	}
	
	public void look(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if(obj.equals(this)) continue;
			
			obj.setVisible(checkCollision(obj, lookDir.row()*2, lookDir.col()*2));
		}
		
		canLook = false;
	}
	
	public void revealNearby(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if(obj.equals(this)) continue;

			//obj.setVisible(false);
			
			for (Direction dir : Direction.values()){
				if (checkCollision(obj, dir.row(), dir.col())) obj.setVisible(true);	
			}
		}
	}
	
	public boolean getCanLook() {
		return this.canLook;
	}
	
	@Override
	public void update(List<GameObject> activeEntities) {
		canLook = true;
		setVisible(true);
		updateState(activeEntities);
		move();
		//updateState(activeEntities);
		revealNearby(activeEntities);
		setLookDir(null);
	}

}