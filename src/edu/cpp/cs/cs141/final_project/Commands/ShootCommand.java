package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class ShootCommand implements Command {

	Application app;
	private int row, col;
	
	public ShootCommand(Application app, int row, int col) {
		this.app = app;
		this.row = row;
		this.col = col;
	}
	
	@Override
	public void execute() {
		app.attackCurrentActor(row, col);
	}

}
