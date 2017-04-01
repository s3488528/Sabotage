package edu.oosd.sabotage.controllers;

import edu.oosd.sabotage.core.*;

public class GameContext {

	private Board board;
	private Player[] players;
	
	public GameContext(int boardWidth, int boardHeight, int deckCount) {
		this.board = new Board(boardWidth, boardHeight, deckCount);
	}
	
	public Board getBoard() {
		return board;
	}
}
