package sabotage.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import sabotage.core.cards.ChanceCard;
import sabotage.core.cards.ClearCard;
import sabotage.core.cards.CornerCard;
import sabotage.core.cards.DeadEndCard;
import sabotage.core.cards.DemolishCard;
import sabotage.core.cards.GoalCard;
import sabotage.core.cards.HostageCard;
import sabotage.core.cards.ObstructionCard;
import sabotage.core.cards.RescueCard;
import sabotage.core.cards.StraightCard;
import sabotage.core.cards.TIntersectionCard;
import sabotage.core.cards.XIntersectionCard;

enum pathCardType {
	deadend,
	straight,
	corner,
	tintersection,
	xintersection
}

public class PathCardFactory extends AbstractCardFactory {

	private static List<pathCardType> VALUES = Collections.unmodifiableList(Arrays.asList(pathCardType.values()));
	private static int SIZE = VALUES.size();
	private static Random RANDOM = new Random();
	  	
	public Card createRandomCard() {
		Card tempCard = null;

		switch(getRandomCardType()) {
			case deadend:
				tempCard = new DeadEndCard();
				break;
			case straight:
				tempCard = new StraightCard();
				break;			
			case corner:
				tempCard = new CornerCard();
				break;
			case tintersection:
				tempCard = new TIntersectionCard();
				break;			
			case xintersection:
				tempCard = new XIntersectionCard();
				break;
		}
				
		return tempCard;
	}
	
	private static pathCardType getRandomCardType() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}

	public static PathCard createStartingCard(Tile parentTile) {
		PathCard tempCard = new XIntersectionCard();
		tempCard.setAsStartingCard();
		return tempCard;
	}

	public static PathCard createGoalCard(Tile parentTile, boolean isGoal) {
		PathCard tempCard = new GoalCard(isGoal);
		return tempCard;
	}
}
