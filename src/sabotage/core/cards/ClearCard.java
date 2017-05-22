package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.Tile;

public class ClearCard extends ActionCard {

	public ClearCard() {
		super();
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setActionCard(null); /* Clear obstruction card */
	}	

	@Override
	public String getDescription() {
		return "Clear Card - Can be used on an obstruction to remove it.";
	}	
}
