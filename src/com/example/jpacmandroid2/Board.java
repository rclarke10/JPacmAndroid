package com.example.jpacmandroid2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class Board {
	/*
	 * Board Array
	 */
	private static int board[][] = new int[50][50];

	/*
	 * Player starting position
	 */
	private int playerStartX;
	private int playerStartY;

	/*
	 * Total score holder as calculated by parser
	 */
	private int totalScore = 0;

	/*
	 * Game holder
	 */
	Game game;

	/*
	 * Board constructor
	 */
	public Board(Game game) {
		this.game = game;
		boardParser();
	}
	
	/*
	 * Get current board layout
	 */
	public int[][] getBoard(){
		return board;
	}

	/*
	 * Gets player position
	 */
	public int startX() {
		return playerStartX;
	}

	public int startY() {
		return playerStartY;
	}

	/*
	 * Parses the .txt file to create a board
	 */
	private void boardParser() {
		// TODO Parse .txt and place into board[][]
		// TODO Place player starting position
		// TODO Place ghost starting position (temp: make ghosts food)
		// TODO Calculate total score
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(game
					.getAssets().open("board.txt")));
			
			
			String line;
			char token;

			// process each line
			for (int y = 0; (line = br.readLine()) != null; y++) {
				// process each char in each line
				for (int x = 0; x < line.length(); x++) {
					token = line.charAt(x);
					
					// Translate each token into the corresponding board element
					if (token == Sprite.WALL_SYM) {
						board[x][y] = Sprite.WALL;
					} else if (token == Sprite.FOOD_SYM) {
						board[x][y] = Sprite.FOOD;
						totalScore += 10;
					} else if (token == Sprite.PACMAN_SYM) {
						board[x][y] = Sprite.PACMAN;
					} else if (token == Sprite.GHOST_SYM) {
						board[x][y] = Sprite.GHOST;
					} else if (token == Sprite.EMPTY_SYM) {
						board[x][y] = Sprite.EMPTY;
					}
				}
			}
			// close file
			br.close();
		} catch (FileNotFoundException fnfe) {
			// TODO Actually deal with exception
			Log.i("board", "FileNotFoundException");
		} catch (IOException ioe) {
			// TODO Actually deal with exception
			Log.i("board", "IOException");
		}

	}

	/*
	 * Returns the total score calculated by the parser
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/*
	 * Returns the value of the sprite at x,y
	 */
	public int getSpriteAt(int x, int y) throws RuntimeException {
		return board[x][y];
	}

	/*
	 * Sets the sprite to the input
	 */
	public void setSpriteAt(int sprite, int x, int y) {
		board[x][y] = sprite;
	}

}
