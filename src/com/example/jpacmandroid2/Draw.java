package com.example.jpacmandroid2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Draw extends View {
	
	 /**
     * Width of an individual cell, in pixels.
     */
    private static final int CELL_WIDTH = 30;

    /**
     * Height of an individual cell, in pixels.
     */
    private static final int CELL_HEIGHT = 30;

    /**
     * The horizontal gap between cells, in pixels.
     */
    public static final int CELL_HGAP = 1;

    /**
     * The vertical gap between cells, in pixels.
     */
    public static final int CELL_VGAP = 1;
    
    /**
     * The manager keeping track of images.
     */
    private final ImageLoader imageLoader;
    private int animationCount;
    
    private int viewHeight;
    
    private int viewWidth; 
        
    private Board board;
    
	Paint paint;	
	
	boolean viewInitialized = false;
	
	public void setBoard(Board b){
		board = b;
	}

	public Draw(Context context, AttributeSet attribute_set) {
		super(context, attribute_set);
		paint = new Paint();
		
		imageLoader = new ImageLoader(CELL_WIDTH, CELL_HEIGHT,this);       
        imageLoader.loadImages();
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		 
		 if(viewInitialized == false){
			 viewWidth = this.getWidth();
			 viewHeight = this.getHeight();
			 Log.i("View", "Height: " + viewHeight);
			 Log.i("View", "Width: " + viewWidth);	
			 imageLoader.resizeAll();
			 viewInitialized = true;
		 }
		 
		 paint = new Paint();	 			 		
		
		 paint.setColor(Color.BLUE);		 
		 drawCells(canvas);	
		
	}
	
	public void drawCells(Canvas canvas){
		final float strokeWidth = 3.0f;
		paint.setStrokeWidth(strokeWidth);
		
		for (int x = 0; x < worldWidth(); x++) {
            for (int y = 0; y < worldHeight(); y++) {
            	drawCell(canvas, x, y);
            }
        }
	}
	
	private void drawCell(Canvas canvas, int x, int y) {
		int fillColor = spriteColor(x, y);
		
        int startx = 2 * CELL_HGAP + (CELL_WIDTH + CELL_HGAP) * x;
        int starty = 2 * CELL_VGAP + (CELL_HEIGHT + CELL_VGAP) * y;
 
        Rect fullCell = fullArea(startx, starty);
               
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawRect(fullCell, paint);
        
        if (board.getSpriteAt(x, y) == Sprite.FOOD) {
        	paint.setStyle(Paint.Style.FILL);
        	Rect centeredCell = centeredArea(startx, starty, 2);
        	paint.setColor(Color.BLACK);
        	canvas.drawRect(fullCell, paint);
        	paint.setColor(Color.RED);
        	canvas.drawRect(centeredCell, paint);
        	
        }else{
        	paint.setStyle(Paint.Style.FILL);
        	paint.setColor(fillColor);
        	canvas.drawRect(fullCell, paint);
        }
        
      	Bitmap bm = spriteBitmap(board.getSpriteAt(x, y));
        if (bm != null) {
        	canvas.drawBitmap(bm, startx, starty, paint);       	
        }else{
        	Log.i("game view", "bitmap is null");
        	
        }        
        
 	}
   
	
	private Rect fullArea(int startx, int starty) {
		Rect rect = new Rect(startx, starty, startx + CELL_WIDTH, starty + CELL_HEIGHT);
		return rect;
	}
	
	private Rect centeredArea(int startx, int starty, int radius) {
		assert radius <= CELL_WIDTH / 2;		
		
		int x = startx + CELL_WIDTH / 2 - radius;
		int y = starty + CELL_HEIGHT / 2 - radius;
		int r = 2 * radius + 1;
		
		return new Rect(x, y, x + r, y + r);
	}
	
	
	private int spriteColor(int x, int y) {
		int st = board.getSpriteAt(x, y);

		int c = Color.YELLOW;
		switch(st){
		case Sprite.GHOST:
			c = Color.BLACK;
			break;
		case Sprite.PACMAN:
			c = Color.BLACK;
			break;
		case Sprite.WALL:
			c = Color.GREEN;
			break;
		case Sprite.EMPTY:
			c = Color.GRAY;
			break;
		}
				
		return c;
	}
	
	/**
	 * @param i
	 * @return A Bitmap for this sprite.
	 */
    private Bitmap spriteBitmap(int i) {
    	
        Bitmap bm = null;
        if (imageLoader != null && i != Sprite.EMPTY) {
            if (i == Sprite.PACMAN) {
            	bm = imageLoader.player(Direction.DOWN, 0);

                Log.i("sprite", "player");
            }
            if (i == Sprite.GHOST) { 
                 bm = imageLoader.monster(animationCount);
            }
        }
        return bm;
    }
    
    public void nextAnimation() {
        if (imageLoader != null) {
            animationCount = (animationCount + 1)
            % (imageLoader.monsterAnimationCount()
                    * imageLoader.playerAnimationCount());
            invalidate(); //this might break everything. try removing 
        }
    }
	

    /**
     * The width of the board viewer in pixels.
     *
     * @return The width of the board viewer.
     */
    public final int windowWidth() {
        return (CELL_WIDTH + CELL_HGAP) * (worldWidth() + 1);
    }

    /**
     * The height of the board viewer in pixels.
     *
     * @return The height of the board viewer.
     */
    public final int windowHeight() {
        return (CELL_HEIGHT + CELL_VGAP) * (worldHeight() + 1);
    }

    /**
     * @return The board height measured in cells, >= 0.
     */  
	private int worldHeight() {
		return board.getHeight();
	}

	 /**
     * @return The board width measured in cells, >= 0.
     */
	private int worldWidth() {
		return board.getWidth();
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