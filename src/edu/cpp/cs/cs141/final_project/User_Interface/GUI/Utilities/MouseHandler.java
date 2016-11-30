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
	


	@Override
	public void mouseClicked(MouseEvent e) {
		UIGridSpace oldSpace = null;
		grid.unlookAllSpaces();
		
		if (grid.hasUpdated) {	
			oldSpace = grid.getPlayerSpace();
		
			if ((grid.getMoveDirection(oldSpace, space) != null) && (app.getDirectionalConditions()[grid.getMoveDirection(oldSpace, space).ordinal()])) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					space.model = new PlayerModel();
					app.playerMove(grid.getMoveDirection(oldSpace, space));
					grid.hasUpdated = false;
					
				}	
			} else {
				oldSpace.model = new PlayerModel();
			}
			
			if (app.getLookStatus()) {
				if ((SwingUtilities.isRightMouseButton(e)) && ((app.getDirectionalConditions()[grid.getMoveDirection(oldSpace, space).ordinal()])) | (app.getRoomCheckCondition())) {
					space.isOpen = true;
					app.playerLook(grid.getMoveDirection(oldSpace, space));
					grid.revealLookedSpace(space, grid.getMoveDirection(oldSpace, space));
					grid.hasUpdated = false;
				}
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
			if ((grid.getMoveDirection(oldSpace, space) != null) && ( (app.getDirectionalConditions()[grid.getMoveDirection(oldSpace, space).ordinal()]))) {
					//| space.isMouseInside(space))) {
				space.isHighlighted = true;
			}
		}
		
		if (space.canShoot) {

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
