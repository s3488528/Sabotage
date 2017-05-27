package sabotage.core.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import sabotage.core.cards.concrete.BackstabCard;
import sabotage.core.cards.concrete.ChanceCard;
import sabotage.core.cards.concrete.ClearCard;
import sabotage.core.cards.concrete.CornerCard;
import sabotage.core.cards.concrete.DeadEndCard;
import sabotage.core.cards.concrete.DemolishCard;
import sabotage.core.cards.concrete.HealCard;
import sabotage.core.cards.concrete.HostageCard;
import sabotage.core.cards.concrete.ObstructionCard;
import sabotage.core.cards.concrete.RescueCard;
import sabotage.core.cards.concrete.StraightCard;
import sabotage.core.cards.concrete.TIntersectionCard;
import sabotage.core.cards.concrete.XIntersectionCard;

enum cardType {
	deadend,
	straight,
	corner,
	tintersection,
	xintersection,
	hostage,
	rescue,
	obstruction,
	clear,
	demolish,
	chance,
	backstab,
	heal
}

public class GeneralCardFactory {
	private static List<cardType> VALUES = Collections.unmodifiableList(Arrays.asList(cardType.values()));
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
			case demolish:
				tempCard = new DemolishCard();
				break;
			case chance:
				tempCard = new ChanceCard();
				break;
			case backstab:
				tempCard = new BackstabCard();
				break;
			case heal:
				tempCard = new HealCard();
				break;
			default:
				break;
		}
				
		return tempCard;
	}
	
	private static cardType getRandomCardType() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
