package edu.oosd.sabotage.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum cardType {
	path,
	action
}

public class CardFactory {

	private static List<cardType> VALUES = Collections.unmodifiableList(Arrays.asList(cardType.values()));
	private static int SIZE = VALUES.size();
	private static Random RANDOM = new Random();
	  	
	public static Card createRandomCard() {
		Card tempCard = null;

		switch(getRandomCardType()) {
			case path:
				tempCard = new PathCard();
				break;
			case action:
				tempCard = new ActionCard();
				break;			
		}
				
		return tempCard;
	}
	
	private static cardType getRandomCardType() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
}
