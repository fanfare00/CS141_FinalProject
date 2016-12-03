/*
 * 
 */
package edu.cpp.cs.cs141.final_project.Commands;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;



/**
 * The Class MoveCommand.
 */
public class MoveCommand implements Command {

	/** The dir. */
	private Direction dir;
	
	/**
	 * Instantiates a new move command.
	 *
	 * @param dir the dir
	 */
	public MoveCommand(Direction dir) {
		this.dir = dir;
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.Commands.Command#execute(edu.cpp.cs.cs141.final_project.Application)
	 */
	@Override
	public void execute(Application app) {
		app.playerMove(dir);
	}

}
