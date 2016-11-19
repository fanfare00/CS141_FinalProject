package edu.cpp.cs.cs141.final_project.Utilities;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Utilities.Commands.*;

public abstract class InputHandler {	
	
	private List<Key> allKeys = new ArrayList<Key>();
	private List<Key> activeKeys = new ArrayList<Key>();
	
	public void setAllKeys(List<Key> allKeys) {
		this.allKeys = allKeys;
	}
	
	public void setActiveKeys(List<Key> activeKeys) {
		this.activeKeys = activeKeys;
	}
	
	public void handleInput(char input){
		for (Key key : activeKeys) {
			if (wasTyped(input, key)) key.executeCommand();
		}
	}
	
	private static boolean wasTyped(char input, Key key) {
		return (input == key.getSymbol());
	}
}
