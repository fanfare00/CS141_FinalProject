package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Commands.*;

public class UISelectionState extends TextUIState {

	public UISelectionState(Application app) {
		super(app);
		
		key_1.setText("Run game in text mode.");
		key_1.setCommand(new SelectTextUICommand());
		key_1.setActive(true);
		
		key_2.setText("Run game in GUI mode.");
		key_2.setCommand(new SelectGUICommand());
		key_2.setActive(true);
	}
	
	public void update(boolean[] activeDirections) {	
		key_3.setActive(false);
		key_4.setActive(false);
		
		keys.get(keys.indexOf(key_W)).setActive(false);
		keys.get(keys.indexOf(key_A)).setActive(false);
		keys.get(keys.indexOf(key_S)).setActive(false);
		keys.get(keys.indexOf(key_D)).setActive(false);
			
		keys.get(keys.indexOf(key_C)).setActive(false);
		keys.get(keys.indexOf(key_Z)).setActive(false);
		keys.get(keys.indexOf(key_M)).setActive(false);
	}

}
