package sabotage.core;

import java.util.ArrayList;

public class Player {

	/* DECLARATIONS */
	private String name;
	
	private PlayerColour color;

	private boolean isVillain;
	
	private ArrayList<Card> hand;
	
	/* CONSTRUCTORS */
	/**
	 * Player constructor specifying their name
	 *
	 * @param	name	This player's name
	 */
	public Player(String name, PlayerColour color) {
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.color = color;
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
	 * Gets if this player is a villain
	 *
	 * @return      True if this player is a villain. False otherwise
	 */
	public boolean isVillain() {
		return isVillain;
	}

	/**
	 * Gets this player's hand of cards
	 *
	 * @return      A List of Card objects as the player's hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/**
	 * Gets this player's color
	 *
	 * @return      This player's color
	 */
	public PlayerColour getColor() {
		return color;
	}
	
	/* METHODS */
	/**
	 * Set this player to be a villain
	 */	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	public void setAsVillain() {
		this.isVillain = true;
	}
}
