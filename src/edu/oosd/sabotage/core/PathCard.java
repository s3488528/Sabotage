package edu.oosd.sabotage.core;

public abstract class PathCard extends Card implements Rotatable, Connectable {

	/* DECLARATIONS */
	private Angle angle = Angle._0;
	private boolean[] connections;

	/* CONSTRUCTORS */
	/**
	 * Card class constructor
	 */
	public PathCard(boolean[] connections) {
		super();
		
		this.connections = connections;
	}

	/* ROUTINES */
	@Override
	public boolean isConnectable(Connectable other, Direction direction) {
		if (other == null) {
			/* If 'other' is empty */
			return true;
		}
		
		boolean[] otherConnections = other.getConnections();
		
		switch(direction) {
			/* If 'other' is to the north of 'this' */
			case N:
				/* If 'this' has a north connection, and 'other' has a south connection */
				if (getConnections()[0] && otherConnections[2]) {
					return true;
				}
				break;

			/* If 'other' is to the east of 'this' */
			case E:
				/* If 'this' has a east connection, and 'other' has a west connection */
				if (getConnections()[1] && otherConnections[3]) {
					return true;
				}
				break;

			/* If 'other' is to the south of 'this' */
			case S:
				/* If 'this' has a south connection, and 'other' has a north connection */
				if (getConnections()[2] && otherConnections[0]) {
					return true;
				}
				break;

			/* If 'other' is to the wegetConnections()[3st of 'this' */
			case W:
				/* If 'this' has a west connection, and 'other' has a east connection */
				if (getConnections()[3] && otherConnections[1]) {
					return true;
				}
				break;
		}

		/* There is no connection */
		return false;
	}

	/* GETTERS & SETTERS */
	@Override
	public Angle getRotation() {
		return angle;
	}
	
	@Override
	public double getRotationAsDouble() {
		switch (angle) {
			case _0:
				return 0;
			case _90:
				return 90;
			case _180:
				return 180;
			case _270:
				return 270;
		}
		
		return 0;
	}

	@Override
	public void setRotation(Angle angle) {
		this.angle = angle;		
	}	
	
	/**
	 * Gets this object's connections, respecting the object's rotation
	 */
	@Override
	public boolean[] getConnections() {
		switch(angle) {
			case _0:
				return new boolean[] {connections[0], connections[1], connections[2], connections[3]};
			case _90:
				return new boolean[] {connections[3], connections[0], connections[1], connections[2]};
			case _180:
				return new boolean[] {connections[2], connections[3], connections[0], connections[1]};
			case _270:
				return new boolean[] {connections[1], connections[2], connections[3], connections[0]};
		}
		
		/* should never reach here as angle should always have a value */
		return null;
	}
}
