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

// TODO: Auto-generated Javadoc
/**
 * The Class MouseHandler.
 */
public class MouseHandler implements MouseListener {
	
	/** The grid. */
	UIGrid grid;
	
	/** The space. */
	UIGridSpace space;
	
	/** The app. */
	Application app;

	/**
	 * Instantiates a new mouse handler.
	 *
	 * @param grid the grid
	 * @param space the space
	 * @param app the app
	 */
	public MouseHandler(UIGrid grid, UIGridSpace space, Application app) {
		this.grid = grid;
		this.space = space;
		this.app = app;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
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
			
			if (app.getLookStatus() && space != grid.getPlayerSpace()) {
				if ((SwingUtilities.isRightMouseButton(e)) && ((app.getDirectionalConditions()[grid.getMoveDirection(oldSpace, space).ordinal()])) | (app.getRoomCheckCondition())) {
					space.isOpen = true;
					app.playerLook(grid.getMoveDirection(oldSpace, space));
					grid.revealLookedSpace(space, grid.getMoveDirection(oldSpace, space));
					grid.hasUpdated = false;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
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
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		space.isHighlighted = false;

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
