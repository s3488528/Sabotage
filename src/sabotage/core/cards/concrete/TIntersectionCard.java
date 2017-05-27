package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.PathCard;



public class TIntersectionCard extends PathCard {	
	public TIntersectionCard() {
		super(new boolean[] {true, true, false, true});
	}	

	@Override
	public String getDescription() {
		return "T-Intersection Card - Can be placed on an empty tile if the path connections align.";
	}	
}
