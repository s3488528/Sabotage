package sabotage.core;

public abstract class Card {
	public abstract String getPlacedText(String playerName, int x, int y);
	public abstract String getPlaceFailedText(int x, int y);
}
