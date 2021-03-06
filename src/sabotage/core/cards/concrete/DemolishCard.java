package sabotage.core.cards.concrete;

import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.DemolishCardLogic;

public class DemolishCard extends ActionCard {
	public DemolishCard() {
		super(new DemolishCardLogic());
	}
	
	@Override
	public String getDescription() {
		return "Demolish Card - Can be placed on any path card to demolish it. Demolishing a path card with a hostage will also remove the hostage.";
	}	
}
