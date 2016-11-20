package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.User_Interface.UIState;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.InputHandler;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class TextUIState implements UIState {
	
	protected List<Key> keys = new ArrayList<Key>();
	protected List<Key> activeKeys = new ArrayList<Key>();
	protected InputHandler inputHandler = new InputHandler();
	
	
	public void handleInput(char input) {
		inputHandler.setActiveKeys(this.getActiveKeys());
		inputHandler.handleInput(input);
	}
	
	public List<Key> getActiveKeys() {
		activeKeys.clear();
		
		for (Key key : keys) {
			if (key.getIsActive()) activeKeys.add(key);
		}
		
		return activeKeys;
	}

	public void update(boolean[] activeCommands) {
		for (int i = 0; i < activeCommands.length; i++){
			keys.get(i).setActive(activeCommands[i]);
		}
	}
}
