package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;



public class XIntersectionCard extends PathCard {	
	public XIntersectionCard() {
		super(new boolean[] {true, true, true, true});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	

	@Override
	public String getDescription() {
		return "X-Intersection Card - Can be placed on an empty tile if the path connections align.";
	}	
}
