package edu.oosd.sabotage.core;

/**
 * An enumeration representing four predefined angles
 */
enum Angle {
	_0,
	_90,
	_180,
	_270
}

public interface Rotatable {
	
	 /**
	 * Gets this object's angle in degrees.
	 *
	 * @return      The angle in degrees
	 */
	Angle getRotation();

	/**
	 * Gets this object's angle in degrees.
	 *
	 * @return      The angle in degrees
	 */
	double getRotationAsDouble();

	/**
	 * Sets this object's angle in degrees.
	 */
	void setRotation(Angle angle);
	 
}
