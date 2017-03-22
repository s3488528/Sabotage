package edu.oosd.sabotage.core;

public abstract class Card {
	
	/* DECLARATIONS */	
	private boolean active = false;
	
	private Tile parentTile = null;
	private Player parentPlayer = null;
	
	/* CONSTRUCTORS */
	/**
	 * Card class constructor specifying starting tile position
	 *
	 * @param	tile	The tile containing this card
	 */
	public Card(Tile tile) {
		this.parentTile = tile;
	}

	/* GETTERS & SETTERS */
	/**
	 * Gets this card's tile
	 *
	 * @return      The tile
	 */
	public Tile getTile() {
		return this.parentTile;
	}

	/**
	 * Sets this card's tile
	 */
	public void setTile(Tile tile) {
		this.parentTile = tile;
	}

	/**
	 * Gets if this card is in a player's hand
	 *
	 * @return      True if this card is a part of a player's hand
	 */
	public boolean isInHand() {
		return (parentPlayer == null);
	}

	/**
	 * Gets this card's owner
	 *
	 * @return      The player object if this card is in a hand. Null otherwise
	 * @see			Player
	 */
	public Player getParentPlayer() {
		return parentPlayer;
	}
	
	/**
	 * Gets if this card is active. A card is considered active if it has been played.
	 *
	 * @return      This card's active state
	 */
	public boolean isActive() {
		return active;
	}
}
