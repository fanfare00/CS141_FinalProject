package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class IntelModel implements Model{

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x + width/2 - width/6, y + height/2 - height/6, width/3, height/3);
		
	}

}
