package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class MenuCommand implements Command{

	public void execute(Application app) {
		app.toggleMenuMode();
	}
}
