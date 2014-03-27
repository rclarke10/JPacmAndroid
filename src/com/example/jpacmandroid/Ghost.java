package com.example.jpacmandroid;

import com.example.jpacmandroid.Board.SpriteType;

public class Ghost extends Sprite{
	@Override
	public SpriteType getSpriteType(){
		return SpriteType.GHOST;
	}
}
