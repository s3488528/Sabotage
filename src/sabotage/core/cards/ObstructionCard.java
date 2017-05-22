package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.Tile;

public class ObstructionCard extends ActionCard {

	public ObstructionCard() {
		super();
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setActionCard(this);
	}	

	@Override
	public String getDescription() {
		return "Obstruction Card - Can be used on any empty tile to place an obstruction. An obstruction prevents path cards from being played on the tile.";
	}	
}
