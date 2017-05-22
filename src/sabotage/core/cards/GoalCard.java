package sabotage.core.cards;

import sabotage.core.PathCard;
import sabotage.core.Tile;



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
	public void placeCardOnTile(Tile tile) {
		tile.setPathCard(this);
	}	

	@Override
	public String getDescription() {
		return "Goal Card";
	}	
}
