package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class ShootCommand implements Command {
	@Override
	public void execute(Application app) {
		app.playerAttack();
	}

}
