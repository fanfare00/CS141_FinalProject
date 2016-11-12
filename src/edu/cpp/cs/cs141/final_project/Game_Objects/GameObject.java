package edu.cpp.cs.cs141.final_project.Game_Objects;

public class GameObject {
	
	private int xPos;
	private int yPos;
	protected char symbol;
	private String name;
	
	public GameObject(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getRow(){
		return xPos;
	}
	
	public int getCol() {
		return yPos;
	}

	public char getSymbol() {
		return symbol;
	}
}
