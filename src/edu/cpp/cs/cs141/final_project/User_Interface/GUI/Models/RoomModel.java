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


/**
 * The Class RoomModel.
 */
public class RoomModel implements Model{

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.Model#draw(int, int, int, int, java.awt.Graphics2D)
	 */
	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		g.setColor(new Color(195, 195, 195));
		g.drawRect(x+1, y+1, width-2, height-2);
		g.drawRect(x+2, y+2, width-4, height-4);
		g.drawRect(x+3, y+3, width-6, height-6);
		
		g.setColor(new Color(102, 51, 0));
		g.drawLine(x+10, y+1, x+width-10, y+1);
		g.drawLine(x+10, y+2, x+width-10, y+2);
		g.drawLine(x+10, y+3, x+width-10, y+3);
		
	}
	
}
