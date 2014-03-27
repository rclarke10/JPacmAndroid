package com.example.jpacmandroid;

import com.example.jpacmandroid.Board.SpriteType;

public class Wall extends Sprite{
	
	@Override
	public SpriteType getSpriteType(){
		return SpriteType.WALL;
	}

}
