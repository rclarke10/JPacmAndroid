package com.example.jpacmandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;



public class GameView extends View{

	 /**
     * Width of an individual cell, in pixels.
     */
    private static final int CELL_WIDTH = 35;

    /**
     * Height of an individual cell, in pixels.
     */
    private static final int CELL_HEIGHT = 35;

    /**
     * The horizontal gap between cells, in pixels.
     */
    public static final int CELL_HGAP = 1;

    /**
     * The vertical gap between cells, in pixels.
     */
    public static final int CELL_VGAP = 1;
    
    int n = 0;
	
    
	Paint paint;
	Bitmap ghost1, ghost2, pacman1;
	
	public GameView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
	}	

	@Override
	public void onDraw(Canvas canvas){
		 super.onDraw(canvas);
		 paint = new Paint();
		 paint.setStrokeWidth(1); 		
		 			 		
		
		 paint.setColor(Color.BLUE);
		 
		 drawCells(canvas);
		
	}    
	
	public void loadImages(){
		//ghost1 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost1);
		//ghost2 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost2);
		//pacman1 = BitmapFactory.decodeResource(getResources(), R.drawable.pacman1);
	}
	
	public void drawCells(Canvas canvas){
		
		for (int x = 0; x < worldWidth(); x++) {
            for (int y = 0; y < worldHeight(); y++) {
            	drawCell(canvas, x, y);
            }
        }
	}
	
	private void drawCell(Canvas canvas, int x, int y) {
       n++;
        int startx = 2 * CELL_HGAP + (CELL_WIDTH + CELL_HGAP) * x;
        int starty = 2 * CELL_VGAP + (CELL_HEIGHT + CELL_VGAP) * y;
 
        Rect fullCell = fullArea(startx, starty);
        
        paint.setColor(Color.BLUE); 
        
		canvas.drawRect(startx, starty, startx + CELL_WIDTH, starty + CELL_HEIGHT, paint);      
      
 	}

  
	private int worldHeight() {
		return 23;
	}

	private int worldWidth() {
		return 23;
	}
	
	private Rect fullArea(int startx, int starty) {
		Rect rect = new Rect(startx, starty, CELL_WIDTH, CELL_HEIGHT);
		return rect;
	}
	
	
}
