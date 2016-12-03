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
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.SwingUtilities;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities.MouseHandler;

import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * The Class UIGridSpace represents the spaces that make up the grid in the GUI.
 */
@SuppressWarnings("serial")
public class UIGridSpace extends JComponent{
	
	/** The x loc. */
	private int xLoc;
	
	/** The y loc. */
	private int yLoc;
	
	/** The Constant WIDTH. */
	private static final int WIDTH = 50;
	
	/** The Constant HEIGHT. */
	private static final int HEIGHT = 50;
	
	/** The row. */
	public int row;
	
	/** The col. */
	public int col;
	
	/** The model. */
	public Model model;
	
	/** The back model. */
	public Model backModel;
	
	/** The grid. */
	UIGrid grid;
	
	/** The app. */
	Application app;
	
	/** The rect. */
	public Rectangle rect;
	
	/** The is highlighted. */
	public boolean isHighlighted;
	
	/** The is open. */
	public boolean isOpen;
	
	/** The is looked at. */
	public boolean isLookedAt;
	
	/** The can shoot. */
	public boolean canShoot;
	
	/**
	 * Instantiates a new UI grid space.
	 *
	 * @param row the row
	 * @param col the col
	 * @param model the model
	 * @param grid the grid
	 * @param app the app
	 */
	public UIGridSpace(int row, int col, Model model, UIGrid grid, Application app) {
		this.row = row;
		this.col = col;
		
		this.yLoc = (row * WIDTH) + grid.yLoc;
		this.xLoc = (col * HEIGHT) + grid.xLoc;
		
		this.model = model;
		this.backModel = new EmptyModel();
		
		this.grid = grid;
		this.app = app;
		
		rect = new Rectangle(xLoc, yLoc, WIDTH, HEIGHT);
		
		this.setLocation(xLoc+5, yLoc+55);
		this.setSize(WIDTH, HEIGHT);
		
		
		this.addMouseListener(new MouseHandler(grid, this, app));
		

		
		if (model instanceof RoomModel) {
			this.setOpaque(true);
			this.setBackground(Color.BLACK);
		}
	}
	
	/**
	 * Checks if is mouse inside.
	 *
	 * @param comp the comp
	 * @return true, if is mouse inside
	 */
	public boolean isMouseInside(JComponent comp){
		boolean isMouseInside = false;
		
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();
		
		SwingUtilities.convertPointFromScreen(mousePoint, this);
		
		Rectangle rect = new Rectangle(0, 0, WIDTH, HEIGHT);
		if (rect.contains(mousePoint)) isMouseInside = true;
		
		return isMouseInside;
	}
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param frame the frame
	 */
	public void draw(Graphics2D g, JFrame frame) {
		
		g.setColor(Color.GRAY);
		g.draw(rect);
		
		
		g.setColor(Color.BLACK);
		if (model instanceof RoomModel) g.fill(rect);
		
		if (isLookedAt && !app.getDeathStatus()) {
			g.setColor(new Color(255, 255, 189));
			g.fillOval(xLoc, yLoc, WIDTH, HEIGHT);
		}
		
		backModel.draw(xLoc, yLoc, WIDTH, HEIGHT, g);
		model.draw(xLoc, yLoc, WIDTH, HEIGHT, g);
		
		if (isHighlighted) {
			
			g.setColor(Color.GREEN);
			g.drawRect(xLoc+1, yLoc+1, WIDTH-2, HEIGHT-2);
			g.drawRect(xLoc+2, yLoc+2, WIDTH-4, HEIGHT-4);

		}
		
		if (isMouseInside(this) && canShoot) {
			UIGridSpace oldSpace = null;
			
			for (int i = 0; i < grid.spaces.size(); i++) {
				if (grid.spaces.get(i).model instanceof PlayerModel) { 
					oldSpace = grid.spaces.get(i);	
				}
			}
			
			grid.setCanShoot(false);
			if (isHighlighted) app.playerAttack(grid.getMoveDirection(oldSpace, this));
		}
		
	}
	
	
}
