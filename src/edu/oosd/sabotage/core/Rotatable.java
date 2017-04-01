package edu.oosd.sabotage.core;

enum Angle {
	N,
	S,
	E,
	W
}

public interface Rotatable {
	
	 /**
	 * Gets this object's angle in degrees.
	 *
	 * @return      The angle in degrees
	 */
	Angle getRotation();

	/**
	 * Sets this object's angle in degrees.
	 */
	void setRotation(Angle angle);
 
}
