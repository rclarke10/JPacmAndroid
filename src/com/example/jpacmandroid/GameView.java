package com.example.jpacmandroid;


import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.view.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
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
    
    /**
     * Representation of the actual board.
     */
    private final IBoardInspector boardInspector;
    
    /**
     * The manager keeping track of images.
     */
    private final ImageLoader imageLoader;
    
    /**
     * Indicator for animation.
     */
    private int animationCount;
    
    int n = 0;
	    
	Paint paint;
	Bitmap ghost1, ghost2, pacman1;
	
	public GameView(Context context, AttributeSet attributeSet, IBoardInspector board){
		super(context, attributeSet);
		boardInspector = board;
    	imageLoader = new ImageLoader(CELL_WIDTH, CELL_HEIGHT,this);       
        imageLoader.loadImages();
	}	

	@Override
	public void onDraw(Canvas canvas){
		 super.onDraw(canvas);
		 paint = new Paint();	 			 		
		
		 paint.setColor(Color.BLUE);		 
		 drawCells(canvas);		
	}  
	
	
	public void drawCells(Canvas canvas){
		final float strokeWidth = 5.0f;
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
        paint.setColor(Color.BLUE); 
        canvas.drawRect(fullCell, paint);
        
        if (boardInspector.spriteTypeAt(x, y) == SpriteType.FOOD) {
        	Rect centeredCell = centeredArea(startx, starty, 2);
        	paint.setColor(Color.BLACK);
        	canvas.drawRect(fullCell, paint);
        	paint.setColor(Color.RED);
        	canvas.drawRect(centeredCell, paint);
     
        }
        
       	Bitmap bm = spriteBitmap(boardInspector.spriteAt(x, y));
        if (bm != null) {
        	canvas.drawBitmap(bm, startx, starty, paint);
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

  
	private int worldHeight() {
		return 23;
	}

	private int worldWidth() {
		return 23;
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
		SpriteType st = boardInspector.spriteTypeAt(x, y);
		int c = Color.YELLOW;
		
		switch (st) {
		case GHOST:
			c = Color.BLUE;
			break;
		case FOOD:
			c = Color.RED;
			break;
		case WALL:
			c = Color.GREEN;
			break;
		case PLAYER:
			c = Color.YELLOW;
			break;
		case OTHER:
			c = Color.BLACK;
			break;
		case EMPTY:
			c = Color.GRAY;
			break;
		default:
			assert false : "No other Sprite Types " + st;
		}
		return c;
	}
	
	/**
	 * @param sprite
	 * @return A Bitmap for this sprite.
	 */
    private Bitmap spriteBitmap(Sprite sprite) {
        Bitmap bm = null;
        if (imageLoader != null && sprite != null) {
            if (sprite instanceof Player) {
                bm = imageLoader.player(
                		((Player) sprite).getDirection(),
                        animationCount);
            }
            if (sprite.getSpriteType() == SpriteType.GHOST) { 
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
	
	
}
