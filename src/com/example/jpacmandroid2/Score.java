package com.example.jpacmandroid2;

import android.graphics.Color;
import android.widget.TextView;

public class Score {
	/*
	 * Current score
	 */
	private int score = 0;
	
	/*
	 * Current state
	 */
	State state;
	
	/*
	 * Total score
	 */
	private int totalScore;
	
	/*
	 * The game
	 */
	Game game;
	
	public Score(int totalScore, State state, Game game){
		this.totalScore = totalScore;
		this.state = state;
		this.game = game;
		displayScore();
	}
	
	public void addScore(){
		score += 10;
		if(score == totalScore){
			state.setState(State.WON);
			game.toast("You have won :)");
		}
		displayScore();
	}
	
	public int getScore(){
		return score;
	}
	
	public int getTotalScore(){
		return totalScore;
	}
	
	
	private String scoreString;
	
	/*
	 * Displays the score on the score TextView
	 */
	private void displayScore(){
		scoreString = this.getScore()+"/"+this.getTotalScore();

		
		TextView s = (TextView) game.findViewById(R.id.score);
		s.setTextColor(Color.BLACK);
		s.setText(scoreString);
	}
}
