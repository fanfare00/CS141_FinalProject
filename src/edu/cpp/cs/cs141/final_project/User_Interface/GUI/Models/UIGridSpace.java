package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class UIGridSpace {
	private int xLoc;
	private int yLoc;
	private int width;
	private int height;
	
	private static final Color COLOR = Color.GRAY;
	protected Model model;
	
	public UIGridSpace(int xLoc, int yLoc, int width, int height, Model model) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.width = width;
		this.height = height;
		this.model = model;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.drawRect(xLoc, yLoc, width, height);
		model.draw(xLoc, yLoc, width, height, g);
	}
}
