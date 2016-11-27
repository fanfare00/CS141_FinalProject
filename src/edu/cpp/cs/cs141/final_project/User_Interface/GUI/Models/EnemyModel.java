package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class EnemyModel implements Model{

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		
		int innerWidth = width/2;
		int innerHeight = height/2;
		
		int innerX = x + (innerWidth/2);
		int innerY = y + (innerHeight/2);

		g.setColor(Color.RED);
		g.drawOval(innerX, innerY, innerWidth, innerHeight);
	}
}
