package sabotage.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sabotage.controllers.GameController;
import sabotage.controllers.JavaFXGameListener;

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
	
	private void showMenuScene() {
		initialiseMenuScene();
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
	GridPane board;
	HBox hand;
	Text roundText;
	Text turnText;
	VBox playerList;
	Text deckText;
	Button quit;
	ImageView inspector;
	Label cardDescription;
	Button rotateLeft;
	Button rotateRight;
	Button discard;
	Spinner<Integer> undoSpinner;
	HBox undo;
	
	private void showGameScene(int boardWidth, int boardHeight, int playerCount) {
		initialiseGameScene();
		_window.setScene(_game);

		gameController = new GameController();
		
		/* Hook new JavaFXListener to the game controller */
		gameController.addListener(new JavaFXGameListener(gameController, topText, roundText, turnText, playerList, board, 
				hand, inspector, cardDescription, rotateRight, rotateLeft, deckText, discard, undoSpinner, undo));

		gameController.initialiseGame(boardWidth, boardHeight, 20, playerCount);
	}
	
	private void initialiseGameScene() {
		topText = new Text("TOP LABEL");
		topText.setFont(new Font(24));
		BorderPane.setAlignment(topText, Pos.CENTER);
		
		roundText = new Text("Round: 0");
		roundText.maxHeight(20);
		roundText.setFont(new Font(16));
		roundText.setTextAlignment(TextAlignment.LEFT);
		
		turnText = new Text("Turn: 0");
		turnText.maxHeight(20);
		turnText.setFont(new Font(16));
		turnText.setTextAlignment(TextAlignment.LEFT);
		
		playerList = new VBox(5);
		
		deckText = new Text("");
		
		quit = new Button("Quit");
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				showMenuScene();
			}
        });
		
		VBox gameInformationPane = new VBox(5);
		gameInformationPane.setStyle("-fx-padding: 5;\n");
		gameInformationPane.setMinSize(250, 0);
		gameInformationPane.setMaxSize(250, WINDOW_HEIGHT - 100);
		gameInformationPane.setAlignment(Pos.CENTER);
		gameInformationPane.getChildren().addAll(quit, roundText, turnText, playerList, deckText);

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
		
		cardDescription = new Label();
		cardDescription.setMaxWidth(150);
		cardDescription.setWrapText(true);
		
		rotateRight = new Button("Rotate Right");
		rotateRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				rotateCurrentCard(true);
			}
        });
		rotateRight.setDisable(true);
		
		rotateLeft = new Button("Rotate Left"); 
		rotateLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				rotateCurrentCard(false);
			}
        });
		rotateLeft.setDisable(true);
		
		HBox rotateButtons = new HBox(5);
		rotateButtons.getChildren().addAll(rotateLeft, rotateRight);
		rotateButtons.setAlignment(Pos.CENTER);
		
		discard = new Button("Discard Card");
		discard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				discardCurrentCard();
			}
        });
		discard.setDisable(true);
		
		undoSpinner = new Spinner<Integer>(1, 3, 1);
		undoSpinner.setPrefWidth(50);

		Button undoButton = new Button("Undo");
		undoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				undoTurns(undoSpinner.getValue());
			}
        });
		
		undo = new HBox(5);
		undo.getChildren().addAll(undoButton, undoSpinner);
		undo.setAlignment(Pos.CENTER);
		
		VBox actionInformationPane = new VBox(5);
		actionInformationPane.getChildren().addAll(inspector, cardDescription, rotateButtons, discard, undo);
		actionInformationPane.setAlignment(Pos.CENTER);
		actionInformationPane.setMinSize(200, 0);
		actionInformationPane.setMaxSize(200, WINDOW_HEIGHT - 100);
		BorderPane.setAlignment(actionInformationPane, Pos.CENTER);
		
		BorderPane main = new BorderPane();
		main.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		main.setTop(topText);
		main.setRight(gameInformationPane);
		main.setCenter(board);
		main.setBottom(hand);
		main.setLeft(actionInformationPane);

		_game = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void discardCurrentCard() {		
		gameController.discardCurrentCard();
	}
	
	private void rotateCurrentCard(Boolean right) {
		gameController.rotateCurrentCard(right);
	}
	
	private void undoTurns(int turns) {
		gameController.undoTurns(turns);
	}
}
