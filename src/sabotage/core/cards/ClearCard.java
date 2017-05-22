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
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has cleared the obstruction at position: " + x + ", " + y;
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "There is no obstruction to be cleared at position: " + x + ", " + y;
	}	
}
