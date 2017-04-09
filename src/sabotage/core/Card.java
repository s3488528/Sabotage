package sabotage.core;

public abstract class Card {
	/**
	 * Places this card on the specified tile
	 * @param card	The tile to place the card on
	 */
	public abstract void placeCardOnTile(Tile tile);

	/**
	 * Returns a message to display when this card is placed
	 * @param playerName	The player's name who placed the card
	 * @param x				The x-position of the tile
	 * @param y				The y-position of the tile
	 * @return				The message to display
	 */
	public abstract String getPlacedText(String playerName, int x, int y);

	/**
	 * Returns a message to display when this card is discarded
	 * @param x				The x-position of the tile
	 * @param y				The y-position of the tile
	 * @return				The message to display
	 */
	public abstract String getPlaceFailedText(int x, int y);
}
