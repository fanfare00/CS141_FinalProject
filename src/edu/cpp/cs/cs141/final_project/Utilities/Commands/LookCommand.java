package edu.cpp.cs.cs141.final_project.Utilities.Commands;

import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;

public class LookCommand implements Command {

	private Actor actor;
	private int row, col;
	
	public LookCommand(Actor actor, int row, int col) {
		this.actor = actor;
		this.row = row;
		this.col = col;
	}
	
	@Override
	public void execute(Actor actor) {
		actor.look(row, col);
		
	}

}
