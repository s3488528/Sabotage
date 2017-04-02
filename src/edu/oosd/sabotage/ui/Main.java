package edu.oosd.sabotage.ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import edu.oosd.sabotage.controllers.GameController;
import edu.oosd.sabotage.controllers.GameListener;
import edu.oosd.sabotage.core.cards.GoalCard;
import edu.oosd.sabotage.core.cards.XIntersectionCard;

public class Main extends Application {

	/* Constants */
	public static final double WINDOW_WIDTH = 800;
	public static final double WINDOW_HEIGHT = 600;

	/* String Constants */
	public final String GAME_TITLE = "Sabotage";

	/* Declarations */
	Stage _window;
	Scene _menu, _game;
	GameController gameController;

	boolean gameCompleted = false;

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
		Spinner<Integer> boardWidthSpinner = new Spinner<Integer>(8, 16, 8);
		Spinner<Integer> boardHeightSpinner = new Spinner<Integer>(5, 10, 5);
		Spinner<Integer> playerCountSpinner = new Spinner<Integer>(3, 8, 4);

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
		optionsPane.add(new Label("Board Width (8 - 16):"), 0, 0);
		optionsPane.add(boardWidthSpinner, 1, 0);
		optionsPane.add(new Label("Board Height (5 - 10):"), 0, 1);
		optionsPane.add(boardHeightSpinner, 1, 1);
		optionsPane.add(new Label("Number of Players (3 - 8):"), 0, 2);
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

	private void showGameScene(int boardWidth, int boardHeight, int playerCount) {
		gameController = new GameController(boardWidth, boardHeight, 20);

		initialiseGameScene();
		_window.setScene(_game);

		/* Add anonymous listener */
		gameController.addListener(new GameListener() {

			@Override
			public void onTurnUpdate(String text) {
				topText.setText(text);
			}

			@Override
			public void onHandUpdate(ArrayList<ImageView> handImages) {
				hand.getChildren().clear();
				
				for (ImageView image : handImages) {
					hand.getChildren().add(image);
				}
			}

			@Override
			public void onLogUpdate(String logAppendText) {
				log.appendText(logAppendText);
			}

			@Override
			public void onBoardUpdate(ImageView[][] boardImages) {
				board.getChildren().clear();

				for (int y = 0; y < boardImages.length; y++) {
					for (int x = 0; x < boardImages[y].length; x++) {
						board.add(boardImages[y][x], x, y);
					}
				}
			}

		});


		gameController.initializeGame(playerCount);

		/* START THE GAME LOOP ON A BG THREAD: */
		gameController.update();
	}
	
	Text topText;
	TextArea log;
	GridPane board;
	HBox hand;
	
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
		BorderPane.setAlignment(board, Pos.CENTER);

		hand = new HBox(5);
		hand.setMinHeight(80);
		hand.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(hand, Pos.CENTER);

		BorderPane main = new BorderPane();
		main.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		main.setTop(topText);
		main.setRight(log);
		main.setCenter(board);
		main.setBottom(hand);

		_game = new Scene(main, WINDOW_WIDTH + 200, WINDOW_HEIGHT);
	}
}
