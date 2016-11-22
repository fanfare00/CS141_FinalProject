package edu.cpp.cs.cs141.final_project;

import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.TextUserInterface;

/**
 * The entry point for the Java application.
 */
public class Main {
    public static void main(String[] args) { 
 	
    	Application app = new Application(new TextUserInterface(), new Game());
    	app.start();
    	
 }
}
