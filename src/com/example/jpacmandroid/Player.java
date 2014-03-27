package com.example.jpacmandroid;

import com.example.jpacmandroid.Board.SpriteType;

public class Player extends Sprite {
	
	Direction dir = Direction.LEFT;
	
	@Override
	public SpriteType getSpriteType(){
		return SpriteType.PLAYER;
	}
	
	public Direction getDirection(){
		return dir;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
		
	}

}
