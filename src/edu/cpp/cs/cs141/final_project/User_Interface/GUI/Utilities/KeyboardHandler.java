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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGridSpace;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyboardHandler.
 */
public class KeyboardHandler implements KeyListener{
	
	/** The grid. */
	UIGrid grid;
	
	/** The space. */
	UIGridSpace space;
	
	/** The app. */
	Application app;
	
	/** The frame. */
	JFrame frame;
	
	/** The p. */
	private boolean p;
	
	/**
	 * Instantiates a new keyboard handler.
	 *
	 * @param grid the grid
	 * @param space the space
	 * @param app the app
	 */
	public KeyboardHandler(UIGrid grid, UIGridSpace space, Application app) {
		this.grid = grid;
		this.space = space;
		this.app = app;
	}
	
	/**
	 * Instantiates a new keyboard handler.
	 *
	 * @param grid the grid
	 * @param app the app
	 * @param frame the frame
	 */
	public KeyboardHandler(UIGrid grid, Application app, JFrame frame) {
		this.grid = grid;
		this.app = app;
		this.frame = frame;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_9) app.toggleDebugMode();
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!app.getGameOverStatus()) app.setPaused(!app.getPaused());
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			p = !p;
			app.pauseEnemies(p);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_Y) {
			app.toggleUIType();
			frame.dispose();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) grid.setCanShoot(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
