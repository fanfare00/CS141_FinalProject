/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectTextUICommand.
 */
public class SelectTextUICommand implements Command {

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	@Override
	public void execute(Application app) {
		app.stayInTextUI();
	}

}
