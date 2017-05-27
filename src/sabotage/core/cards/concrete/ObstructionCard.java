package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.ActionCardLogic;

public class ObstructionCard extends ActionCard {

	public ObstructionCard() {
		super(new ActionCardLogic());
	}

	@Override
	public void placeCardOnTile(Tile tile) {
		logic.placeCardOnTile(this, tile);
	}	

	@Override
	public String getDescription() {
		return "Obstruction Card - Can be used on any empty tile to place an obstruction. An obstruction prevents path cards from being played on the tile.";
	}	
}
