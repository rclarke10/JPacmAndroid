package com.example.jpacmandroid2;

import android.util.Log;

public class PlayerMovement {

	/*
	 * Game board
	 */
	Board board;

	/*
	 * Current game state
	 */
	State state;

	/*
	 * Current score keeper
	 */
	Score score;

	/*
	 * Current view to draw to
	 */
	Draw draw;

	public PlayerMovement(Board board, State state, Score score, Draw draw) {
		this.board = board;
		this.playerX = 11;//board.startX();
		this.playerY = 15;//board.startY();
		this.oldX = 11;//board.startX();
		this.oldY = 15;//board.startY();
		this.state = state;
		this.score = score;
		this.draw = draw;
	}

	/*
	 * Current position in x,y
	 */
	private int playerX;
	private int playerY;
	private int oldX;
	private int oldY;

	
	public void up() {
		try {
			oldX = playerX;
			oldY = playerY;
			playerY--;
			switch (board.getSpriteAt(playerX, playerY)) {
			case Sprite.EMPTY:
				board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				break;
			case Sprite.FOOD:
					board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				eat();
				break;
			case Sprite.WALL:
				//undo movement because it is impossible
				playerY++;
				break;
			case Sprite.GHOST:
				board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				state.setState(State.LOST);
				break;
			default:
				break;
			}
			board.setSpriteAt(Sprite.PACMAN, playerX, playerY);
		} catch (RuntimeException e) {
			Log.i("PlayerMovement",
					"getSpriteAt tried to access a sprite at (x,y) out of bounds");
		}
		draw.invalidate();

	}

	public void down() {
		try {
			oldX = playerX;
			oldY = playerY;
			playerY++;
			switch (board.getSpriteAt(playerX, playerY)) {
			case Sprite.EMPTY:
				board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				break;
			case Sprite.FOOD:
					board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				eat();
				break;
			case Sprite.WALL:
				//undo movement because it is impossible
				playerY--;
				break;
			case Sprite.GHOST:
				board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				state.setState(State.LOST);
				break;
			default:
				break;
			}
			board.setSpriteAt(Sprite.PACMAN, playerX, playerY);
		} catch (RuntimeException e) {
			Log.i("PlayerMovement",
					"getSpriteAt tried to access a sprite at (x,y) out of bounds");
		}
		draw.invalidate();
	}

	public void left() {
		try {
			oldY = playerY;
			oldX = playerX;
			playerX--;
			switch (board.getSpriteAt(playerX, playerY)) {
			case Sprite.EMPTY:
					board.setSpriteAt(Sprite.EMPTY, oldX, oldY);	
				break;
			case Sprite.FOOD:
					board.setSpriteAt(Sprite.EMPTY, oldX, oldY);	
				eat();
				break;
			case Sprite.WALL:
				//undo movement because it is impossible
				playerX++;
				break;
			case Sprite.GHOST:
				board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				state.setState(State.LOST);
				break;
			default:
				break;
			}
			board.setSpriteAt(Sprite.PACMAN, playerX, playerY);
		} catch (RuntimeException e) {
			Log.i("PlayerMovement",
					"getSpriteAt tried to access a sprite at (x,y) out of bounds");
		}
		draw.invalidate();
	}

	public void right() {
		try {
			oldY = playerY;
			oldX = playerX;
			playerX++;
			switch (board.getSpriteAt(playerX, playerY)) {
			case Sprite.EMPTY:
					board.setSpriteAt(Sprite.EMPTY, oldX, oldY);	
				break;
			case Sprite.FOOD:
					board.setSpriteAt(Sprite.EMPTY, oldX, oldY);	
				eat();
				break;
			case Sprite.WALL:
				//undo movement because it is impossible
				playerX--;
				break;
			case Sprite.GHOST:
				board.setSpriteAt(Sprite.EMPTY, oldX, oldY);
				state.setState(State.LOST);
				break;
			default:
				break;
			}
			board.setSpriteAt(Sprite.PACMAN, playerX, playerY);
		} catch (RuntimeException e) {
			Log.i("PlayerMovement",
					"getSpriteAt tried to access a sprite at (x,y) out of bounds");
		}
		draw.invalidate();
	}

	/*
	 * Eats the food and increases score
	 */
	private void eat() {
		score.addScore();
	}
}
