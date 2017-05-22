package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.Tile;



public class RescueCard extends ActionCard {

	public RescueCard() {
		super();
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setActionCard(null); /* Remove hostage action card */
	}	

	@Override
	public String getDescription() {
		return "Rescue Card - Can be used on a hostage to remove the hostage from the path.";
	}	
}
