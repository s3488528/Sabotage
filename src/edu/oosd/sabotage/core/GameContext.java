package edu.oosd.sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameContext {

	private Board board;
	private ArrayList<Player> players;
	private Player currentPlayer;
	
	private Random random = new Random();
	
	public GameContext(int boardWidth, int boardHeight, int deckCount) {
		this.board = new Board(boardWidth, boardHeight, deckCount);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void initializePlayers(int playerCount) {
		players = new ArrayList<Player>();
		
		for (int i = 0; i < playerCount; i++) {
			Player tempPlayer = new Player("Player " + (i + 1));
			
			for (int j = 0; j < 10; j++) {
				tempPlayer.addCardToHand(CardBuilder.createRandomCard());
			}
			
			players.add(tempPlayer);
		}
		
		players.get(random.nextInt(playerCount)).setAsSaboteur();
	}
	
	public void shufflePlayers() {
		Collections.shuffle(players);
		currentPlayer = players.get(0);
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public String getPlayersAsString() {
		String[] string = new String[players.size()];

		for (int i = 0; i < players.size(); i++) {
			string[i] = players.get(i).getName();
		}
		
		return String.join(", ", string);
	}
}
