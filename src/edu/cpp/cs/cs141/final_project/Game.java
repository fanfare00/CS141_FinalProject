package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.*;

public class Game {

	public static final int GAME_ROWS = 9;
	public static final int GAME_COLS = 9;
	
	private static final int MAX_ENEMY_COUNT = 6;
	private static final int MAX_ROOM_COUNT = 9;
	
	private static final int ROOM_SPACING = 3;
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private Player player;
	
	public Game() {
		player = new Player();
		spawnEnemiesRandomly();
		spawnRooms();
		spawnBriefcase();
	}
	
	private void spawnBriefcase() {
		int randomRoomNumber = (int) (Math.random() * MAX_ROOM_COUNT);
		
		 rooms.get(randomRoomNumber).placeIntel();
	}
	
	private void spawnRooms() {
		for (int i = 1; i < MAX_ROOM_COUNT; i+=ROOM_SPACING) {
			for (int j = 1; j < MAX_ROOM_COUNT; j+=ROOM_SPACING) {
				rooms.add(new Room(i,j));
			}
		}
	}

	private void spawnEnemiesRandomly() {
		int randomX;
		int randomY;
		
		for (int i = 0; i < MAX_ENEMY_COUNT; i++) {
			randomX = (int) (Math.random() * GAME_ROWS);
			randomY = (int) (Math.random() * GAME_COLS);
			
			//activeEnemies.add(new Enemy(randomX, randomY));
		}
	}
	
	public List<GameObject> getActiveEntities() {
	    List<GameObject> activeEntities = new ArrayList<GameObject>();
	    activeEntities.addAll(rooms);
	    activeEntities.addAll(enemies);
		
		return activeEntities;
	}
}
