package com.example.jpacmandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Main extends Activity {

	
	/*
	 * On creation of activity, create a full screen activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
	}
	
	/*
	 * When the Start button is clicked, do this
	 */
	public void onStartClick(View view) {
		setDirection(START);
	}
	
	/*
	 * When the Stop button is clicked, do this
	 */
	public void onStopClick(View view) {
		setDirection(STOP);
	}
	
	/*
	 * When the Up button is clicked, do this
	 */
	public void onUpClick(View view) {
		setDirection(UP);
	}
	
	/*
	 * When the Down button is clicked, do this
	 */
	public void onDownClick(View view) {
		setDirection(DOWN);
	}
	
	/*
	 * When the Left button is clicked, do this
	 */
	public void onLeftClick(View view) {
		setDirection(LEFT);
	}
	
	/*
	 * When the Right button is clicked, do this
	 */
	public void onRightClick(View view) {
		setDirection(RIGHT);
	}
	
	private static int direction = 0;
	
	public static int getDirection(){
		return direction;
	}
	
	public static void setDirection(int dir){
		direction = dir;
	}
	
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int START = 4;
	public static final int STOP = 5;

}
