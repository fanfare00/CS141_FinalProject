package edu.cpp.cs.cs141.final_project.User_Interface.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.Commands.Command;

public class GraphicalUserInterface extends JFrame implements IUserInterface, Runnable{
	
	private static final int FRAME_WIDTH = 1024;
	private static final int FRAME_HEIGHT = 768;
	
	private static final int GRID_WIDTH = 450;
	private static final int GRID_HEIGHT = 450;
	
	private static final int GRID_X = (FRAME_WIDTH/2) - (GRID_WIDTH/2);
	private static final int GRID_Y = (FRAME_HEIGHT/2) - (GRID_HEIGHT/2);

	
	private static final int FPS = 30;
	
	BufferedImage backBuffer; 
	
	Insets insets; 
	
	UIGrid grid;
	
	//private char grid[][];
	
    Graphics2D g; 
    Graphics2D bbg;
    
    Command command;
    
    private boolean isRunning;
    
    Application app;

	@Override
	public void init(Application app) {
		this.app = app;
		
		pack();
		setResizable(false);
			
		insets = getInsets(); 
		setSize(insets.left + FRAME_WIDTH + insets.right, 
				insets.top + FRAME_HEIGHT + insets.bottom); 
		
		backBuffer = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT,   BufferedImage.TYPE_INT_RGB); 
		g = (Graphics2D) getGraphics();
		bbg  = (Graphics2D) backBuffer.getGraphics();  
		
		grid = new UIGrid(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT, null, app);
				
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setVisible(true);
		toFront();
		setAlwaysOnTop(true); 
		setAlwaysOnTop(false);
		requestFocus();
		
	}
	
	public void draw() {
		drawBackground();
		drawGrid();
		g.drawImage(backBuffer, insets.left, insets.top, this);
		
	}
	
	
	public void run() {
		
		while(true){ 
			long time = System.currentTimeMillis(); 
			time = (1000 / FPS) - (System.currentTimeMillis()-time); 
			
			draw();
			
			if (time > 0) { 
				try { 
					Thread.sleep(time); 
                } 
                	catch(Exception e){} 
             } 
        }
		
	}

	@Override
	public void update() {
		
		
		if (!isRunning) { 
			isRunning = true;
			 (new Thread(this)).start();
		}
		
//		command = input.handleInput();
//    	
//    	if (command != null) {
//    	  command.execute(currentActor);
//    	}
	}

	@Override
	public void createGrid(int rows, int cols) {
		//grid = new char[rows][cols];
	
		grid.create(new char[rows][cols]);
	}

	@Override
	public void addToGrid(int row, int col, char symbol) {
		//grid[row][col] = symbol;
		grid.add(row, col, symbol);
	}
	
	private void drawBackground() {
        bbg.setColor(Color.BLACK); 
        bbg.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT); 
	}

	@Override
	public void drawGrid() {
		int innerX = (FRAME_WIDTH/2) - 225;
		int innerY = (FRAME_HEIGHT/2) - 225;
		
		//bbg.setColor(Color.RED); 
		//bbg.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
		
		grid.draw(bbg, this);
		
		
//		for (int m = 0; m < grid[0].length; m++) {
//		    for (int n = 0; n < grid.length; n++) {
//		    	
//		    	if (grid[m][n] == 'R') {
//		    		bbg.setColor(Color.RED); 
//		    		bbg.drawRect(n*50 + innerX, m*50 + innerY, 50, 50);
//		    	}
//		    	
//		    	if (grid[m][n] == 'P') {
//		    		bbg.setColor(Color.GREEN); 
//		    		bbg.drawRect(n*50 + innerX +12, m*50 + innerY+12, 25, 25);
//		    	}
//		    	
//		    	if (grid[m][n] == 'N') {
//		    		bbg.setColor(Color.BLUE); 
//		    		bbg.drawRect(n*50 + innerX + 12, m*50 + innerY + 12, 25, 25);
//		    	}
//		    	
////		    	if (grid[m][n] == 'N') {
////		    		Rectangle2D rectangle = new Rectangle2D.Double(n*50 + innerX + 12.5, m*50 + innerY + 12.5, 25, 25);
////		    		AffineTransform transform = new AffineTransform();
////		    		transform.rotate(Math.PI/4, rectangle.getX() + rectangle.getWidth()/2,    rectangle.getY() + rectangle.getHeight()/2);
////		    		
////		    		bbg.setColor(Color.BLUE); 
////		    		bbg.draw((Shape)transform);
////		    	}
//		    }
//		    	
//		}
	}

	@Override
	public void toggleMenuState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleMoveState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleLookState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleShootState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAndHandleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatusText(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAlertText(String string) {
		// TODO Auto-generated method stub
		
	}

}
