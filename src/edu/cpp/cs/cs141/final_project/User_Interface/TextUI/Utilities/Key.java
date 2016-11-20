package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import edu.cpp.cs.cs141.final_project.Commands.Command;

public class Key {
	private char symbol;
	private String text;
	private Command command;
	private boolean isActive = true;
	
	public Key(char symbol) {
		this.symbol = symbol;
	}
	
	public Key(char symbol, String text) {
		this.symbol = symbol;
		this.text = text;
	}
	
	public Key(char symbol, String text, Command command) {
		this.symbol = symbol;
		this.text = text;
		this.command = command;
	}
	
	public void setCommand(Command command){this.command = command;}
	
	public void setText(String text) {this.text = text;}
	
	public void setActive(boolean flag) {this.isActive = flag;}
	
	public char getSymbol() {return this.symbol;}
	
	public String getText() {return this.text;}
	
	public boolean getIsActive() {return this.isActive;}
	
	public void executeCommand() {
		if (isActive) command.execute();
	}
}
