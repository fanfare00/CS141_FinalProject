package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.Utilities.Direction;

public class UIGrid {

	public int xLoc;
	public int yLoc;
	public int width;
	public int height;
	
	private char grid[][];
	
	private Application app;
	
	public boolean hasUpdated;
	
	//private UIGridSpace componentGrid[][];
	
	public List<UIGridSpace> spaces = new ArrayList<UIGridSpace>();
	
	public UIGrid(int xLoc, int yLoc, int width, int height, char[][] grid, Application app) {
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		
		this.app = app;
		
		
	}

	public void create(char[][] grid) {
		hasUpdated = true;
		
		if (spaces.size() == 0) {
			
			for (int m = 0; m < grid[0].length; m++) {
				for (int n = 0; n < grid.length; n++) {
	
					spaces.add(new UIGridSpace(m, n, new EmptyModel(), this, app));
				}
			}
		} else {
			for (int m = 0; m < grid[0].length; m++) {
				for (int n = 0; n < grid.length; n++) {
					//spaces.set((m*grid.length) + n, new UIGridSpace(m, n, new EmptyModel(), this, app));
					spaces.get((m*grid.length) + n).model = new EmptyModel();
					spaces.get((m*grid.length) + n).backModel = new EmptyModel();
				}
			}
		}
		
		this.grid = grid;
	}
	
	public void resetGrid() {
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).isOpen = false;
		}
	}

	public void add(int row, int col, char symbol) {
		
		switch (symbol) {
			case 'R': 
				if (spaces.get((row*grid.length) + col).isOpen) {
					spaces.get((row*grid.length) + col).model = new OpenRoomModel();
				} else {
					spaces.get((row*grid.length) + col).model = new RoomModel();
				}
			
				break;
			case 'r': spaces.get((row*grid.length) + col).backModel = new RadarModel();
				break;	
			case 'a': spaces.get((row*grid.length) + col).backModel = new AmmoModel();
				break;
			case 'i': spaces.get((row*grid.length) + col).backModel = new InvincibilityModel();
				break;
			case 'P': spaces.get((row*grid.length) + col).model = new PlayerModel();
				break;
			case 'N': spaces.get((row*grid.length) + col).model = new EnemyModel();
				break;
			case 'I':
				if (spaces.get((row*grid.length) + col).isOpen) {
					spaces.get((row*grid.length) + col).model = new OpenRoomModel();
				} else {
					spaces.get((row*grid.length) + col).model = new RoomModel();
				}
				spaces.get((row*grid.length) + col).backModel = new IntelModel();
				break;
			default: //spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new EmptyModel(), this, app));
		}
		
//		if(symbol == 'R') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new RoomModel()));
//		if(symbol == 'P') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
//		if(symbol == 'N') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
		
		//parseSpace();
	}
	
	public List<UIGridSpace> getSpaces() {
		return spaces;
	}
	
	public void draw(Graphics2D g, JFrame frame) {
//		for (UIGridSpace space : spaces) {
//			space.draw(g);
//		}
		
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).draw(g, frame);
			frame.add(spaces.get(i));
			
			
		}
		
//		for (UIGridSpace space : spaces) {
//			if (space.model instanceof RoomModel) space.draw(g);
//		}
//		
	}
	
	public UIGridSpace getPlayerSpace() {
		for (int i = 0; i < spaces.size(); i++) {
			if (spaces.get(i).model instanceof PlayerModel) return spaces.get(i);
		} 
		
		return spaces.get(0);
	}
	
	public void unlookAllSpaces() {
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).isLookedAt = false;
		}
	}

	public void revealLookedSpace(UIGridSpace oldSpace, Direction dir) {
		if (oldSpace.model instanceof OpenRoomModel) return;
		
		for (int i = 0; i < spaces.size(); i++) {	
			if ((oldSpace.col + dir.col()) == spaces.get(i).col) {	
				if ((oldSpace.row + dir.row()) == spaces.get(i).row) {
						spaces.get(i).isLookedAt = true;
				}
			}
						 
		} 
	}

	public void setCanShoot(boolean flag) {
		for (int i = 0; i < spaces.size(); i++) {
			spaces.get(i).canShoot = flag;
		}
	}
	
	public Direction getMoveDirection(UIGridSpace oldSpace, UIGridSpace space) {
		Direction dir = null;
		
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col + 1))) dir = Direction.LEFT;
		if ((oldSpace.row == space.row) && (oldSpace.col == (space.col - 1))) dir = Direction.RIGHT;
		if ((oldSpace.row == (space.row - 1)) && (oldSpace.col == (space.col))) dir = Direction.DOWN;
		if ((oldSpace.row == (space.row + 1)) && (oldSpace.col == (space.col))) dir = Direction.UP;
		
		return dir;
	}
}
