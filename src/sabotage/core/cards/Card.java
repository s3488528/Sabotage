package sabotage.core.cards;

import sabotage.core.Player;
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
	 * Places this card on the specified tile (used by Path & Action cards only)
	 * @param card	The tile to place the card on
	 */
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);
	}
	
	/**
	 * Places this card on the specified tile(used by Personal cards only)
	 * @param card	The tile to place the card on
	 */
	public void useCardOnPlayer(Player player) {
		logic.useCardOnPlayer(this, player);
	}

	/**
	 * Returns a message to display when this card is selected
	 */
	public abstract String getDescription();
}
