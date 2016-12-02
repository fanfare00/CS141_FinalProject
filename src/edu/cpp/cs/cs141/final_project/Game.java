/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodríguez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import edu.cpp.cs.cs141.final_project.Game_Objects.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Fixtures.*;
import edu.cpp.cs.cs141.final_project.Game_Objects.Powerups.*;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

// TODO: Auto-generated Javadoc
/**
 * The game engine. It stores the game state and controls the mechanics of the game.
 */
public class Game {

	/** The Constant GAME_ROWS. */
	public static final int GAME_ROWS = 9;
	
	/** The Constant GAME_COLS. */
	public static final int GAME_COLS = 9;
	
	/** The Constant MAX_ENEMY_COUNT. */
	private static final int MAX_ENEMY_COUNT = 6;
	
	/** The Constant MAX_ROOM_COUNT. */
	private static final int MAX_ROOM_COUNT = 9;
	
	/** The Constant ROOM_SPACING. */
	private static final int ROOM_SPACING = 3;
	
	/** The Constant ENEMY_SPAWN_DISTANCE. */
	private static final int ENEMY_SPAWN_DISTANCE = 3;
	
	/** The Constant PLAYER_SPAWN_ROW. */
	private static final int PLAYER_SPAWN_ROW = 8;
	
	/** The Constant PLAYER_SPAWN_COL. */
	private static final int PLAYER_SPAWN_COL = 0;
	
	/** The Constant MAX_LIVES. */
	private static final int MAX_LIVES = 3;
	
	/** The Constant MAX_PLAYER_AMMO. */
	private static final int MAX_PLAYER_AMMO = 1;
	
	/** The active entities. */
	List<GameObject> activeEntities = new ArrayList<GameObject>();
	
	
	/** The player. */
	private Player player;
	
	/** The game over. */
	private boolean gameOver = false;
	
	/** The game won. */
	private boolean gameWon = false;
	
	/** The debug mode. */
	private boolean debugMode = false;
	
	/** The timer. */
	Timer timer = new Timer(true);
	
	/** The paused. */
	private boolean paused;
	
	/** The turn based. */
	private boolean turnBased;
	
	/**
	 * Instantiates a new game.
	 */
	public Game() {

		
		spawnPlayer();
		spawnRooms();
		spawnPowerups();
		spawnEnemies();
//		Enemy enemy = new Enemy(7, 0);
//		activeEntities.add(enemy);
		spawnBriefcase();
		
		initializeEntities();
		
		TimerTask task = new TimerTask() {
			@Override
	        public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						if (!paused && !turnBased) {
							for (GameObject obj : activeEntities){
								if (obj instanceof Enemy) {
									((Actor)obj).updateState(activeEntities);
									obj.update(activeEntities);
								}
							}
						}
					}
					
				});
			}
		};
		
        timer.schedule(task, 0, 900);
        
	}
	
	/**
	 * Toggle pause.
	 *
	 * @param flag the flag
	 */
	public void togglePause(boolean flag) {
		paused = flag;
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
	 * Spawn player.
	 */
	private void spawnPlayer() {
		player = new Player(PLAYER_SPAWN_ROW, PLAYER_SPAWN_COL, MAX_LIVES, MAX_PLAYER_AMMO);
		activeEntities.add(player);
	}
	
	/**
	 * Initialize entities.
	 */
	private void initializeEntities() {
		for (GameObject obj : activeEntities) {
			if (obj instanceof Actor) ((Actor) obj).init(activeEntities);
		}
	}
	
	/**
	 * Places the briefcase in a random room.
	 */
	private void spawnBriefcase() {
		
		List<Room> rooms = new ArrayList<Room>();

		int randomRoomNumber = (int) (Math.random() * MAX_ROOM_COUNT);
		
		for (GameObject obj : activeEntities) {
			if (obj instanceof Room) rooms.add((Room)obj);
		}
		 
		rooms.get(randomRoomNumber).placeIntel();
	}
	
	/**
	 * Spawns the rooms. They will spawn in the same place each time.
	 */
	private void spawnRooms() {
		for (int i = 1; i < MAX_ROOM_COUNT; i+=ROOM_SPACING) {
			for (int j = 1; j < MAX_ROOM_COUNT; j+=ROOM_SPACING) {
				activeEntities.add(new Room(i,j));
			}
		}
	}
	
	/**
	 * Spawn randomly.
	 *
	 * @param obj the obj
	 */
	private void spawnRandomly(GameObject obj) {
		Random rand = new Random();
		
		int randomRow = rand.nextInt(GAME_ROWS);
		int randomCol = rand.nextInt(GAME_COLS);
		
		obj.setRow(randomRow);
		obj.setCol(randomCol);
		
		if(isValidSpawnLocation(randomRow, randomCol)) activeEntities.add(obj);
		else spawnRandomly(obj);
	}
	
	/**
	 * Spawn powerups.
	 */
	private void spawnPowerups() {
		spawnRandomly(new Radar(0,0));
		spawnRandomly(new ExtraAmmo(0,0));
		spawnRandomly(new Invincibility(0,0));
	}

	/**
	 * Randomly spawns enemies around the map.
	 */
	private void spawnEnemies() {	
		for (int i = 0; i < MAX_ENEMY_COUNT; i++) {
			spawnRandomly(new Enemy(0,0));
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
		for (GameObject obj : activeEntities){
			if ((obj.getRow() == row) && obj.getCol() == col) {
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
	 *
	 * @return the active entities
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
		this.player = (Player) activeEntities.get(0);
	}
	
	/**
	 * Player look.
	 *
	 * @param dir the dir
	 */
	public void playerLook(Direction dir) {
		player.setLookDir(dir);
		player.look(activeEntities);
		toggleEntityVisibility(debugMode);
		revealEntitiesNearPlayer();
	}
	
	/**
	 * Player attack.
	 *
	 * @param dir the dir
	 */
	public void playerAttack(Direction dir) {
		player.attack(dir);
		removeInactiveObjects();
	}
	
	/**
	 * Sets the debug mode.
	 *
	 * @param flag the new debug mode
	 */
	public void setDebugMode(boolean flag) {
		this.debugMode = flag;
		
		if (flag) toggleEntityVisibility(true);
		else toggleEntityVisibility(false);
		
		player.setVisible(true);
		player.revealNearby(activeEntities);
	}
	
	/**
	 * Toggle entity visibility.
	 *
	 * @param flag the flag
	 */
	private void toggleEntityVisibility(boolean flag) {
		for (GameObject obj : activeEntities){
			if(obj instanceof Player) continue;
			
			if ((flag) && (obj instanceof Room)) {
				if ((((Room)obj).hasIntel())) obj.setSymbol('I');
			} else if (obj instanceof Room){
				 obj.setSymbol('R');
			}
			
			obj.setVisible(flag);
			if(obj.isLookedAt()) {
				obj.setVisible(true);
			}
		}
	}
	
	/**
	 * Gets the debug mode.
	 *
	 * @return the debug mode
	 */
	public boolean getDebugMode() {
		return debugMode;
	}

	
	/**
	 * Removes the inactive objects.
	 */
	private void removeInactiveObjects() {
		List<GameObject> inactiveEntities = new ArrayList<GameObject>();
		
		for (GameObject obj : activeEntities){
			if (!obj.isActive()) inactiveEntities.add(obj);
		}
		
		activeEntities.removeAll(inactiveEntities);
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Update entities.
	 */
	private void updateEntities() {
		toggleEntityVisibility(false);
		
		for (GameObject obj : activeEntities){
			if (obj instanceof Player)continue;
			//if (obj instanceof Actor) ((Actor)obj).updateState(activeEntities);
			
			if (!(obj instanceof Enemy) | (turnBased)) obj.update(activeEntities);
			
			if ((debugMode) && (obj instanceof Room)) {
				if ((((Room)obj).hasIntel())) obj.setSymbol('I');
			} else if (obj instanceof Room){
				 obj.setSymbol('R');
			}
			
			if(debugMode) obj.setVisible(true);
		}
		
		player.updatePowerup();
	}
	
	/**
	 * Update enemy states.
	 */
	private void updateEnemyStates() {
		for (GameObject obj : activeEntities){
			if (obj instanceof Actor) ((Actor) obj).updateState(activeEntities);
		}
	}
	
	/**
	 * Reset player.
	 */
	private void resetPlayer() {
		player.reset();
		player.updateState(activeEntities);
	}
	
	/**
	 * Handle enemy combat.
	 */
	private void handleEnemyCombat() {
		for (GameObject obj : activeEntities){
			if (obj instanceof Enemy) {
				if (( (Enemy) obj).getCanAttack()) resetPlayer();
			}
		}
	}
	
	/**
	 * Check game over.
	 */
	public void checkGameOver() {
		if (player.getRemainingLives() == 0) gameOver = true;
		if (player.foundIntel()) gameWon = true;
	}
	
	/**
	 * Gets the game won.
	 *
	 * @return the game won
	 */
	public boolean getGameWon() {
		return gameWon;
	}
	
	/**
	 * Gets the game over.
	 *
	 * @return the game over
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Update.
	 */
	public void update() {
		//move player
		//update everything elses state
		//move everything else
		//update everything elses state
		//update player state
		
		player.update(activeEntities);
		updateEnemyStates();
		
		updateEntities();
		updateEnemyStates();
		
		player.updateState(activeEntities);
		
		revealEntitiesNearPlayer();
		
		handleEnemyCombat();
		
		removeInactiveObjects();
		
		checkGameOver();
		
	}

	/**
	 * Reveal entities near player.
	 */
	private void revealEntitiesNearPlayer() {
		//toggleEntityVisibility(false);
		player.revealNearby(activeEntities);
		
	}
	
	/**
	 * Dispose.
	 */
	public void dispose(){
		timer.cancel();
	}
	
	/**
	 * Sets the turn based.
	 *
	 * @param flag the new turn based
	 */
	public void setTurnBased(boolean flag) {
		turnBased = flag;
	}
}
