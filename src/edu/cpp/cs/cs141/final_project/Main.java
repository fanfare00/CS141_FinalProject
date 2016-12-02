/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodríguez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project;

import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.TextUserInterface;

// TODO: Auto-generated Javadoc
/**
 * The entry point for the Java application.
 */
public class Main {
	
	/** The Constant FPS. */
	private static final int FPS = 30; 
	
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) { 
 	
    	Application app = new Application(new TextUserInterface(), new Game());
    	app.start();
    	
		while(!app.isRunning()){ 
			long time = System.currentTimeMillis(); 
			time = (1000 / FPS) - (System.currentTimeMillis()-time); 
			
			app.run();
			
//			if (app.getClose()) {
//				app = new Application(new TextUserInterface(), new Game());
//				app.start();
//			}
			 
			if (time > 0) { 
				try { 
					Thread.sleep(time); 
                } 
                	catch(Exception e){} 
             } 
		}
 }
}
