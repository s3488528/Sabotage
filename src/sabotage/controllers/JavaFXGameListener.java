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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sabotage.core.Card;
import sabotage.core.PlayerColour;
import sabotage.core.PathCard;
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
	Image RESCUEIMAGE = new Image("/sabotage/assets/images/rescue.png");
	Image GOALIMAGE = new Image("/sabotage/assets/images/goal.png");
	Image BACKIMAGE = new Image("/sabotage/assets/images/back.png");

	/* Game Scene controls */
	Text topText;
	TextArea log;
	GridPane board;
	HBox hand;
	ImageView inspector;
	Button rotate;
	Text deckText;
	Button discard;
	
	// A reference to the GameController so we can handle events (clicks)
	GameController gameCon;

	public JavaFXGameListener(GameController gameCon, Text topText, TextArea log, GridPane board, 
			HBox hand, ImageView inspector, Button rotate, Text deckText, Button discard) {
		this.gameCon = gameCon;
		this.topText = topText;
		this.log = log;
		this.board = board;
		this.hand = hand;
		this.inspector = inspector;
		this.rotate = rotate;
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
	public void onLogUpdate(String logAppendText) {
		log.appendText(logAppendText + "\n");
	}

	@Override
	public void onBoardUpdate(Tile[][] tiles) {
		ArrayList<TileImageView> boardImages = new ArrayList<TileImageView>();

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				Tile tile = tiles[y][x];
				PathCard card = tile.getPathCard();
				TileImageView temp;

				if (card == null) {
					temp = new TileImageView(EMPTYIMAGE, x, y);
				} else if (tile.hasHostage()) {
					temp = new TileImageView(HOSTAGEIMAGE, x, y);
					temp.setRotate(0);
				} else if (card instanceof DeadEndCard) {		
					if (!tile.isActive()) {
						temp = new TileImageView(DEADENDINACTIVEIMAGE, x, y);
					} else {
						temp = new TileImageView(DEADENDIMAGE, x, y);
					}
					temp.setRotate(card.getRotationAsDouble());		
					
				} else if (card instanceof CornerCard) {
					if (!tile.isActive()) {
						temp = new TileImageView(CORNERINACTIVEIMAGE, x, y);
					} else {
						temp = new TileImageView(CORNERIMAGE, x, y);
					}
					temp.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof StraightCard) {
					if (!tile.isActive()) {
						temp = new TileImageView(STRAIGHTINACTIVEIMAGE, x, y);
					} else {
						temp = new TileImageView(STRAIGHTIMAGE, x, y);
					}
					temp.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof TIntersectionCard) {
					if (!tile.isActive()) {
						temp = new TileImageView(TINTINACTIVEIMAGE, x, y);
					} else {
						temp = new TileImageView(TINTIMAGE, x, y);
					}
					temp.setRotate(card.getRotationAsDouble());
					
				} else if (card instanceof XIntersectionCard) {
					if (!tile.isActive()) {
						temp = new TileImageView(XINTINACTIVEIMAGE, x, y);
					} else {
						temp = new TileImageView(XINTIMAGE, x, y);
					}
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
						gameCon.placeCurrentCard(image.getxPos(), image.getyPos());
					}
				});

				temp.setFitHeight(64);
				temp.setFitWidth(64);
				boardImages.add(temp);
			}
		}

		board.getChildren().clear();

		for (TileImageView image : boardImages) {
			board.add(image, image.getxPos(), image.getyPos());
		}
	}

	@Override
	public void onDeckUpdate(int deckCount) {
		deckText.setText("Deck: " + deckCount);
	}

	@Override
	public void onCardSelected() {
		rotate.setDisable(false);
		discard.setDisable(false);
		board.setDisable(false);
	}

	@Override
	public void onTurnStart(String playerName, PlayerColour playerColor, boolean isVillain) {
		if (!isVillain) {
			topText.setText(playerName + "'s turn.");
		} else {
			topText.setText(playerName + "'s turn (You are a villain).");
		}
		
		switch (playerColor) {
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

		rotate.setDisable(true);
		discard.setDisable(true);
		inspector.setImage(null);
		board.setDisable(true);
	}

	@Override
	public void onInspectorRefresh(Card card) {
		ImageView cardImage = new ImageView();
		cardImage = getImageViewOfCard(card);

		if (card instanceof PathCard) {
			cardImage.setRotate(((PathCard) card).getRotationAsDouble());
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
}
