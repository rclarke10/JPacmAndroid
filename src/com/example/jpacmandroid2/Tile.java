package com.example.jpacmandroid2;

import com.example.jpacmandroid2.Board.SpriteType;

public class Tile {
	
	public int x;
	public int y;
	public Sprite sprite;
	
	public Tile(int x,int y, Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public SpriteType getSpriteStype(){
		return sprite.getSpriteType();
	}

}
