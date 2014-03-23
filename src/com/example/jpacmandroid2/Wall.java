package com.example.jpacmandroid2;

import com.example.jpacmandroid2.Board.SpriteType;

public class Wall extends Sprite{
	
	@Override
	public SpriteType getSpriteType(){
		return SpriteType.WALL;
	}

}
