package sabotage.core.cards;

import sabotage.core.ActionCard;



public class HostageCard extends ActionCard {

	public HostageCard() {
		super();
	}

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a hostage at position: " + x + ", " + y;
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "A hostage cannot be placed at position: " + x + ", " + y;
	}	
}
