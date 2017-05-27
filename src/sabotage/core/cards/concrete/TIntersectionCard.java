package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.PathCard;
import sabotage.core.cards.logic.PathCardLogic;



public class TIntersectionCard extends PathCard {	
	public TIntersectionCard() {
		super(new PathCardLogic(), new boolean[] {true, true, false, true});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);
	}	

	@Override
	public String getDescription() {
		return "T-Intersection Card - Can be placed on an empty tile if the path connections align.";
	}	
}
