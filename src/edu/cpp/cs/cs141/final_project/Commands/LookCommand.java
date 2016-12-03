/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;


/**
 * The Class LookCommand.
 */
public class LookCommand implements Command {

	/** The dir. */
	Direction dir;
	
	/**
	 * Instantiates a new look command.
	 *
	 * @param dir the dir
	 */
	public LookCommand(Direction dir) {
		this.dir = dir;
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	@Override
	public void execute(Application app) {
		app.playerLook(dir);
	}

}
