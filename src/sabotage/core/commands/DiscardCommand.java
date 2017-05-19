package sabotage.core.commands;

import java.util.ArrayList;
import java.util.Stack;

import sabotage.core.Card;
import sabotage.core.Player;

public class DiscardCommand implements Command {
	
	private Player player;
	private Card card;
	private ArrayList<Card> hand;
	
	/***
	 * A command for when a player discards a card. 
	 * Also allows undo, which will add the card back to the player's hand.
	 * @param player	The player who is discarding the card
	 * @param card		The card to be discarded
	 */
	public DiscardCommand(Player player, Card card) {
		this.player = player;
		
		this.hand = new ArrayList<Card>();
		for (Card c : player.getHand()) {
			this.hand.add(c); 
		}
		
		this.card = card;
	}
	
	public void execute() {
		player.removeCardFromHand(card);
	}
	
	public void undo() {
		player.replaceHand(hand);
	}
}
