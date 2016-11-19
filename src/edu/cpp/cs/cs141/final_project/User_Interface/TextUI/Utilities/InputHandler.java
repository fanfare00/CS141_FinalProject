package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import java.util.ArrayList;
import java.util.List;


public abstract class InputHandler {	
	
	public static List<Key> AllKeys = new ArrayList<Key>();
	public static List<Key> ActiveKeys = new ArrayList<Key>();
	
	public static void handleInput(char input){
		for (Key key : ActiveKeys) {
			if (wasTyped(input, key)) key.executeCommand();
		}
	}
	
	private static boolean wasTyped(char input, Key key) {
		return (input == key.getSymbol());
	}
}
