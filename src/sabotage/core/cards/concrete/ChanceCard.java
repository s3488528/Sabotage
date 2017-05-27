package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.ChanceCardLogic;

public class ChanceCard extends ActionCard {

	public ChanceCard() {
		super(new ChanceCardLogic());
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);	
	}	
	
	@Override
	public String getDescription() {
		return "Chance Card - Can be placed on any empty tile. When placed this card will turn into a random path card.";
	}
}
