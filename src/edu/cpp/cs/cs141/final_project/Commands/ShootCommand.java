package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class ShootCommand implements Command {
	Direction dir;
	
	public ShootCommand(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public void execute(Application app) {
		app.playerAttack(dir);
	}

}
