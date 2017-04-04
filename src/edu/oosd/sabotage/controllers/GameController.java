package edu.oosd.sabotage.controllers;

import java.util.ArrayList;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.oosd.sabotage.core.ActionCard;
import edu.oosd.sabotage.core.Card;
import edu.oosd.sabotage.core.PathCard;
import edu.oosd.sabotage.core.GameContext;
import edu.oosd.sabotage.core.Player;
import edu.oosd.sabotage.core.Tile;
import edu.oosd.sabotage.core.cards.*;
import edu.oosd.sabotage.ui.TileImageView;

public class GameController {

	/* Image Constants */
	Image EMPTYIMAGE = new Image("/edu/oosd/sabotage/assets/images/empty.png");
	Image DEADENDIMAGE = new Image(
			"/edu/oosd/sabotage/assets/images/deadend.png");
	Image CORNERIMAGE = new Image("/edu/oosd/sabotage/assets/images/corner.png");
	Image STRAIGHTIMAGE = new Image(
			"/edu/oosd/sabotage/assets/images/straight.png");
	Image TINTIMAGE = new Image(
			"/edu/oosd/sabotage/assets/images/tintersection.png");
	Image XINTIMAGE = new Image(
			"/edu/oosd/sabotage/assets/images/xintersection.png");
	Image DEMOLISHIMAGE = new Image(
			"/edu/oosd/sabotage/assets/images/demolish.png");
	Image GOALIMAGE = new Image("/edu/oosd/sabotage/assets/images/goal.png");
	Image BACKIMAGE = new Image("/edu/oosd/sabotage/assets/images/back.png");

	private GameContext gc;
	private GameListener listener;

	/* Flags */
	boolean gameCompleted = false;

	public GameController(int boardWidth, int boardHeight, int deckCount) {
		gc = new GameContext(boardWidth, boardHeight, deckCount);
	}

	public void initialiseGame(int playerCount) {
		Tile[][] tiles = gc.getBoard().getTiles();
		listener.onLogUpdate("> Generated board with " + tiles.length
				+ " vertical tiles and " + tiles[0].length
				+ " horizontal tiles.\n");

		/* DISPLAY BOARD */
		ArrayList<TileImageView> boardImages = new ArrayList<TileImageView>();

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				PathCard card = tiles[y][x].getPathCard();
				TileImageView temp;

				if (card == null) {
					temp = new TileImageView(EMPTYIMAGE, x, y);
				} else if (card instanceof XIntersectionCard) {
					temp = new TileImageView(XINTIMAGE, x, y);
					listener.onLogUpdate("> Starting position set at: " + x + ", " + y + "\n");
				} else if (card instanceof GoalCard) {
					temp = new TileImageView(BACKIMAGE, x, y);
					listener.onLogUpdate("> Goal card set at: " + x + ", " + y + "\n");
				} else {
					/* Should never reach here */
					temp = null;
				}

				temp.setFitHeight(64);
				temp.setFitWidth(64);
				boardImages.add(temp);
			}
		}

		listener.onBoardUpdate(boardImages);

		/* INIT PLAYERS */
		gc.initializePlayers(playerCount);
		listener.onLogUpdate("> "
				+ playerCount
				+ " players ("
				+ gc.getPlayersAsString()
				+ ") have joined the game. Someone has been chosen as the saboteur!\n");

		gc.shufflePlayers();
		listener.onLogUpdate("> Players have been shuffled. "
				+ gc.getCurrentPlayer().getName() + " goes first!\n");

		displayTurn();
	}

	private void displayBoard(Tile[][] tiles) {
		ArrayList<TileImageView> boardImages = new ArrayList<TileImageView>();

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				PathCard card = tiles[y][x].getPathCard();
				TileImageView temp;

				if (card == null) {
					temp = new TileImageView(EMPTYIMAGE, x, y);
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

				temp.setFitHeight(64);
				temp.setFitWidth(64);
				boardImages.add(temp);
			}
		}

		listener.onBoardUpdate(boardImages);
	}

	public void addListener(GameListener listener) {
		this.listener = listener;
	}

	public void handCardClicked(ImageView temp, Card card) {
		gc.setCurrentCard(card);
		listener.onCardSelected();
		temp.setVisible(false);
		displayInspector();
	}

	/**
	 * Cycle to the next player and update the UI
	 */
	public void turnCompleted() {
		gc.cyclePlayer();
		displayTurn();
	}

	/**
	 * Updates the UI based on the game model data (GameContext).
	 * 
	 * @see GameContext
	 */
	public void displayTurn() {
		Player player = gc.getCurrentPlayer();

		listener.onLogUpdate("==========\n");
		listener.onLogUpdate("> It's " + player.getName() + "'s turn.\n");

		if (player.isSaboteur()) {
			listener.onTurnUpdate(player.getName()
					+ "'s turn (You are a saboteur).");
		} else {
			listener.onTurnUpdate(player.getName() + "'s turn.");
		}

		listener.onDeckTextUpdate("Deck: " + gc.getBoard().getDeck().size());
		
		/* DISPLAY BOARD */
		displayBoard(gc.getBoard().getTiles());
		
		/* DISPLAY PLAYER'S HAND */
		displayHand(player);
	}

	public void displayHand(Player player) {
		ArrayList<ImageView> handImages = new ArrayList<ImageView>();

		for (Card card : player.getHand()) {
			ImageView temp = getImageViewOfCard(card);

			temp.setFitHeight(64);
			temp.setFitWidth(64);
			temp.setOnMouseEntered(e -> temp.getScene().setCursor(Cursor.HAND));
			temp.setOnMouseExited(e -> temp.getScene()
					.setCursor(Cursor.DEFAULT));
			temp.setOnMouseClicked(e -> handCardClicked(temp, card));

			handImages.add(temp);
		}

		listener.onHandUpdate(handImages);
	}

	public void rotateCurrentCard() {
		gc.rotateCurrentCard();
		displayInspector();
	}

	public void displayInspector() {
		Card card = gc.getCurrentCard();

		ImageView temp = new ImageView();

		temp = getImageViewOfCard(card);
		
		if (card instanceof PathCard) {
			temp.setRotate(((PathCard) card).getRotationAsDouble());
		}

		listener.onInspectorRefresh(temp);
	}

	public void placeCurrentCard(int x, int y) {
		// VALIDATE CARD PLACEMENT
		if (gc.validateCurrentCard(x, y)) {
			
			gc.placeCurrentCard(x, y);
			gc.setCurrentCard(null);
			listener.onCardPlaced();

			turnCompleted();
		} else {
			listener.onLogUpdate("That card cannot be placed at " + x + ", "
					+ y + "\n");
		}
	}

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
		} else {
			/* Should never reach here */
			temp = new ImageView(BACKIMAGE);
		}

		return temp;
	}
}
