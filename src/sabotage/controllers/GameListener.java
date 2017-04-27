package sabotage.controllers;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import sabotage.core.Card;
import sabotage.core.Player;
import sabotage.core.PlayerColour;
import sabotage.core.Tile;

public interface GameListener {
	void onHandUpdate(ArrayList<Card> handCards);
	void onRoundUpdate(int round);
	void onBoardUpdate(Tile[][] tiles);
	void onDeckUpdate(int deckCount);
	void onCardSelected(ArrayList<Player> list, Player currentPlayer);
	void onInspectorRefresh(Card card);
	void onTurnStart(ArrayList<Player> playerList, Player currentPlayer, int turnNumber);
	void onGameCompleted(Boolean villainWins);
}
