package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class RoomModel implements Model{

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x+1, y+1, width-2, height-2);
		
	}
	
}
