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
package edu.cpp.cs.cs141.final_project.Utilities;


/**
 * A direction Enum for easier handling of row/col direction changes.
 */
public enum Direction {
    
    /** The UP enum */
    UP 		(-1,  0),
    
    /** The left enum. */
    LEFT	( 0, -1),
    
    /** The down enum. */
    DOWN	( 1,  0),
    
    /** The right enum. */
    RIGHT	( 0,  1);
	
	/** row. */
	final int row;
	
	/** column. */
	final int col;
	
	/**
	 * Instantiates a new direction.
	 *
	 * @param row the row
	 * @param col the col
	 */
	Direction(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Row.
	 *
	 * @return the int row
	 */
	public int row(){return row;}
	
	/**
	 * Col.
	 *
	 * @return the int col
	 */
	public int col(){return col;}
	
	
}

