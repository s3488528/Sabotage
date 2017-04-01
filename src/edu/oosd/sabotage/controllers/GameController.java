package edu.oosd.sabotage.controllers;

import edu.oosd.sabotage.core.GameContext;

public class GameController {
	
	private GameContext gc;
	
	public GameController(int boardWidth, int boardHeight, int deckCount) {
		gc = new GameContext(boardWidth, boardHeight, deckCount);
	}
	
	
	public void startGame(int playerCount) {
	}
	
	public void initializePlayers(int playerCount) {
		gc.initializePlayers(playerCount);
	}
	
	public String getDetails() {
		String string = gc.getBoard().getTilesAsString();		
		string += gc.getPlayersAsString();
		
		return string;
	}
	
}
