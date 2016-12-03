/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;


/**
 * The Class ToggleMenuCommand.
 */
public class ToggleMenuCommand implements Command{

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	public void execute(Application app) {
		app.toggleMenuMode();
	}
}
