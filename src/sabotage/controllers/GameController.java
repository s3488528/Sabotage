package sabotage.controllers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sabotage.core.Card;
import sabotage.core.GameContext;
import sabotage.core.PathCard;
import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.cards.CornerCard;
import sabotage.core.cards.DeadEndCard;
import sabotage.core.cards.DemolishCard;
import sabotage.core.cards.HostageCard;
import sabotage.core.cards.RescueCard;
import sabotage.core.cards.StraightCard;
import sabotage.core.cards.TIntersectionCard;
import sabotage.core.cards.XIntersectionCard;
import sabotage.ui.TileImageView;

public class GameController {

	private GameContext gc;
	private GameListener listener;

	/* Flags */
	boolean gameCompleted = false;
	
	/**
	 * GameController class Constructor
	 */
	public GameController() {
		gc = new GameContext();
	}
	
	/**
	 * Adds a GameListener to this GameController
	 * @param listener	The GameListener to add
	 */
	public void addListener(GameListener listener) {
		this.listener = listener;
	}

	/**
	 * Initializes a new game with the specified settings
	 * @param boardWidth	The number of tiles spanning across the board
	 * @param boardHeight	The number of tiles spanning down the board
	 * @param deckCount		The number of starting cards in the deck
	 * @param playerCount	The number of players in the game
	 */
	public void initialiseGame(int boardWidth, int boardHeight, int deckCount, int playerCount) {		
		/* Initialise board */
		gc.initializeBoard(boardWidth, boardHeight);
		Tile[][] tiles = gc.getBoard().getTiles();
		listener.onLogUpdate("> Generated board with " + tiles.length + " vertical tiles and " + tiles[0].length + " horizontal tiles.");
		
		/* Initialise board */
		gc.initializeDeck(deckCount);
		listener.onLogUpdate("> The deck has been created with " + deckCount + " cards.");
		
		/* Initialise players */
		gc.initializePlayers(playerCount);
		listener.onLogUpdate("> " + gc.getPlayers().size() + " players (" + gc.getPlayersAsString() + ") have joined the game. Someone has been chosen as the villain!");

		/* Shuffle players */
		gc.shufflePlayers();
		listener.onLogUpdate("> Players have been shuffled. " + gc.getCurrentPlayer().getName() + " goes first!");

		/* Update the UI */
		displayTurn();
	}

	/**
	 * Updates the UI based on the game model data (GameContext).
	 * 
	 * @see GameContext
	 */
	public void displayTurn() {
		Player player = gc.getCurrentPlayer();

		/* Update deck */
		listener.onDeckUpdate(gc.getDeck().size());

		/* Display the board */
		displayBoard();

		/* Display the current player's hand */
		displayHand();

		listener.onTurnStart(player.getName(), player.isVillain());

		/* Output to log */
		listener.onLogUpdate("==========");
		listener.onLogUpdate("> It's " + player.getName() + "'s turn.");
	}

	/**
	 * Updates the UI board based on the GameContext's Board
	 */
	private void displayBoard() {
		listener.onBoardUpdate(gc.getBoard().getTiles());
	}

	/**
	 * Updates the UI hand
	 */
	public void displayHand() {
		listener.onHandUpdate(gc.getCurrentPlayer().getHand());
	}

	/**
	 * Updates the UI inspector with the GameContext's currently selected card
	 */
	public void displayCurrentCard() {
		listener.onInspectorRefresh(gc.getCurrentCard());
	}

	/**
	 * Notifies the game model that a hand card has been clicked
	 * @param cardImage	The ImageView of the card being clicked
	 * @param card		The Card being clicked
	 */
	public void handCardClicked(ImageView cardImage, Card card) {
		gc.setCurrentCard(card);

		listener.onCardSelected(cardImage);
		displayCurrentCard();
	}

	/**
	 * Rotates the currently selected card
	 */
	public void rotateCurrentCard() {
		gc.rotateCurrentCard();
		displayCurrentCard();
	}

	/**
	 * Places the current card at position (x, y) on the board
	 */
	public void placeCurrentCard(int x, int y) {
		if (gc.validateCurrentCard(x, y)) {

			listener.onLogUpdate(gc.getCurrentCard().getPlacedText(gc.getCurrentPlayer().getName(), x, y));

			gc.placeCurrentCard(x, y);
			gc.setCurrentCard(null);

			turnCompleted();
		} else {
			listener.onLogUpdate("> " + gc.getCurrentCard().getPlaceFailedText(x, y));
		}
	}

	/**
	 * Discards the currently selected card
	 */
	public void discardCurrentCard() {
		listener.onLogUpdate(gc.getCurrentPlayer().getName() + " discarded a card.");

		gc.discardCurrentCard();
		turnCompleted();
	}

	/**
	 * Cycle to the next player and update the UI
	 */
	public void turnCompleted() {
		if (gc.drawFromDeck()) {
			listener.onLogUpdate(gc.getCurrentPlayer().getName() + " drew a card from the deck.");
		} else {
			listener.onLogUpdate("> There are not more cards in the deck!");
		}
		
		gc.cyclePlayer();
		displayTurn();
	}
}
