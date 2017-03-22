package edu.oosd.sabotage.core;

public abstract class Card {
	
	/* DECLARATIONS */
	private int x = 0;
	private int y = 0;
	
	private boolean active = false;
	
	private Player parentPlayer = null;
	
	/* CONSTRUCTORS */
	/**
	 * Card class constructor specifying starting x and y tile positions
	 *
	 * @param	x	The starting x tile position
	 * @param	y	The starting y tile position
	 */
	public Card(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* GETTERS & SETTERS */
	/**
	 * Gets this card's x tile position
	 *
	 * @return      The x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets this card's x tile position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets this card's y tile position
	 *
	 * @return      The y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets this card's y tile position
	 */
	public void setY(int y) {
		this.y = y;
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
