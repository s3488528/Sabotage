package sabotage.controllers;

import java.util.ArrayList;

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
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

	/* Image Constants */
	Image EMPTYIMAGE = new Image("/sabotage/assets/images/empty.png");
	Image DEADENDIMAGE = new Image("/sabotage/assets/images/deadend.png");
	Image CORNERIMAGE = new Image("/sabotage/assets/images/corner.png");
	Image STRAIGHTIMAGE = new Image("/sabotage/assets/images/straight.png");
	Image TINTIMAGE = new Image("/sabotage/assets/images/tintersection.png");
	Image XINTIMAGE = new Image("/sabotage/assets/images/xintersection.png");
	Image DEMOLISHIMAGE = new Image("/sabotage/assets/images/demolish.png");
	Image HOSTAGEIMAGE = new Image("/sabotage/assets/images/hostage.png");
	Image RESCUEIMAGE = new Image("/sabotage/assets/images/rescue.png");
	Image GOALIMAGE = new Image("/sabotage/assets/images/goal.png");
	Image BACKIMAGE = new Image("/sabotage/assets/images/back.png");

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
		listener.onDeckTextUpdate("Deck: " + gc.getDeck().size());

		/* Display the board */
		displayBoard();

		/* Display the current player's hand */
		displayHand();

		if (player.isVillain()) {
			listener.onTurnStart(player.getName() + "'s turn (You are a villain).");
		} else {
			listener.onTurnStart(player.getName() + "'s turn.");
		}

		/* Output to log */
		listener.onLogUpdate("==========");
		listener.onLogUpdate("> It's " + player.getName() + "'s turn.");

	}

	/**
	 * Updates the UI board based on the GameContext's Board
	 */
	private void displayBoard() {
		Tile[][] tiles = gc.getBoard().getTiles();
		ArrayList<TileImageView> boardImages = new ArrayList<TileImageView>();

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				PathCard card = tiles[y][x].getPathCard();
				TileImageView temp;

				if (card == null) {
					temp = new TileImageView(EMPTYIMAGE, x, y);
				} else if (tiles[y][x].hasHostage()) {
					temp = new TileImageView(HOSTAGEIMAGE, x, y);
					temp.setRotate(0);
				} else if (card instanceof DeadEndCard) {
					temp = new TileImageView(DEADENDIMAGE, x, y);
					temp.setRotate(card.getRotationAsDouble());
				} else if (card instanceof CornerCard) {
					temp = new TileImageView(CORNERIMAGE, x, y);
					temp.setRotate(card.getRotationAsDouble());
				} else if (card instanceof StraightCard) {
					temp = new TileImageView(STRAIGHTIMAGE, x, y);
					temp.setRotate(card.getRotationAsDouble());
				} else if (card instanceof TIntersectionCard) {
					temp = new TileImageView(TINTIMAGE, x, y);
					temp.setRotate(card.getRotationAsDouble());
				} else if (card instanceof XIntersectionCard) {
					temp = new TileImageView(XINTIMAGE, x, y);
					temp.setRotate(card.getRotationAsDouble());
				} else {
					/* Should never reach here */
					temp = new TileImageView(BACKIMAGE, x, y);
				}

				temp.setOnMouseEntered(e -> temp.getScene().setCursor(Cursor.HAND));
				temp.setOnMouseExited(e -> temp.getScene().setCursor(Cursor.DEFAULT));
				temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						TileImageView image = (TileImageView) e.getSource();
						placeCurrentCard(image.getxPos(), image.getyPos());
					}
				});

				temp.setFitHeight(64);
				temp.setFitWidth(64);
				boardImages.add(temp);
			}
		}

		listener.onBoardUpdate(boardImages);
	}

	/**
	 * Updates the UI hand
	 */
	public void displayHand() {
		Player player = gc.getCurrentPlayer();
		ArrayList<ImageView> handImages = new ArrayList<ImageView>();

		for (Card card : player.getHand()) {
			ImageView temp = getImageViewOfCard(card);

			temp.setFitHeight(64);
			temp.setFitWidth(64);
			temp.setOnMouseEntered(e -> temp.getScene().setCursor(Cursor.HAND));
			temp.setOnMouseExited(e -> temp.getScene().setCursor(Cursor.DEFAULT));
			temp.setOnMouseClicked(e -> handCardClicked(temp, card));

			handImages.add(temp);
		}

		listener.onHandUpdate(handImages);
	}

	/**
	 * Updates the UI inspector with the GameContext's currently selected card
	 */
	public void displayCurrentCard() {
		Card card = gc.getCurrentCard();

		ImageView temp = new ImageView();
		temp = getImageViewOfCard(card);

		if (card instanceof PathCard) {
			temp.setRotate(((PathCard) card).getRotationAsDouble());
		}

		listener.onInspectorRefresh(temp);
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
	
	/**
	 * Creates a new ImageView based on the specified card
	 * @param card	The card to make an ImageView from
	 * @return		An ImageView representing the card
	 */
	public ImageView getImageViewOfCard(Card card) {
		ImageView temp;

		if (card instanceof DeadEndCard) {
			temp = new ImageView(DEADENDIMAGE);
		} else if (card instanceof CornerCard) {
			temp = new ImageView(CORNERIMAGE);
		} else if (card instanceof StraightCard) {
			temp = new ImageView(STRAIGHTIMAGE);
		} else if (card instanceof TIntersectionCard) {
			temp = new ImageView(TINTIMAGE);
		} else if (card instanceof XIntersectionCard) {
			temp = new ImageView(XINTIMAGE);
		} else if (card instanceof DemolishCard) {
			temp = new ImageView(DEMOLISHIMAGE);
		} else if (card instanceof HostageCard) {
			temp = new ImageView(HOSTAGEIMAGE);
		} else if (card instanceof RescueCard) {
			temp = new ImageView(RESCUEIMAGE);
		} else {
			/* Should never reach here */
			temp = new ImageView(BACKIMAGE);
		}

		return temp;
	}
}
