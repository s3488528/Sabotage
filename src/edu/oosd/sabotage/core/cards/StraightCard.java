package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.PathCard;

public class StraightCard extends PathCard {	
	public StraightCard() {
		super(new boolean[] {true, false, true, false});
	}	

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a straight card at position: " + x + ", " + y;
	}	
}
