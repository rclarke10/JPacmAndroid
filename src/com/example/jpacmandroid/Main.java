package com.example.jpacmandroid;

import java.util.Observable;
import java.util.Observer;

import org.jpacman.framework.controller.IController;
import org.jpacman.framework.controller.RandomGhostMover;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.model.Level;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.PacmanInteraction;
import org.jpacman.framework.view.Animator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Main extends Activity implements Observer {


    /**
     * The level we're currently playing.
     */
    private Level level;
    
    GameView gv;
    
	/*
	 * On creation of activity, create a full screen activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("Main", "onCreate entered");
		super.onCreate(savedInstanceState);
			
	
		// Make full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);		

		setContentView(R.layout.activity_main);
	
		level = new Level();
		gv = (GameView)findViewById(R.id.gameView1);
		
		try {
			main();
		} catch (FactoryException e) {
			Log.i("Main", "Factory Exception");
			//ignore problems
		} catch(RuntimeException e){
			Log.i("Main", "Runtime Exception");
		}
	}
	
	/*
	 * When the Start button is clicked, do this
	 */
	public void onStartClick(View view) {
		setDirection(START);
		buttonPanel.start();
		TextView num_points_textview = (TextView) findViewById(R.id.num_points_text);
		num_points_textview.setText(getGame().getPointManager().getFoodEaten() + "/" + getGame().getPointManager().totalFoodInGame());
	}
	
	/*
	 * When the Stop button is clicked, do this
	 */
	public void onStopClick(View view) {
		setDirection(STOP);
		buttonPanel.pause();
		TextView num_points_textview = (TextView) findViewById(R.id.num_points_text);
		num_points_textview.setText(getGame().getPointManager().getFoodEaten() + "/" + getGame().getPointManager().totalFoodInGame());
	}
	
	/*
	 * When the Up button is clicked, do this
	 */
	public void onUpClick(View view) {
		setDirection(UP);
		TextView num_points_textview = (TextView) findViewById(R.id.num_points_text);
		num_points_textview.setText(getGame().getPointManager().getFoodEaten() + "/" + getGame().getPointManager().totalFoodInGame());
	}
	
	/*
	 * When the Down button is clicked, do this
	 */
	public void onDownClick(View view) {
		setDirection(DOWN);
		TextView num_points_textview = (TextView) findViewById(R.id.num_points_text);
		num_points_textview.setText(getGame().getPointManager().getFoodEaten() + "/" + getGame().getPointManager().totalFoodInGame());
	}
	
	/*
	 * When the Left button is clicked, do this
	 */
	public void onLeftClick(View view) {
		setDirection(LEFT);
		TextView num_points_textview = (TextView) findViewById(R.id.num_points_text);
		num_points_textview.setText(getGame().getPointManager().getFoodEaten() + "/" + getGame().getPointManager().totalFoodInGame());
	}
	
	/*
	 * When the Right button is clicked, do this
	 */
	public void onRightClick(View view) {
		setDirection(RIGHT);
		TextView num_points_textview = (TextView) findViewById(R.id.num_points_text);
		num_points_textview.setText(getGame().getPointManager().getFoodEaten() + "/" + getGame().getPointManager().totalFoodInGame());
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
	
	/*
	 * 
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  Conversion of MainUI goes below 
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  */
	
	
	
	
	
	

    /**
     * Universal version ID for serialization.
     */
    static final long serialVersionUID = -59470379321937183L;
    
    /**
     * The underlying game.
     */
	private transient IGameInteractor theGame;

	/**
	 * Mapping of UI events to model actions.
	 */
	private transient PacmanInteraction pi;

	/**
	 * The main window components.
	 */
	private ButtonPanel buttonPanel;
	
	/**
	 * Controllers that will trigger certain events.
	 */
	private transient IController ghostController;
	private transient Animator animator;
	

	/**
	 * Create all the ui components and attach appropriate
	 * listeners.
	 * @throws FactoryException If resources for game can't be loaded.
	 */
    private void createUI() throws FactoryException {
    	assert getGame() != null;
    	assert ghostController != null;
    	
    	pi = (pi == null ? new PacmanInteraction() : pi)
    		.withGameInteractor(getGame())
    		.controlling(ghostController)
    		.controlling(animator);
    	
    	if (buttonPanel == null) {
    		buttonPanel = new ButtonPanel(pi);
    	}
        
        getGame().attach(pi);
    	
        //REPLACE THIS WITH THE MAIN GRID DRAWER
    	//JPanel mainGrid = createMainGrid();
        
        //getContentPane().add(mainGrid);
    }
    

    

    
    ///////////////////////////////////////////////////
    //REWRITE TO DRAW TO ANDROID///////////////////////
    ///////////////////////////////////////////////////
    /**
     * The state of the game has changed.
     * Reset button enabling depending on the state.
     * @param o Ignored
     * @param arg Ignored
     */
	@Override
	public void update(Observable o, Object arg) {
		//statusField.setText(pi.getCurrentState().message());
    	//boardView.repaint();		
    }

	/**
	 * Create the controllers and user interface elements.
	 * @throws FactoryException If required resources can't be loaded.
	 * @return The main UI object.
	 */
    public Main initialize() throws FactoryException {
    	Log.i("init", "CreadModel");
    	theGame = createModel();
    	Log.i("init", "CreadModel Passed");
        getGame().attach(this);
        Log.i("init", "Attached to getGame()");
        getGame();
        Log.i("init", "getGame() passed");
        withGhostController(new RandomGhostMover(getGame()));
        Log.i("init", "trying CreateUI");
      	createUI();
      	Log.i("init", "CreateUI passed");
      	gv.setBoardInspector(theGame.getBoardInspector());
      	return this;
    }
    	
    /**
     * Actually start the the controllers, and show the UI.
     */
	public void start()  {
		animator.start();
        //setVisible(true);
	}
	
	/**
	 * Read a board from file and load it.
	 * @return The resulting game.
	 * @throws FactoryException
	 */
	private IGameInteractor createModel() throws FactoryException {
		return level.parseMap();
	}
	
	/**
	 * @return The mapping between keyboard events and model events.
	 */
	public PacmanInteraction eventHandler() {
		return pi;
	}
	
	/**
	 * @return The underlying game.
	 */
	public IGameInteractor getGame() {
		return theGame;
	}

	/**
	 * Provide a given ghost controller.
	 * @param gc The new ghost controller.
	 * @return Itself for fluency.
	 */
	public Main withGhostController(IController gc) {
		Log.i("withGhost", "trying assert");
		assert gc != null;
		Log.i("withGhost", "assert passed");
		ghostController = gc;
		Log.i("withGhost", "ghostControll = gc passed");
		return this;
	}
	
	/**
	 * Provide the name of the file containing the board.
	 * @param fileName Board file name.
	 * @return Itself for fluency.
	 */
	public Main withBoard(String fileName) {
		assert fileName != null;
		level.setMapFile(fileName);
		return this;
	}
	
	/**
	 * Provide a factory to create model elements.
	 * @param fact The actual factory
	 * @return Itself for fluency.
	 */
	public Main withFactory(IGameFactory fact) {
		assert fact != null;
		assert level != null;
		level.setFactory(fact);
		return this;
	}
	
	/**
	 * Provide the row of buttons.
	 * @param bp The new row of buttons
	 * @return Itself for fluency
	 *//*
	public Main withButtonPanel(ButtonPanel bp) {
		assert bp != null;
		buttonPanel = bp;
		return this;
	}*/
	
	/**
	 * Proivde the interface to interact with the model.
	 * @param pi New model interactor.
	 * @return Itself for fluency.
	 */
	public Main withModelInteractor(PacmanInteraction pi) {
		assert pi != null;
		this.pi = pi;
		return this;
	}
	
	/**
	 * Top level method creating the game, and 
	 * starting up the interactions.
	 * @throws FactoryException If creating the game fails.
	 */
	public void main() throws FactoryException {
		initialize();
		start();
	}

}
