package edu.cpp.cs.cs141.final_project.Utilities.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class LookCommand implements Command {

	private int row, col;
	private Application app;
	
	public LookCommand(Application app, int row, int col) {
		this.row = row;
		this.col = col;
		this.app = app;
	}
	
	@Override
	public void execute() {
		app.lookCurrentActor(row, col);
	}

}
