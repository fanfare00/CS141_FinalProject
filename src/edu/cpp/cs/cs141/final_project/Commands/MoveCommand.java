package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;


public class MoveCommand implements Command {

	private int row, col;
	
	public MoveCommand(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public void execute(Application app) {
		app.moveCurrentActor(row, col);
	}

}
