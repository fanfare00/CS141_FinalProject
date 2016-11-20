package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class ShootCommand implements Command {

	private int row, col;
	
	public ShootCommand(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public void execute(Application app) {
		app.attackCurrentActor(row, col);
	}

}
