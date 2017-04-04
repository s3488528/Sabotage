package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.ActionCard;

public class DemolishCard extends ActionCard {
	public DemolishCard() {
		super();
	}	

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has demolished the tile at position: " + x + ", " + y;
	}	
}
