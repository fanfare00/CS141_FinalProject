package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class LookCommand implements Command {

	Direction dir;
	
	public LookCommand(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public void execute(Application app) {
		app.playerLook(dir);
	}

}
