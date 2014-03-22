package org.jpacman.framework.ui;

import java.util.Observable;
import java.util.Observer;

/**
 * A panel containing the buttons for controlling
 * JPacman.
 * 
 * @author Arie van Deursen, TU Delft, Jan 21, 2012
 */
public class ButtonPanel implements Observer {
	private PacmanInteraction pacmanInteractor;
	
	/**
	 * Obtain the handler capable of dealing with
	 * button events.
	 * @return The pacman interactor.
	 */
	public ButtonPanel(PacmanInteraction pi){
		this.pacmanInteractor = pi;
	}
	
	public IPacmanInteraction getPacmanInteractor() {
		return pacmanInteractor;
	}

    
    public void pause() {
		getPacmanInteractor().stop();  	
    }
    
    public void start() {
		getPacmanInteractor().start();
    }

	@Override
	public void update(Observable o, Object arg) {
		//nothing
	}
}
