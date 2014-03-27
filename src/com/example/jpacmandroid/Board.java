package com.example.jpacmandroid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class Board {
	
	public enum SpriteType { 
		PLAYER, 
		GHOST, 
		FOOD, 
		EMPTY, 
		WALL, 
		OTHER
	};
		
	
	private static int width;
	private static int height;
	
	
	/*
	 * Board Array
	 */
	private Tile board[][];
	
	
	/*
	 * Player starting position. For default game this is (11,15)
	 */
	private int playerStartX;
	private int playerStartY;
	
	/*
	 * Ghost position holder
	 */
	public int[] ghostX = new int[50];
	public int[] ghostY = new int[50];
	
	/*
	 * Ghost counter
	 */
	private int numGhosts = 0;

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
		getBoardSize();
		board = new Tile[width][height];
		boardParser();
	}
	
	/*
	 * Get current board layout
	 */
	public Tile[][] getBoard(){
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
						board[x][y] = new Tile(x,y, new Wall());
					} else if (token == Sprite.FOOD_SYM) {
						board[x][y] = new Tile(x,y, new Food());
						totalScore += 10;
					} else if (token == Sprite.PACMAN_SYM) {
						board[x][y] = new Tile(x,y, new Player());
						//initialize player starting position
						playerStartX = x;
						playerStartY = y;
					} else if (token == Sprite.GHOST_SYM) {
						board[x][y] = new Tile(x,y, new Ghost());
						ghostX[numGhosts] = x;
						ghostY[numGhosts] = y;
						numGhosts++;
					} else if (token == Sprite.EMPTY_SYM) {
						board[x][y] = new Tile(x,y, new Sprite());
					}
				}
			}
			
			board[playerStartX][playerStartY] = new Tile(playerStartX,playerStartY, new Player());
			
			// close file
			br.close();
		} catch (FileNotFoundException fnfe) {
			Log.i("board", "FileNotFoundException");
		} catch (IOException ioe) {
			Log.i("board", "IOException");
		}

	}
	
	/**
	 * Finds the width and height of the source board
	 */	
	public void getBoardSize(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(game
					.getAssets().open("board.txt")));
			
			width = br.readLine().length();
			while(br.readLine() != null){
				height ++;
			}
			height++;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns the total score calculated by the parser
	 */
	public int getTotalScore() {
		return totalScore;
	}

	public Sprite getSpriteAt(int x, int y)throws IndexOutOfBoundsException{
		if(x < 0 || x >= getWidth() || y < 0|| y >= getHeight()){
			throw new IndexOutOfBoundsException();
		}
		if(board[x][y] != null){
			return board[x][y].getSprite();	
		}
		return new Sprite();
	}
	
	public SpriteType getSpriteTypeAt(int x, int y)throws IndexOutOfBoundsException{
		if(x < 0 || x >= getWidth() || y < 0|| y >= getHeight()){
			throw new IndexOutOfBoundsException();
		}
		if(board[x][y] != null){
			return board[x][y].getSprite().getSpriteType();	
		}
		return SpriteType.EMPTY;		
	}
	
	public void setSpriteAt(Sprite sprite, int x, int y){
		board[x][y].sprite = sprite;
	}
	
	/*
	 * Get number of ghosts 
	 */
	public int getNumGhosts(){
		return numGhosts;
	}
	
	/*
	 * Get ghosts
	 */
	public int getGhostX(int x){
		return ghostX[x];
	}
	
	public int getGhostY(int y){
		return ghostY[y];
	}

	public int getHeight() {
		return board[0].length;
	}
	
	public int getWidth(){
		return board.length;
	}
	
	/**
	 * Verify that the given location falls within the
	 * borders of the board.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return True iff (x,y) falls within the board.
	 */
	public boolean withinBorders(int x, int y) {
		return
			x >= 0 && x < width 
			&& y >= 0 && y < height;
	}

}
