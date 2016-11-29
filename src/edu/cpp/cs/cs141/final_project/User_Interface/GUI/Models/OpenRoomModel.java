package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class OpenRoomModel implements Model {

	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawRect(x+1, y+1, width-2, height-2);
		g.drawRect(x+2, y+2, width-4, height-4);
		g.drawRect(x+3, y+3, width-6, height-6);
		
		//g.setColor(new Color(102, 51, 0));
		g.setColor(new Color(255, 255, 189));
		g.drawLine(x+10, y+1, x+width-10, y+1);
		g.drawLine(x+10, y+2, x+width-10, y+2);
		g.drawLine(x+10, y+3, x+width-10, y+3);
		
		g.setColor(new Color(102, 51, 0));
		g.drawLine(x+10, y+1, x+10, y+height-22);
		g.drawLine(x+11, y+1, x+11, y+height-22);
		g.drawLine(x+12, y+1, x+12, y+height-22);
		
	}

}
