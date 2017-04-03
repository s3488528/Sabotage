package edu.oosd.sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Board {
	
	private Tile[][] tiles;
	private Stack<Card> deck;
	
	private Random random = new Random();

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	boardWidth	The number of tiles spanning across the board
	 * @param	boardHeight	The number of tiles spanning down the board
	 * @param	deckCount	The number of starting cards in the deck
	 */
	public Board(int boardWidth, int boardHeight, int deckCount) {
		tiles = new Tile[boardHeight][boardWidth];
		ArrayList<Tile> rightTiles = new ArrayList<Tile>();

		/* Instantiate all tiles on the board */
		for (int y = 0; y < boardHeight; y++) { 
			for (int x = 0; x < boardWidth; x++) { 
				tiles[y][x] = new Tile(this);
				
				if (x == boardWidth - 1) {
					rightTiles.add(tiles[y][x]);
				}
			}
		}
		
		/* Set a random tile on the left as the starting tile */
		Tile startingTile = tiles[random.nextInt(boardHeight)][0];
		startingTile.setPathCard(CardBuilder.createStartingCard(startingTile));
		deck = new Stack<Card>();

		/* Set 3 random tiles on the right as the goal tiles */		
		Collections.shuffle(rightTiles);
		rightTiles.get(0).setPathCard(CardBuilder.createGoalCard(rightTiles.get(0), true));
		rightTiles.get(1).setPathCard(CardBuilder.createGoalCard(rightTiles.get(1), false));
		rightTiles.get(2).setPathCard(CardBuilder.createGoalCard(rightTiles.get(2), false));
				
		/* Populate the deck with random cards */
		for (int i = 0; i < deckCount; i++) {			
			Card tempCard = CardBuilder.createRandomCard();
			deck.add(tempCard);
		}
	}

	public Stack<Card> getDeck() {
		return deck;
	}

	/**
	 * Gets all tiles contained in this board
	 *
	 * @return      All tiles as 2d Array
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Gets all tiles as string
	 *
	 * @return      All tiles as String
	 */
	public String getTilesAsString() {
		String str = "";
		
		for (int y = 0; y < tiles.length; y++) { 
			for (int x = 0; x < tiles[y].length; x++) { 
				str += "[" + x + ", " + y + "]";
			}
			str += System.getProperty("line.separator");
		}
		
		for (int i = 0; i < deck.size(); i++) { 
			str += i + ". " + deck.get(i).toString() + System.getProperty("line.separator");
		}
		
		return str;
	}

	/**
	 * Checks if a card is able to be placed
	 * @param validationCard The card to be validated
	 * @param x	The x position of the card
	 * @param y The y position of the card
	 * @return True if card can be placed, false otherwise
	 */
	public boolean validateCard(Card validationCard, int x, int y) {
		if (PathCard.class.isAssignableFrom(validationCard.getClass())) {
			if (getTiles()[y][x].getPathCard() != null) {
				return false;	
			}
			
			boolean checkN = true;
			boolean checkE = true;
			boolean checkS = true;
			boolean checkW = true;
			
			PathCard card = (PathCard) validationCard;
			
			if (y == 0) 					{ checkN = false; }
			if (y == tiles.length - 1) 		{ checkS = false; }
			if (x == 0) 					{ checkW = false; }
			if (x == tiles[0].length - 1) 	{ checkE = false; }
			
			if (checkN) {			
				Tile northTile = getTiles()[y - 1][x];
				
				if (!card.isConnectable(northTile.getPathCard(), Direction.N)) {
					return false;
				}
			}
			
			if (checkE) {			
				Tile eastTile = getTiles()[y][x + 1];
					
				if (!card.isConnectable(eastTile.getPathCard(), Direction.E)) {
					return false;
				}
			}
			
			if (checkS) {			
				Tile southTile = getTiles()[y + 1][x];
				
				if (!card.isConnectable(southTile.getPathCard(), Direction.S)) {
					return false;
				}
			}
			
			if (checkW) {			
				Tile westTile = getTiles()[y][x - 1];
				
				if (!card.isConnectable(westTile.getPathCard(), Direction.W)) {
					return false;
				}
			}
			
			return true;			
		} else if (ActionCard.class.isAssignableFrom(validationCard.getClass())) {
			
		}
		
		return false;
	}

	public void placeCard(Card currentCard, int x, int y) {
		Tile newTile = new Tile(this);
		
		if (currentCard.getClass() == PathCard.class) {
			newTile.setPathCard((PathCard) currentCard);				
		}
		
		tiles[y][x] = newTile;
	}
}
