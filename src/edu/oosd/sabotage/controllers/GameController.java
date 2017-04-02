package edu.oosd.sabotage.controllers;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.oosd.sabotage.core.Card;
import edu.oosd.sabotage.core.GameContext;
import edu.oosd.sabotage.core.Player;
import edu.oosd.sabotage.core.Tile;
import edu.oosd.sabotage.core.cards.CornerCard;
import edu.oosd.sabotage.core.cards.DeadEndCard;
import edu.oosd.sabotage.core.cards.GoalCard;
import edu.oosd.sabotage.core.cards.StraightCard;
import edu.oosd.sabotage.core.cards.TIntersectionCard;
import edu.oosd.sabotage.core.cards.XIntersectionCard;

public class GameController {
	
	private GameContext gc;
	private GameListener listener;
	
	public GameController(int boardWidth, int boardHeight, int deckCount) {
		gc = new GameContext(boardWidth, boardHeight, deckCount);
	}
	
	public void initialiseGame(int playerCount) {
		Tile[][] tiles = gc.getBoard().getTiles();
		listener.onLogUpdate("> Generated board with " + tiles.length + " vertical tiles and " + tiles[0].length + " horizontal tiles.\n");
		
		/* DISPLAY BOARD */
		ImageView[][] boardImages = new ImageView[tiles.length][tiles[0].length];
		
		for (int y = 0; y < tiles.length; y++) { 
			for (int x = 0; x < tiles[y].length; x++) { 
				ImageView temp;
				
				if (tiles[y][x].getPathCard() == null) {
					temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/empty.png"));
				} else if (tiles[y][x].getPathCard() instanceof XIntersectionCard) {
					temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/xintersection.png"));
					listener.onLogUpdate("> Starting position set at: " + x + ", " + y + "\n");
				} else if (tiles[y][x].getPathCard() instanceof GoalCard) {
					temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/back.png"));
					listener.onLogUpdate("> Goal card set at: " + x + ", " + y + "\n");
				} else {
					/* Should never reach here */
					temp = null;
				}
				
				temp.setFitHeight(64);
				temp.setFitWidth(64);
				boardImages[y][x] = temp;
			}
		}
		
		listener.onBoardUpdate(boardImages);

		/* INIT PLAYERS */
		gc.initializePlayers(playerCount);		
		listener.onLogUpdate("> " + playerCount + " players (" + gc.getPlayersAsString() + ") have joined the game. Someone has been chosen as the saboteur!\n");	
		
		gc.shufflePlayers();	
		listener.onLogUpdate("> Players have been shuffled. " + gc.getCurrentPlayer().getName() + " goes first!\n");
	}
	
	public void addListener(GameListener listener) {
		this.listener = listener;
	}
	
	public void handCardClicked(ImageView temp, Card card) {
		temp.setVisible(false);
	}
	
	boolean turnUpdated = false;

	public void update() {
		if (!turnUpdated) {
			updateTurn();
			turnUpdated = true;
		}
	}

	public void updateTurn() {
		/* UPDATE TURN */
		Player player = gc.getCurrentPlayer();

		listener.onLogUpdate("==========\n");
		listener.onLogUpdate("> It's " + player.getName() + "'s turn.\n");
		
		if (player.isSaboteur()) {
			listener.onTurnUpdate(player.getName() + "'s turn (You are a saboteur).");
		} else {
			listener.onTurnUpdate(player.getName() + "'s turn.");			
		}

		/* DISPLAY PLAYER'S HAND */
		displayHand(player);
	}
	
	public void displayHand(Player player) {	
		ArrayList<ImageView> handImages = new ArrayList<ImageView>();
		
		for (Card card : player.getHand()) {
			ImageView temp;
			
			if (card instanceof DeadEndCard) {
				temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/xintersection.png"));
			} else if (card instanceof CornerCard) {
				temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/corner.png"));
			} else if (card instanceof StraightCard) {
				temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/straight.png"));
			} else if (card instanceof TIntersectionCard) {
				temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/tintersection.png"));
			} else if (card instanceof XIntersectionCard) {
				temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/xintersection.png"));
			} else {
				/* Should never reach here */
				temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/back.png"));
			}
			
			temp.setFitHeight(64);
			temp.setFitWidth(64);
			temp.setOnMouseEntered(e ->	temp.getScene().setCursor(Cursor.HAND));
			temp.setOnMouseExited(e ->	temp.getScene().setCursor(Cursor.DEFAULT));
			temp.setOnMouseClicked(e ->	handCardClicked(temp, card));			
			
			handImages.add(temp);
		}

		listener.onHandUpdate(handImages);
	}

	public void startGame() {
		/* START THE GAME LOOP ON A BG THREAD: */
		ExecutorService service = Executors.newCachedThreadPool();

		service.submit(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Platform.runLater(() -> update());
				}
			}
		});
		
		service.shutdown();
	}
	
}


