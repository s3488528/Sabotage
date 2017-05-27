package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.PathCard;
import sabotage.core.cards.logic.PathCardLogic;



public class StraightCard extends PathCard {	
	public StraightCard() {
		super(new PathCardLogic(), new boolean[] {true, false, true, false});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);
	}	

	@Override
	public String getDescription() {
		return "Straight Card - Can be placed on an empty tile if the path connections align.";
	}	
}
