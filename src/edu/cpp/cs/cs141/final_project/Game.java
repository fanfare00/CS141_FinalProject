package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.*;

public class Game {

	public static final int GAME_ROWS = 9;
	public static final int GAME_COLS = 9;
	
	private static final int MAX_ENEMY_COUNT = 6;
	private static final int MAX_ROOM_COUNT = 9;
	
	private static final int ROOM_SPACING = 3;
	private static final int ENEMY_SPAWN_DISTANCE = 3;
	
	private static final int PLAYER_SPAWN_ROW = 8;
	private static final int PLAYER_SPAWN_COL = 0;
	
	private List<Room> rooms = new ArrayList<Room>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	List<GameObject> activeEntities = new ArrayList<GameObject>();
	private Player player;
	
	private boolean gameOver;
	private int turnNumber;
	
	public Game() {
		player = new Player(PLAYER_SPAWN_ROW, PLAYER_SPAWN_COL);
		spawnEnemiesRandomly();
		spawnRooms();
		spawnBriefcase();
	}
	
	public boolean isGameOver()
	{
	    return gameOver;
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
	
	private boolean isValidSpawnLocation(int row, int col) {
		if (isSpawnOccupied(row, col)) return false;
		if (isSpawnTooClose(row, col)) return false;
		
		return true;
	}
	
	private boolean isSpawnOccupied(int row, int col) {
		for (Room r : rooms){
			if ((r.getRow() == row) && r.getCol() == col) {
				return true;
			}
		}
		return false;
	}

	private boolean isSpawnTooClose(int row, int col) {
		int closeRow = GAME_ROWS - ENEMY_SPAWN_DISTANCE - 1;
		int closeCol = ENEMY_SPAWN_DISTANCE;
		
		if ((row > closeRow) && (col < closeCol)){
			return true;
		}
		return false;
	}

	
	public List<GameObject> getActiveEntities() {
	    
	    
	    activeEntities.addAll(rooms);
	    activeEntities.addAll(enemies);
	    activeEntities.add(player);
		
		return activeEntities;
	}

	public void setActiveEntities(List<GameObject> activeEntities) {
		List<GameObject> entities = new ArrayList<GameObject>();
		entities.addAll(activeEntities);
		this.activeEntities = entities;
	    //this.activeEntities.clear();
		//this.activeEntities.addAll(activeEntities);
	}
	
	/**
	 * Attempts to move the player in the specified direction.
	 * @param direction 1 = up, 2 = down, 3 = left, 4 = right
	 * @return True if the player was able to move in this direction, and false otherwise.
	 */
	public boolean movePlayer(int direction)
	{
	    boolean success = false;
	    switch (direction) //check if other entities are there
	    {
		case 1:
		    if (isPlayerMovePossible(player.getRow() - 1, player.getCol(), false))
		    {
			success = true;
			player.moveRow(-1);
		    }
		    break;
		case 2:
		    if (isPlayerMovePossible(player.getRow() + 1, player.getCol(), true))
		    {
			success = true;
			player.moveRow(1);
		    }
		    break;
		case 3:
		    if (isPlayerMovePossible(player.getRow(), player.getCol() - 1, false))
		    {
			success = true;
			player.moveCol(-1);
		    }
		    break;
		case 4:
		    if (isPlayerMovePossible(player.getRow(), player.getCol() + 1, false))
		    {
			success = true;
			player.moveCol(1);
		    }
		default:
		    break;
	    }
	    return success;
	}
	
	private boolean isPlayerMovePossible(int row, int col, boolean movingDown)
	{
	    if (row >= 0 && row < GAME_ROWS && col >= 0 && col < GAME_COLS)
	    {
		for (GameObject o : activeEntities)
		{
		    if (o.getRow() == row && o.getCol() == col)
		    {
			if (o instanceof Room && movingDown && ((Room)o).hasIntel())
			    return true;
			else
			    return false;
		    }
		}
		return true;
	    }
	    return false;
	}
	
	public void performEnemyActions()
	{
	    
	}
}
