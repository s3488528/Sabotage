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

enum cardType {
	deadend,
	straight,
	corner,
	tintersection,
	xintersection,
	demolish,
	hostage,
	rescue,
	obstruction,
	clear,
	chance
}

public class CardBuilder {

	private static List<cardType> VALUES = Collections.unmodifiableList(Arrays.asList(cardType.values()));
	private static int SIZE = VALUES.size();
	private static Random RANDOM = new Random();
	  	
	public static Card createRandomCard() {
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
			case demolish:
				tempCard = new DemolishCard();
				break;	
			case hostage:
				tempCard = new HostageCard();
				break;	
			case rescue:
				tempCard = new RescueCard();
				break;
			case obstruction:
				tempCard = new ObstructionCard();
				break;
			case clear:
				tempCard = new ClearCard();
				break;
			case chance:
				tempCard = new ChanceCard();
				break;
		}
				
		return tempCard;
	}
	
	private static cardType getRandomCardType() {
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

	public static PathCard createRandomPathCard() {
		PathCard tempCard = null;
		Boolean success = false;
		
		while (!success) {
			switch(getRandomCardType()) {
				case deadend:
					tempCard = new DeadEndCard();
					success = true;
					break;
				case straight:
					tempCard = new StraightCard();
					success = true;
					break;			
				case corner:
					tempCard = new CornerCard();
					success = true;
					break;
				case tintersection:
					tempCard = new TIntersectionCard();
					success = true;
					break;			
				case xintersection:
					tempCard = new XIntersectionCard();
					success = true;
					break;
			default:
				break;	
			}		
		}
				
		return tempCard;
	}
}
