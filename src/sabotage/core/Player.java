package sabotage.core;

import java.util.ArrayList;

import sabotage.core.Tile.TileMemento;
import sabotage.core.cards.Card;

public class Player {

	/* DECLARATIONS */
	private String name;	
	private PlayerColour color;	
	private boolean canUndo;
	private boolean isVillain;	
	private boolean isActive;
	private ArrayList<Card> hand;
	private int score; 
	
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
		this.canUndo = true;
		this.score = 0;
		this.isActive = true;
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
	 * Adds a card to this player's hand
	 */	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	/**
	 * Removes a card from this player's hand
	 */	
	public void removeCardFromHand(Card card) {
		hand.remove(card);
	}

	/**
	 * Set this player to be a villain
	 */	
	public void setAsVillain(boolean isVillain) {
		this.isVillain = isVillain;
	}
	
	/**
	 * Gets if this player can undo
	 *
	 * @return      True if player can undo; false otherwise
	 */
	public boolean canUndo() {
		return canUndo;
	}
	
	/**
	 * Called when player has used undo 
	 */
	public void setUndo(boolean canUndo) {
		this.canUndo = canUndo;
	}
	/**
	 * Gets if this player can make a turn
	 *
	 * @return      True if player can make a turn; false otherwise
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Sets this player's active state
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Replaces this player's hand with another hand 
	 */
	public void replaceHand(ArrayList<Card> hand) {
		this.hand.clear();
		this.hand = hand;
	}

	/**
	 * Changes this player's score
	 * @param amount	The amount to change the score by
	 */
	public void addToScore(int amount) {
		this.score += amount;
	}

	public int getScore() {
		return score;
	}
 
    public void restoreFromMemento(PlayerStateMemento memento) {
    	this.isActive = memento.getSavedState();
    }
    
    public static class PlayerStateMemento {
    	private boolean isActive;

        public PlayerStateMemento(Player player) {
        	this.isActive = player.isActive;
        }
 
        private boolean getSavedState() {
            return isActive;
        }
    }
}
