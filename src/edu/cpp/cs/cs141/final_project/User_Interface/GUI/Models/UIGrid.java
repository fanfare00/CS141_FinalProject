package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.cpp.cs.cs141.final_project.Application;

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
	
//	public void clearGrid() {
//		spaces.clear();
//	}


	public void add(int row, int col, char symbol) {
		
		switch (symbol) {
			case 'R': spaces.get((row*grid.length) + col).model = new RoomModel();//spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new RoomModel(), this, app));
				break;
			case 'r': spaces.get((row*grid.length) + col).backModel = new RadarModel();//spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new RadarModel(), this, app));
				break;	
			case 'a': spaces.get((row*grid.length) + col).backModel = new AmmoModel();//spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new AmmoModel(), this, app));
				break;
			case 'i': spaces.get((row*grid.length) + col).backModel = new InvincibilityModel();// spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new InvincibilityModel(), this, app));
				break;
			case 'P': spaces.get((row*grid.length) + col).model = new PlayerModel();//spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new PlayerModel(), this, app));
				break;
			case 'N': spaces.get((row*grid.length) + col).model = new EnemyModel();//spaces.set((row*grid.length) + col, new UIGridSpace(row, col, new EnemyModel(), this, app));
				break;
			case 'I': spaces.get((row*grid.length) + col).model = new RoomModel();
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
}
