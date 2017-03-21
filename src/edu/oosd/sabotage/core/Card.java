package edu.oosd.sabotage.core;

public abstract class Card {
	
	private int x = 0;
	private int y = 0;
	
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
}
