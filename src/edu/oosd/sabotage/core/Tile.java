package edu.oosd.sabotage.core;

public class Tile {

	/* DECLARATIONS */
	private Board parentBoard;

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	parentBoard	The board that contains this tile
	 */
	public Tile(Board parentBoard) {
		this.parentBoard = parentBoard;		
	}
}
