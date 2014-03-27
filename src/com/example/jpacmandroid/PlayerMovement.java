package com.example.jpacmandroid;

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
	
	public void move(Direction dir){
		
		try{
			Player player = (Player) board.getSpriteAt(playerX, playerY);
			Log.i("Move", "a");
			player.setDirection(dir);
			Log.i("Move", "b");
			oldX = playerX;
			oldY = playerY;

			playerX += dir.getDx();
			playerY += dir.getDy();
			
			if(playerX < 0){
				playerX = board.getWidth() - 1;
			}
			if(playerX >= board.getWidth()){
				playerX = 0;
			}
			
			switch (board.getSpriteTypeAt(playerX, playerY)) {
			case EMPTY:
				board.setSpriteAt(new Sprite(), oldX, oldY);
				break;
			case FOOD:
				Log.i("Move", "c");
				board.setSpriteAt(new Sprite(), oldX, oldY);
				eat();
				break;
			case WALL:
				//undo movement because it is impossible
				playerY = oldY;
				playerX = oldX;
				break;
			case GHOST:
				board.setSpriteAt(new Sprite(), oldX, oldY);
				state.setState(State.LOST);
				break;
			case OTHER:
				board.setSpriteAt(new Sprite(), oldX, oldY);
			default:
				break;
			}
			board.setSpriteAt(player, playerX, playerY);
			
			Log.i("move", Integer.toString((playerX)));
			
			}catch (RuntimeException e) {
			//Log.i("PlayerMovement",
				//	"getSpriteAt tried to access a sprite at (x,y) out of bounds");
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
