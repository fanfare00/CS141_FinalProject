package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;


public class InputHandler {	
	
	public static List<Key> keys = new ArrayList<Key>();
	
	public void handleInput(Application app, char input){
		for (Key key : keys) {
			if (wasTyped(input, key)) key.executeCommand(app);
		}
	}
	
	private static boolean wasTyped(char input, Key key) {
		return (input == key.getSymbol());
	}
	
	public void setActiveKeys(List<Key> activeKeys){
		this.keys = activeKeys;
	}
}
