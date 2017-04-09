package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;



public class StraightCard extends PathCard {	
	public StraightCard() {
		super(new boolean[] {true, false, true, false});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	
	
	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a straight card at position: " + x + ", " + y;
	}	

	@Override
	public String getPlaceFailedText(int x, int y) {
		return "A straight card cannot be placed at position: " + x + ", " + y;
	}	
}
