package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

public class ActorState {
	boolean[] moveConditions;
	boolean[] proximityConditions;
	
	public ActorState(boolean[] moveConditions, boolean[] proximityConditions) {
		this.moveConditions = moveConditions;
		this.proximityConditions = proximityConditions;
	}
	
	public boolean[] getMoveConditions(){
		return moveConditions;
	}

	public boolean[] getProximityConditions() {
		return proximityConditions;
	}
}
