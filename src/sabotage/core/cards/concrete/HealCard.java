package sabotage.core.cards.concrete;

import sabotage.core.cards.PersonalCard;
import sabotage.core.cards.logic.HealCardLogic;

public class HealCard extends PersonalCard {

	public HealCard() {
		super(new HealCardLogic());
	}

	@Override
	public String getDescription() {
		return "Heal Card - Use this card on a backstabbed player to allow them to make their turn.";
	}

}
