package com.example.jpacmandroid2;

public class State {
	/*
	 * State definitions
	 */
	public static final int START = 0;
	public static final int STOP = 1;
	public static final int WON = 2;
	public static final int LOST = 3;
	
	/*
	 * Defines current state
	 */
	private int currentState;
	
	/*
	 * Constructor. Make default state STOP
	 */
	public State(){
		currentState = STOP;
	}
	
	/*
	 * Getter for current state
	 */
	public int getState(){
		return currentState;
	}
	
	/*
	 * Setter for current state
	 */
	public void setState(int state){
		currentState = state;
	}
	
}