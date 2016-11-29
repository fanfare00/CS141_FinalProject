package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class IntelModel implements Model{

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		int innerWidth = width - 15;
		int innerHeight = height - 15;
		
		int innerX = x + (innerWidth/3)-3;
		int innerY = y + (innerHeight/3)-3;

		//g.setColor(Color.GREEN);
		//g.drawOval(innerX, innerY, innerWidth, innerHeight);
		
		g.drawImage(Toolkit.getDefaultToolkit().getImage("res/intel.png"), innerX, innerY, innerWidth, innerHeight, null);
		
	}

}
