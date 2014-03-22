package com.example.jpacmandroid2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View {
	Paint paint;

	public Draw(Context context, AttributeSet attribute_set) {
		super(context, attribute_set);
		paint = new Paint();
	}

	@Override
	public void onDraw(Canvas canvas) {
		//Make the background black
		canvas.drawColor(Color.BLACK);
		
		//Get maximum width to calculate pixel density if needed
		int max_x = getWidth() - 1;
		int max_y = getHeight() - 1;
		
		
		
		
		
	}
	
	private int spriteArray[][] = new int[50][50];
	
	public void setSpriteArray(int [][] array){
		
		for(int x = 0; x < 50; x++){
			for(int y = 0; y < 50; y++){
				this.spriteArray[x][y] = array[x][y];	
			}
		}
	}
	
	
}