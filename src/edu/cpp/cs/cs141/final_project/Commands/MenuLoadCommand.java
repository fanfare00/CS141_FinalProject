/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuLoadCommand.
 */
public class MenuLoadCommand implements Command {

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	@Override
	public void execute(Application app) {
		app.loadGameFromFile();
	}

}
