package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class RoomModel implements Model{

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		g.setColor(new Color(195, 195, 195));
		g.drawRect(x+1, y+1, width-2, height-2);
		g.drawRect(x+2, y+2, width-4, height-4);
		g.drawRect(x+3, y+3, width-6, height-6);
		
		g.setColor(new Color(102, 51, 0));
		g.drawLine(x+10, y+1, x+width-10, y+1);
		g.drawLine(x+10, y+2, x+width-10, y+2);
		g.drawLine(x+10, y+3, x+width-10, y+3);
		
	}
	
}
