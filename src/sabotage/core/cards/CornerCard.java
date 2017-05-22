package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;

public class CornerCard extends PathCard {	
	public CornerCard() {
		super(new boolean[] {true, false, false, true});
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	

	@Override
	public String getDescription() {
		return "Corner Card - Can be placed on an empty tile if the path connections align.";
	}	
	
}
