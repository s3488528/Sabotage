package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.ActionCardLogic;



public class HostageCard extends ActionCard {

	public HostageCard() {
		super(new ActionCardLogic());
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);
	}	

	@Override
	public String getDescription() {
		return "Hostage Card - Can be placed on any path card to block adjacent connections.";
	}	
}
