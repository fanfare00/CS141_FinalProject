package edu.cpp.cs.cs141.final_project.Utilities;

public enum Direction {
    UP 		(-1,  0),
    LEFT	( 0, -1),
    DOWN	( 1,  0),
    RIGHT	( 0,  1);
	
	final int row;
	final int col;
	
	Direction(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int row(){return row;}
	public int col(){return col;}
	
	
}

