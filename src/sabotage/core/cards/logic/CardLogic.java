package sabotage.core.cards.logic;

import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.cards.Card;

public abstract class CardLogic {
	public abstract void placeCardOnTile(Card card, Tile tile);
	public abstract void useCardOnPlayer(Card card, Player player);
}
