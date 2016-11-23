package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Player;

public class Radar extends GameObject implements Powerup{

	private static final char RADAR_SYMBOL = 'r';
	private static final String RADAR_DESCRIPTION = "Radar has revealed the loctation of the intelligence.";
	
	public Radar(int row, int col) {
		super(row, col);
		this.symbol = RADAR_SYMBOL;
	}

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		((Player) consumer).setHasRadar(true);
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if ((obj instanceof Player) && (checkCollision (obj))) { 
				((Player)obj).setCurrentPowerup(this);
				((Player)obj).setHasRadar(true);
			}
		}	
	}

	@Override
	public String getDescription() {
		return RADAR_DESCRIPTION;
	}
	
}
