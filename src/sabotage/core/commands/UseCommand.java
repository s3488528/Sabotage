package sabotage.core.commands;

import java.util.ArrayList;

import sabotage.core.Player;
import sabotage.core.Player.PlayerStateMemento;
import sabotage.core.cards.Card;
import sabotage.core.cards.PersonalCard;

public class UseCommand implements Command {
	
	private Player player;
	private Card card;
	private ArrayList<Card> hand;
	
	private Player targetPlayer;
	private PlayerStateMemento targetPlayerMemento;
	
	/***
	 * A command for when a player uses a card on another player.
	 * @param player		The player using the card
	 * @param targetPlayer	The player the card is used against
	 * @param card			The card to be used
	 */
	public UseCommand(Player player, Player targetPlayer, PersonalCard card) {
		this.player = player;
		this.hand = new ArrayList<Card>();
		for (Card c : player.getHand()) {
			this.hand.add(c); 
		}
		
		this.targetPlayer = targetPlayer;
		this.targetPlayerMemento = new PlayerStateMemento(targetPlayer);
		
		this.card = card;		
	}
	
	@Override
	public void execute() {
		card.useCardOnPlayer(targetPlayer);
		player.removeCardFromHand(card);
	}
	
	@Override
	public void undo() {
		player.replaceHand(hand);
		targetPlayer.restoreFromMemento(targetPlayerMemento);
	}

}
