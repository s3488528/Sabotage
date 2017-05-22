package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;

public class DeadEndCard extends PathCard {	
	public DeadEndCard() {
		super(new boolean[] {false, false, true, false});
	}	

	@Override
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	

	@Override
	public String getDescription() {
		return "Dead-End Card - Can be placed on an empty tile if the path connections align.";
	}	
}
