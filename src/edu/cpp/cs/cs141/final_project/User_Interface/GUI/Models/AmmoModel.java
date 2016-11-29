package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class AmmoModel implements Model{
	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		int innerWidth = 20;
		int innerHeight = 20;
		
		int innerX = x + (width/4) - innerWidth/2;
		int innerY = y + (height - 10) - innerHeight/2;

		//g.setColor(Color.MAGENTA);
		//g.fillOval(innerX, innerY, innerWidth, innerHeight);
		
		g.drawImage(Toolkit.getDefaultToolkit().getImage("res/bullet.png"), innerX, innerY, innerWidth, innerHeight, null);
	}
}
