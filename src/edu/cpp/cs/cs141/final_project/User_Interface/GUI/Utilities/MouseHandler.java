package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.EmptyModel;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.PlayerModel;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGridSpace;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class MouseHandler extends InputHandler implements MouseListener {
	
	UIGrid grid;
	UIGridSpace space;
	Application app;

	public MouseHandler(UIGrid grid, UIGridSpace space, Application app) {
		this.grid = grid;
		this.space = space;
		this.app = app;
	}
	
	private Direction getMoveDirection(UIGridSpace oldSpace) {
		Direction dir = Direction.UP;
		
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col + 1))) dir = Direction.LEFT;
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col - 1))) dir = Direction.RIGHT;
		if ((oldSpace.row == (space.row - 1)) && (oldSpace.col == (space.col))) dir = Direction.DOWN;
		if ((oldSpace.row == (space.row + 1)) && (oldSpace.col == (space.col))) dir = Direction.UP;
		
		return dir;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		UIGridSpace oldSpace = null;
		
		for (int i = 0; i < grid.spaces.size(); i++) {
			if (grid.spaces.get(i).model instanceof PlayerModel) { 
				oldSpace = grid.spaces.get(i);
				grid.spaces.get(i).model = new EmptyModel();
			}
		}
		//grid.spaces.get(grid.spaces.indexOf(space)).model = new PlayerModel();
		for (int i = 0; i < grid.spaces.size(); i++) {
			if ((grid.spaces.get(i).row == space.row) && (grid.spaces.get(i).col == space.col)) {
				grid.spaces.get(i).model = new PlayerModel();
			}
		}
		
		app.playerMove(getMoveDirection(oldSpace));

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
