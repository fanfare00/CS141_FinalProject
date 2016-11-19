package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.States;

public interface TextUIState {

	public void handleInput(char input);
	public TextUIState update();
}
