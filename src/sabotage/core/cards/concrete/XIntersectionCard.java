package sabotage.core.cards.concrete;

import sabotage.core.cards.PathCard;


public class XIntersectionCard extends PathCard {	
	public XIntersectionCard() {
		super(new boolean[] {true, true, true, true});
	}		

	@Override
	public String getDescription() {
		return "X-Intersection Card - Can be placed on an empty tile if the path connections align.";
	}	
}
