package sabotage.core.cards.concrete;

import sabotage.core.cards.PathCard;

public class DeadEndCard extends PathCard {	
	public DeadEndCard() {
		super(new boolean[] {false, false, true, false});
	}

	@Override
	public String getDescription() {
		return "Dead-End Card - Can be placed on an empty tile if the path connections align.";
	}	
}
