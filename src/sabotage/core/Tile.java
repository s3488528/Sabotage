package sabotage.core;

import sabotage.core.cards.HostageCard;

public class Tile {

	/* DECLARATIONS */
	private Board parentBoard;
	private PathCard pathCard = null;
	private ActionCard actionCard = null;
	private boolean active = false;

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

	/**
	 * Gets if this tile has a path card and is reachable from the starting tile
	 * @return	True if active, false otherwise
	 */
	public boolean isActive() {
		return active;
	}


	/**
	 * Sets if this tile has a path card and is reachable from the starting tile
	 * @param active True if active, false otherwise
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
