package sabotage.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

	static List<Angle> VALUES = Collections.unmodifiableList(Arrays.asList(Angle.values()));
	static int SIZE = VALUES.size();
	static Random RANDOM = new Random();

	/**
	 * Gets a random angle
	 * @return Returns an angle
	 */
	static Angle getRandomAngle() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
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
