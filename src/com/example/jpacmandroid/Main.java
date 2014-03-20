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
		
	}
	
	/*
	 * When the Stop button is clicked, do this
	 */
	public void onStopClick(View view) {
		
	}
	
	/*
	 * When the Up button is clicked, do this
	 */
	public void onUpClick(View view) {
		
	}
	
	/*
	 * When the Down button is clicked, do this
	 */
	public void onDownClick(View view) {
		
	}
	
	/*
	 * When the Left button is clicked, do this
	 */
	public void onLeftClick(View view) {
		
	}
	
	/*
	 * When the Right button is clicked, do this
	 */
	public void onRightClick(View view) {
		
	}

}
