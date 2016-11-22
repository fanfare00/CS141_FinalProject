package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class MenuQuitCommand implements Command {

	@Override
	public void execute(Application app) {
		app.close();
	}

}
