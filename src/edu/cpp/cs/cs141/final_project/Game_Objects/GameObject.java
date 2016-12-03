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
package edu.cpp.cs.cs141.final_project.Game_Objects;

import java.io.Serializable;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Utilities.Direction;

/**
 * An object that has some position, appearance, and rules governing them in the game.
 */
public abstract class GameObject implements Serializable{
	
	/** The Constant MAX_ROW. */
	protected static final int MAX_ROW = 8;
	
	/** The Constant MAX_COL. */
	protected static final int MAX_COL = 8;
	
	/** The row. */
	protected int row;
	
	/** The col. */
	protected int col;
	
	/** The symbol. */
	protected char symbol;
	
	/** The name. */
	protected String name;
	
	/** The is active. */
	protected boolean isActive;
	
	/** The is visible. */
	protected boolean isVisible;
	
	/** The is looked at. */
	protected boolean isLookedAt;
	
	/**
	 * Instantiates a new game object.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public GameObject(int row, int col) {
		this.row = row;
		this.col = col;
		this.isActive = true;
		this.isVisible = false;
	}
	
	/**
	 * Checks if is looked at.
	 *
	 * @return true, if is looked at
	 */
	public boolean isLookedAt() {
		return isLookedAt;
	}
	
	/**
	 * Sets the checks if is looked at.
	 *
	 * @param isLookedAt the new checks if is looked at
	 */
	public void setIsLookedAt(boolean isLookedAt) {
		this.isLookedAt = isLookedAt;
	}
	
	/**
	 * Returns the row of this {@link GameObject}.
	 *
	 * @return the row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Returns the column of this {@link GameObject}.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns the symbol of this {@link GameObject}.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Sets the col.
	 *
	 * @param col the new col
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param flag the new active
	 */
	public void setActive(boolean flag) {
		isActive = flag;
	}
	
	/**
	 * Sets the visible.
	 *
	 * @param flag the new visible
	 */
	public void setVisible(boolean flag) {
		isVisible = flag;
	}
	
	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible(){
		return isVisible;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Sets the symbol.
	 *
	 * @param symbol the new symbol
	 */
	public void setSymbol(char symbol){
		this.symbol = symbol;
	}
	
	/**
	 * Update.
	 *
	 * @param activeEntities the active entities
	 */
	public abstract void update(List<GameObject> activeEntities);
	
	/**
	 * Check collision.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean checkCollision(GameObject obj){
		return (this.row == obj.getRow()) &&
			   (this.col == obj.getCol());
	}
	
	/**
	 * Check collision.
	 *
	 * @param obj the obj
	 * @param dir the dir
	 * @return true, if successful
	 */
	public boolean checkCollision(GameObject obj, Direction dir){
		return (this.row+dir.row() == obj.getRow()) &&
			   (this.col+dir.col() == obj.getCol());
	}
	
	/**
	 * Check collision.
	 *
	 * @param obj the obj
	 * @param row the row
	 * @param col the col
	 * @return true, if successful
	 */
	public boolean checkCollision(GameObject obj, int row, int col){
		return (this.row+row == obj.getRow()) &&
			   (this.col+col == obj.getCol());
	}
	
	/**
	 * Check out of bounds.
	 *
	 * @param maxRow the max row
	 * @param maxCol the max col
	 * @param dir the dir
	 * @return true, if successful
	 */
	public boolean checkOutOfBounds(int maxRow, int maxCol, Direction dir) {	
		return (this.row+dir.row() < (maxRow-maxRow))   || 
			   (this.row+dir.row() >  (maxRow))         ||
			   (this.col+dir.col() < (maxCol-maxCol))   ||
			   (this.col+dir.col() > (maxCol));
	}
}
