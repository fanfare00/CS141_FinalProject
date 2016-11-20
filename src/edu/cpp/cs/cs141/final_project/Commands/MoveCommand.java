package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Direction;


public class MoveCommand implements Command {

	private Direction dir;
	
	public MoveCommand(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public void execute(Application app) {
		app.playerMove(dir);
	}

}
