package org.jpacman.framework.view;

import java.util.Timer;
import java.util.TimerTask;

import org.jpacman.framework.controller.IController;

import com.example.jpacmandroid.GameView;

/**
 * The primary responsibility of this class is
 * to trigger the board viewer to display the
 * next animation.
 *
 * @author Arie van Deursen, 2007.
 * @version $Id: Animator.java 4222 2011-01-24 11:28:49Z arievandeursen $
 *
 */
public class Animator implements IController {

    /**
     * The viewer that must be informed to show the
     * next animation.
     */
    private final GameView gameViewer;

    /**
     * The timer used.
     */
    private final Timer timer;

    /**
     * The delay between two animations.
     */
    private static final int DELAY = 200;

    /**
     * Create an animator for a particular board viewer.
     * @param bv The view to be animated.
     * @throws InterruptedException 
     */
    public Animator(GameView gv) throws InterruptedException {
        gameViewer = gv;
        timer = new Timer();
        timer.schedule(new TickTask(), DELAY);       
    }
    
    class TickTask extends TimerTask{
		@Override
		public void run() {
			doTick();
			
		}    	
    }

    /**
     * Stop triggering animation events.
     */
    @Override
	public void stop() {
        ((IController) timer).stop();
    }

    /**
     * Start triggering animation events.
     */
    @Override
	public void start()  {
        ((IController) timer).start();
    }
    
    @Override
    public void doTick() { 
    	System.out.println("tick");
    	gameViewer.nextAnimation();
    }
}
