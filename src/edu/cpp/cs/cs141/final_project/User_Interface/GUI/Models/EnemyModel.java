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

import java.awt.Graphics2D;
import java.awt.Toolkit;



/**
 * The Class EnemyModel.
 */
public class EnemyModel implements Model{

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.Model#draw(int, int, int, int, java.awt.Graphics2D)
	 */
	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		
		int innerWidth = width - 15;
		int innerHeight = height - 15;
		
		int innerX = x + (innerWidth/3)-3;
		int innerY = y + (innerHeight/3)-3;

		//g.setColor(Color.RED);
		//g.drawOval(innerX, innerY, innerWidth, innerHeight);
		
		
		g.drawImage(Toolkit.getDefaultToolkit().getImage("res/ninja.png"), innerX, innerY, innerWidth, innerHeight, null);
	}
}
