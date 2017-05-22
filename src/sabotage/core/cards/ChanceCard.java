package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.CardBuilder;
import sabotage.core.PathCard;
import sabotage.core.Rotatable;
import sabotage.core.Tile;

public class ChanceCard extends ActionCard {

	public ChanceCard() {
		super();
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		PathCard card = CardBuilder.createRandomPathCard();		
		card.setRotation(Rotatable.getRandomAngle());
		tile.setPathCard(card);	
	}	
	
	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return playerName + " has placed a chance card at position: " + x + ", " + y;
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "A chance card cannot be placed at position: " + x + ", " + y;
	}	
}
