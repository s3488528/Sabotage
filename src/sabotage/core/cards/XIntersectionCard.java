package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;



public class XIntersectionCard extends PathCard {	
	public XIntersectionCard() {
		super(new boolean[] {true, true, true, true});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	
	
	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed an X-intersection card at position: " + x + ", " + y;
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "An X-intersection card cannot be placed at position: " + x + ", " + y;
	}	
}
