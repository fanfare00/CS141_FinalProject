package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGridSpace;

public class ButtonHandler implements ActionListener{
	Application app;
	JFrame frame;
	UIGrid grid;
	
	public ButtonHandler(UIGrid grid, JFrame frame, Application app) {

		this.app = app;
		this.frame = frame;
		this.grid = grid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action.equals("Quit Game")) {
			frame.dispose();
			app.close();
		}
		
		if (action.equals("New Game")) {
			//frame.setVisible(false);
			app.startNewGame();
		}
		
		if (action.equals("Load Game")) {
			app.loadGameFromFile();
		}
		
		if (action.equals("Save Game")) {
			app.saveGameData();
		}
		
		if (action.equals("Toggle UI")) {
			if (!app.getGameOverStatus()) {
				app.setPaused(false);
				app.toggleUIType();
			}
		}
	}

}
