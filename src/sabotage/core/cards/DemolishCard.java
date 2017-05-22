package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.Tile;

public class DemolishCard extends ActionCard {
	public DemolishCard() {
		super();
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(null);
		tile.setActionCard(null);
	}	

	@Override
	public String getDescription() {
		return "Demolish Card - Can be placed on any path card to demolish it. Demolishing a path card with a hostage will also remove the hostage.";
	}	
}
