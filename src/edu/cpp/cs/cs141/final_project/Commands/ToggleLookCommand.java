package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class ToggleLookCommand implements Command {

	private Application app;
	
	public ToggleLookCommand(Application app){
		this.app = app;
	}
	
	@Override
	public void execute() {
		app.toggleLookMode();
	}

}
