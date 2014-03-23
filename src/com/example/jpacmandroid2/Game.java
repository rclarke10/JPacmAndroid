package com.example.jpacmandroid2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Game extends Activity {
	
	
	/*
	 * Holds the draw object
	 */
	Draw draw;
	
	/*
	 * Game state
	 */
	private State state;
	
	/*
	 * PlayerMovement
	 */
	private PlayerMovement pm;
	
	/*
	 * Ghostmovement
	 */
	private GhostMovement gm;
	
	/*
	 * Game board
	 */
	private Board board;
	
	/*
	 * Score keeper
	 */
	private Score score;
	
	/*
	 * Called on Activity creation
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Make full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game);
		
		//Initializations
		draw = (Draw)findViewById(R.id.drawGame);
		state = new State();
		board = new Board(this);
		score = new Score(board.getTotalScore(), state, this);
		draw.setSpriteArray(board.getBoard());
		draw.setBoard(board);
		//gm = new GhostMovement(board, draw, state);
		pm = new PlayerMovement(board, state, score, draw);
		
		Log.i("sPOS","x:"+Integer.toString(board.startX()));
		Log.i("sPOS","y:"+Integer.toString(board.startY()));
	}
	
	/*
	 * Starts the game
	 */
	public void startClick(View view) {
		if(state.getState()!= 3){
		state.setState(State.START);
		toast("Game started.");
		}
	}
	
	/*
	 * Stops the game
	 */
	public void stopClick(View view) {
		state.setState(State.STOP);
		toast("Game stopped.");
	}
	
	/*
	 * Responds to clicking the up button
	 */
	public void upClick(View view) {
		if(state.getState() == State.START){
			pm.up();
			draw.invalidate();
		}
		Log.i("xy",""+board.getSpriteAt(11, 15));
	}

	/*
	 * Responds to clicking the down button
	 */
	public void downClick(View view) {
		if(state.getState() == State.START){
			pm.down();
			draw.invalidate();
		}
		Log.i("xy",""+board.getSpriteAt(11, 15));
	}

	/*
	 * Responds to clicking the right button
	 */
	public void rightClick(View view) {
		if(state.getState() == State.START){
			pm.right();			
		}
		Log.i("xy",""+board.getSpriteAt(11, 15));
	}

	/*
	 * Responds to clicking the left button
	 */
	public void leftClick(View view) {
		if(state.getState() == State.START){
			pm.left();
			draw.invalidate();
		}
		Log.i("xy",""+board.getSpriteAt(11, 15));
	}
	
	/*
	 * Displays a toast message to user
	 */
	public void toast(String string) {

		Toast t = Toast.makeText(getApplicationContext(), string,
									Toast.LENGTH_LONG);
		t.show();
		
	}

}