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
	private int[] ghostX;
	private int[] ghostY;

	/*
	 * Random generator
	 */
	private Random random = new Random();

	/*
	 * Timer delays in millisecond
	 */
	private static final int GHOST_MOVE_DELAY = 300;

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
	private int dir;

	/*
	 * Draw holder
	 */
	private Draw draw;

	/*
	 * State holder
	 */
	private State state;

	/*
	 * Constructor
	 */
	public GhostMovement(Board board, Draw draw, State state) {
		Log.i("gm","beginning of gm constructor");
		this.numGhosts = board.getNumGhosts();
		Timer timer = new Timer();
		GhostTimerTask gt = new GhostTimerTask();
		this.draw = draw;
		this.state = state;
		
		Log.i("gm","before ghost x,y sent");
		for (int i = 0; i < numGhosts; i++) {
			ghostX[i] = board.getGhostX(i);
			ghostY[i] = board.getGhostY(i);
		}
		timer.schedule(gt, GHOST_MOVE_DELAY, GHOST_MOVE_DELAY);
		Log.i("ghost", "timer created");
	}

	public class GhostTimerTask extends TimerTask {

		public void run() {
			for (int i = 0; i < numGhosts; i++) {
				dir = random.nextInt(4) + 1;
				Log.i("gm","direction: " + dir);
				moveGhost(i, dir);
			}

		}

		/*
		 * Holds the value of the sprite that is in the ghosts path
		 */
		private int[] nextSprite = new int[numGhosts];
		private int[] oldSprite = new int[numGhosts];
		
		private void moveGhost(int i, int dir) {
			oldSprite[i] = nextSprite[i];
			int oldX = ghostX[i];
			int oldY = ghostY[i];
			if (dir == UP) {
				if (board.getSpriteAt(ghostY[i] - 1, ghostX[i]) != Sprite.WALL) {
					nextSprite[i] = board.getSpriteAt(ghostY[i] - 1, ghostX[i]);
					ghostY[i]--;
					board.setSpriteAt(oldSprite[i], oldX, oldY);
				}
			} else if (dir == DOWN) {
				if (board.getSpriteAt(ghostY[i] + 1, ghostX[i]) != Sprite.WALL) {
					nextSprite[i] = board.getSpriteAt(ghostY[i] + 1, ghostX[i]);
					ghostY[i]++;
					board.setSpriteAt(oldSprite[i], oldX, oldY);
				}
			} else if (dir == LEFT) {
				if (board.getSpriteAt(ghostY[i], ghostX[i] - 1) != Sprite.WALL) {
					nextSprite[i] = board.getSpriteAt(ghostY[i], ghostX[i] - 1);
					ghostX[i]--;
					board.setSpriteAt(oldSprite[i], oldX, oldY);
				}
			} else if (dir == RIGHT) {
				if (board.getSpriteAt(ghostY[i], ghostX[i] + 1) != Sprite.WALL) {
					nextSprite[i] = board.getSpriteAt(ghostY[i], ghostX[i] + 1);
					ghostX[i]++;
					board.setSpriteAt(oldSprite[i], oldX, oldY);
				}
			}
			
			// If after move the ghost is in the same position as PACMAN, then
			// LOST state
			if (board.getSpriteAt(ghostY[i], ghostX[i]) == Sprite.PACMAN) {
				state.setState(State.LOST);
			}
			board.setSpriteAt(Sprite.GHOST, ghostX[i], ghostY[i]);
			
						
			draw.invalidate();
		}
	}
}
