package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import java.util.ArrayList;
import java.util.List;


public class InputHandler {	
	
	public static List<Key> keys = new ArrayList<Key>();
	
	public void handleInput(char input){
		for (Key key : keys) {
			if (wasTyped(input, key)) key.executeCommand();
		}
	}
	
	private static boolean wasTyped(char input, Key key) {
		return (input == key.getSymbol());
	}
	
	public void setActiveKeys(List<Key> activeKeys){
		this.keys = activeKeys;
	}
}
