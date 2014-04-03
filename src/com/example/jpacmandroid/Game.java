package com.example.jpacmandroid;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jpacmandroid2.R;

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

		// Initializations
		draw = (Draw) findViewById(R.id.drawGame);
		state = new State();
		board = new Board(this);
		score = new Score(board.getTotalScore(), state, this);
		draw.setBoard(board);
		gm = new GhostMovement(board, draw, state);
		pm = new PlayerMovement(board, state, score, draw);

		Timer updateTimer = new Timer();
		GhostTimerTask mtt = new GhostTimerTask();
		updateTimer.schedule(mtt, GhostMovement.GHOST_MOVE_DELAY, GhostMovement.GHOST_MOVE_DELAY);
	}

	/*
	 * Starts the game
	 */

	public void startClick(View view) {
		if(state.getState()!= State.WON && state.getState()!= State.LOST ){
		state.setState(State.START);
		toast("Game started.");
		}else{
			draw = (Draw) findViewById(R.id.drawGame);
			state = new State();
			board = new Board(this);
			score = new Score(board.getTotalScore(), state, this);	
			draw.setBoard(board);
			gm = new GhostMovement(board, draw, state);
			pm = new PlayerMovement(board, state, score, draw);
			Timer updateTimer = new Timer();
			GhostTimerTask mtt = new GhostTimerTask();
			updateTimer.schedule(mtt, GhostMovement.GHOST_MOVE_DELAY, GhostMovement.GHOST_MOVE_DELAY);

			state.setState(State.START);
			toast("Game started.");
		}
	}

	/*
	 * Stops the game only if the game has been started
	 */
	public void stopClick(View view) {
		if(state.getState() == State.START){
			state.setState(State.STOP);
			toast("Game stopped.");
		}
		
	}

	/*
	 * Responds to clicking the up button
	 */
	public void upClick(View view) {

		if(state.getState() == State.START){
			pm.move(Direction.UP);
			draw.invalidate();
		}
			
	}

	/*
	 * Responds to clicking the down button
	 */
	public void downClick(View view) {

		if(state.getState() == State.START){
			pm.move(Direction.DOWN);
			draw.invalidate();
		}		
	
	}

	/*
	 * Responds to clicking the right button
	 */
	public void rightClick(View view) {		
	
		if(state.getState() == State.START){
			pm.move(Direction.RIGHT);
			draw.invalidate();
		}
		
	}

	/*
	 * Responds to clicking the left button
	 */
	public void leftClick(View view){
		
		if(state.getState() == State.START){
			pm.move(Direction.LEFT);
			draw.invalidate();
		}		
		
	}

	/*
	 * Displays a toast message to user
	 */
	public void toast(String string) {

		Toast t = Toast.makeText(getApplicationContext(), string,
				Toast.LENGTH_LONG);
		t.show();

	}

	public class GhostTimerTask extends TimerTask {
		public void run() {
			if (state.getState() == State.START) {
				for (int i = 0; i < board.getNumGhosts(); i++) {
					int dir = gm.random.nextInt(4) + 1;
					gm.moveGhost(i, dir);

				}
			}
			
			runOnUiThread(new Runnable() {
				public void run() {
					draw.invalidate();
				}
			});
		}
	}
	
	public State getState(){
		return state;
	}

}