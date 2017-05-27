package sabotage.core.cards.concrete;

import sabotage.core.Tile;
import sabotage.core.cards.PathCard;
import sabotage.core.cards.logic.PathCardLogic;



public class GoalCard extends PathCard {
	
	private boolean isGoal;
	private boolean isRevealed = false;

	public GoalCard(boolean isGoal) {
		super(new PathCardLogic(), new boolean[] {true, true, true, true});
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
		logic.placeCardOnTile(this, tile);
	}	

	@Override
	public String getDescription() {
		return "Goal Card";
	}	
}
