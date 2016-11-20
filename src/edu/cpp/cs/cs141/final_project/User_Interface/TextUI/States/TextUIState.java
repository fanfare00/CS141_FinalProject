package edu.cpp.cs.cs141.final_project.User_Interface.TextUI.States;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.UIState;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.InputHandler;
import edu.cpp.cs.cs141.final_project.User_Interface.TextUI.Utilities.Key;

public class TextUIState implements UIState {
	
	protected Key key_W = new Key('W');
	protected Key key_A = new Key('A');
	protected Key key_S = new Key('S');
	protected Key key_D = new Key('D');
	protected Key key_C = new Key('C');
	protected Key key_Z = new Key('Z');
	protected Key key_M = new Key('M');
	protected Key key_1 = new Key('1');
	protected Key key_2 = new Key('2');
	protected Key key_3 = new Key('3');
	protected Key key_4 = new Key('4');
	
	protected List<Key> keys = new ArrayList<Key>();
	protected List<Key> activeKeys = new ArrayList<Key>();
	
	protected InputHandler inputHandler = new InputHandler();
	protected Application app;
	
	public TextUIState(Application app) {
		this.app = app;
		
		keys.add(key_W);
		keys.add(key_A);
		keys.add(key_S);
		keys.add(key_D);
		keys.add(key_C);
		keys.add(key_Z);
		keys.add(key_M);
		keys.add(key_1);
		keys.add(key_2);
		keys.add(key_3);
		keys.add(key_4);
	}
	
	public void handleInput(char input) {
		inputHandler.setActiveKeys(this.getActiveKeys());
		inputHandler.handleInput(app, input);
	}
	
	public List<Key> getActiveKeys() {
		activeKeys.clear();
		
		for (Key key : keys) {
			if (key.getIsActive()) activeKeys.add(key);
		}
		
		return activeKeys;
	}

	public void update(boolean[] activeDirections) {
		
		keys.get(keys.indexOf(key_W)).setActive(activeDirections[0]);
		keys.get(keys.indexOf(key_A)).setActive(activeDirections[1]);
		keys.get(keys.indexOf(key_S)).setActive(activeDirections[2]);
		keys.get(keys.indexOf(key_D)).setActive(activeDirections[3]);
		
		keys.get(keys.indexOf(key_C)).setActive(true);
		if (app.getShootStatus()) keys.get(keys.indexOf(key_Z)).setActive(true);
	}
}
