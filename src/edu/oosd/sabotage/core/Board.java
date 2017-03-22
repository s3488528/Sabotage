package edu.oosd.sabotage.core;

public class Board {
	
	private Tile[][] tiles;	

	private Card[] deck;

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	boardWidth	The number of tiles spanning across the board
	 * @param	boardHeight	The number of tiles spanning down the board
	 * @param	deckCount	The number of starting cards in the deck
	 */
	public Board(int boardWidth, int boardHeight, int deckCount) {
		tiles = new Tile[boardWidth][boardHeight];		
	}

	/**
	 * Gets all tiles contained in this board
	 *
	 * @return      All tiles as 2d Array
	 */
	public Tile[][] getTiles() {
		return tiles;
	}
}
