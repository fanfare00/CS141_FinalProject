package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.EmptyModel;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.OpenRoomModel;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.PlayerModel;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGridSpace;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class MouseHandler implements MouseListener {
	
	UIGrid grid;
	UIGridSpace space;
	Application app;

	public MouseHandler(UIGrid grid, UIGridSpace space, Application app) {
		this.grid = grid;
		this.space = space;
		this.app = app;
	}
	
	private Direction getMoveDirection(UIGridSpace oldSpace) {
		Direction dir = null;
		
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col + 1))) dir = Direction.LEFT;
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col - 1))) dir = Direction.RIGHT;
		if ((oldSpace.row == (space.row - 1)) && (oldSpace.col == (space.col))) dir = Direction.DOWN;
		if ((oldSpace.row == (space.row + 1)) && (oldSpace.col == (space.col))) dir = Direction.UP;
		
		return dir;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		UIGridSpace oldSpace = null;
		
		if (grid.hasUpdated) {	
			oldSpace = grid.getPlayerSpace();
		
			if ((getMoveDirection(oldSpace) != null) && (app.getDirectionalConditions()[getMoveDirection(oldSpace).ordinal()])) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					space.model = new PlayerModel();
					app.playerMove(getMoveDirection(oldSpace));
					grid.hasUpdated = false;
				}	
			} else {
				oldSpace.model = new PlayerModel();
			}
			
			if ((SwingUtilities.isRightMouseButton(e)) && ((app.getDirectionalConditions()[getMoveDirection(oldSpace).ordinal()])) | (app.getRoomCheckCondition())) {
				space.isOpen = true;
				app.playerLook(getMoveDirection(oldSpace));
				grid.hasUpdated = false;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		UIGridSpace oldSpace = null;
		
		if (app.getRoomCheckCondition()) space.isHighlighted = true;
		
		for (int i = 0; i < grid.spaces.size(); i++) {
			if (grid.spaces.get(i).model instanceof PlayerModel) { 
				oldSpace = grid.spaces.get(i);	
			}
		}
		
		if (oldSpace != null) {
			if ((getMoveDirection(oldSpace) != null) && ( (app.getDirectionalConditions()[getMoveDirection(oldSpace).ordinal()]))) {
					//| space.isMouseInside(space))) {
				space.isHighlighted = true;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		space.isHighlighted = false;

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
