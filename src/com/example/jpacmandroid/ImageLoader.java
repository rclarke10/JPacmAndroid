package com.example.jpacmandroid;


import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.jpacmandroid2.R;

/**
 * The responsibilities of this class include obtaining images from file,
 * that can be used for animations of the player and the monsters in Pacman.
 *
 * @author Arie van Deursen, Delft University of Technology, May 2007
 *
 */

public class ImageLoader {

    /**
     * Animation sequence of images for the ghosts.
     */
    private Bitmap[] ghostImage;

    /**
     * Animation sequence of images for the player.
     */
    private Bitmap[][] playerImage;  
    
    /**
     * Width of the images.
     */
    private int width = -1;
    
    /**
     * Height of the images.
     */
    private int height = -1;
    
    private View view;
    
       
    /**
     * Create an empty (non initialized) image factory
     * requiring that all images are of the given (width, height).
     * @param w requested image width
     * @param h requested image height
     * 
     */
    public ImageLoader(int w, int h, View view) { 
        width = w;
        height = h;
        this.view = view;
    }
     
    
    /**
     * Read images for player and ghosts from file.
     * Different images exist for different phases of the animation.
     */
    public void loadImages() throws IOException{
    	ghostImage = new Bitmap[]{
				BitmapFactory.decodeResource(view.getResources(), R.drawable.ghost1),
				BitmapFactory.decodeResource(view.getResources(), R.drawable.ghost1)
		};

		String[] sequence = new String[]{"2", "3", "4", "3", "2"};	
		
		
		playerImage = new Bitmap[Direction.values().length][sequence.length + 1];
		
		for(Direction d: Direction.values()){
			int dir = d.ordinal();
			playerImage[dir][0] = BitmapFactory.
					decodeResource(view.getResources(), R.drawable.pacman1);
		}
		
		
		playerImage[Direction.DOWN.ordinal()][1] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2down);
		playerImage[Direction.DOWN.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3down);
		playerImage[Direction.DOWN.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4down);
		

		playerImage[Direction.UP.ordinal()][1] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2up);
		playerImage[Direction.UP.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3up);
		playerImage[Direction.UP.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4up);
		
		playerImage[Direction.LEFT.ordinal()][1] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2left);
		playerImage[Direction.LEFT.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3left);
		playerImage[Direction.LEFT.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4left);
		
		playerImage[Direction.RIGHT.ordinal()][1] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2right);
		playerImage[Direction.RIGHT.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3right);
		playerImage[Direction.RIGHT.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4right);
		
		resizeAll();
    }    
    
    /**
     * Resizes all images to the standard width and height
     */    
    public void resizeAll(){
    	for(int i = 0; i < playerImage.length; i++){
    		for(int j = 0; j < playerImage[i].length; j++){
    			playerImage[i][j] = resize(playerImage[i][j]);
    		}
    	}
    	
    	for(int i = 0; i < ghostImage.length; i++){
    		ghostImage[i] = resize(ghostImage[i]);
    	}
    }

    /**
     * @return Number of different monster animation steps
     */
    public int monsterAnimationCount() {
        assert ghostImage != null : "Monster image should not be null.";
        int result = ghostImage.length;
        assert result >= 0;
        return result;
    }

    /**
     * @return Number of different player animation steps
     */
    public int playerAnimationCount() {
        assert playerImage != null;
        assert playerImage[0] != null;
        return playerImage[0].length;
    }


    /**
     * Get a player (pizza slice) in the appropriate direction at the
     * given animation sequence.
     * 
     * @param dir Direction pacman is moving to.
     * @param anim Animation step
     * @return Player image in appropriate direction.
     */
    public Bitmap player(Direction dir, int anim) {
        assert anim >= 0;
        Bitmap bm = null;
        int dirIndex = dir.ordinal();
        bm = playerImage[dirIndex][anim % 4];
        assert bm != null;
        //Log.i("Image Loader", "returning player bm " + anim + " " + playerAnimationCount() + " "+ dir.ordinal());
        return bm;
    }

    /**
     * Obtain a picture of a ghost.
     * @param animationIndex counter indicating which animation to use.
     * @return The ghost image at the given animation index.
     */
    public Bitmap ghostImage(int animationIndex) {
        assert animationIndex >= 0;
        return ghostImage[animationIndex % monsterAnimationCount()];
    }   
     
    /**
     * Resize a given bitmap to the required dimension.
     * @param bm The bitmap
     * @return The resized bitmap.
     */    
    public Bitmap resize(Bitmap bm) {
        if(bm != null){
        	  bm.getWidth();
              bm.getHeight();
              Bitmap result = bm;
              if (width > 0 && height > 0) {
              	result = Bitmap.createScaledBitmap(bm, width, height, false);
              }
              
              return result;
        }      
       
        return bm;
    }


	public void setWidth(int cellWidth) {
		width = cellWidth;		
	}
	
	public void setHeight(int cellHeight){
		height = cellHeight;
	}
    
}
