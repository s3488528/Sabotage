package edu.oosd.sabotage.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.oosd.sabotage.core.cards.*;

enum cardType {
	deadend,
	straight,
	corner,
	tintersection,
	xintersection
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
		}
				
		return tempCard;
	}
	
	private static cardType getRandomCardType() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
}
