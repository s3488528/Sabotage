package sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import sabotage.core.cards.DemolishCard;
import sabotage.core.cards.GoalCard;
import sabotage.core.cards.HostageCard;
import sabotage.core.cards.RescueCard;

public class Board {
	
	private Tile[][] tiles;
	
	private Random random = new Random();

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param	boardWidth	The number of tiles spanning across the board
	 * @param	boardHeight	The number of tiles spanning down the board
	 */
	public Board(int boardWidth, int boardHeight) {
		tiles = new Tile[boardHeight][boardWidth];
		ArrayList<Tile> rightTiles = new ArrayList<Tile>();

		/* Instantiate all tiles on the board */
		for (int y = 0; y < boardHeight; y++) { 
			for (int x = 0; x < boardWidth; x++) { 
				tiles[y][x] = new Tile(this);
				
				if (x == boardWidth - 1) {
					rightTiles.add(tiles[y][x]);
				}
			}
		}
		
		/* Set a random tile on the left as the starting tile */
		Tile startingTile = tiles[random.nextInt(boardHeight)][0];
		startingTile.setPathCard(CardBuilder.createStartingCard(startingTile));

		/* Set 3 random tiles on the right as the goal tiles */		
		Collections.shuffle(rightTiles);
		rightTiles.get(0).setPathCard(CardBuilder.createGoalCard(rightTiles.get(0), true));
		rightTiles.get(1).setPathCard(CardBuilder.createGoalCard(rightTiles.get(1), false));
		rightTiles.get(2).setPathCard(CardBuilder.createGoalCard(rightTiles.get(2), false));				
	}

	/**
	 * Gets all tiles contained in this board
	 *
	 * @return      All tiles as 2d Array
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Checks if a card is able to be placed
	 * @param validationCard The card to be validated
	 * @param x	The x position of the card
	 * @param y The y position of the card
	 * @return True if card can be placed, false otherwise
	 */
	public boolean validateCard(Card validationCard, int x, int y) {
		Tile existingTile = getTiles()[y][x];
		
		if (validationCard instanceof PathCard) {
			
			/* Path cards cannot be placed on top of another path card */
			if (existingTile.getPathCard() != null) {
				return false;
			}
			
			boolean checkN = true;
			boolean checkE = true;
			boolean checkS = true;
			boolean checkW = true;
			
			boolean connected = false;
			
			PathCard card = (PathCard) validationCard;
			
			/* No need to check outside edge tiles */
			if (y == 0) 					{ checkN = false; }
			if (y == tiles.length - 1) 		{ checkS = false; }
			if (x == 0) 					{ checkW = false; }
			if (x == tiles[0].length - 1) 	{ checkE = false; }
			
			if (checkN) {			
				Tile northTile = getTiles()[y - 1][x];

				if (northTile.getPathCard() != null) {
					if (!card.isConnectable(northTile.getPathCard(), Direction.N) || northTile.hasHostage()) {
						return false;
					} else {
						if (card.getConnections()[0]) {
							connected = true;
						}
					}
				}
			}
			
			if (checkE) {			
				Tile eastTile = getTiles()[y][x + 1];

				if (eastTile.getPathCard() != null) {
					if (!card.isConnectable(eastTile.getPathCard(), Direction.E) || eastTile.hasHostage()) {
						return false;
					} else {
						if (card.getConnections()[1]) {
							connected = true;
						}
					}
				}
			}
			
			if (checkS) {			
				Tile southTile = getTiles()[y + 1][x];

				if (southTile.getPathCard() != null) {
					if (!card.isConnectable(southTile.getPathCard(), Direction.S) || southTile.hasHostage()) {
						return false;
					} else {
						if (card.getConnections()[2]) {
							connected = true;
						}	
					}
				}
			}
			
			if (checkW) {			
				Tile westTile = getTiles()[y][x - 1];

				if (westTile.getPathCard() != null) {
					if (!card.isConnectable(westTile.getPathCard(), Direction.W) || westTile.hasHostage()) {
						return false;
					} else {
						if (card.getConnections()[3]) {
							connected = true;
						}
					}
				}
			}
			
			if (connected) {
				/* If there is at least one connection: */
				return true;				
			} else {
				/* If there are no connections: */
				return false;
			}
			
		} else if (validationCard instanceof ActionCard) {

			/* Action cards cannot be played on a goal card or the starting card */
			if (existingTile.getPathCard() instanceof GoalCard || existingTile.getPathCard().isStartingCard()) {
				return false;
			}
			
			if (validationCard instanceof RescueCard) {
				if (existingTile.hasHostage()) {
					return true;
				}
			} else if (validationCard instanceof HostageCard) {
				if (existingTile.getPathCard() != null && !tiles[y][x].hasHostage()) {
					return true;
				}
			} else {
				if (existingTile.getPathCard() != null) {
					return true;
				}
			}
		}
		
		return false;
	}
}
