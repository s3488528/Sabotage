package sabotage.core.cards;

import sabotage.core.ActionCard;
import sabotage.core.PathCard;
import sabotage.core.PathCardFactory;
import sabotage.core.Rotatable;
import sabotage.core.Tile;

public class ChanceCard extends ActionCard {

	public ChanceCard() {
		super();
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		PathCard card = (PathCard) new PathCardFactory().createRandomCard();		
		card.setRotation(Rotatable.getRandomAngle());
		tile.setPathCard(card);	
	}	
	
	@Override
	public String getDescription() {
		return "Chance Card - Can be placed on any empty tile. When placed this card will turn into a random path card.";
	}
}
