package edu.oosd.sabotage.core;

public class GameContext {

	private Board board;
	private Player[] players;
	
	public GameContext(int boardWidth, int boardHeight, int deckCount) {
		this.board = new Board(boardWidth, boardHeight, deckCount);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void initializePlayers(int playerCount) {
		players = new Player[playerCount];
		
		for (int i = 0; i < playerCount; i++) {
			Player tempPlayer = new Player("Player " + (i + 1));			
			players[i] = tempPlayer;
		}
	}

	public String getPlayersAsString() {
		String[] string = new String[players.length];

		for (int i = 0; i < players.length; i++) {
			string[i] = players[i].getName();
		}
		
		return String.join(", ", string);
	}
}
