package com.example.jpacmandroid2;

import com.example.jpacmandroid2.Board.SpriteType;

public class Sprite {
	/*
	 * Sprite list
	 */
	public static final int PACMAN = 0;
	public static final int GHOST = 1;
	public static final int EMPTY = 2;
	public static final int FOOD = 3;
	public static final int WALL = 4;
	
	/*
	 * Symbols used in .txt files to draw board
	 */
	public static final char PACMAN_SYM = 'P';
	public static final char GHOST_SYM = 'G';
	public static final char EMPTY_SYM = ' ';
	public static final char FOOD_SYM = '.';
	public static final char WALL_SYM = '#';
	
	protected Sprite() {
		
	}
	
	
	public SpriteType getSpriteType() {
		return SpriteType.OTHER;
	}
	

	
	
}
