package edu.oosd.sabotage.core.cards;

import edu.oosd.sabotage.core.PathCard;

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

}
