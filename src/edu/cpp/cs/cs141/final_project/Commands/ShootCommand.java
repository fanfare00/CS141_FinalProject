/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;


/**
 * The Class ShootCommand.
 */
public class ShootCommand implements Command {
	
	/** The dir. */
	Direction dir;
	
	/**
	 * Instantiates a new shoot command.
	 *
	 * @param dir the dir
	 */
	public ShootCommand(Direction dir) {
		this.dir = dir;
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	@Override
	public void execute(Application app) {
		app.playerAttack(dir);
	}

}
