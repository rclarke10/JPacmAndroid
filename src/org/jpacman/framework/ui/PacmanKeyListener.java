package org.jpacman.framework.ui;

import com.example.jpacmandroid.Main;

/**
 * Map keyboard events to jpacman events.
 * 
 * @author Arie van Deursen, TU Delft, Jan 29, 2012
 */
public class PacmanKeyListener {
	
	/**
	 * The interface to the underlying model.
	 */
	private final IPacmanInteraction modelEvents;

	/**
	 * Create a new keyboard listener, given a handler
	 * for model events keyboard events should be mapped to.
	 * 
	 * @param me Events the model can handle.
	 */
	PacmanKeyListener(IPacmanInteraction me) {
		modelEvents = me;
	}

	public void keyPressed() {
		int code;

		code = Main.getDirection();

		switch (code) {
		case Main.UP:
			modelEvents.up();
			break;
		case Main.DOWN:
			modelEvents.down();
			break;
		case Main.LEFT:
			modelEvents.left();
			break;
		case Main.RIGHT:
			modelEvents.right();
			break;
		case Main.STOP:
			modelEvents.stop();  
			break;
		case Main.START:
			modelEvents.start(); 
			break;
		default:
			// all other events ignored.
		}
	}
}