package com.example.jpacmandroid2;

import android.util.Log;
import com.example.jpacmandroid2.Board.SpriteType;

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
			Player player = (Player) board.gsa(playerX, playerY);
			player.setDirection(dir);
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
			
			switch(board.gsta(playerX, playerY)){
			case EMPTY:
				board.ssa(new Sprite(), oldX, oldY);
				break;
			case FOOD:
					board.ssa(new Sprite(), oldX, oldY);
				eat();
				break;
			case WALL:
				//undo movement because it is impossible
				playerY -= dir.getDy();
				playerX -= dir.getDx();
				break;
			case GHOST:
				board.ssa(new Sprite(), oldX, oldY);
				state.setState(State.LOST);
				//playerX -=dir.getDx();
				//playerY -= dir.getDy();
				break;
			case OTHER:
				board.ssa(new Sprite(), oldX, oldY);
			default:
				break;
			}			
			board.ssa(player, playerX, playerY);			
			
		}catch(RuntimeException e){
			Log.i("PlayerMovement",
					"getSpriteAt tried to access a sprite at (x,y) out of bounds");
		}
	}	
	
	/*
	 * Eats the food and increases score
	 */
	private void eat() {
		score.addScore();
	}
}
