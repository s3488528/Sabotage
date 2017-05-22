package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;



public class TIntersectionCard extends PathCard {	
	public TIntersectionCard() {
		super(new boolean[] {true, true, false, true});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}

	@Override
	public String getDescription() {
		return "T-Intersection Card - Can be placed on an empty tile if the path connections align.";
	}	
}
