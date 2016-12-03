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


/**
 * The game engine. It stores the game state and controls the date and
 *  mechanics of the game. It stores and manipulates game objects and
 *  has resolves calls from the application class.
 */
public class Game {

	/** The total number of rows */
	public static final int GAME_ROWS = 9;
	
	/** The total number of cols */
	public static final int GAME_COLS = 9;
	
	/** The maximum enemy count. */
	private static final int MAX_ENEMY_COUNT = 6;
	
	/** The maximum room count. */
	private static final int MAX_ROOM_COUNT = 9;
	
	/** The spacing between rooms */
	private static final int ROOM_SPACING = 3;
	
	/** The spawn distance from player */
	private static final int ENEMY_SPAWN_DISTANCE = 3;
	
	/** The row the player spawns in */
	private static final int PLAYER_SPAWN_ROW = 8;
	
	/** The col the player spawns in. */
	private static final int PLAYER_SPAWN_COL = 0;
	
	/** The maximum number of lives the player has. */
	private static final int MAX_LIVES = 3;
	
	/** The maximum amount of ammo the player starts with. */
	private static final int MAX_PLAYER_AMMO = 1;
	
	/** The active entities. */
	List<GameObject> activeEntities = new ArrayList<GameObject>();
	
	/** The player. */
	private Player player;
	
	/** Whether game is over or not */
	private boolean gameOver = false;
	
	/** Whether game is won or not. */
	private boolean gameWon = false;
	
	/** Whether game is in debug mode or not. */
	private boolean debugMode = false;
	
	/** Timer for enemy movements (GUI ONLY) */
	Timer timer = new Timer(true);
	
	/** Whether the game is paused or not. */
	private boolean paused;
	
	/** Whether the game is real time or not */
	private boolean turnBased;
	
	/**
	 * Instantiates a new game. Spawns game objects and initalizes them.
	 */
	public Game() {
		
		spawnPlayer();
		spawnRooms();
		spawnPowerups();
		spawnEnemies();
		spawnIntel();
		initializeEntities();
		
		scheduleEnemyMovements();
	}
	
	/**
	 * Updates the game. Primary function of this class being called in game loop.
	 */
	public void update() {
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
	 * Spawn player.
	 */
	private void spawnPlayer() {
		player = new Player(PLAYER_SPAWN_ROW, PLAYER_SPAWN_COL, MAX_LIVES, MAX_PLAYER_AMMO);
		activeEntities.add(player);
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
	 * Finds a random, valid row and col for game object to spawn in.
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
	 * Places the briefcase in a random room.
	 */
	private void spawnIntel() {
		
		List<Room> rooms = new ArrayList<Room>();

		int randomRoomNumber = (int) (Math.random() * MAX_ROOM_COUNT);
		
		for (GameObject obj : activeEntities) {
			if (obj instanceof Room) rooms.add((Room)obj);
		}
		 
		rooms.get(randomRoomNumber).placeIntel();
	}
	
	/**
	 * Initializes entities.
	 */
	private void initializeEntities() {
		for (GameObject obj : activeEntities) {
			if (obj instanceof Actor) ((Actor) obj).init(activeEntities);
		}
	}
	
	/**
	 * Schedule enemy movements. (Note this has to be scheduled no matter what,
	 *  but cant be active when the game is not in GUI mode).
	 */
	private void scheduleEnemyMovements() {
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
	 * Update enemy states.
	 */
	private void updateEnemyStates() {
		for (GameObject obj : activeEntities){
			if (obj instanceof Actor) ((Actor) obj).updateState(activeEntities);
		}
	}
	
	/**
	 * Update entities.
	 */
	private void updateEntities() {
		toggleEntityVisibility(false);
		
		for (GameObject obj : activeEntities){
			if (obj instanceof Player)continue;

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
	 * Reveal entities near player.
	 */
	private void revealEntitiesNearPlayer() {
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
			if(obj.isLookedAt())obj.setVisible(true);
		}
	}
	
	/**
	 * Handle enemy combat, checks if enemy can attack and if so resets player.
	 */
	private void handleEnemyCombat() {
		for (GameObject obj : activeEntities){
			if (obj instanceof Enemy) {
				if (( (Enemy) obj).getCanAttack()) resetPlayer();
			}
		}
	}
	
	/**
	 * Removes the inactive objects. Those that have died or been consumed.
	 */
	private void removeInactiveObjects() {
		List<GameObject> inactiveEntities = new ArrayList<GameObject>();
		
		for (GameObject obj : activeEntities){
			if (!obj.isActive()) inactiveEntities.add(obj);
		}
		
		activeEntities.removeAll(inactiveEntities);
	}
	
	/**
	 * Check if game is over
	 */
	public void checkGameOver() {
		if (player.getRemainingLives() == 0) gameOver = true;
		if (player.foundIntel()) gameWon = true;
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
	 * Gets the list of currently active entities.
	 *
	 * @return the active entities
	 */
	public List<GameObject> getActiveEntities() {return activeEntities;}
	
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
	 * Gets the debug mode.
	 *
	 * @return the debug mode
	 */
	public boolean getDebugMode() {return debugMode;}

	/**
	 * Sets the turn based.
	 *
	 * @param flag the new turn based
	 */
	public void setTurnBased(boolean flag) {turnBased = flag;}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {return player;}
	
	/**
	 * Toggle pause.
	 *
	 * @param flag the flag
	 */
	public void setPaused(boolean flag) {paused = flag;}
	
	/**
	 * Checks if the game is over.
	 * @return True if the game is over, false if it is still going.
	 */
	public boolean isGameOver() {return gameOver;}
	
	/**
	 * Reset player.
	 */
	private void resetPlayer() {
		player.reset();
		player.updateState(activeEntities);
	}

	/**
	 * Gets the game won.
	 *
	 * @return the game won
	 */
	public boolean getGameWon() {return gameWon;}
	
	/**
	 * Gets the game over.
	 *
	 * @return the game over
	 */
	public boolean getGameOver() {return gameOver;}
	
	/**
	 * Dispose.
	 */
	public void dispose(){
		timer.cancel();
	}
	

}
