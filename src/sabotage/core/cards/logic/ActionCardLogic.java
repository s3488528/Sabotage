package sabotage.core.cards.logic;

import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.Card;

public class ActionCardLogic extends CardLogic {

	@Override
	public void placeCardOnTile(Card card, Tile tile) {
		tile.setActionCard((ActionCard) card);
	}

	@Override
	public void useCardOnPlayer(Card card, Player player) {
		// Do nothing (Action cards should not be used on players)
	}

}
