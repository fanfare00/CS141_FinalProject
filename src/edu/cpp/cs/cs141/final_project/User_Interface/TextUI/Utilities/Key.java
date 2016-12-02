/*
 * 
 */
package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class Key.
 */
public class Key {
	
	/** The symbol. */
	private char symbol;
	
	/** The text. */
	private String text;
	
	/** The command. */
	private Command command;
	
	/** The is active. */
	private boolean isActive = false;
	
	/** The is visible. */
	private boolean isVisible = true;
	
	/**
	 * Instantiates a new key.
	 */
	public Key() {

	}
	
	/**
	 * Instantiates a new key.
	 *
	 * @param symbol the symbol
	 */
	public Key(char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Instantiates a new key.
	 *
	 * @param symbol the symbol
	 * @param text the text
	 */
	public Key(char symbol, String text) {
		this.symbol = symbol;
		this.text = text;
	}
	
	/**
	 * Instantiates a new key.
	 *
	 * @param symbol the symbol
	 * @param text the text
	 * @param command the command
	 */
	public Key(char symbol, String text, Command command) {
		this.symbol = symbol;
		this.text = text;
		this.command = command;
	}
	
	/**
	 * Sets the command.
	 *
	 * @param command the new command
	 */
	public void setCommand(Command command){this.command = command;}
	
	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {this.text = text;}
	
	/**
	 * Sets the active.
	 *
	 * @param flag the new active
	 */
	public void setActive(boolean flag) {this.isActive = flag;}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {return this.symbol;}
	
	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {return this.text;}
	
	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public boolean getIsActive() {return this.isActive;}
	
	/**
	 * Execute command.
	 *
	 * @param app the app
	 */
	public void executeCommand(Application app) {
		if (isActive) command.execute(app);
	}

	/**
	 * Gets the visible.
	 *
	 * @return the visible
	 */
	public boolean getVisible() {return this.isVisible;}
	
	/**
	 * Sets the visible.
	 *
	 * @param flag the new visible
	 */
	public void setVisible(boolean flag) {this.isVisible = flag;}
}
