package edu.oosd.sabotage.core;

import java.util.Deque;

public class Board {
	
	private Tile[][] tiles;	

	private Deque deck;

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
		
		for (Tile[] i : tiles) { 
			for (Tile j : i) { 
				str += "[0]";
			}
			str += System.getProperty("line.separator");
		}
		
		return str;
	}
}
