package edu.oosd.sabotage.core;

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

	public PathCard getPathCard() {
		return pathCard;
	}

	public void setPathCard(PathCard pathCard) {
		this.pathCard = pathCard;
	}

	public ActionCard getActionCard() {
		return actionCard;
	}

	public void setActionCard(ActionCard actionCard) {
		this.actionCard = actionCard;
	}
}
