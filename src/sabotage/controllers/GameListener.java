package sabotage.controllers;

import java.util.ArrayList;

import sabotage.core.Player;
import sabotage.core.Tile;
import sabotage.core.cards.Card;

public interface GameListener {
	void onHandUpdate(ArrayList<Card> handCards);
	void onRoundUpdate(int round);
	void onBoardUpdate(Tile[][] tiles);
	void onDeckUpdate(int deckCount);
	void onCardSelected(ArrayList<Player> list, Player currentPlayer);
	void onInspectorRefresh(Card card);
	void onTurnStart(ArrayList<Player> playerList, Player currentPlayer, int turnNumber, int undoStackCount);
	void onGameCompleted(Boolean villainWins);
	void onTimerUpdate(int timeLeft);
}
