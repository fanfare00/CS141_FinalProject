package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class MenuCommand implements Command{

	Application app;
	
	public MenuCommand(Application app){
		this.app = app;
	}

	public void execute() {
		app.toggleMenuMode();
	}
	

}
