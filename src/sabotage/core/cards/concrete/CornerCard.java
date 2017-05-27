package sabotage.core.cards.concrete;

import sabotage.core.cards.PathCard;

public class CornerCard extends PathCard {	
	public CornerCard() {
		super(new boolean[] {true, false, false, true});
	}

	@Override
	public String getDescription() {
		return "Corner Card - Can be placed on an empty tile if the path connections align.";
	}	
}
