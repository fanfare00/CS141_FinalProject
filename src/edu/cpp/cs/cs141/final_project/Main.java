package edu.cpp.cs.cs141.final_project;

import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.TextUserInterface;

/**
 * The entry point for the Java application.
 */
public class Main {
	private static final int FPS = 30; 
	
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
