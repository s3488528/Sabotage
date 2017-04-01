package edu.oosd.sabotage.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import edu.oosd.sabotage.assets.*;
import edu.oosd.sabotage.controllers.GameController;

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
	
	
	public static void main(String[] args) {
		launch(args); 		/* start JavaFX window */
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		/* window */
		_window = stage;
		_window.setTitle(GAME_TITLE);

		initialiseMenuScene();
		
		/* display the window */
		_window.setScene(_menu);		
		_window.show();
	}
	
	private void initialiseMenuScene() {		
		/*Spinners */
		Spinner<Integer> boardWidthSpinner = new Spinner<Integer>(8, 16, 8);
		Spinner<Integer> boardHeightSpinner = new Spinner<Integer>(5, 10, 5);
		Spinner<Integer> playerCountSpinner = new Spinner<Integer>(3, 8, 4);
		
		/* title */
		Text title = new Text("ISYS1084 - " + GAME_TITLE);
		title.setFont(new Font(48));
		
		/* start button */
		Button start = new Button("Start Game");
		start.setOnAction(e -> showGameScene(boardWidthSpinner.getValue(), boardHeightSpinner.getValue(), playerCountSpinner.getValue()));
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
		gameController.initializePlayers(playerCount);
		
		initialiseGameScene();
		
		_window.setScene(_game);
		
		gameController.startGame(playerCount);
	}

	private void initialiseGameScene() {				
		Label lbl = new Label(gameController.getDetails());
		StackPane main2 = new StackPane(lbl);
		
		_game = new Scene(main2, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
}
