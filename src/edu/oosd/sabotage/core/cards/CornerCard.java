package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.PathCard;

public class CornerCard extends PathCard {	
	public CornerCard() {
		super(new boolean[] {true, false, false, true});
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
