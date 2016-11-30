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
	
	private static final int MAX_LIVES = 3;
	private static final int MAX_PLAYER_AMMO = 1;
	
	List<GameObject> activeEntities = new ArrayList<GameObject>();
	
	
	private Player player;
	
	private boolean gameOver = false;
	
	private boolean gameWon = false;
	
	private boolean debugMode = false;
	
	Timer timer = new Timer(true);
	

	
	public Game() {

		
		spawnPlayer();
		spawnRooms();
		spawnPowerups();
		spawnEnemies();
		spawnBriefcase();
		
		initializeEntities();
		
		TimerTask task = new TimerTask() {
			@Override
	        public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						for (GameObject obj : activeEntities){
							if (obj instanceof Enemy) {
								((Actor)obj).updateState(activeEntities);
								obj.update(activeEntities);
							}
						}
					}
					
				});
			}
		};
		
        timer.schedule(task, 0, 900);
        
	}
	
	public void togglePause(boolean flag) {
		TimerTask task = new TimerTask() {
			@Override
	        public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						for (GameObject obj : activeEntities){
							if (obj instanceof Enemy) {
								((Actor)obj).updateState(activeEntities);
								obj.update(activeEntities);
							}
						}
					}
					
				});
			}
		};
		
		if (flag) timer.cancel();
		else {
			timer = new Timer();
			timer.schedule(task, 0, 800);
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
	
	private void spawnPlayer() {
		player = new Player(PLAYER_SPAWN_ROW, PLAYER_SPAWN_COL, MAX_LIVES, MAX_PLAYER_AMMO);
		activeEntities.add(player);
	}
	
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
	
	private void spawnRandomly(GameObject obj) {
		Random rand = new Random();
		
		int randomRow = rand.nextInt(GAME_ROWS);
		int randomCol = rand.nextInt(GAME_COLS);
		
		obj.setRow(randomRow);
		obj.setCol(randomCol);
		
		if(isValidSpawnLocation(randomRow, randomCol)) activeEntities.add(obj);
		else spawnRandomly(obj);
	}
	
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
	
	public void playerLook(Direction dir) {
		player.setLookDir(dir);
		player.look(activeEntities);
	}
	
	public void playerAttack(Direction dir) {
		player.attack(dir);
		removeInactiveObjects();
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
			
			
			
			if ((flag) && (obj instanceof Room)) {
				if ((((Room)obj).hasIntel())) obj.setSymbol('I');
			} else if (obj instanceof Room){
				 obj.setSymbol('R');
			}
			
			obj.setVisible(flag);
			if(obj.isLookedAt()) obj.setVisible(true);
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
	
	private void updateEntities() {
		toggleEntityVisibility(false);
		
		for (GameObject obj : activeEntities){
			if (obj instanceof Player)continue;
			//if (obj instanceof Actor) ((Actor)obj).updateState(activeEntities);
			
			if (!(obj instanceof Enemy)) obj.update(activeEntities);
			
			if ((debugMode) && (obj instanceof Room)) {
				if ((((Room)obj).hasIntel())) obj.setSymbol('I');
			} else if (obj instanceof Room){
				 obj.setSymbol('R');
			}
			
			if(debugMode) obj.setVisible(true);
		}
		
		player.updatePowerup();
	}
	
	private void updateEnemyStates() {
		for (GameObject obj : activeEntities){
			if (obj instanceof Actor) ((Actor) obj).updateState(activeEntities);
		}
	}
	
	private void resetPlayer() {
		player.reset();
		player.updateState(activeEntities);
	}
	
	private void handleEnemyCombat() {
		for (GameObject obj : activeEntities){
			if (obj instanceof Enemy) {
				if (( (Enemy) obj).getCanAttack()) resetPlayer();
			}
		}
	}
	
	public void checkGameOver() {
		if (player.getRemainingLives() == 0) gameOver = true;
		if (player.foundIntel()) gameWon = true;
	}
	
	public boolean getGameWon() {
		return gameWon;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
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

	private void revealEntitiesNearPlayer() {
		//toggleEntityVisibility(false);
		player.revealNearby(activeEntities);
		
	}
}
