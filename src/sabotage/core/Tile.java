package sabotage.core;

import sabotage.core.cards.HostageCard;
import sabotage.core.cards.ObstructionCard;

public class Tile {

	/* DECLARATIONS */
	private Board parentBoard;
	private PathCard pathCard = null;
	private ActionCard actionCard = null;
	private boolean active = false;

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	parentBoard	The board that contains this tile
	 */
	public Tile(Board parentBoard) {
		this.parentBoard = parentBoard;	
	}

	/**
	 * Gets the path card associated with this tile
	 * @return	The path card
	 */
	public PathCard getPathCard() {
		return pathCard;
	}

	/**
	 * Sets the path card associated with this tile
	 * @param pathCard	The path card to set
	 */
	public void setPathCard(PathCard pathCard) {
		this.pathCard = pathCard;
	}

	/**
	 * Removed the path card associated with this tile
	 */
	public void removePathCard() {
		this.pathCard = null;
	}
	
	/**
	 * Gets the action card associated with this tile
	 * @return	The path card
	 */
	public void setActionCard(ActionCard actionCard) {
		this.actionCard = actionCard;
	}

	/**
	 * Sets the action card associated with this tile
	 * @param pathCard	The path card to set
	 */
	public boolean hasHostage() {		
		return (actionCard instanceof HostageCard);
	}

	/**
	 * Checks if this tile has an obstruction 
	 */
	public boolean hasObstruction() {		
		return (actionCard instanceof ObstructionCard);
	}
	
	/**
	 * Gets if this tile has a path card and is reachable from the starting tile
	 * @return	True if active, false otherwise
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets if this tile has a path card and is reachable from the starting tile
	 * @param active True if active, false otherwise
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

    public TileMemento getCurrentAsMemento() {
        return new TileMemento(this);
    }
 
    public void restoreFromMemento(TileMemento memento) {
    	Tile prevTile = memento.getSavedState();
    	
    	this.pathCard = prevTile.pathCard;
    	this.actionCard = prevTile.actionCard;
    	this.active = prevTile.active;
    	
    	parentBoard.validateActiveTiles();
    }
    
    public static class TileMemento {
    	private Tile tile;

        public TileMemento(Tile tile) {
        	this.tile = new Tile(tile.parentBoard);
        	this.tile.pathCard = tile.pathCard;
        	this.tile.actionCard = tile.actionCard;
        	this.tile.active = tile.active;
        }
 
        private Tile getSavedState() {
            return tile;
        }
    }

	public Card getActionCard() {
		return actionCard;
	}
	
	public Boolean hasPathCard() {
		return pathCard != null;
	}
	
	public Boolean hasActionCard() {
		return actionCard != null;
	}
}
