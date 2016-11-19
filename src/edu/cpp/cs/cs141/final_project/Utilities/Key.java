package edu.cpp.cs.cs141.final_project.Utilities;

import edu.cpp.cs.cs141.final_project.Utilities.Commands.Command;

public class Key {
	private char symbol;
	private String text;
	private Command command;
	private boolean isActive;
	
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
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setActive(boolean flag) {
		this.isActive = flag;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	public void executeCommand() {
		this.command.execute();
	}
}
