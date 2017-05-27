package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.ActionCardLogic;



public class RescueCard extends ActionCard {

	public RescueCard() {
		super(new ActionCardLogic());
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(null, tile); /* Null to remove hostage action card */
	}	

	@Override
	public String getDescription() {
		return "Rescue Card - Can be used on a hostage to remove the hostage from the path.";
	}	
}
