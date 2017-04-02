package edu.oosd.sabotage.controllers;

import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import edu.oosd.sabotage.core.GameContext;
import edu.oosd.sabotage.core.Tile;
import edu.oosd.sabotage.core.cards.*;

public class GameController {
	
	private GameContext gc;
	private GridPane board;
	private TextArea log;
	private HBox hand;
	
	public GameController(int boardWidth, int boardHeight, int deckCount) {
		gc = new GameContext(boardWidth, boardHeight, deckCount);
		board = null;
		log = null;
	}
		
	public void startGame(int playerCount) {
		Tile[][] tiles = gc.getBoard().getTiles();

		log.appendText("> Generating board with " + tiles.length + " vertical tiles and " + tiles[0].length + " horizontal tiles.\n");
		
		for (int y = 0; y < tiles.length; y++) { 
			for (int x = 0; x < tiles[y].length; x++) { 
				ImageView temp;
				
				if (tiles[y][x].getPathCard() == null) {
					temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/empty.png"));
				} else if (tiles[y][x].getPathCard() instanceof XIntersectionCard) {
					temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/xintersection.png"));
					log.appendText("> Starting position set at: " + x + ", " + y + "\n");
				} else if (tiles[y][x].getPathCard() instanceof GoalCard) {
					temp = new ImageView(new Image("/edu/oosd/sabotage/assets/images/back.png"));
					log.appendText("> Goal card set at: " + x + ", " + y + "\n");
				} else {
					/* Should never reach here */
					temp = null;
				}
				
				temp.setFitHeight(64);
				temp.setFitWidth(64);
				temp.setOnMouseEntered(e ->	temp.getScene().setCursor(Cursor.HAND));
				temp.setOnMouseExited(e ->	temp.getScene().setCursor(Cursor.DEFAULT));
				board.add(temp, x, y);
			}
		}
		

		gc.initializePlayers(playerCount);
		
		log.appendText("> " + playerCount + " players (" + gc.getPlayersAsString() + ") have joined the game!\n");
	}
	
	public void setBoardControl(GridPane board) {
		this.board = board;
	}
	
	public void setLogControl(TextArea log) {
		this.log = log;
	}
	
	public void setHandControl(HBox hand) {
		this.hand = hand;
	}
}
