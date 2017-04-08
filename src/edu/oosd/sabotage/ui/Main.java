package edu.oosd.sabotage.ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import edu.oosd.sabotage.controllers.GameController;
import edu.oosd.sabotage.controllers.GameListener;

public class Main extends Application {

	/* Constants */
	public static final double WINDOW_WIDTH = 1200;
	public static final double WINDOW_HEIGHT = 600;

	/* String Constants */
	public final String GAME_TITLE = "Railway Sabotage";

	/* Declarations */
	Stage _window;
	Scene _menu, _game;
	GameController gameController;

	public static void main(String[] args) {
		launch(args); /* start JavaFX window */
	}

	@Override
	public void start(Stage stage) throws Exception {
		/* window */
		_window = stage;
		// _window.setResizable(false);
		_window.setTitle(GAME_TITLE);

		initialiseMenuScene();

		/* display the window */
		_window.setScene(_menu);
		_window.show();
	}

	private void initialiseMenuScene() {
		/* Spinners */
		Spinner<Integer> boardWidthSpinner = new Spinner<Integer>(6, 9, 8);
		Spinner<Integer> boardHeightSpinner = new Spinner<Integer>(4, 6, 5);
		Spinner<Integer> playerCountSpinner = new Spinner<Integer>(3, 6, 4);

		/* title */
		Text title = new Text("ISYS1084 - " + GAME_TITLE);
		title.setFont(new Font(48));

		/* start button */
		Button start = new Button("Start Game");
		start.setOnAction(e -> showGameScene(boardWidthSpinner.getValue(),
				boardHeightSpinner.getValue(), playerCountSpinner.getValue()));
		start.setPrefWidth(85);

		/* options pane */
		GridPane optionsPane = new GridPane();
		optionsPane.setHgap(10);
		optionsPane.setVgap(10);
		optionsPane.add(new Label("Board Width (6 - 9):"), 0, 0);
		optionsPane.add(boardWidthSpinner, 1, 0);
		optionsPane.add(new Label("Board Height (4 - 6):"), 0, 1);
		optionsPane.add(boardHeightSpinner, 1, 1);
		optionsPane.add(new Label("Number of Players (3 - 6):"), 0, 2);
		optionsPane.add(playerCountSpinner, 1, 2);

		/* main grid */
		GridPane main = new GridPane();
		main.setPadding(new Insets(20));
		main.setVgap(10);

		RowConstraints row = new RowConstraints();
		row.setPercentHeight(50);

		main.getRowConstraints().add(row);
		main.add(title, 0, 0);
		main.add(optionsPane, 0, 1);
		main.add(start, 0, 2);

		_menu = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	/* Game Scene controls */
	Text topText;
	TextArea log;
	GridPane board;
	HBox hand;
	ImageView inspector;
	Button rotate;
	Text deckText;
	Button discard;
	
	private void showGameScene(int boardWidth, int boardHeight, int playerCount) {
		initialiseGameScene();
		_window.setScene(_game);

		gameController = new GameController();
		
		/* Hook anonymous listener to the game controller */
		gameController.addListener(new GameListener() {
			@Override
			public void onHandUpdate(ArrayList<ImageView> handImages) {
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
			public void onBoardUpdate(ArrayList<TileImageView> boardImages) {
				board.getChildren().clear();

				for (TileImageView image : boardImages) {
					board.add(image, image.getxPos(), image.getyPos());
				}
			}

			@Override
			public void onDeckTextUpdate(String text) {
				deckText.setText(text);
			}

			@Override
			public void onCardSelected(ImageView cardImage) {
				for (Node card : hand.getChildren()) {
			        ((ImageView)card).setVisible(true);
				}
				
				cardImage.setVisible(false);
				
				rotate.setDisable(false);
				discard.setDisable(false);
				board.setDisable(false);
			}

			@Override
			public void onTurnStart(String text) {
				topText.setText(text);
				
				rotate.setDisable(true);
				discard.setDisable(true);
				inspector.setImage(null);
				board.setDisable(true);
			}

			@Override
			public void onInspectorRefresh(ImageView card) {
				inspector.setImage(card.getImage());
				inspector.setRotate(card.getRotate());
			}
		});

		gameController.initialiseGame(boardWidth, boardHeight, 20, playerCount);
	}
	
	private void initialiseGameScene() {
		topText = new Text("TOP LABEL");
		topText.setFont(new Font(24));
		BorderPane.setAlignment(topText, Pos.CENTER);

		log = new TextArea("LOG OUTPUT:\n");
		log.setMaxSize(250, WINDOW_HEIGHT - 100);
		log.setWrapText(true);
		log.setEditable(false);

		board = new GridPane();
		board.setHgap(5);
		board.setVgap(5);
		board.setAlignment(Pos.CENTER);
		board.setDisable(true);
		BorderPane.setAlignment(board, Pos.CENTER);

		hand = new HBox(5);
		hand.setMinHeight(80);
		hand.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(hand, Pos.CENTER);
		
		inspector = new ImageView();
		
		rotate = new Button("Rotate Card");
		rotate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				rotateCurrentCard();
			}
        });
		rotate.setDisable(true);
		
		discard = new Button("Discard Card");
		discard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				discardCurrentCard();
			}
        });
		discard.setDisable(true);
		
		deckText = new Text("");
		
		VBox detailsPane = new VBox(5);
		detailsPane.getChildren().addAll(inspector, rotate, discard, deckText);
		detailsPane.setAlignment(Pos.CENTER);
		detailsPane.setMinWidth(150);
		BorderPane.setAlignment(detailsPane, Pos.CENTER);
		
		BorderPane main = new BorderPane();
		main.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		main.setTop(topText);
		main.setRight(log);
		main.setCenter(board);
		main.setBottom(hand);
		main.setLeft(detailsPane);

		_game = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void discardCurrentCard() {		
		gameController.discardCurrentCard();
	}
	
	private void rotateCurrentCard() {
		gameController.rotateCurrentCard();
	}
}
