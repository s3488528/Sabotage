package sabotage.core.commands;

import java.util.ArrayList;
import java.util.Stack;

import sabotage.core.Card;
import sabotage.core.Player;

public class DonateCommand implements Command {
	
	private Player donator;
	private Player donatee;
	
	private ArrayList<Card> donatorHand;
	private ArrayList<Card> donateeHand;
	
	private Card card;
	
	/***
	 * A command for when a player donates a card. 
	 * Also allows undo, which will add the card back to the player's hand.
	 * @param player	The player who is discarding the card
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
	
	public void execute() {
		donator.removeCardFromHand(card);
		donatee.addCardToHand(card);
	}
	
	public void undo() {
		donator.replaceHand(donatorHand);
		donatee.replaceHand(donateeHand);
	}
}
