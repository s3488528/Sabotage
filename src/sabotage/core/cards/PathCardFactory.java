package sabotage.core.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import sabotage.core.Tile;
import sabotage.core.cards.concrete.CornerCard;
import sabotage.core.cards.concrete.DeadEndCard;
import sabotage.core.cards.concrete.GoalCard;
import sabotage.core.cards.concrete.StraightCard;
import sabotage.core.cards.concrete.TIntersectionCard;
import sabotage.core.cards.concrete.XIntersectionCard;

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
	  	
	@Override
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
