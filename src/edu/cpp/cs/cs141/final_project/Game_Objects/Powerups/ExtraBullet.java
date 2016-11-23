package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

public class ExtraBullet extends GameObject implements Powerup {
	
	private static final char EXTRA_BULLET_SYMBOL = 'a';

	public ExtraBullet(int xPos, int yPos) {
		super(xPos, yPos);
		this.symbol = EXTRA_BULLET_SYMBOL;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consume(Actor consumer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(List<GameObject> activeEntities) {
		// TODO Auto-generated method stub
		
	}

}
