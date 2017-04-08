package edu.oosd.sabotage.core;

import edu.oosd.sabotage.core.cards.HostageCard;

public class Tile {

	/* DECLARATIONS */
	private Board parentBoard;
	private PathCard pathCard = null;
	private ActionCard actionCard = null;

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	parentBoard	The board that contains this tile
	 */
	public Tile(Board parentBoard) {
		this.parentBoard = parentBoard;	
	}

	/**
	 * Gets the path card associated with this tile
	 * @return	The path card
	 */
	public PathCard getPathCard() {
		return pathCard;
	}

	/**
	 * Sets the path card associated with this tile
	 * @param pathCard	The path card to set
	 */
	public void setPathCard(PathCard pathCard) {
		this.pathCard = pathCard;
	}

	/**
	 * Gets the action card associated with this tile
	 * @return	The path card
	 */
	public void setActionCard(ActionCard actionCard) {
		this.actionCard = actionCard;
	}

	/**
	 * Sets the action card associated with this tile
	 * @param pathCard	The path card to set
	 */
	public boolean hasHostage() {		
		return (actionCard instanceof HostageCard);
	}
}
