package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;

public class CornerCard extends PathCard {	
	public CornerCard() {
		super(new boolean[] {true, false, false, true});
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	
	
	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a corner card at position: " + x + ", " + y;
	}	

	@Override
	public String getPlaceFailedText(int x, int y) {
		return "A corner card cannot be placed at position: " + x + ", " + y;
	}	
	
}
