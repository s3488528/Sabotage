package sabotage.controllers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sabotage.core.Card;
import sabotage.core.Player;
import sabotage.core.PlayerColour;
import sabotage.core.PathCard;
import sabotage.core.Tile;
import sabotage.core.cards.CornerCard;
import sabotage.core.cards.DeadEndCard;
import sabotage.core.cards.DemolishCard;
import sabotage.core.cards.GoalCard;
import sabotage.core.cards.HostageCard;
import sabotage.core.cards.RescueCard;
import sabotage.core.cards.StraightCard;
import sabotage.core.cards.TIntersectionCard;
import sabotage.core.cards.XIntersectionCard;
import sabotage.ui.TileStackPane;

public class JavaFXGameListener implements GameListener {

	/* Image Constants */
	Image EMPTYIMAGE = new Image("/sabotage/assets/images/empty.png");
	
	Image DEADENDIMAGE = new Image("/sabotage/assets/images/deadend.png");
	Image DEADENDINACTIVEIMAGE = new Image("/sabotage/assets/images/deadend_inactive.png");
	Image CORNERIMAGE = new Image("/sabotage/assets/images/corner.png");
	Image CORNERINACTIVEIMAGE = new Image("/sabotage/assets/images/corner_inactive.png");
	Image STRAIGHTIMAGE = new Image("/sabotage/assets/images/straight.png");
	Image STRAIGHTINACTIVEIMAGE = new Image("/sabotage/assets/images/straight_inactive.png");
	Image TINTIMAGE = new Image("/sabotage/assets/images/tintersection.png");
	Image TINTINACTIVEIMAGE = new Image("/sabotage/assets/images/tintersection_inactive.png");
	Image XINTIMAGE = new Image("/sabotage/assets/images/xintersection.png");
	Image XINTINACTIVEIMAGE = new Image("/sabotage/assets/images/xintersection_inactive.png");
	
	Image DEMOLISHIMAGE = new Image("/sabotage/assets/images/demolish.png");
	Image HOSTAGEIMAGE = new Image("/sabotage/assets/images/hostage.png");
	Image HOSTAGEICONIMAGE = new Image("/sabotage/assets/images/hostage_icon.png");
	Image RESCUEIMAGE = new Image("/sabotage/assets/images/rescue.png");
	Image GOALIMAGE = new Image("/sabotage/assets/images/goal.png");
	Image BACKIMAGE = new Image("/sabotage/assets/images/back.png");

	/* Game Scene controls */
	Text topText;
	Text roundText;
	Text turnText;
	VBox playerList;
	GridPane board;
	HBox hand;
	ImageView inspector;
	Button rotateRight;
	Button rotateLeft;
	Text deckText;
	Button discard;
	
	// A reference to the GameController so we can handle events (clicks)
	GameController gameCon;

	public JavaFXGameListener(GameController gameCon, Text topText, Text roundText, Text turnText, VBox playerList, GridPane board, 
			HBox hand, ImageView inspector, Button rotateRight, Button rotateLeft, Text deckText, Button discard) {
		this.gameCon = gameCon;
		this.topText = topText;
		this.roundText = roundText;
		this.turnText = turnText;
		this.playerList = playerList;
		this.board = board;
		this.hand = hand;
		this.inspector = inspector;
		this.rotateRight = rotateRight;
		this.rotateLeft = rotateLeft;
		this.deckText = deckText;
		this.discard = discard;
	}

	@Override
	public void onHandUpdate(ArrayList<Card> handCards) {
		ArrayList<ImageView> handImages = new ArrayList<ImageView>();

		for (Card card : handCards) {
			ImageView temp = getImageViewOfCard(card);

			temp.setFitHeight(64);
			temp.setFitWidth(64);
			temp.setOnMouseEntered(e -> temp.getScene().setCursor(Cursor.HAND));
			temp.setOnMouseExited(e -> temp.getScene().setCursor(Cursor.DEFAULT));
			temp.setOnMouseClicked(e -> gameCon.handCardClicked(card));

			handImages.add(temp);
		}

		hand.getChildren().clear();

		for (ImageView image : handImages) {
			hand.getChildren().add(image);
		}
	}

	@Override
	public void onRoundUpdate(int round) {
		roundText.setText("ROUND: " + round);
	}
	
	@Override
	public void onBoardUpdate(Tile[][] tiles) {
		ArrayList<TileStackPane> boardImages = new ArrayList<TileStackPane>();

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				Tile tile = tiles[y][x];
				PathCard card = tile.getPathCard();
				TileStackPane tsp = new TileStackPane(x, y);			
				ImageView image;

				if (card == null) {			
					image = new ImageView(EMPTYIMAGE);
				} else if (card instanceof DeadEndCard) {		
					if (!tile.isActive()) {
						image = new ImageView(DEADENDINACTIVEIMAGE);
					} else {
						image = new ImageView(DEADENDIMAGE);
					}
					image.setRotate(card.getRotationAsDouble());		
					
				} else if (card instanceof CornerCard) {
					if (!tile.isActive()) {
						image = new ImageView(CORNERINACTIVEIMAGE);
					} else {
						image = new ImageView(CORNERIMAGE);
					}
					image.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof StraightCard) {
					if (!tile.isActive()) {
						image = new ImageView(STRAIGHTINACTIVEIMAGE);
					} else {
						image = new ImageView(STRAIGHTIMAGE);
					}
					image.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof TIntersectionCard) {
					if (!tile.isActive()) {
						image = new ImageView(TINTINACTIVEIMAGE);
					} else {
						image = new ImageView(TINTIMAGE);
					}
					image.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof XIntersectionCard) {
					if (!tile.isActive()) {
						image = new ImageView(XINTINACTIVEIMAGE);
					} else {
						image = new ImageView(XINTIMAGE);
					}
					image.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof GoalCard) {
					if (((GoalCard) card).isRevealed()) {
						if (((GoalCard) card).isGoal()) {
							image = new ImageView(GOALIMAGE);
						} else {
							image = new ImageView(XINTIMAGE);
						}
					} else {
						image = new ImageView(BACKIMAGE);
					}					
				} else {					
					/* Should never reach here */
					image = new ImageView(BACKIMAGE);
				}
				
				image.setFitWidth(64);
				image.setFitHeight(64);
				
				tsp.getChildren().add(image);
				
				if (tile.hasHostage()) {
					ImageView hostageImage = new ImageView(HOSTAGEICONIMAGE);
					tsp.getChildren().add(hostageImage);
				}

				tsp.setOnMouseEntered(e -> tsp.getScene().setCursor(Cursor.HAND));
				tsp.setOnMouseExited(e -> tsp.getScene().setCursor(Cursor.DEFAULT));
				tsp.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						TileStackPane image = (TileStackPane) e.getSource();
						gameCon.placeCurrentCard(image.getxPos(), image.getyPos());
					}
				});
				
				boardImages.add(tsp);
			}
		}

		board.getChildren().clear();

		for (TileStackPane image : boardImages) {
			board.add(image, image.getxPos(), image.getyPos());
		}
	}

	@Override
	public void onDeckUpdate(int deckCount) {
		deckText.setText("Deck: " + deckCount);
	}

	@Override
	public void onCardSelected(ArrayList<Player> list, Player currentPlayer) {
		rebuildPlayerList(list, currentPlayer, true);
		rotateRight.setDisable(false);
		rotateLeft.setDisable(false);
		discard.setDisable(false);
		board.setDisable(false);
	}

	@Override
	public void onTurnStart(ArrayList<Player> list, Player currentPlayer, int turnNumber) {
		String playerName = currentPlayer.getName();
		PlayerColour playerColour = currentPlayer.getColor();
		
		if (!currentPlayer.isVillain()) {
			topText.setText(playerName + "'s turn.");
		} else {
			topText.setText(playerName + "'s turn (You are a villain).");
		}
		
		turnText.setText("TURN: " + turnNumber);
		
		switch (playerColour) {
			case red:
				topText.setFill(Color.RED);
				break;
			case blue:
				topText.setFill(Color.BLUE);
				break;
			case green:
				topText.setFill(Color.GREEN);
				break;
			case yellow:
				topText.setFill(Color.GOLD);
				break;
			case teal:
				topText.setFill(Color.TEAL);
				break;
			case orange:
				topText.setFill(Color.ORANGE);
				break;
		}
		
		rebuildPlayerList(list, currentPlayer, false);
		
		rotateRight.setDisable(true);
		rotateLeft.setDisable(true);
		discard.setDisable(true);
		inspector.setImage(null);
		board.setDisable(true);
	}
	
	private void rebuildPlayerList(ArrayList<Player> list, Player currentPlayer, boolean enableDonate) {
		playerList.getChildren().clear();
		
		for (Player player : list) {
			HBox temp = new HBox(5);
			Text name = new Text(player.getName());
			
			Button donate = new Button("Donate Card");
			donate.setOnMouseClicked(e -> gameCon.donateCurrentCard(player));
			
			if (enableDonate) {
				donate.setDisable(false);
			} else {
				donate.setDisable(true);
			}
			
			if (player.equals(currentPlayer)) {
				donate.setDisable(true); // Cannot donate to self
				
				name.setStyle("-fx-font-weight: bold");
				name.setFill(Color.WHITE);

				switch (player.getColor()) {
					case red:
						temp.setStyle("-fx-background-color: red;\n" +
									 "-fx-padding: 8;");
						break;
					case blue:
						temp.setStyle("-fx-background-color: blue;\n" +
								 "-fx-padding: 8;");
						break;
					case green:
						temp.setStyle("-fx-background-color: green;\n" +
								 "-fx-padding: 8;");
						break;
					case yellow:
						temp.setStyle("-fx-background-color: gold;\n" +
								 "-fx-padding: 8;");
						break;
					case teal:
						temp.setStyle("-fx-background-color: teal;\n" +
								 "-fx-padding: 8;");
						break;
					case orange:
						temp.setStyle("-fx-background-color: darkorange;\n" +
								 "-fx-padding: 8;");
						break;
				}
			} else {
				temp.setStyle("-fx-padding: 8;");
			}
			
			temp.getChildren().addAll(name, donate);			
			
			playerList.getChildren().add(temp);
		}
	}

	@Override
	public void onInspectorRefresh(Card card) {
		ImageView cardImage = new ImageView();
		cardImage = getImageViewOfCard(card);

		if (card instanceof PathCard) {
			cardImage.setRotate(((PathCard) card).getRotationAsDouble());
			rotateRight.setDisable(false);
			rotateLeft.setDisable(false);
		} else {
			rotateRight.setDisable(true);
			rotateLeft.setDisable(true);
		}

		inspector.setImage(cardImage.getImage());
		inspector.setRotate(cardImage.getRotate());
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

	@Override
	public void onGameCompleted(Boolean villainWins) {
		topText.setFill(Color.BLACK);
		
		if (villainWins) {
			topText.setText("THE VILLAINS HAVE WON!");
		} else {
			topText.setText("THE VILLAINS HAVE FAILED!");
		}
		
		// Disable all controls
		board.setDisable(true);
		hand.setDisable(true);
		rotateRight.setDisable(true);
		rotateLeft.setDisable(true);
		discard.setDisable(true);
	}
}
