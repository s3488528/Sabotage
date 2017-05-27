package sabotage.core.cards.logic;

import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.cards.Card;

public class BackstabCardLogic extends CardLogic {

	@Override
	public void placeCardOnTile(Card card, Tile tile) {
		// Do nothing (Persnoal cards should not be placed on tiles)
	}

	@Override
	public void useCardOnPlayer(Card card, Player player) {
		player.setActive(false);
	}

}
