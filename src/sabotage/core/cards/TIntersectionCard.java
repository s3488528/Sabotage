package sabotage.core.cards;

import sabotage.core.PathCard;



public class TIntersectionCard extends PathCard {	
	public TIntersectionCard() {
		super(new boolean[] {true, true, false, true});
	}	

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a T-intersection card at position: " + x + ", " + y;
	}	

	@Override
	public String getPlaceFailedText(int x, int y) {
		return "A T-intersection card cannot be placed at position: " + x + ", " + y;
	}	
}
