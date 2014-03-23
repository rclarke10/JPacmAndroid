package com.example.jpacmandroid2;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

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
	 * Random number holder
	 */
	//private int dir;

	/*
	 * State holder
	 */
	//private State state;

	/*
	 * Holds the value of the sprite that is in the ghosts path and the sprite
	 * it is currently on top of
	 */
	private int[] nextSprite = new int[50];
	private int[] currSprite = new int[50];

	/*
	 * Constructor
	 */
	public GhostMovement(Board board, Draw draw, State state) {
		this.board = board;
		// Log.i("gmm", "beginning of gm constructor");
		this.numGhosts = board.getNumGhosts();
		//Timer timer = new Timer();
		//GhostTimerTask gt = new GhostTimerTask();
		//this.state = state;
		for (int i = 0; i < numGhosts; i++) {
			nextSprite[i] = Sprite.EMPTY;
			currSprite[i] = Sprite.EMPTY;
		}
		// Log.i("gmm", "before ghost x,y sent");
		for (int i = 0; i < numGhosts; i++) {
			ghostX[i] = board.getGhostX(i);
			// Log.i("gxy","ghost x:"+ghostX[i]);
			ghostY[i] = board.getGhostY(i);
			// Log.i("gxy","ghost y:"+ghostY[i]);
		}
		// Log.i("gmm", "after ghost x,y sent");
		// timer.schedule(gt, GHOST_MOVE_DELAY, GHOST_MOVE_DELAY);
		// Log.i("gmm", "timer created");
	}

	private boolean validMove(int ghost, int x, int y) {
		if (board.getSpriteAt(x, y) == Sprite.WALL
				|| board.getSpriteAt(x, y) == Sprite.GHOST) {
			return false;
		} else {
			return true;
		}
	}

	public void moveGhost(int i, int dir) {
		currSprite[i] = nextSprite[i];
		switch (dir) {
		case UP:
			if (validMove(i, ghostX[i], ghostY[i] - 1)) {
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostY[i]--;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				board.setSpriteAt(Sprite.GHOST, ghostX[i], ghostY[i]);
			}
			break;
		case DOWN:
			if (validMove(i, ghostX[i], ghostY[i] + 1)) {
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostY[i]++;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				board.setSpriteAt(Sprite.GHOST, ghostX[i], ghostY[i]);
			}
			break;

		case LEFT:
			if (validMove(i, ghostX[i] - 1, ghostY[i])) {
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostX[i]--;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				board.setSpriteAt(Sprite.GHOST, ghostX[i], ghostY[i]);
			}
			break;

		case RIGHT:
			if (validMove(i, ghostX[i] + 1, ghostY[i])) {
				board.setSpriteAt(currSprite[i], ghostX[i], ghostY[i]);
				ghostX[i]++;
				nextSprite[i] = board.getSpriteAt(ghostX[i], ghostY[i]);
				board.setSpriteAt(Sprite.GHOST, ghostX[i], ghostY[i]);
			}
			break;
		default:
			// don't move: Pacman is not allowed to move in the Z plane
		}

		// Checks if the ghost moves onto Pacman, triggering a loss
		/*
		 * if (board.getSpriteAt(ghostY[i], ghostX[i]) == Sprite.PACMAN) {
		 * state.setState(State.LOST); }
		 */

	}
}
