package edu.cpp.cs.cs141.final_project.User_Interface.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.Commands.Command;
import edu.cpp.cs.cs141.final_project.Game_Objects.GameObject;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Actor;
import edu.cpp.cs.cs141.final_project.Game_Objects.Actors.Enemy;

public class GraphicalUserInterface extends JFrame implements IUserInterface, Runnable{
	
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	private static final int GRID_WIDTH = 450;
	private static final int GRID_HEIGHT = 450;
	
	private static final int GRID_X = ((FRAME_WIDTH+10)/3) - (GRID_WIDTH/2);
	private static final int GRID_Y = ((FRAME_HEIGHT+10)/2) - (GRID_HEIGHT/2) - 50;

	
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
    
    JLabel alertLabel;
    JLabel instructionLabel;
    
    JPanel rightPanel;
    JPanel topPanel;
    JPanel botPanel;

	@Override
	public void init(Application app) {
		this.app = app;
		
		pack();
		setResizable(false);
			
		insets = getInsets(); 
		setSize(insets.left + FRAME_WIDTH + insets.right, 
				insets.top + FRAME_HEIGHT + insets.bottom); 
		
		backBuffer = new BufferedImage(FRAME_WIDTH-255, FRAME_HEIGHT-100, BufferedImage.TYPE_INT_RGB); 
		g = (Graphics2D) getGraphics();
		bbg  = (Graphics2D) backBuffer.getGraphics();  
		
		grid = new UIGrid(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT, null, app);
				
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setTitle("Spies vs. Ninjas");
		setVisible(true);
		toFront();
		setAlwaysOnTop(true); 
		setAlwaysOnTop(false);
		requestFocus();
		
		this.setLayout(null);
		setupAndDisplayPanels();
		setupAndDisplayLabels();
		
	}
	
	private void setupAndDisplayLabels() {
		alertLabel = new JLabel(" ");
		alertLabel.setHorizontalAlignment(JLabel.CENTER);
		alertLabel.setSize(topPanel.getWidth(), topPanel.getHeight());
		alertLabel.setFont(new Font("Arial", Font.BOLD, 17));
		alertLabel.setForeground(Color.RED);
		alertLabel.setVisible(true);
		topPanel.add(alertLabel);
		
		instructionLabel = new JLabel("Search each room to find the intel, but avoid getting near the enemy ninjas!");
		instructionLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionLabel.setSize(botPanel.getWidth(), botPanel.getHeight());
		instructionLabel.setFont(new Font("Arial", Font.BOLD, 17));
		instructionLabel.setForeground(Color.WHITE);
		instructionLabel.setVisible(true);
		botPanel.add(instructionLabel);
		
		JLabel leftClickLabel = new JLabel();
		leftClickLabel.setSize(105, 100);
		leftClickLabel.setLocation(5, 25);
		leftClickLabel.setOpaque(true);
		leftClickLabel.setBackground(Color.BLACK);
		leftClickLabel.setHorizontalAlignment(JLabel.CENTER);
		leftClickLabel.setIcon(new ImageIcon("res/left_click.png"));
		leftClickLabel.setVisible(true);
		rightPanel.add(leftClickLabel);
		
			JLabel leftClickDescLabel = new JLabel("MOVE");
			leftClickDescLabel.setFont(new Font("Arial", Font.BOLD, 18));
			leftClickDescLabel.setForeground(Color.YELLOW);
			leftClickDescLabel.setSize(105, 100);
			leftClickDescLabel.setLocation(110, 25);
			leftClickDescLabel.setOpaque(true);
			leftClickDescLabel.setBackground(Color.BLACK);
			leftClickDescLabel.setHorizontalAlignment(JLabel.CENTER);
			leftClickDescLabel.setVisible(true);
			rightPanel.add(leftClickDescLabel);
		
		JLabel rightClickLabel = new JLabel();
		rightClickLabel.setSize(110, 100);
		rightClickLabel.setLocation(10, 140);
		rightClickLabel.setOpaque(true);
		rightClickLabel.setBackground(Color.BLACK);
		rightClickLabel.setHorizontalAlignment(JLabel.CENTER);
		rightClickLabel.setIcon(new ImageIcon("res/right_click.png"));
		rightClickLabel.setVisible(true);
		rightPanel.add(rightClickLabel);
			
			JLabel rightClickDescLabel = new JLabel("LOOK");
			rightClickDescLabel.setFont(new Font("Arial", Font.BOLD, 18));
			rightClickDescLabel.setForeground(Color.YELLOW);
			rightClickDescLabel.setSize(105, 100);
			rightClickDescLabel.setLocation(110, 140);
			rightClickDescLabel.setOpaque(true);
			rightClickDescLabel.setBackground(Color.BLACK);
			rightClickDescLabel.setHorizontalAlignment(JLabel.CENTER);
			rightClickDescLabel.setVisible(true);
			rightPanel.add(rightClickDescLabel);
			
			
		JLabel escKeyLabel = new JLabel();
		escKeyLabel.setSize(110, 100);
		escKeyLabel.setLocation(8, 255);
		escKeyLabel.setOpaque(true);
		escKeyLabel.setBackground(Color.BLACK);
		escKeyLabel.setHorizontalAlignment(JLabel.CENTER);
		escKeyLabel.setIcon(new ImageIcon("res/esc_key.png"));
		escKeyLabel.setVisible(true);
		rightPanel.add(escKeyLabel);
		
			JLabel escKeyDescLabel = new JLabel("MENU");
			escKeyDescLabel.setFont(new Font("Arial", Font.BOLD, 18));
			escKeyDescLabel.setForeground(Color.YELLOW);
			escKeyDescLabel.setSize(105, 100);
			escKeyDescLabel.setLocation(110, 255);
			escKeyDescLabel.setOpaque(true);
			escKeyDescLabel.setBackground(Color.BLACK);
			escKeyDescLabel.setHorizontalAlignment(JLabel.CENTER);
			escKeyDescLabel.setVisible(true);
			rightPanel.add(escKeyDescLabel);
		
		JLabel spaceKeyLabel = new JLabel();
		spaceKeyLabel.setSize(210, 65);
		spaceKeyLabel.setLocation(5, 390);
		spaceKeyLabel.setOpaque(true);
		spaceKeyLabel.setBackground(Color.BLACK);
		spaceKeyLabel.setHorizontalAlignment(JLabel.CENTER);
		spaceKeyLabel.setIcon(new ImageIcon("res/space_key.png"));
		spaceKeyLabel.setVisible(true);
		rightPanel.add(spaceKeyLabel);
			
			JLabel spaceKeyDescLabel = new JLabel("SHOOT");
			spaceKeyDescLabel.setFont(new Font("Arial", Font.BOLD, 18));
			spaceKeyDescLabel.setForeground(Color.YELLOW);
			spaceKeyDescLabel.setSize(200, 35);
			spaceKeyDescLabel.setLocation(10, 360);
			spaceKeyDescLabel.setOpaque(true);
			spaceKeyDescLabel.setBackground(Color.BLACK);
			spaceKeyDescLabel.setHorizontalAlignment(JLabel.CENTER);
			spaceKeyDescLabel.setVisible(true);
			rightPanel.add(spaceKeyDescLabel);
			
			rightPanel.repaint();

		
	}
	
	private void setupAndDisplayPanels() {

		topPanel = new JPanel();
		topPanel.setLocation(5,5);
		topPanel.setOpaque(true);
		topPanel.setBackground(Color.BLACK);
		topPanel.setSize(FRAME_WIDTH, 50);
		topPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 50));
		topPanel.setVisible(true);
		this.add(topPanel);
		
		rightPanel = new JPanel();
		rightPanel.setLocation(FRAME_WIDTH-250, 75);
		rightPanel.setOpaque(true);
		rightPanel.setBackground(Color.BLACK);
		//rightPanel.setForeground(Color.WHITE);
		rightPanel.setSize(220, FRAME_HEIGHT - 135);
		rightPanel.setPreferredSize(new Dimension(220, FRAME_HEIGHT - 135));
		rightPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(
						EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Controls",TitledBorder.CENTER, TitledBorder.TOP));
		((javax.swing.border.TitledBorder)rightPanel.getBorder()).setTitleColor(Color.WHITE);
		rightPanel.setLayout(null);
		rightPanel.setVisible(true);
		this.add(rightPanel);
		
		botPanel = new JPanel();
		botPanel.setLocation(5, FRAME_HEIGHT - 45);
		botPanel.setOpaque(true);
		botPanel.setBackground(Color.BLACK);
		botPanel.setSize(FRAME_WIDTH, 50);
		botPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 50));
		botPanel.setVisible(true);
		this.add(botPanel);
	}
	
	public void draw() {
		drawBackground();
		drawGrid();
		
		g.drawImage(backBuffer, insets.left, insets.top+50, this);	
		
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
        bbg.fillRect(5, 25, FRAME_WIDTH, FRAME_HEIGHT); 
        
        g.setColor(Color.BLACK);
        g.fillRect(FRAME_WIDTH-27, 101, 35, FRAME_HEIGHT-135);
        
        g.fillRect(FRAME_WIDTH-247, 80, 255, 22);
        
        g.fillRect(FRAME_WIDTH-247, FRAME_HEIGHT-35, 255, 22);
	}

	@Override
	public void drawGrid() {

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
	
		if (string != "") {
			alertLabel.setText(string);
		}
		
		
		
		
	}

}
