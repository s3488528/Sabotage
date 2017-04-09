package sabotage.controllers;

import java.util.ArrayList;

import sabotage.ui.TileImageView;
import javafx.scene.image.ImageView;

public interface GameListener {
	void onHandUpdate(ArrayList<ImageView> handImages);
	void onLogUpdate(String logAppendText);
	void onBoardUpdate(ArrayList<TileImageView> boardImages);
	void onDeckTextUpdate(String deckText);
	void onCardSelected(ImageView cardImage);
	void onInspectorRefresh(ImageView card);
	void onTurnStart(String text);
}
