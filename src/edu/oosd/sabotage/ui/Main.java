package edu.oosd.sabotage.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import edu.oosd.sabotage.assets.*;

public class Main extends Application {
	
	/* Constants */
	public static final double WINDOW_WIDTH = 800;
	public static final double WINDOW_HEIGHT = 600;
	
	/* Declarations */
	Stage _window;
	Scene _menu, _game;
	
	public static void main(String[] args) {
		launch(args); 		/* start JavaFX window */
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		/* window */
		_window = stage;
		_window.setTitle(StringResources.GAME_TITLE);

		initialiseMenuScene();
		initialiseGameScene();
		
		/* display the window */
		_window.setScene(_menu);		
		_window.show();
	}
	
	private void initialiseMenuScene() {
		Text title = new Text("ISYS1084 - " + StringResources.GAME_TITLE);
		title.setFont(new Font(48));
		
		Button play = new Button("Play");
		play.setOnAction(e -> _window.setScene(_game));
		play.setPrefWidth(85);
		
		/* main grid */
		GridPane main = new GridPane();
		main.setPadding(new Insets(20));
		main.setVgap(10); 
		
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(50);
		
		main.getRowConstraints().add(row);
		main.add(title, 0, 1);
		main.add(play, 0, 2);

		_menu = new Scene(main, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void initialiseGameScene() {
		Label lbl = new Label("game");
		StackPane main2 = new StackPane(lbl);
		
		_game = new Scene(main2, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
}
