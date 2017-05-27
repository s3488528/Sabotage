package sabotage.core.cards;

import sabotage.core.Tile;
import sabotage.core.cards.logic.CardLogic;

public abstract class Card {
	
	protected CardLogic logic;
	
	/* CONSTRUCTORS */
	/**
	 * Card class constructor 
	 */
	public Card(CardLogic logic) {
		this.logic = logic;
	}
	
	/**
	 * Places this card on the specified tile
	 * @param card	The tile to place the card on
	 */
	public abstract void placeCardOnTile(Tile tile);

	/**
	 * Returns a message to display when this card is selected
	 */
	public abstract String getDescription();
}
