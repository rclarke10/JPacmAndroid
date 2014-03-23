package com.example.jpacmandroid2;

import com.example.jpacmandroid2.Board.SpriteType;

public class Ghost extends Sprite{
	@Override
	public SpriteType getSpriteType(){
		return SpriteType.GHOST;
	}
}
