package edu.cpp.cs.cs141.final_project.Game_Objects;

import java.io.Serializable;

public class GameObject implements Serializable{
	
	protected int row;
	protected int col;
	protected char symbol;
	protected String name;
	
	public GameObject(int xPos, int yPos) {
		this.row = xPos;
		this.col = yPos;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public char getSymbol() {
		return symbol;
	}
}
