package sabotage.core.cards.concrete;

import sabotage.core.cards.PathCard;



public class StraightCard extends PathCard {	
	public StraightCard() {
		super(new boolean[] {true, false, true, false});
	}	
	
	@Override
	public String getDescription() {
		return "Straight Card - Can be placed on an empty tile if the path connections align.";
	}	
}
