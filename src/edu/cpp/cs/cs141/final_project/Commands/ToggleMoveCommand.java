package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class ToggleMoveCommand implements Command {

	private Application app;
	
	public ToggleMoveCommand(Application app){
		this.app = app;
	}
	
	@Override
	public void execute() {
		app.toggleMoveMode();
	}

}
