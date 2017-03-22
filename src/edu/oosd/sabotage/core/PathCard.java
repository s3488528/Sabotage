package edu.oosd.sabotage.core;

public class PathCard extends Card implements IRotatable {

	/* DECLARATIONS */
	private double angle;

	/* CONSTRUCTORS */
	/**
	 * Card class constructor specifying starting tile position
	 *
	 * @param	tile	The tile holding this card
	 */
	public PathCard(Tile tile) {
		super(tile);		
	}

	/* GETTERS & SETTERS */
	@Override
	public double getRotation() {
		return angle;
	}

	@Override
	public void setRotation(double angle) {
		this.angle = angle;		
	}
}
