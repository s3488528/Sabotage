package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.PathCard;

public class TIntersectionCard extends PathCard {	
	public TIntersectionCard() {
		super(new boolean[] {true, true, false, true});
	}	

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a T-intersection card at position: " + x + ", " + y;
	}	
}
