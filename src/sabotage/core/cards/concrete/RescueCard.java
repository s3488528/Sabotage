package sabotage.core.cards.concrete;

import sabotage.core.cards.ActionCard;
import sabotage.core.cards.logic.RemoveActionCardLogic;



public class RescueCard extends ActionCard {

	public RescueCard() {
		super(new RemoveActionCardLogic());
	}
	
	@Override
	public String getDescription() {
		return "Rescue Card - Can be used on a hostage to remove the hostage from the path.";
	}	
}
