package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class Radar.
 */
public class Radar extends Powerup{

	/** The Constant RADAR_SYMBOL. */
	private static final char RADAR_SYMBOL = 'r';
	
	/** The Constant RADAR_DESCRIPTION. */
	private static final String RADAR_DESCRIPTION = "Radar has revealed the loctation of the intelligence.";
	
	/**
	 * Instantiates a new radar.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Radar(int row, int col) {
		super(row, col);
		this.symbol = RADAR_SYMBOL;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup#consume(edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor)
	 */
	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		((Player) consumer).setHasRadar(true);
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.GameObject#update(java.util.List)
	 */
	@Override
	public void update(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if ((obj instanceof Player) && (checkCollision (obj))) { 
				((Player)obj).setCurrentPowerup(this);
				((Player)obj).setHasRadar(true);
			}
		}	
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.Powerup#getDescription()
	 */
	@Override
	public String getDescription() {
		return RADAR_DESCRIPTION;
	}
	
}
