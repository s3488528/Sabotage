package edu.oosd.sabotage.core;

import java.util.List;

public class Player {

	/* DECLARATIONS */
	private String name;
	
	private boolean isSaboteur;
	
	private List<Card> hand;
	
	/* CONSTRUCTORS */
	/**
	 * Player constructor specifying their name
	 *
	 * @param	name	This player's name
	 */
	public Player(String name) {
		this.name = name;
	}

	/* GETTERS & SETTERS */
	/**
	 * Gets this player's name
	 *
	 * @return      The player's name as String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets if this player is a saboteur
	 *
	 * @return      True if this player is a saboteur. False otherwise
	 */
	public boolean isSaboteur() {
		return isSaboteur;
	}

	/**
	 * Gets this player's hand of cards
	 *
	 * @return      A List of Card objects as the player's hand
	 */
	public List<Card> getHand() {
		return hand;
	}

	/* METHODS */
	/**
	 * Set this player to be a saboteur
	 */
	public void setAsSaboteur() {
		this.isSaboteur = true;
	}
}
