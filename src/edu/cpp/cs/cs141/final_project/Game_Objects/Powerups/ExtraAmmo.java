package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Player;

public class ExtraAmmo extends Powerup {
	
	private static final char EXTRA_AMMO_SYMBOL = 'a';
	private static final char EXTRA_AMMO_AMMOUNT = 1;
	private static final String EXTRA_AMMO_DESCRIPTION = "A cache of ammo yeilds you 1 extra rounds.";

	public ExtraAmmo(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = EXTRA_AMMO_SYMBOL;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		((Player) consumer).setRemainingAmmo(EXTRA_AMMO_AMMOUNT);
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		for (GameObject obj : activeEntities) {
			if ((obj instanceof Player) && (checkCollision (obj))) ((Player)obj).setCurrentPowerup(this);
		}	
		
	}

	@Override
	public String getDescription() {
		return EXTRA_AMMO_DESCRIPTION;
	}

}
