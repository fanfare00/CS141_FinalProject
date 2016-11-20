package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;


public class InputHandler {	
	
	private List<Key> keys = new ArrayList<Key>();
	
	public void handleInput(Application app, char input){
		for (int i = 0; i < keys.size(); i++) {
			if (wasTyped(input, keys.get(i))) keys.get(i).executeCommand(app);
		}
	}
	
	private static boolean wasTyped(char input, Key key) {
		return (input == key.getSymbol());
	}
	
	public void setActiveKeys(List<Key> activeKeys){
		this.keys = activeKeys;
	}
}
