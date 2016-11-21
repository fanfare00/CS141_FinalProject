package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class ToggleDebugCommand implements Command{

	@Override
	public void execute(Application app) {
		app.toggleDebugMode();
	}
}
