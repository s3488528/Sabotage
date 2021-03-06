package sabotage.core.cards.concrete;

import sabotage.core.cards.PathCard;



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

	public void reveal() {
		isRevealed = true;
	}

	@Override
	public String getDescription() {
		return "Goal Card";
	}	
}
