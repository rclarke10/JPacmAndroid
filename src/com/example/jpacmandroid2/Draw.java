package com.example.jpacmandroid2;

import com.example.jpacmandroid2.Board.SpriteType;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View {
	
	private static final int H = 21;
	private static final int W = 23;
	
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
        
    private int cellHeight;
    private int cellWidth;
    
    private Board board;
    
	Paint paint;	
	
	boolean viewInitialized = false;
	
	public void setBoard(Board b){
		board = b;
	}

	public Draw(Context context, AttributeSet attribute_set) {
		super(context, attribute_set);
		paint = new Paint();
		
		imageLoader = new ImageLoader(cellWidth, cellHeight,this);       
        imageLoader.loadImages();
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		 
		 if(viewInitialized == false){
			 viewWidth = this.getWidth();
			 viewHeight = this.getHeight();
			 
			 cellWidth = viewWidth/W;
			 cellHeight = viewHeight/H;
			 
			 imageLoader.setWidth(cellWidth);
			 imageLoader.setHeight(cellHeight);
			 
			 imageLoader.resizeAll();
			 viewInitialized = true;
		 }
		 
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
		
        int startx = 2 * CELL_HGAP + (cellWidth + CELL_HGAP) * x;
        int starty = 2 * CELL_VGAP + (cellHeight + CELL_VGAP) * y;
 
        Rect fullCell = fullArea(startx, starty);
               
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawRect(fullCell, paint);

        if (board.getSpriteTypeAt(x,y) == SpriteType.FOOD) {
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
        

        Bitmap bm = spriteBitmap(board.getSpriteAt(x,y));
        if (bm != null) {
           	canvas.drawBitmap(bm, startx, starty, paint);       	
        }
              
 	}
   
	
	private Rect fullArea(int startx, int starty) {
		Rect rect = new Rect(startx, starty, startx + cellWidth, starty + cellHeight);
		return rect;
	}
	
	private Rect centeredArea(int startx, int starty, int radius) {
		assert radius <= cellWidth / 2;		
		
		int x = startx + cellWidth / 2 - radius;
		int y = starty + cellHeight / 2 - radius;
		int r = 2 * radius + 1;
		
		return new Rect(x, y, x + r, y + r);
	}
	
	
	private int spriteColor(int x, int y) {
		SpriteType st = board.getSpriteTypeAt(x, y);

		int c = Color.GRAY;
		switch(st){
		case GHOST:
			c = Color.BLACK;
			break;
		case PLAYER:
			c = Color.BLACK;
			break;
		case WALL:
			c = Color.GREEN;
			break;
		case EMPTY:
			c = Color.GRAY;
			break;
		case OTHER:
			c = Color.GRAY;
			break;
		}
				
		return c;
	}
	
	/**
	 * @param sprite.getSpriteType()
	 * @return A Bitmap for this sprite.
	 */
    private Bitmap spriteBitmap(Sprite sprite) {
        Bitmap bm = null;

        if (imageLoader != null && sprite.getSpriteType() != SpriteType.EMPTY) {
            if (sprite.getSpriteType() == SpriteType.PLAYER) {
            	bm = imageLoader.player(((Player) sprite).getDirection(), animationCount);
                nextAnimation();

            }
            if (sprite.getSpriteType() == SpriteType.GHOST) { 
                 bm = imageLoader.monster(animationCount);
            }
        }
        return bm;
    }
    
    private Bitmap spriteBitmap(int i) {
        Bitmap bm = null;

        bm = imageLoader.monster(animationCount);
        return bm;
    }
    
    public void nextAnimation() {
        if (imageLoader != null) {
            animationCount = (animationCount + 1) % (imageLoader.monsterAnimationCount() * imageLoader.playerAnimationCount());
            invalidate(); //this might break everything. try removing 
        }
    }
	

    /**
     * The width of the board viewer in pixels.
     *
     * @return The width of the board viewer.
     */
    public final int windowWidth() {
        return (cellWidth + CELL_HGAP) * (worldWidth() + 1);
    }

    /**
     * The height of the board viewer in pixels.
     *
     * @return The height of the board viewer.
     */
    public final int windowHeight() {
        return (cellHeight + CELL_VGAP) * (worldHeight() + 1);
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
	
	private int spriteArray[][] = new int[23][21];
	
	public void setSpriteArray(int [][] array){
		
		for(int x = 0; x < 23; x++){
			for(int y = 0; y < 21; y++){
				this.spriteArray[x][y] = array[x][y];	
			}
		}
	}
	
	
}