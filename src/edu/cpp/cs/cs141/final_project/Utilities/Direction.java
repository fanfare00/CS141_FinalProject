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

// TODO: Auto-generated Javadoc
/**
 * The Enum Direction.
 */
public enum Direction {
    
    /** The up. */
    UP 		(-1,  0),
    
    /** The left. */
    LEFT	( 0, -1),
    
    /** The down. */
    DOWN	( 1,  0),
    
    /** The right. */
    RIGHT	( 0,  1);
	
	/** The row. */
	final int row;
	
	/** The col. */
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
	 * @return the int
	 */
	public int row(){return row;}
	
	/**
	 * Col.
	 *
	 * @return the int
	 */
	public int col(){return col;}
	
	
}

