package sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import sabotage.core.cards.ChanceCard;
import sabotage.core.cards.ClearCard;
import sabotage.core.cards.DemolishCard;
import sabotage.core.cards.GoalCard;
import sabotage.core.cards.HostageCard;
import sabotage.core.cards.ObstructionCard;
import sabotage.core.cards.RescueCard;

public class Board {

	private Tile[][] tiles;
	private int startingY;
	private Random random = new Random();
	private Boolean goalReached = false;

	/* CONSTRUCTORS */
	/**
	 * Tile class constructor
	 * 
	 * @param boardWidth
	 *            The number of tiles spanning across the board
	 * @param boardHeight
	 *            The number of tiles spanning down the board
	 */
	public Board(int boardWidth, int boardHeight, int treasureCount) {
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
		startingY = random.nextInt(boardHeight);
		Tile startingTile = tiles[startingY][0];
		startingTile.setPathCard(PathCardFactory.createStartingCard(startingTile));
		startingTile.setActive(true);

		/* Set random tiles on the right as the goal tiles */
		Collections.shuffle(rightTiles);

		/* Make one real goal: */
		rightTiles.get(0).setPathCard(PathCardFactory.createGoalCard(rightTiles.get(0), true));

		/* Rest are fake: */
		for (int i = 1; i < treasureCount; i++) {
			rightTiles.get(i).setPathCard(PathCardFactory.createGoalCard(rightTiles.get(i), false));
		}
	}

	/**
	 * Gets all tiles contained in this board
	 *
	 * @return All tiles as 2d Array
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Checks if a card is able to be placed
	 * 
	 * @param validationCard
	 *            The card to be validated
	 * @param x
	 *            The x position of the card
	 * @param y
	 *            The y position of the card
	 * @return True if card can be placed, false otherwise
	 */
	public boolean validateCard(Card validationCard, int x, int y) {
		Tile existingTile = getTiles()[y][x];

		if (validationCard instanceof PathCard) {

			/* Path cards cannot be placed on top of another path card */
			if (existingTile.getPathCard() != null) {
				return false;
			}

			/* Path cards cannot be placed on top of a obstruction */
			if (existingTile.hasObstruction()) {
				return false;
			}

			boolean checkN = true;
			boolean checkE = true;
			boolean checkS = true;
			boolean checkW = true;

			boolean connected = false;

			PathCard card = (PathCard) validationCard;

			/* No need to check outside edge tiles */
			if (y == 0) {
				checkN = false;
			}
			if (y == tiles.length - 1) {
				checkS = false;
			}
			if (x == 0) {
				checkW = false;
			}
			if (x == tiles[0].length - 1) {
				checkE = false;
			}

			if (checkN) {
				Tile northTile = getTiles()[y - 1][x];

				if (northTile.getPathCard() != null) {
					if (!card.isConnectable(northTile.getPathCard(), Direction.N)) {
						return false;
					} else {
						if (card.getConnections()[0] && northTile.isActive()) {
							connected = true;
						}
					}
				}
			}

			if (checkE) {
				Tile eastTile = getTiles()[y][x + 1];

				if (eastTile.getPathCard() != null) {
					if (!card.isConnectable(eastTile.getPathCard(), Direction.E)) {
						return false;
					} else {
						if (card.getConnections()[1] && eastTile.isActive()) {
							connected = true;
						}
					}
				}
			}

			if (checkS) {
				Tile southTile = getTiles()[y + 1][x];

				if (southTile.getPathCard() != null) {
					if (!card.isConnectable(southTile.getPathCard(), Direction.S)) {
						return false;
					} else {
						if (card.getConnections()[2] && southTile.isActive()) {
							connected = true;
						}
					}
				}
			}

			if (checkW) {
				Tile westTile = getTiles()[y][x - 1];

				if (westTile.getPathCard() != null) {
					if (!card.isConnectable(westTile.getPathCard(), Direction.W)) {
						return false;
					} else {
						if (card.getConnections()[3] && westTile.isActive()) {
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
			if (existingTile.getPathCard() != null) {
				/*
				 * Action cards cannot be played on a goal card or the starting
				 * card
				 */
				if (existingTile.getPathCard() instanceof GoalCard || existingTile.getPathCard().isStartingCard()) {
					return false;
				}

				if (validationCard instanceof RescueCard) {
					if (existingTile.hasHostage()) {
						return true;
					}
				} else if (validationCard instanceof HostageCard) {
					if (!tiles[y][x].hasHostage()) {
						return true;
					}
				} else if (validationCard instanceof DemolishCard) {
					return true;
				}
			} else {
				/*
				 * Obstruction, Clear and Chance cards must be played on an
				 * empty tile:
				 */
				if (validationCard instanceof ObstructionCard) {
					return true;
				} else if (validationCard instanceof ClearCard) {
					if (existingTile.hasObstruction()) {
						return true;
					}
				} else if (validationCard instanceof ChanceCard) {
					if (!existingTile.hasObstruction()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void validateActiveTiles() {
		/* Clear all tiles active on the board */
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				tiles[y][x].setActive(false);
			}
		}

		// Direction.E is a hack, since we know that all starting tiles will
		// start on the left edge
		recursivelyActivateTiles(0, startingY, Direction.E);
	}

	/***
	 * Validates all tiles' active states (i.e. tile is connected to the
	 * starting path card)
	 * 
	 * @param x
	 *            The x-position of the tile being validated
	 * @param y
	 *            The y-position of the tile being validated
	 * @param direction
	 *            The direction from the previously recursed tile (so we don't
	 *            re-validate tiles)
	 */
	private void recursivelyActivateTiles(int x, int y, Direction direction) {
		// Set this tile as active
		tiles[y][x].setActive(true);

		/* Break out if tile has a hostage */
		if (tiles[y][x].hasHostage()) {
			tiles[y][x].setActive(false);
			return;
		}

		boolean checkN = true;
		boolean checkE = true;
		boolean checkS = true;
		boolean checkW = true;

		PathCard card = tiles[y][x].getPathCard();

		if (card instanceof GoalCard) {
			((GoalCard) card).reveal();
			tiles[y][x].setActive(false);

			if (((GoalCard) card).isGoal()) {
				goalReached = true;
			}

			return;
		}

		/* No need to check outside edge tiles */
		if (y == 0) {
			checkN = false;
		}
		if (y == tiles.length - 1) {
			checkS = false;
		}
		if (x == 0) {
			checkW = false;
		}
		if (x == tiles[0].length - 1) {
			checkE = false;
		}

		/* No need to re-check tiles */
		if (direction == Direction.S) {
			checkN = false;
		}
		if (direction == Direction.W) {
			checkE = false;
		}
		if (direction == Direction.N) {
			checkS = false;
		}
		if (direction == Direction.E) {
			checkW = false;
		}

		/* No need to check tiles unconnected by path card */
		boolean[] conn = card.getConnections();
		if (!conn[0]) {
			checkN = false;
		}
		if (!conn[1]) {
			checkE = false;
		}
		if (!conn[2]) {
			checkS = false;
		}
		if (!conn[3]) {
			checkW = false;
		}

		if (checkN) {
			Tile northTile = tiles[y - 1][x];

			if (northTile.getPathCard() != null && northTile.isActive() == false) {
				if (card.isConnectable(northTile.getPathCard(), Direction.N)) {
					recursivelyActivateTiles(x, y - 1, Direction.N);
				}
			}
		}

		if (checkE) {
			Tile eastTile = getTiles()[y][x + 1];

			if (eastTile.getPathCard() != null && eastTile.isActive() == false) {
				if (card.isConnectable(eastTile.getPathCard(), Direction.E)) {
					recursivelyActivateTiles(x + 1, y, Direction.E);
				}
			}
		}

		if (checkS) {
			Tile southTile = getTiles()[y + 1][x];

			if (southTile.getPathCard() != null && southTile.isActive() == false) {
				if (card.isConnectable(southTile.getPathCard(), Direction.S)) {
					recursivelyActivateTiles(x, y + 1, Direction.S);
				}
			}
		}

		if (checkW) {
			Tile westTile = getTiles()[y][x - 1];

			if (westTile.getPathCard() != null && westTile.isActive() == false) {
				if (card.isConnectable(westTile.getPathCard(), Direction.W)) {
					recursivelyActivateTiles(x - 1, y, Direction.W);
				}
			}
		}
	}

	/***
	 * Gets if the goal card has been reached by the path
	 * 
	 * @return True if goal has been reached, false otherwise
	 */
	public Boolean getGoalReached() {
		return goalReached;
	}
}
