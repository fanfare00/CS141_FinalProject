package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.*;

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
	    
	    
	    activeEntities.addAll(rooms);
	    activeEntities.addAll(enemies);
	    activeEntities.add(player);
		
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
	
	/**
	 * Attempts to move the {@link Player} in the specified direction.
	 * @param direction 1 = up, 2 = down, 3 = left, 4 = right
	 * @return True if the {@link Player} was able to move in the specified direction, and false otherwise.
	 */
	public boolean movePlayer(int direction)
	{
	    return moveActor(direction, player);
	}
	
	/**
	 * Attempts to move the {@link Enemy} in the specified direction.
	 * @param direction 1 = up, 2 = down, 3 = left, 4 = right
	 * @param enemyID The array index of the enemy that should be moved
	 * @return True if the {@link Enemy} was able to move in the specified direction, and false otherwise.
	 */
	public boolean moveEnemy(int direction, int enemyID)
	{
	    return moveActor(direction, enemies.get(enemyID));
	}
	
	/**
	 * Attempts to move the {@link Actor} in the specified direction.
	 * @param direction 1 = up, 2 = down, 3 = left, 4 = right
	 * @return True if the {@link Actor} was able to move in the specified direction, and false otherwise.
	 */
	private boolean moveActor(int direction, Actor actor)
	{
	    boolean success = false;
	    switch (direction)
	    {
		case 1:
		    if (isActorMovePossible(actor.getRow() - 1, actor.getCol(), false))
		    {
			success = true;
			actor.moveRow(-1);
		    }
		    break;
		case 2:
		    boolean canEnterRoom = actor instanceof Player;
		    if (isActorMovePossible(actor.getRow() + 1, actor.getCol(), canEnterRoom))
		    {
			success = true;
			actor.moveRow(1);
		    }
		    break;
		case 3:
		    if (isActorMovePossible(actor.getRow(), actor.getCol() - 1, false))
		    {
			success = true;
			actor.moveCol(-1);
		    }
		    break;
		case 4:
		    if (isActorMovePossible(actor.getRow(), actor.getCol() + 1, false))
		    {
			success = true;
			actor.moveCol(1);
		    }
		default:
		    break;
	    }
	    return success;
	}
	
	/**
	 * Performs collision checking to ensure it is possible for the {@link Actor} to move to the specified location.
	 * @param row The row of the target location.
	 * @param col The column of the target location.
	 * @param canEnterRoom Set this to true if the {@link Actor} should be allowed to enter a room if one exists in the target location.
	 * @return True if the {@link Actor} could successfully move to the specified location.
	 */
	private boolean isActorMovePossible(int row, int col, boolean canEnterRoom)
	{
	    if (row >= 0 && row < GAME_ROWS && col >= 0 && col < GAME_COLS)
	    {
		for (GameObject o : activeEntities)
		{
		    if (o.getRow() == row && o.getCol() == col)
		    {
			if (o instanceof Room && canEnterRoom && ((Room)o).hasIntel())
			    return true;
			else
			    return false;
		    }
		}
		return true;
	    }
	    return false;
	}
	
	/**
	 * Executes enemy actions like moving and attacking.
	 */
	public void performEnemyActions()
	{
		checkPlayerProximity();
		moveEnemies();
		checkPlayerProximity();
	}
	
	/**
	 * Checks the proximity between the player and all enemies
	 * @return -1 = player isn't close to an enemy, anything else = array index of enemy that's close to player
	 */
	private int checkPlayerProximity()
	{
		return -1; //temporarily added to get rid of error
	}
	
    /**
     * Randomly moves enemies around
     */
    private void moveEnemies()
    {
	for (int i = 0; i < enemies.size(); i++)
	{
	    while (!moveEnemy((int)(Math.random() * 5), i));
	}
    }
}
