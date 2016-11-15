package edu.cpp.cs.cs141.final_project.Game_Objects;

import java.io.Serializable;

/**
 * An object that has some position and appearance in the game.
 */
public class GameObject implements Serializable{
	
	protected int row;
	protected int col;
	protected char symbol;
	protected String name;
	
	public GameObject(int xPos, int yPos) {
		this.row = xPos;
		this.col = yPos;
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
	
	public boolean checkCollision(GameObject o) {
		return (o.getCol() == this.col) && (o.getRow() == this.row);
	}
}
