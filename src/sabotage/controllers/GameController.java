package sabotage.controllers;

import javafx.scene.image.ImageView;
import sabotage.core.Card;
import sabotage.core.GameContext;
import sabotage.core.Player;
import sabotage.core.Tile;

public class GameController {

	private GameContext gc;
	private GameListener listener;
	
	private int roundNo = 1;

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
		
		/* Initialise board */
		gc.initializeDeck(deckCount);
		
		/* Initialise players */
		gc.initializePlayers(playerCount);

		/* Shuffle players */
		gc.shufflePlayers();

		listener.onRoundUpdate(roundNo);
		
		/* Update the UI */
		displayTurn();
	}

	/**
	 * Updates the UI based on the game model data (GameContext).
	 * 
	 * @see GameContext
	 */
	private void displayTurn() {
		Player player = gc.getCurrentPlayer();
		
		/* Update deck */
		listener.onDeckUpdate(gc.getDeck().size());

		/* Display the board */
		displayBoard();

		/* Display the current player's hand */
		displayHand();

		listener.onTurnStart(gc.getPlayers(), player, gc.getTurnNo(), gc.getUndoCount());
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
	private void displayHand() {
		listener.onHandUpdate(gc.getCurrentPlayer().getHand());
	}

	/**
	 * Updates the UI inspector with the GameContext's currently selected card
	 */
	private void displayCurrentCard() {
		listener.onInspectorRefresh(gc.getCurrentCard());
	}

	/**
	 * Notifies the game model that a hand card has been clicked
	 * @param cardImage	The ImageView of the card being clicked
	 * @param card		The Card being clicked
	 */
	public void handCardClicked(Card card) {
		gc.setCurrentCard(card);
		
		listener.onCardSelected(gc.getPlayers(), gc.getCurrentPlayer());
		displayCurrentCard();
	}

	/**
	 * Rotates the currently selected card
	 */
	public void rotateCurrentCard(boolean right) {
		gc.rotateCurrentCard(right);
		displayCurrentCard();
	}

	/**
	 * Places the current card at position (x, y) on the board
	 */
	public void placeCurrentCard(int x, int y) {
		if (gc.validateCurrentCard(x, y)) {

			//listener.onLogUpdate(gc.getCurrentCard().getPlacedText(gc.getCurrentPlayer().getName(), x, y));

			gc.placeCurrentCard(x, y);
			gc.setCurrentCard(null);
			
			gc.validateActiveTiles();
			
			turnCompleted();
		} else {
			//listener.onLogUpdate("> " + gc.getCurrentCard().getPlaceFailedText(x, y));
		}
	}

	/**
	 * Discards the currently selected card
	 */
	public void discardCurrentCard() {
		gc.discardCurrentCard();
		turnCompleted();
	}

	/**
	 * Donates the currently selected card
	 */
	public void donateCurrentCard(Player player) {
		gc.donateCurrentCard(player);
		turnCompleted();
	}

	/**
	 * Cycle to the next player and update the UI
	 */
	private void turnCompleted() {
		if (gc.getBoard().getGoalReached()) {
			displayTurn();
			listener.onGameCompleted(false);
			return;
		} else if (allCardsPlayed()) {
			displayTurn();
			listener.onGameCompleted(true);
			return;
		} 
		
		if (gc.drawFromDeck()) {
			//listener.onLogUpdate(gc.getCurrentPlayer().getName() + " drew a card from the deck.");
		} else {
			//listener.onLogUpdate("> There are not more cards in the deck!");
		}

		do {
			gc.cyclePlayer();
		} while ((gc.getCurrentPlayer().getHand().isEmpty()));

		displayTurn();
	}
	
	private boolean allCardsPlayed() {
		if (!gc.getDeck().empty()) {
			return false;
		}
		
		for (Player player : gc.getPlayers()) {
			if (!player.getHand().isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public void undoTurns(Integer turns) {		
		gc.getCurrentPlayer().UndoUsed();
		
		for (int i = 0; i < turns; i++) {
			gc.undoTurn();
		}

		displayTurn();
	}
}
