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
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed an obstruction at position: " + x + ", " + y;
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "An obstruction cannot be placed at position: " + x + ", " + y;
	}	
}
