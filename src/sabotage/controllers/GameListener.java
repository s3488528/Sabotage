package sabotage.controllers;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import sabotage.core.Card;
import sabotage.core.Tile;

public interface GameListener {
	void onHandUpdate(ArrayList<Card> handCards);
	void onLogUpdate(String logAppendText);
	void onBoardUpdate(Tile[][] tiles);
	void onDeckUpdate(int deckCount);
	void onCardSelected();
	void onInspectorRefresh(Card card);
	void onTurnStart(String playerName, boolean isVillain);
}
