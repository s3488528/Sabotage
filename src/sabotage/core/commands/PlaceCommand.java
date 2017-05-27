package sabotage.core.commands;

import java.util.ArrayList;

import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.Tile.TileMemento;
import sabotage.core.cards.Card;

public class PlaceCommand implements Command {
	
	private Player player;
	private Card card;
	private ArrayList<Card> hand;
	
	private Tile tile;
	private TileMemento prevTile;
	
	/***
	 * A command for when a player places a card on a tile. 
	 * Also allows undo, which will add the card back to the player's hand and remove it from the board.
	 * @param player	The player who is discarding the card
	 * @param tile		The tile being placed upon
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
	
	@Override
	public void execute() {
		card.placeCardOnTile(tile);
		player.removeCardFromHand(card);
	}
	
	@Override
	public void undo() {
		tile.restoreFromMemento(prevTile);
		player.replaceHand(hand);
	}
}
