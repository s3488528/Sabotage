package edu.oosd.sabotage.core;

public class PathCard extends Card implements Rotatable {

	/* DECLARATIONS */
	private Angle angle;

	/* CONSTRUCTORS */
	/**
	 * Card class constructor
	 */
	public PathCard() {
		super();
	}
	
	/* GETTERS & SETTERS */
	@Override
	public Angle getRotation() {
		return angle;
	}

	@Override
	public void setRotation(Angle angle) {
		this.angle = angle;		
	}
}
