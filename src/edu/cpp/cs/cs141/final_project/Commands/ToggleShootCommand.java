/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;

// TODO: Auto-generated Javadoc
/**
 * The Class ToggleShootCommand.
 */
public class ToggleShootCommand implements Command{
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	public void execute(Application app) {
		app.toggleShootMode();
	}
}
