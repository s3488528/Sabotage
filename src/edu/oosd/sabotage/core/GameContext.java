package edu.oosd.sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class GameContext {

	private Board board;
	private Stack<Card> deck;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Card currentCard;
	
	private Random random = new Random();

	/**
	 * Initializes a new board with the specified settings
	 * @param boardWidth	The number of tiles spanning across the board
	 * @param boardHeight	The number of tiles spanning down the board
	 */
	public void initializeBoard(int boardWidth, int boardHeight) {
		this.board = new Board(boardWidth, boardHeight);		
	}
	
	/**
	 * Initialises the deck with the amount of cards specified
	 * @param deckCount		The number of starting cards in the deck
	 */
	public void initializeDeck(int deckCount) {
		/* Populate the deck with random cards */
		deck = new Stack<Card>();		
		for (int i = 0; i < deckCount; i++) {			
			Card tempCard = CardBuilder.createRandomCard();
			deck.add(tempCard);
		}
	}

	/**
	 * Initialises players and adds cards to their hand
	 * @param playerCount		The number of players to initialise
	 */
	public void initializePlayers(int playerCount) {
		players = new ArrayList<Player>();
		
		for (int i = 0; i < playerCount; i++) {
			Player tempPlayer = new Player("Player " + (i + 1));
			
			for (int j = 0; j < 5; j++) {
				tempPlayer.addCardToHand(CardBuilder.createRandomCard());
			}
			
			players.add(tempPlayer);
		}
		
		players.get(random.nextInt(playerCount)).setAsVillain();
	}
	
	/**
	 * Shuffles the players
	 */
	public void shufflePlayers() {
		Collections.shuffle(players);
		currentPlayer = players.get(0);
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

	/**
	 * Places the current card on the board
	 */
	public boolean validateCurrentCard(int x, int y) {
		return board.validateCard(currentCard, x, y);
	}

	/**
	 * Places the current card on the board
	 */
	public void placeCurrentCard(int x, int y) {
		board.placeCard(currentCard, x, y);
		currentPlayer.getHand().remove(currentCard);
	}

	/**
	 * Removes the current card from the player's hand
	 */
	public void discardCurrentCard() {
		currentPlayer.getHand().remove(currentCard);
	}
	
	/**
	 * Cycles to the next player
	 */
	public void cyclePlayer() {
		int currentIndex = players.indexOf(currentPlayer);
	
		if (currentIndex < players.size() - 1) {
			currentIndex += 1;
		} else {
			/* If we've reached the end of the list, loop back to the start */
			currentIndex = 0;
		}
		
		currentPlayer = players.get(currentIndex);
	}
	
	/**
	 * Draws one card from the deck and gives it to the current player
	 * @return Returns true if deck is not empty; false otherwise
	 */
	public Boolean drawFromDeck() {
		if (deck.isEmpty()) {
			return false;
		}

		Card drawnCard = deck.pop();
		currentPlayer.addCardToHand(drawnCard);

		return true;
	}
	
	/**
	 * Gets the GameContext's board
	 * @return	The board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Gets the GameContext's deck
	 * @return	The deck
	 */
	public Stack<Card> getDeck() {
		return deck;
	}

	/**
	 * Gets the GameContext's current card
	 * @return	The current card
	 */
	public Card getCurrentCard() {
		return currentCard;
	}

	/**
	 * Sets the GameContext's current card
	 */
	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	/**
	 * Gets the GameContext's current player
	 * @return	The current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Gets the GameContext's list of players as a string
	 * @return	A list of players as a String
	 */
	public String getPlayersAsString() {
		String[] string = new String[players.size()];

		for (int i = 0; i < players.size(); i++) {
			string[i] = players.get(i).getName();
		}
		
		return String.join(", ", string);
	}

	/**
	 * Gets the GameContext's list of players
	 * @return	An ArrayList of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
