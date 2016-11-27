package edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class UIGrid {

	private int xLoc;
	private int yLoc;
	private int width;
	private int height;
	
	private char grid[][];
	
	private List<UIGridSpace> spaces = new ArrayList<UIGridSpace>();
	
	public UIGrid(int xLoc, int yLoc, int width, int height, char[][] grid) {
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}

	public void create(char[][] grid) {
		this.grid = grid;

		for (int m = 0; m < grid[0].length; m++) {
			for (int n = 0; n < grid.length; n++) {

				spaces.add(new UIGridSpace(n*50 + xLoc, m*50 + yLoc, 50, 50, new EmptyModel()));
			}
		}
	}
	
	public void clearGrid() {
		spaces.clear();
	}
	
	private void parseSpace() {
		for (int m = 0; m < grid[0].length; m++) {
			for (int n = 0; n < grid.length; n++) {
				if(grid[n][m] == 'R') spaces.set(n*m, (new UIGridSpace(n*50 + xLoc, m*50 + yLoc, 50, 50, new RoomModel())));
			}
		}
		//if (symbol == 'R') spaces.add(new UIGridSpace(x, y, 50, 50, new RoomModel()));
		//else spaces.add(new UIGridSpace(x, y, 50, 50, new EmptyModel()));
		// spaces.add(new UIGridSpace(x, y, 50, 50, new EmptyModel()));
	}

	public void add(int row, int col, char symbol) {
		grid[row][col] = symbol;
		
		switch (symbol) {
			case 'R': spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new RoomModel()));
				break;
			case 'r': spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new RadarModel()));
				break;	
			case 'a': spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new AmmoModel()));
				break;
			case 'i': spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new InvincibilityModel()));
				break;
			case 'P': spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new PlayerModel()));
				break;
			case 'N': spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EnemyModel()));
				break;
			default: //spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
		}
		
//		if(symbol == 'R') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new RoomModel()));
//		if(symbol == 'P') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
//		if(symbol == 'N') spaces.add(new UIGridSpace(col*50 + xLoc, row*50 + yLoc, 50, 50, new EmptyModel()));
		
		//parseSpace();
	}
	
	public void draw(Graphics2D g) {
		for (UIGridSpace space : spaces) {
			space.draw(g);
		}
		
//		for (UIGridSpace space : spaces) {
//			if (space.model instanceof RoomModel) space.draw(g);
//		}
//		
	}
}
