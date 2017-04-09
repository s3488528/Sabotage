package sabotage.core;

public interface Connectable {
	
	 /**
	 * Checks if this object is connectable to another object
	 *
	 * @return      True if connection is valid. False otherwise
	 */
	abstract boolean isConnectable(Connectable other, Direction direction);

	/**
	 * Gets this object's connections
	 */
	abstract boolean[] getConnections(); 
}