package edu.oosd.sabotage.core;

enum Direction {
	N,
	E,
	S,
	W
}

public interface Connectable {
	
	 /**
	 * Checks if this object is connectable to another object
	 *
	 * @return      True if connection is valid. False otherwise
	 */
	boolean isConnectable(Connectable other, Direction direction);

	/**
	 * Gets this object's connections
	 */
	boolean[] getConnections(); 
}