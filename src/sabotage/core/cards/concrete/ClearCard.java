package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.ActionCardLogic;

public class ClearCard extends ActionCard {

	public ClearCard() {
		super(new ActionCardLogic());
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(null, tile);/* Null to clear obstruction card */
	}	

	@Override
	public String getDescription() {
		return "Clear Card - Can be used on an obstruction to remove it.";
	}	
}
