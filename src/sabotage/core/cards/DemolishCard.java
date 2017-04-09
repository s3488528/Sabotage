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
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has demolished the tile at position: " + x + ", " + y;
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "The tile at position: " + x + ", " + y + " cannot be demolished.";
	}	
}
