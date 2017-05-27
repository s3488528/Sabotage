package sabotage.core.cards.logic;

import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.cards.Card;
import sabotage.core.cards.PathCard;

public class PathCardLogic extends CardLogic {

	@Override
	public void placeCardOnTile(Card card, Tile tile) {
		tile.setPathCard((PathCard) card);
	}

	@Override
	public void useCardOnPlayer(Card card, Player player) {
		// Do nothing (Path cards should not be used on players)
	}

}
