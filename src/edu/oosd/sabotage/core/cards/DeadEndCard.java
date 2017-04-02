package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.PathCard;

public class DeadEndCard extends PathCard {	
	public DeadEndCard() {
		super(new boolean[] {false, false, true, false});
	}	
}
