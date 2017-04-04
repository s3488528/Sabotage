package edu.oosd.sabotage.core;

public abstract class Card {
	
	/* DECLARATIONS */	
	private boolean active;
	
	private Tile parentTile;
	private Player parentPlayer;
	
	/* CONSTRUCTORS */
	/**
	 * Card class constructor
	 */
	public Card() {
		this.active = false;
		this.parentTile = null;
		this.parentPlayer = null;
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
		this.active = true;
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

	public String getPlacedText(String playerName, int x, int y) {
		return "";
	}
}
