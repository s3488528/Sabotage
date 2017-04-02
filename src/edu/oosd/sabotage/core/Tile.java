package edu.oosd.sabotage.core;

public class Tile {

	/* DECLARATIONS */
	private Board parentBoard;
	private PathCard pathCard = null;

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	parentBoard	The board that contains this tile
	 */
	public Tile(Board parentBoard) {
		this.parentBoard = parentBoard;	
	}

	public PathCard getPathCard() {
		return pathCard;
	}

	public void setPathCard(PathCard pathCard) {
		this.pathCard = pathCard;
	}
}
