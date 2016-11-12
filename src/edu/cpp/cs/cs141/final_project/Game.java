package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;

public class Game {

	public static final int GAME_ROWS = 9;
	public static final int GAME_COLS = 9;
	
	private List<GameObject> activeEntities = new ArrayList<GameObject>();
	
	public List<GameObject> getActiveEntities() {
		return activeEntities;
	}
}
