package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.*;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

/**
 * The game engine. It stores the game state and controls the mechanics of the game.
 */
public class Game {

	public static final int GAME_ROWS = 9;
	public static final int GAME_COLS = 9;
	
	private static final int MAX_ENEMY_COUNT = 6;
	private static final int MAX_ROOM_COUNT = 9;
	
	private static final int ROOM_SPACING = 3;
	private static final int ENEMY_SPAWN_DISTANCE = 3;
	
	private static final int PLAYER_SPAWN_ROW = 8;
	private static final int PLAYER_SPAWN_COL = 0;
	
	//private static final int MAX_DIRECTIONS = Direction.values().length;
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	List<GameObject> activeEntities = new ArrayList<GameObject>();
	
	private Player player;
	
	private boolean gameOver;
	
	private boolean debugMode = false;
	
	public Game() {
		player = new Player(PLAYER_SPAWN_ROW, PLAYER_SPAWN_COL);
		spawnEnemiesRandomly();
		spawnRooms();
		spawnBriefcase();
		
		activeEntities.add(player);
		activeEntities.addAll(enemies);
		activeEntities.addAll(rooms);
		
		for (GameObject obj : activeEntities) {
			if (obj instanceof Actor) ((Actor) obj).init(activeEntities);
		}
	}
	
	/**
	 * Checks if the game is over.
	 * @return True if the game is over, false if it is still going.
	 */
	public boolean isGameOver()
	{
	    return gameOver;
	}
	
	/**
	 * Places the briefcase in a random room.
	 */
	private void spawnBriefcase() {
		int randomRoomNumber = (int) (Math.random() * MAX_ROOM_COUNT);
		
		 rooms.get(randomRoomNumber).placeIntel();
	}
	
	/**
	 * Spawns the rooms. They will spawn in the same place each time.
	 */
	private void spawnRooms() {
		for (int i = 1; i < MAX_ROOM_COUNT; i+=ROOM_SPACING) {
			for (int j = 1; j < MAX_ROOM_COUNT; j+=ROOM_SPACING) {
				rooms.add(new Room(i,j));
			}
		}
	}

	/**
	 * Randomly spawns enemies around the map.
	 */
	private void spawnEnemiesRandomly() {
		int randomRow;
		int randomCol;
		
	    Random rand = new Random();
		
		for (int i = 0; i < MAX_ENEMY_COUNT; i++) {
			randomRow = rand.nextInt(GAME_ROWS);
			randomCol = rand.nextInt(GAME_COLS);
			
			if (isValidSpawnLocation(randomRow, randomCol)) {
				enemies.add(new Enemy(randomRow, randomCol));
			} else {
				i--;
			}
		}
	}
	
	/**
	 * Checks if the specified location is valid for spawning an enemy.
	 * @param row The row of the target location.
	 * @param col The column of the target location.
	 * @return True if this location is valid, false if it is not.
	 */
	private boolean isValidSpawnLocation(int row, int col) {
		if (isSpawnOccupied(row, col)) return false;
		if (isSpawnTooClose(row, col)) return false;
		
		return true;
	}
	
	/**
	 * Checks if this location is occupied by an {@link Actor} or a {@link Room}.
	 * @param row The row of the target location.
	 * @param col The column of the target location.
	 * @return True if the spawn is occupied, false otherwise.
	 */
	private boolean isSpawnOccupied(int row, int col) {
		for (Room r : rooms){
			if ((r.getRow() == row) && r.getCol() == col) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the specified enemy spawn location is too close to the player.
	 * @param row The row of the enemy spawn location.
	 * @param col The column of the enemy spawn location.
	 * @return True of the spawn is too close, false otherwise.
	 */
	private boolean isSpawnTooClose(int row, int col) {
		int closeRow = GAME_ROWS - ENEMY_SPAWN_DISTANCE - 1;
		int closeCol = ENEMY_SPAWN_DISTANCE;
		
		return ((row > closeRow) && (col < closeCol));
	}

	/**
	 * Gets the list of currently active entities.
	 */
	public List<GameObject> getActiveEntities() {
		return activeEntities;
	}

	/**
	 * Replaces the currently active entities with a new list of entities.
	 * @param activeEntities The list of entities to make active.
	 */
	public void setActiveEntities(List<GameObject> activeEntities) {
		List<GameObject> entities = new ArrayList<GameObject>();
		entities.addAll(activeEntities);
		this.activeEntities = entities;
	    //this.activeEntities.clear();
		//this.activeEntities.addAll(activeEntities);
	}
	
	public void playerLook(Direction dir) {
		player.setLookDir(dir);
		player.look(activeEntities);
	}
	
	public void setDebugMode(boolean flag) {
		this.debugMode = flag;
		
		if (flag) toggleEntityVisibility(true);
		else toggleEntityVisibility(false);
		
		player.setVisible(true);
		player.revealNearby(activeEntities);
	}
	
	private void toggleEntityVisibility(boolean flag) {
		for (GameObject obj : activeEntities){
			if(obj instanceof Player) continue;
			obj.setVisible(flag);
		}
	}
	
	public boolean getDebugMode() {
		return debugMode;
	}

	
	private void removeInactiveObjects() {
		List<GameObject> inactiveEntities = new ArrayList<GameObject>();
		
		for (GameObject obj : activeEntities){
			if (!obj.isActive()) inactiveEntities.add(obj);
		}
		
		activeEntities.removeAll(inactiveEntities);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void update() {
		removeInactiveObjects();
		toggleEntityVisibility(false);
		
		for (GameObject obj : activeEntities){
			
			obj.update(activeEntities);
			
			if(debugMode) obj.setVisible(true);
		}
		
		for (GameObject obj : activeEntities){
			if (obj instanceof Actor) ((Actor) obj).updateState(activeEntities);
		}		
	}
}
