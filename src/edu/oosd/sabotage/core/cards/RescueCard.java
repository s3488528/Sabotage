package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.ActionCard;

public class RescueCard extends ActionCard {

	public RescueCard() {
		super();
	}

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has rescued the hostage at position: " + x + ", " + y;
	}	
}
