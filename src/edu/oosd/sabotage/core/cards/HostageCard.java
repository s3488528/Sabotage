package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.ActionCard;

public class HostageCard extends ActionCard {

	public HostageCard() {
		super();
	}

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a hostage at position: " + x + ", " + y;
	}	
}
