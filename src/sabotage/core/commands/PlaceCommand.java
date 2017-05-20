package sabotage.core.commands;

import java.util.ArrayList;
import java.util.Stack;

import sabotage.core.ActionCard;
import sabotage.core.Card;
import sabotage.core.PathCard;
import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.Tile.TileMemento;

public class PlaceCommand implements Command {
	
	private Player player;
	private Card card;
	private ArrayList<Card> hand;
	
	private Tile tile;
	private TileMemento prevTile;
	
	/***
	 * A command for when a player discards a card. 
	 * Also allows undo, which will add the card back to the player's hand.
	 * @param player	The player who is discarding the card
	 * @param card		The card to be discarded
	 */
	public PlaceCommand(Player player, Tile tile, Card card) {
		this.player = player;
		
		this.hand = new ArrayList<Card>();
		for (Card c : player.getHand()) {
			this.hand.add(c); 
		}
		
		this.tile = tile;
		this.prevTile = tile.getCurrentAsMemento();
		
		this.card = card;
	}
	
	public void execute() {
		card.placeCardOnTile(tile);
		player.removeCardFromHand(card);
	}
	
	public void undo() {
		tile.restoreFromMemento(prevTile);
		player.replaceHand(hand);
	}
}
