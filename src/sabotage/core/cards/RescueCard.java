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
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has rescued the hostage at position: " + x + ", " + y;
	}	

	@Override
	public String getPlaceFailedText(int x, int y) {
		return "The tile at position: " + x + ", " + y + " has no hostage to rescue.";
	}	
}
