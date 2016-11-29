package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGridSpace;

public class KeyboardHandler implements KeyListener{
	UIGrid grid;
	UIGridSpace space;
	Application app;
	
	public KeyboardHandler(UIGrid grid, UIGridSpace space, Application app) {
		this.grid = grid;
		this.space = space;
		this.app = app;
	}
	public KeyboardHandler(UIGrid grid) {
		this.grid = grid;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}