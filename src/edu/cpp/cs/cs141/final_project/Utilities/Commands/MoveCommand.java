package edu.cpp.cs.cs141.final_project.Utilities.Commands;

import edu.cpp.cs.cs141.final_project.Application;


public class MoveCommand implements Command {

	private Application app;
	private int row, col;
	
	public MoveCommand(Application app, int row, int col) {
		this.app = app;
		this.row = row;
		this.col = col;
	}
	
	@Override
	public void execute() {
		app.moveCurrentActor(row, col);
	}

}
