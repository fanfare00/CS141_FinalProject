package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

public class ExtraAmmo extends GameObject implements Powerup {
	
	private static final char EXTRA_AMMO_SYMBOL = 'a';

	public ExtraAmmo(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = EXTRA_AMMO_SYMBOL;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		System.out.println("TEST AMM CONSUME");
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		// TODO Auto-generated method stub
		
	}

}
