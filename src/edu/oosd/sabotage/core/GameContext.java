package edu.oosd.sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameContext {

	private Board board;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Card currentCard;
	
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
			
			for (int j = 0; j < 7; j++) {
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

	public Card getCurrentCard() {
		return currentCard;
	}
		
	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
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

	public void rotateCurrentCard() {
		PathCard card = (PathCard) currentCard;
		
		switch (card.getRotation()) {
			case _0:
				card.setRotation(Angle._90);
				break;
			case _90:
				card.setRotation(Angle._180);
				break;
			case _180:
				card.setRotation(Angle._270);
				break;
			case _270:
				card.setRotation(Angle._0);
				break;
		}
	}

	public boolean validateCurrentCard(int x, int y) {
		return board.validateCard(currentCard, x, y);
	}

	public void placeCurrentCard(int x, int y) {
		board.placeCard(currentCard, x, y);
		currentPlayer.getHand().remove(currentCard);
	}

	public void discardCurrentCard() {
		currentPlayer.getHand().remove(currentCard);
	}
	
	public void cyclePlayer() {
		int currentIndex = players.indexOf(currentPlayer);
	
		if (currentIndex < players.size() - 1) {
			currentIndex += 1;
		} else {
			currentIndex = 0;
		}
		
		currentPlayer = players.get(currentIndex);
	}
}
