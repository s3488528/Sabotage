package sabotage.core.commands;

import java.util.ArrayList;

import sabotage.core.Player;
import sabotage.core.cards.Card;

public class DonateCommand implements Command {
	
	private Player donator;
	private Player donatee;
	
	private ArrayList<Card> donatorHand;
	private ArrayList<Card> donateeHand;
	
	private Card card;
	
	/***
	 * A command for when a player donates a card. 
	 * Also allows undo, which will reset both player's hands.
	 * @param donator	The player who is donating the card
	 * @param donatee	The player who is receiving the card
	 * @param card		The card to be discarded
	 */
	public DonateCommand(Player donator, Player donatee, Card card) {
		this.donator = donator;
		this.donatorHand = new ArrayList<Card>();
		for (Card c : donator.getHand()) {
			this.donatorHand.add(c); 
		}
		
		this.donatee = donatee;
		this.donateeHand = new ArrayList<Card>();
		for (Card c : donatee.getHand()) {
			this.donateeHand.add(c); 
		}
		
		this.card = card;
	}
	
	@Override
	public void execute() {
		donator.removeCardFromHand(card);
		donatee.addCardToHand(card);
	}
	
	@Override
	public void undo() {
		donator.replaceHand(donatorHand);
		donatee.replaceHand(donateeHand);
	}
}
