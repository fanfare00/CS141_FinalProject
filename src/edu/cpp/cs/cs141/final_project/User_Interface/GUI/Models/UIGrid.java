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
package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

// TODO: Auto-generated Javadoc
/**
 * The Class UIGrid.
 */
public class UIGrid {

	/** The x loc. */
	public int xLoc;
	
	/** The y loc. */
	public int yLoc;
	
	/** The width. */
	public int width;
	
	/** The height. */
	public int height;
	
	/** The grid. */
	private char grid[][];
	
	/** The app. */
	private Application app;
	
	/** The has updated. */
	public boolean hasUpdated;
	
	//private UIGridSpace componentGrid[][];
	
	/** The spaces. */
	public List<UIGridSpace> spaces = new ArrayList<UIGridSpace>();
	
	/**
	 * Instantiates a new UI grid.
	 *
	 * @param xLoc the x loc
	 * @param yLoc the y loc
	 * @param width the width
	 * @param height the height
	 * @param grid the grid
	 * @param app the app
	 */
	public UIGrid(int xLoc, int yLoc, int width, int height, char[][] grid, Application app) {
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		
		this.app = app;
		
		
	}

	/**
	 * Creates the.
	 *
	 * @param grid the grid
	 */
	public void create(char[][] grid) {
		hasUpdated = true;
		
		if (spaces.size() == 0) {
			
			for (int m = 0; m < grid[0].length; m++) {
				for (int n = 0; n < grid.length; n++) {
	
					spaces.add(new UIGridSpace(m, n, new EmptyModel(), this, app));
				}
			}
		} else {
			for (int m = 0; m < grid[0].length; m++) {
				for (int n = 0; n < grid.length; n++) {
					//spaces.set((m*grid.length) + n, new UIGridSpace(m, n, new EmptyModel(), this, app));
					spaces.get((m*grid.length) + n).model = new EmptyModel();
					spaces.get((m*grid.length) + n).backModel = new EmptyModel();
				}
			}
		}
		
		this.grid = grid;
	}
	
	/**
	 * Reset grid.
	 */
	public void resetGrid() {
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).isOpen = false;
		}
	}

	/**
	 * Adds the.
	 *
	 * @param row the row
	 * @param col the col
	 * @param symbol the symbol
	 */
	public void add(int row, int col, char symbol) {
		
		switch (symbol) {
			case 'R': 
				if (spaces.get((row*grid.length) + col).isOpen) {
					spaces.get((row*grid.length) + col).model = new OpenRoomModel();
				} else {
					spaces.get((row*grid.length) + col).model = new RoomModel();
				}
			
				break;
			case 'r': spaces.get((row*grid.length) + col).backModel = new RadarModel();
				break;	
			case 'a': spaces.get((row*grid.length) + col).backModel = new AmmoModel();
				break;
			case 'i': spaces.get((row*grid.length) + col).backModel = new InvincibilityModel();
				break;
			case 'P': spaces.get((row*grid.length) + col).model = new PlayerModel();
				break;
			case 'N': spaces.get((row*grid.length) + col).model = new EnemyModel();
				break;
			case 'I':
				if (spaces.get((row*grid.length) + col).isOpen) {
					spaces.get((row*grid.length) + col).model = new OpenRoomModel();
				} else {
					spaces.get((row*grid.length) + col).model = new RoomModel();
				}
				spaces.get((row*grid.length) + col).backModel = new IntelModel();
				break;
			default: //spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new EmptyModel(), this, app));
		}
		
//		if(symbol == 'R') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new RoomModel()));
//		if(symbol == 'P') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
//		if(symbol == 'N') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
		
		//parseSpace();
	}
	
	/**
	 * Gets the spaces.
	 *
	 * @return the spaces
	 */
	public List<UIGridSpace> getSpaces() {
		return spaces;
	}
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param frame the frame
	 */
	public void draw(Graphics2D g, JFrame frame) {
//		for (UIGridSpace space : spaces) {
//			space.draw(g);
//		}
		
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).draw(g, frame);
			frame.add(spaces.get(i));
			
			
		}
		
//		for (UIGridSpace space : spaces) {
//			if (space.model instanceof RoomModel) space.draw(g);
//		}
//		
	}
	
	/**
	 * Gets the player space.
	 *
	 * @return the player space
	 */
	public UIGridSpace getPlayerSpace() {
		for (int i = 0; i < spaces.size(); i++) {
			if (spaces.get(i).model instanceof PlayerModel) return spaces.get(i);
		} 
		
		return spaces.get(0);
	}
	
	/**
	 * Unlook all spaces.
	 */
	public void unlookAllSpaces() {
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).isLookedAt = false;
		}
	}

	/**
	 * Reveal looked space.
	 *
	 * @param oldSpace the old space
	 * @param dir the dir
	 */
	public void revealLookedSpace(UIGridSpace oldSpace, Direction dir) {
		if (oldSpace.model instanceof OpenRoomModel) return;
		
		for (int i = 0; i < spaces.size(); i++) {	
			if ((oldSpace.col + dir.col()) == spaces.get(i).col) {	
				if ((oldSpace.row + dir.row()) == spaces.get(i).row) {
						spaces.get(i).isLookedAt = true;
				}
			}
						 
		} 
	}

	/**
	 * Sets the can shoot.
	 *
	 * @param flag the new can shoot
	 */
	public void setCanShoot(boolean flag) {
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).canShoot = flag;
		}
	}
	
	/**
	 * Gets the move direction.
	 *
	 * @param oldSpace the old space
	 * @param space the space
	 * @return the move direction
	 */
	public Direction getMoveDirection(UIGridSpace oldSpace, UIGridSpace space) {
		Direction dir = null;
		
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col + 1))) dir = Direction.LEFT;
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col - 1))) dir = Direction.RIGHT;
		if ((oldSpace.row == (space.row - 1)) && (oldSpace.col == (space.col))) dir = Direction.DOWN;
		if ((oldSpace.row == (space.row + 1)) && (oldSpace.col == (space.col))) dir = Direction.UP;
		
		return dir;
	}
}
