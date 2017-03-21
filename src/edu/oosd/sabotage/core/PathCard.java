package edu.oosd.sabotage.core;

public class PathCard extends Card implements IRotatable {
	
	private double angle;

	/**
	 * Card class constructor specifying starting x and y tile positions
	 *
	 * @param	x	The starting x tile position
	 * @param	y	The starting y tile position
	 */
	public PathCard(int x, int y) {
		super(x, y);		
	}

	@Override
	public double getRotation() {
		return angle;
	}

	@Override
	public void setRotation(double angle) {
		this.angle = angle;		
	}
}
