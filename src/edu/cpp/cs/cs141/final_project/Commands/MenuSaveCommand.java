package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

public class MenuSaveCommand implements Command {

	@Override
	public void execute(Application app) {
		app.saveGameData();
		
	}

}
