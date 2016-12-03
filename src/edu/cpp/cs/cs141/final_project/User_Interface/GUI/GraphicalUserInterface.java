/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodríguez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project.User_Interface.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import edu.cpp.cs.cs141.final_project.Application;
import edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Models.UIGrid;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities.ButtonHandler;
import edu.cpp.cs.cs141.final_project.User_Interface.GUI.Utilities.KeyboardHandler;
import edu.cpp.cs.cs141.final_project.Commands.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphicalUserInterface.
 */
@SuppressWarnings("serial")
public class GraphicalUserInterface extends JFrame implements IUserInterface, Runnable{
	
	/** The Constant FRAME_WIDTH. */
	private static final int FRAME_WIDTH = 800;
	
	/** The Constant FRAME_HEIGHT. */
	private static final int FRAME_HEIGHT = 600;
	
	/** The Constant GRID_WIDTH. */
	private static final int GRID_WIDTH = 450;
	
	/** The Constant GRID_HEIGHT. */
	private static final int GRID_HEIGHT = 450;
	
	/** The Constant GRID_X. */
	private static final int GRID_X = ((FRAME_WIDTH+10)/3) - (GRID_WIDTH/2);
	
	/** The Constant GRID_Y. */
	private static final int GRID_Y = ((FRAME_HEIGHT+10)/2) - (GRID_HEIGHT/2) - 50;

	
	/** The Constant FPS. */
	private static final int FPS = 30;
	
	/** The back buffer. */
	BufferedImage backBuffer; 
	
	/** The insets. */
	Insets insets; 
	
	/** The grid. */
	UIGrid grid;
	
	//private char grid[][];
	
    /** The g. */
	Graphics2D g; 
    
    /** The bbg. */
    Graphics2D bbg;
    
    /** The command. */
    Command command;
    
    /** The is running. */
    private boolean isRunning;
    
    
    /** The app. */
    Application app;
    
    /** The alert label. */
    JLabel alertLabel;
    
    /** The instruction label. */
    JLabel instructionLabel;
    
    /** The ammo label. */
    JLabel ammoLabel;
    
    /** The lives label. */
    JLabel livesLabel;
    
    /** The right panel. */
    JPanel rightPanel;
    
    /** The top panel. */
    JPanel topPanel;
    
    /** The bot panel. */
    JPanel botPanel;
    
    /** The menu panel. */
    JPanel menuPanel;
    
    /** The life counter. */
    private int lifeCounter = 3;

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#init(edu.cpp.cs.cs141.final_project.Application)
	 */
	@Override
	public void init(Application app) {
		this.app = app;
		app.setTurnBased(false);
		
		pack();
		setResizable(false);
			
		insets = getInsets(); 
		setSize(insets.left + FRAME_WIDTH + insets.right, 
				insets.top + FRAME_HEIGHT + insets.bottom); 
		
		backBuffer = new BufferedImage(FRAME_WIDTH-255, FRAME_HEIGHT-100, BufferedImage.TYPE_INT_RGB); 
		g = (Graphics2D) getGraphics();
		bbg  = (Graphics2D) backBuffer.getGraphics();  
		
		grid = new UIGrid(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT, null, app);
		app.updateUIGrid();
				
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
		setupMenuButtons();
		
		this.getContentPane().setBackground(Color.BLACK);
		
		this.addKeyListener(new KeyboardHandler(grid, app, this));

		
	}
	
	/**
	 * Setup menu buttons.
	 */
	private void setupMenuButtons() {
		JButton newGameButton = new JButton("New Game");
		newGameButton.setSize(160, 35);
		newGameButton.setLocation(20, 25);
		newGameButton.setVisible(true);
		
		newGameButton.addActionListener(new ButtonHandler(grid, this, app));
		newGameButton.setActionCommand(newGameButton.getText());
		
		menuPanel.add(newGameButton);
		
		JButton loadGameButton = new JButton("Load Game");
		loadGameButton.setSize(160, 35);
		loadGameButton.setLocation(20, 75);
		loadGameButton.setVisible(true);
		loadGameButton.addActionListener(new ButtonHandler(grid, this, app));
		loadGameButton.setActionCommand(loadGameButton.getText());
		
		menuPanel.add(loadGameButton);
		
		JButton saveGameButton = new JButton("Save Game");
		saveGameButton.setSize(160, 35);
		saveGameButton.setLocation(20, 125);
		saveGameButton.setVisible(true);
		saveGameButton.addActionListener(new ButtonHandler(grid, this, app));
		saveGameButton.setActionCommand(saveGameButton.getText());
		
		menuPanel.add(saveGameButton);
		
		JButton toggleUIButton = new JButton("Toggle UI");
		toggleUIButton.setSize(160, 35);
		toggleUIButton.setLocation(20, 175);
		toggleUIButton.setVisible(true);
		toggleUIButton.addActionListener(new ButtonHandler(grid, this, app));
		toggleUIButton.setActionCommand(toggleUIButton.getText());
		
		menuPanel.add(toggleUIButton);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.setSize(160, 35);
		quitGameButton.setLocation(20, 250);
		quitGameButton.setVisible(true);
		quitGameButton.addActionListener(new ButtonHandler(grid, this, app));
		quitGameButton.setActionCommand(quitGameButton.getText());
		
		menuPanel.add(quitGameButton);
		

	}
	
	/**
	 * Setup and display labels.
	 */
	private void setupAndDisplayLabels() {
		alertLabel = new JLabel(" ");
		alertLabel.setHorizontalAlignment(JLabel.CENTER);
		alertLabel.setLocation(170, 14);
		alertLabel.setSize(topPanel.getWidth()-300, topPanel.getHeight());
		alertLabel.setFont(new Font("Arial", Font.BOLD, 13));
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
		escKeyLabel.setLocation(8, 245);
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
			escKeyDescLabel.setLocation(110, 245);
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

		
		ammoLabel = new JLabel();
		ammoLabel = new JLabel();
		ammoLabel.setIcon(new ImageIcon("res/full_ammo.png"));
		ammoLabel.setSize(70, 25);
		ammoLabel.setLocation(120, 25);
		ammoLabel.setVisible(true);
		topPanel.add(ammoLabel);
		
		livesLabel = new JLabel();
		livesLabel.setIcon(new ImageIcon("res/3lives.png"));
		livesLabel.setSize(70, 25);
		livesLabel.setLocation(45, 25);
		livesLabel.setVisible(true);
		topPanel.add(livesLabel);
		
		topPanel.repaint();
		
		
			
		
	}
	
	/**
	 * Setup and display panels.
	 */
	private void setupAndDisplayPanels() {

		topPanel = new JPanel();
		topPanel.setLocation(5,5);
		topPanel.setOpaque(true);
		topPanel.setBackground(Color.BLACK);
		topPanel.setSize(FRAME_WIDTH, 50);
		topPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 50));
		topPanel.setLayout(null);
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
		
		menuPanel = new JPanel();
		
		menuPanel.setLocation(180, 150);
		menuPanel.setSize(200, 300);
		//menuPanel.setPreferredSize(new Dimension(350, 500));
		menuPanel.setOpaque(true);
		
		menuPanel.setBackground(Color.DARK_GRAY);
		
		menuPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(
						EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "MENU",TitledBorder.CENTER, TitledBorder.TOP));
		((javax.swing.border.TitledBorder)menuPanel.getBorder()).setTitleColor(Color.WHITE);
		
		menuPanel.addKeyListener(new KeyboardHandler(grid, app, this));
		
		menuPanel.setLayout(null);
		menuPanel.setVisible(false);
		this.add(menuPanel);
	}
	
	/**
	 * Draw.
	 */
	public void draw() {
		drawBackground();
		drawGrid();
		
		g.drawImage(backBuffer, insets.left, insets.top+50, this);	
		
	}
	
	/**
	 * Update counters.
	 */
	private void updateCounters() {
		
		if (app.getPlayerLives() == 3) livesLabel.setIcon(new ImageIcon("res/3lives.png"));
		
		if (app.getPlayerLives() == 2) livesLabel.setIcon(new ImageIcon("res/2lives.png"));
	
		if (app.getPlayerLives() == 1) livesLabel.setIcon(new ImageIcon("res/1lives.png"));
	
		if (app.getPlayerLives() == 0) livesLabel.setIcon(new ImageIcon("res/nolives.png"));
		
		if (app.getPlayerAmmo() == 1) ammoLabel.setIcon(new ImageIcon("res/full_ammo.png"));
		
		if (app.getPlayerAmmo() == 0)ammoLabel.setIcon(new ImageIcon("res/no_ammo.png"));
		
		//if (app.getLookStatus()) 
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {	
		while(!app.getPaused()){ 
			long time = System.currentTimeMillis(); 
			time = (1000 / FPS) - (System.currentTimeMillis()-time); 
			
			this.requestFocus();
			
			draw();
			updateCounters();

				
			if (time > 0) { 
				try { 
					Thread.sleep(time); 
	               } 
	                catch(Exception e){} 
	        } 
        }	
		toggleMenuState();
		isRunning = false;
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#update()
	 */
	@Override
	public void update() {
		if (!isRunning) { 
			menuPanel.setVisible(false);
			isRunning = true;
			 (new Thread(this)).start();
		}
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#createGrid(int, int)
	 */
	@Override
	public void createGrid(int rows, int cols) {
		//grid = new char[rows][cols];
	
		grid.create(new char[rows][cols]);
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#addToGrid(int, int, char)
	 */
	@Override
	public void addToGrid(int row, int col, char symbol) {
		//grid[row][col] = symbol;
		grid.add(row, col, symbol);
	}
	
	/**
	 * Draw background.
	 */
	private void drawBackground() {
        bbg.setColor(Color.BLACK); 
        bbg.fillRect(5, 25, FRAME_WIDTH, FRAME_HEIGHT); 
        
        bbg.setColor(Color.BLACK);
        g.fillRect(FRAME_WIDTH-27, 101, 35, FRAME_HEIGHT-135);
        
        g.fillRect(FRAME_WIDTH-247, 80, 255, 22);
        
        g.fillRect(FRAME_WIDTH-247, FRAME_HEIGHT-35, 255, 22);
        
        bbg.setColor(new Color(255, 255, 189));
        bbg.fillOval(grid.getPlayerSpace().getX()-55, grid.getPlayerSpace().getY()-105, grid.getPlayerSpace().getWidth()*3, grid.getPlayerSpace().getHeight()*3);
        
        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, 45, FRAME_HEIGHT);
        
        bbg.fillRect(496, 0, 55, FRAME_HEIGHT);
        
        bbg.fillRect(5, FRAME_HEIGHT-120, FRAME_WIDTH-300, 20);
        bbg.fillRect(5, 0, FRAME_WIDTH-300, 30);
        
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#drawGrid()
	 */
	@Override
	public void drawGrid() {
		grid.draw(bbg, this);
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleMenuState()
	 */
	@Override
	public void toggleMenuState() {
		if (isRunning) {
			draw();
			menuPanel.setVisible(true);
			menuPanel.requestFocus();
			menuPanel.repaint();
		}
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleMoveState()
	 */
	@Override
	public void toggleMoveState() {

	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleLookState()
	 */
	@Override
	public void toggleLookState() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#toggleShootState()
	 */
	@Override
	public void toggleShootState() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#getAndHandleInput()
	 */
	@Override
	public void getAndHandleInput() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#setStatusText(java.lang.String)
	 */
	@Override
	public void setStatusText(String string) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#setAlertText(java.lang.String)
	 */
	@Override
	public void setAlertText(String string) {
	
//		if (string != "") {
			alertLabel.setText(string);
//		}
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.User_Interface.IUserInterface#resetGrid()
	 */
	@Override
	public void resetGrid() {
		grid.resetGrid();
		
	}

}
