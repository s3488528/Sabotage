package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;



public class StraightCard extends PathCard {	
	public StraightCard() {
		super(new boolean[] {true, false, true, false});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	

	@Override
	public String getDescription() {
		return "Straight Card - Can be placed on an empty tile if the path connections align.";
	}	
}
