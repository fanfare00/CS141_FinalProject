package edu.cpp.cs.cs141.final_project.Game_Objects.Powerups;

import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

public class Radar extends GameObject implements Powerup{

	private static final char RADAR_SYMBOL = 'r';
	
	public Radar(int row, int col) {
		super(row, col);
		this.symbol = RADAR_SYMBOL;
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
