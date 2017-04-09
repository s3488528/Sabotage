package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;

public class DeadEndCard extends PathCard {	
	public DeadEndCard() {
		super(new boolean[] {false, false, true, false});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	
	
	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a dead-end card at position: " + x + ", " + y;
	}	

	@Override
	public String getPlaceFailedText(int x, int y) {
		return "A dead-end card cannot be placed at position: " + x + ", " + y;
	}	
}
