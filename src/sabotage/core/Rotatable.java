package sabotage.core;

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
	abstract Angle getRotation();

	/**
	 * Gets this object's angle in degrees.
	 *
	 * @return      The angle in degrees
	 */
	abstract double getRotationAsDouble();

	/**
	 * Sets this object's angle in degrees.
	 */
	abstract void setRotation(Angle angle);
	 
}
