package edu.cpp.cs.cs141.final_project.Utilities;

import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Utilities.Commands.*;

public class InputHandler {
	private Command key_W;
	private Command key_A;
	private Command key_S;
	private Command key_D;
	private Command key_Z;
	private Command key_C;
	private Command key_M;
	
	private Actor actor;
	
	
	public InputHandler(Actor actor){
		this.actor = actor;
		
	}
	
	private void bindInitialCommands() {
		
		
		bindMoveCommands();
	}
	
	public void bindMoveCommands() {
		key_W = new MoveCommand(actor, 1, 0);
		key_A = new MoveCommand(actor, 0, -1);
		key_S = new MoveCommand(actor, -1, 0);
		key_D = new MoveCommand(actor, 0, 1);
	}
	
	public void bindLookCommands() {
		key_W = new LookCommand(actor, 1, 0);
		key_A = new LookCommand(actor, 0, -1);
		key_S = new LookCommand(actor, -1, 0);
		key_D = new LookCommand(actor, 0, 1);
	}
	
	public void handleInput(){	
	}
}
