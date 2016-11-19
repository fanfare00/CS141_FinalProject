package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.States.*;


public class TextUIStateManager {	
	
	public static MovingState moving;
	public static LookingState looking;
	
	public TextUIStateManager(Application app){
		moving = new MovingState(app);
		looking = new LookingState(app);
	}
	
}
