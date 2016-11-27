package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class InvincibilityModel implements Model{

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		int innerWidth = width/4;
		int innerHeight = height/4;
		
		int innerX = x + (width/2) - innerWidth/2;
		int innerY = y + (height/2) - innerHeight/2;

		g.setColor(Color.ORANGE);
		g.fillOval(innerX, innerY, innerWidth, innerHeight);
		
	}

}
