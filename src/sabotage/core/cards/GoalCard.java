package sabotage.core.cards;

import sabotage.core.PathCard;



public class GoalCard extends PathCard {
	
	private boolean isGoal;
	private boolean isRevealed = false;

	public GoalCard(boolean isGoal) {
		super(new boolean[] {true, true, true, true});
		this.isGoal = isGoal;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public boolean isRevealed() {
		return isRevealed;
	}

	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}

	@Override
	public String getPlacedText(String playerName, int x, int y) {
		return "";
	}	
	
	@Override
	public String getPlaceFailedText(int x, int y) {
		return "";
	}	
}
