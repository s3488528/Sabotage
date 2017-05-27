package sabotage.core.cards.logic;

import sabotage.core.Player;
import sabotage.core.Rotatable;
import sabotage.core.Tile;
import sabotage.core.cards.Card;
import sabotage.core.cards.PathCard;
import sabotage.core.cards.PathCardFactory;

public class ChanceCardLogic extends CardLogic {

	@Override
	public void placeCardOnTile(Card card, Tile tile) {
		PathCard tempCard = (PathCard) new PathCardFactory().createRandomCard();		
		tempCard.setRotation(Rotatable.getRandomAngle());
		tile.setPathCard(tempCard);	
	}	

	@Override
	public void useCardOnPlayer(Card card, Player player) {
		// Do nothing (Action cards should not be used on players)
	}

}
