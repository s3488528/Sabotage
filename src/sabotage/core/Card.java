package sabotage.core;

public abstract class Card {
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
