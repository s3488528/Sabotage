package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.PathCard;
import sabotage.core.cards.logic.PathCardLogic;



public class XIntersectionCard extends PathCard {	
	public XIntersectionCard() {
		super(new PathCardLogic(), new boolean[] {true, true, true, true});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);
	}		

	@Override
	public String getDescription() {
		return "X-Intersection Card - Can be placed on an empty tile if the path connections align.";
	}	
}
