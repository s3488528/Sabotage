package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.ActionCardLogic;
import sabotage.core.cards.logic.RemoveActionCardLogic;

public class ClearCard extends ActionCard {

	public ClearCard() {
		super(new RemoveActionCardLogic());
	}

	@Override
	public String getDescription() {
		return "Clear Card - Can be used on an obstruction to remove it.";
	}	
}
