package edu.cpp.cs.cs141.final_project.Game_Objects.Actors;

public class Player extends Actor{
	private boolean isInvincible;
	private static final int START_XPOS = 0;
	private static final int START_YPOS = 8;
	
	public Player() {
		super(START_XPOS, START_YPOS);
	}
}