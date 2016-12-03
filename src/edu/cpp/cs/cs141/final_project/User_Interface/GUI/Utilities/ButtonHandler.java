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
package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;


/**
 * The Class ButtonHandler handles menu button inputs and sends commands to application.
 */
public class ButtonHandler implements ActionListener{
	
	/** The app. */
	Application app;
	
	/** The frame. */
	JFrame frame;
	
	/** The grid. */
	UIGrid grid;
	
	/**
	 * Instantiates a new button handler.
	 *
	 * @param grid the grid
	 * @param frame the frame
	 * @param app the app
	 */
	public ButtonHandler(UIGrid grid, JFrame frame, Application app) {

		this.app = app;
		this.frame = frame;
		this.grid = grid;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action.equals("Quit Game")) {
			frame.dispose();
			app.close();
		}
		
		if (action.equals("New Game")) {
			//frame.setVisible(false);
			app.startNewGame();
		}
		
		if (action.equals("Load Game")) {
			app.loadGameFromFile();
		}
		
		if (action.equals("Save Game")) {
			if (!app.getGameOverStatus()) {
				app.saveGameData();
			} else {
				app.setAlertText("Can't do that now.");
			}
		}
		
		if (action.equals("Toggle UI")) {
			if (!app.getGameOverStatus()) {
				app.setPaused(false);
				app.toggleUIType();
			} else {
				app.setAlertText("Can't do that now.");
			}
		}
	}

}
