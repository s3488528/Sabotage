package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.PathCard;

public class DeadEndCard extends PathCard {	
	public DeadEndCard() {
		super(new boolean[] {false, false, true, false});
	}	

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a dead-end card at position: " + x + ", " + y;
	}	
}
