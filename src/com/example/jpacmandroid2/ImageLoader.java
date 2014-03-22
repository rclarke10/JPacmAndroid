package com.example.jpacmandroid2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

/**
 * The responsibilities of this class include obtaining images from file,
 * that can be used for animations of the player and the monsters in Pacman.
 *
 * @author Arie van Deursen, Delft University of Technology, May 2007
 *
 */

public class ImageLoader {

    /**
     * Animation sequence of images for monsters.
     */
    private Bitmap[] monsterImage;

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
     * Read images for player and monsters from file.
     * Different images exist for different phases of the animation.
     * @throws FactoryException if the images can't be found.
     */
    public void loadImages() {
    	monsterImage = new Bitmap[]{
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
		
		
		playerImage[Direction.DOWN.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2down);
		playerImage[Direction.DOWN.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2down);
		playerImage[Direction.DOWN.ordinal()][4] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2down);
		

		playerImage[Direction.UP.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2up);
		playerImage[Direction.UP.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3up);
		playerImage[Direction.UP.ordinal()][4] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4up);
		
		playerImage[Direction.LEFT.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2left);
		playerImage[Direction.LEFT.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3left);
		playerImage[Direction.LEFT.ordinal()][4] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4left);
		
		playerImage[Direction.RIGHT.ordinal()][2] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman2right);
		playerImage[Direction.RIGHT.ordinal()][3] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman3right);
		playerImage[Direction.RIGHT.ordinal()][4] = BitmapFactory.
				decodeResource(view.getResources(), R.drawable.pacman4right);
		
		resizeAll();
    }
    
    public void resizeAll(){
    	for(int i = 0; i < playerImage.length; i++){
    		for(int j = 0; j < playerImage[i].length; j++){
    			playerImage[i][j] = resize(playerImage[i][j]);
    		}
    	}
    	
    	for(int i = 0; i < monsterImage.length; i++){
    		monsterImage[i] = resize(monsterImage[i]);
    	}
    }

    /**
     * @return Number of different monster animation steps
     */
    public int monsterAnimationCount() {
        assert monsterImage != null : "Monster image should not be null.";
        int result = monsterImage.length;
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
        bm = playerImage[dirIndex][anim % playerAnimationCount()];
        assert bm != null;
        Log.i("Image Loader", "returning player bm " + anim + " " + playerAnimationCount() + " "+ dir.ordinal());
        return bm;
    }

    /**
     * Obtain a picture of a monster.
     * @param animationIndex counter indicating which animation to use.
     * @return The monster image at the given animation index.
     */
    public Bitmap monster(int animationIndex) {
        assert animationIndex >= 0;
        return monsterImage[animationIndex % monsterAnimationCount()];
    }   
     
    /**
     * Resize a given image to the required dimension.
     * @param im The image
     * @return The resized image.
     */
    
    Bitmap resize(Bitmap bm) {
    	Log.i("Image Loader", "resizing bm");
        if(bm != null){
        	  bm.getWidth();
              bm.getHeight();
              Bitmap result = bm;
              if (width > 0 && height > 0) {
              	Log.i("Image Loader", "creating scaled bm");
              	result = Bitmap.createScaledBitmap(bm, width, height, false);
              	Log.i("Image Loader", "created scaled bm");
              }
              
              return result;
        }      
       
        return bm;
    }
    
}
