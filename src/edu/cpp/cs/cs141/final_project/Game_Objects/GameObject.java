package edu.cpp.cs.cs141.final_project.Game_Objects;

import java.io.Serializable;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Direction;

/**
 * An object that has some position and appearance in the game.
 */
public abstract class GameObject implements Serializable{
	
	protected static final int MAX_ROW = 8;
	protected static final int MAX_COL = 8;
	
	protected int row;
	protected int col;
	protected char symbol;
	protected String name;
	protected boolean isActive;
	protected boolean isVisible;
	
	public GameObject(int xPos, int yPos) {
		this.row = xPos;
		this.col = yPos;
		this.isActive = true;
		this.isVisible = false;
	}
	
	/**
	 * Returns the row of this {@link GameObject}.
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Returns the column of this {@link GameObject}.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns the symbol of this {@link GameObject}.
	 */
	public char getSymbol() {
		return symbol;
	}
	
	public void setActive(boolean flag) {
		isActive = flag;
	}
	
	public void setVisible(boolean flag) {
		isVisible = flag;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public abstract void update(List<GameObject> activeEntities);
	
	
	public boolean checkCollision(GameObject obj, Direction dir){
		return (this.row+dir.row() == obj.getRow()) &&
			   (this.col+dir.col() == obj.getCol());
	}
	
	public boolean checkCollision(GameObject obj, int row, int col){
		return (this.row+row == obj.getRow()) &&
			   (this.col+col == obj.getCol());
	}
	
	public boolean checkOutOfBounds(int maxRow, int maxCol, Direction dir) {	
		return (this.row+dir.row() < (maxRow-maxRow))   || 
			   (this.row+dir.row() >  (maxRow))         ||
			   (this.col+dir.col() < (maxCol-maxCol))   ||
			   (this.col+dir.col() > (maxCol));
	}
}
