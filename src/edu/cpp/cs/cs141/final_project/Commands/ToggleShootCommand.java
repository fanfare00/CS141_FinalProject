package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class ToggleShootCommand implements Command{
	private Application app;

	public ToggleShootCommand(Application app){
		this.app = app;
	}
	
	public void execute() {
		app.toggleShootMode();
	}
}
