package edu.oosd.sabotage.controllers;

import java.util.ArrayList;

import edu.oosd.sabotage.ui.TileImageView;
import javafx.scene.image.ImageView;

public interface GameListener {
	void onTurnUpdate(String text);
	void onHandUpdate(ArrayList<ImageView> handImages);
	void onLogUpdate(String logAppendText);
	void onBoardUpdate(ArrayList<TileImageView> boardImages);
	void onDeckTextUpdate(String deckText);
	void onCardSelected();
	void onInspectorRefresh(ImageView card);
	void onCardPlaced();
}
