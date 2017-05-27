package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.PathCard;
import sabotage.core.cards.logic.PathCardLogic;

public class CornerCard extends PathCard {	
	public CornerCard() {
		super(new PathCardLogic(), new boolean[] {true, false, false, true});
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
