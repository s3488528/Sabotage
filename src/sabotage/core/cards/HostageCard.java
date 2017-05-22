package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.Tile;



public class HostageCard extends ActionCard {

	public HostageCard() {
		super();
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setActionCard(this);
	}	

	@Override
	public String getDescription() {
		return "Hostage Card - Can be placed on any path card to block adjacent connections.";
	}	
}
