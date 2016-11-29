package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import javax.swing.SwingUtilities;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities.KeyboardHandler;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities.MouseHandler;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class UIGridSpace extends JComponent{
	private int xLoc;
	private int yLoc;
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	
	public int row;
	public int col;
	
	private static final Color COLOR = Color.GRAY;
	public Model model;
	public Model backModel;
	
	public Rectangle rect;
	
	public boolean isHighlighted;
	
	public UIGridSpace(int row, int col, Model model, UIGrid grid, Application app) {
		this.row = row;
		this.col = col;
		
		this.yLoc = (row * WIDTH) + grid.yLoc;
		this.xLoc = (col * HEIGHT) + grid.xLoc;
		
		this.model = model;
		this.backModel = new EmptyModel();
		
		rect = new Rectangle(xLoc, yLoc, WIDTH, HEIGHT);
		
		this.setLocation(xLoc+5, yLoc+55);
		this.setSize(WIDTH, HEIGHT);
		
		
		this.addMouseListener(new MouseHandler(grid, this, app));
		this.addKeyListener(new KeyboardHandler(grid, this, app));
	}
	
//	public boolean isMouseInside(JComponent comp){
//		boolean isMouseInside = false;
//		
//		Point mousePoint = MouseInfo.getPointerInfo().getLocation();
//		
//		SwingUtilities.convertPointFromScreen(mousePoint, this);
//		
//		//mousePoint.x = (int) (mousePoint.getX() - 10);
//		//mousePoint.y = (int) (mousePoint.getY() - 30);
//		
//		Rectangle rect = new Rectangle(0, 0, WIDTH, HEIGHT);
//		if (rect.contains(mousePoint)) isMouseInside = true;
//		
//		return isMouseInside;
//	}
	
	public void draw(Graphics2D g, JFrame frame) {
		
		//
		g.setColor(Color.GRAY);
		g.draw(rect);
		
		//if (isMouseInside(frame)) {
		if (isHighlighted) {
		
			g.setColor(Color.GREEN);
			g.drawRect(xLoc+1, yLoc+1, WIDTH-2, HEIGHT-2);
			g.drawRect(xLoc+2, yLoc+2, WIDTH-4, HEIGHT-4);
		//}
		}
		
		backModel.draw(xLoc, yLoc, WIDTH, HEIGHT, g);
		model.draw(xLoc, yLoc, WIDTH, HEIGHT, g);
		
	}
	
	
}
