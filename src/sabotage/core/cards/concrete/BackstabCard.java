package sabotage.core.cards.concrete;

import sabotage.core.cards.PersonalCard;
import sabotage.core.cards.logic.BackstabCardLogic;

public class BackstabCard extends PersonalCard {

	public BackstabCard() {
		super(new BackstabCardLogic());
	}

	@Override
	public String getDescription() {
		return "Backstab Card - Use this card on a player to take them out of the game for one turn.";
	}
	
}
