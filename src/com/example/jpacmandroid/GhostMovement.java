package com.example.jpacmandroid;

import java.util.Random;

import android.util.Log;

import com.example.jpacmandroid.Board.SpriteType;

public class GhostMovement {

	/*
	 * Number of ghosts
	 */
	private int numGhosts;

	/*
	 * Board holder
	 */
	private Board board;

	/*
	 * Ghost holders for (x,y)
	 */
	private int[] ghostX = new int[50];
	private int[] ghostY = new int[50];

	/*
	 * Random generator
	 */
	public Random random = new Random();

	/*
	 * Timer delays in millisecond
	 */
	public static final int GHOST_MOVE_DELAY = 200;
	
	/*
	 * Direction definitions
	 */
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;


	/*
	 * Holds the value of the sprite that is in the ghosts path and the sprite
	 * it is currently on top of
	 */
	private Sprite[] nextSprite = new Sprite[50];
	private Sprite[] currSprite = new Sprite[50];

	/*
	 * Constructor
	 */
	public GhostMovement(Board board, Draw draw, State state) {
		this.board = board;
		this.numGhosts = board.getNumGhosts();
		
		for (int i = 0; i < numGhosts; i++) {
			nextSprite[i] = new Sprite();
			currSprite[i] = new Sprite();
		}

		for (int i = 0; i < numGhosts; i++) {
			ghostX[i] = board.getGhostX(i);
			ghostY[i] = board.getGhostY(i);
		}
		
	}

	/**
	 * Checks to see if the specified location is a valid location for the ghost to move
	 * @param x: The x-coordinate of the location
	 * @param y: The y-coordinate of the location
	 * */
	private boolean validMove(int x, int y) {
		try{
			if (board.getSpriteTypeAt(x, y) == SpriteType.WALL
					|| board.getSpriteTypeAt(x, y) == SpriteType.GHOST) {
				return false;
			} else {
				return true;
			}	
		}catch(IndexOutOfBoundsException e){
			return false;
		}
		
	}

	/**
	 * Moves the ghost, specified by its index i, in the direction dir
	 * */	
	public void moveGhost(int i, int dir) {
		currSprite[i] = nextSprite[i];				
		
		switch (dir) {
		case UP:
			if(ghostY[i] - 1 < 0){
				//do nothing
			}else if (validMove(ghostX[i], ghostY[i] - 1)) {
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostY[i]--;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				 if (board.getSpriteTypeAt(ghostX[i], ghostY[i]) == SpriteType.PLAYER) {
					 board.game.getState().setState(State.LOST); 
				 }
				board.setSpriteAt(new Ghost(), ghostX[i], ghostY[i]);
			}
			break;
			
		case DOWN:
			if (validMove(ghostX[i], ghostY[i] + 1)) {

			if(ghostY[i] + 1 >= board.getHeight()){
				//do nothing
			}else if (validMove(ghostX[i], ghostY[i] + 1)) {
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostY[i]++;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				 if (board.getSpriteTypeAt(ghostX[i], ghostY[i]) == SpriteType.PLAYER) {
					 board.game.getState().setState(State.LOST); 
				 }
				board.setSpriteAt(new Ghost(), ghostX[i], ghostY[i]);
			}
			break;
			}
		case LEFT:

			if (validMove(ghostX[i] - 1, ghostY[i])) {

			if(ghostX[i] - 1 < 0){
			//do nothing
			}else if (validMove(ghostX[i] - 1, ghostY[i])) {

				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostX[i]--;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				 if (board.getSpriteTypeAt(ghostX[i], ghostY[i]) == SpriteType.PLAYER) {
					 board.game.getState().setState(State.LOST); 
				 }
				board.setSpriteAt(new Ghost(), ghostX[i], ghostY[i]);
			}
			break;
			}
		case RIGHT:

			if (validMove(ghostX[i] + 1, ghostY[i])) {

			if(ghostX[i] + 1 >= board.getWidth()){
				//do nothing
			}else if (validMove(ghostX[i] + 1, ghostY[i])) {
				
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostX[i]++;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				 if (board.getSpriteTypeAt(ghostX[i], ghostY[i]) == SpriteType.PLAYER) {
					 board.game.getState().setState(State.LOST); 
				 }
				board.setSpriteAt(new Ghost(), ghostX[i], ghostY[i]);
			}
			break;
			}
		default:
			// don't move: Pacman is not allowed to move in the Z plane
		}

		
	}
}
