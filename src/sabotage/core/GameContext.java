package sabotage.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import sabotage.core.commands.CommandHistory;
import sabotage.core.commands.DiscardCommand;
import sabotage.core.commands.DonateCommand;
import sabotage.core.commands.PlaceCommand;

public final class GameContext {
	
	private static final GameContext INSTANCE = new GameContext();

	private Board board;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private Card currentCard;

	private int deckCount;
	private int boardWidth;
	private int boardHeight;
	private int treasureCount;
	
	private Deck deck;
	private Stack<Deck.DeckMemento> deckStates;
	
	private CommandHistory commHistory = new CommandHistory();
	
	private Random random = new Random();

	private int turnNo = 1;
	
	private GameContext() {	}
	
    public static GameContext getInstance() {
        return INSTANCE;
    }

	/**
	 * Initializes a new board with the specified settings
	 * @param boardWidth	The number of tiles spanning across the board
	 * @param boardHeight	The number of tiles spanning down the board
	 */
	public void initializeBoard(int boardWidth, int boardHeight, int treasureCount) {
		 // cache the board dimensions for later rounds
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.treasureCount = treasureCount;
		
		this.board = new Board(boardWidth, boardHeight, treasureCount);		
	}
	
	/**
	 * Initialises the deck with the amount of cards specified
	 * @param deckCount		The number of starting cards in the deck
	 */
	public void initializeDeck(int deckCount) {
		
		this.deckCount = deckCount; // cache the starting deck count for later rounds
		
		/* Populate the deck with random cards */
		deck = new Deck();		
		for (int i = 0; i < deckCount; i++) {			
			Card tempCard = new GeneralCardFactory().createRandomCard();
			deck.add(tempCard);
		}
		
		Collections.shuffle(deck);
		
		// initialise deck memento stack
		deckStates = new Stack<Deck.DeckMemento>();
	}

	/**
	 * Initialises players and adds cards to their hand
	 * @param playerCount		The number of players to initialise
	 */
	public void initializePlayers(int playerCount) {
		players = new ArrayList<Player>();
				
		for (int i = 0; i < playerCount; i++) {
			Player tempPlayer = new Player(PlayerColour.values()[i].name(), PlayerColour.values()[i]);
			
			for (int j = 0; j < 5; j++) {
				tempPlayer.addCardToHand(new GeneralCardFactory().createRandomCard());
			}
			
			players.add(tempPlayer);
		}	
	}
	
	/**
	 * Shuffles the players and sets one as villain
	 */
	public void shufflePlayers() {
		Collections.shuffle(players);
		currentPlayer = players.get(0);	
		players.get(random.nextInt(players.size())).setAsVillain(true);	
	}

	public void rotateCurrentCard(boolean right) {
		PathCard card = (PathCard) currentCard;
		
		if (right) {
			switch (card.getRotation()) {
			case _0:
				card.setRotation(Angle._90);
				break;
			case _90:
				card.setRotation(Angle._180);
				break;
			case _180:
				card.setRotation(Angle._270);
				break;
			case _270:
				card.setRotation(Angle._0);
				break;
			}
		} else {
			switch (card.getRotation()) {
			case _0:
				card.setRotation(Angle._270);
				break;
			case _90:
				card.setRotation(Angle._0);
				break;
			case _180:
				card.setRotation(Angle._90);
				break;
			case _270:
				card.setRotation(Angle._180);
				break;
			}
		}
	}

	/**
	 * Places the current card on the board
	 */
	public boolean validateCurrentCard(int x, int y) {
		return board.validateCard(currentCard, x, y);
	}

	/**
	 * Places the current card on the board
	 */
	public void placeCurrentCard(int x, int y) {
		commHistory.executeCommand(new PlaceCommand(currentPlayer, board.getTiles()[y][x], currentCard));
	}

	/**
	 * Removes the current card from the player's hand
	 */
	public void discardCurrentCard() {
		commHistory.executeCommand(new DiscardCommand(currentPlayer, currentCard));
	}
	
	/**
	 * Cycles to the next player
	 */
	public void cyclePlayer() {
		int currentIndex = players.indexOf(currentPlayer);
	
		if (currentIndex < players.size() - 1) {
			currentIndex += 1;
		} else {
			/* If we've reached the end of the list, loop back to the start */
			currentIndex = 0;
			
			/* Also increment turn number */
			turnNo += 1;
		}
		
		currentPlayer = players.get(currentIndex);
	}
	
	/**
	 * Cycles to the previous player
	 */
	public void cyclePreviousPlayer() {
		int currentIndex = players.indexOf(currentPlayer);
	
		if (currentIndex > 0) {
			currentIndex -= 1;
		} else {
			/* If we've reached the start of the list, loop back to the end */
			currentIndex = players.size() - 1;
			
			/* Also decrement turn number */
			turnNo -= 1;
		}
		
		currentPlayer = players.get(currentIndex);
	}

	public void validateActiveTiles() {
		board.validateActiveTiles();
	}
	
	/**
	 * Draws one card from the deck and gives it to the current player
	 * @return Returns true if deck is not empty; false otherwise
	 */
	public Boolean drawFromDeck() {
		if (deck.isEmpty()) {
			return false;
		}

		deckStates.push(deck.getCurrentAsMemento());

		Card drawnCard = deck.pop();
		currentPlayer.addCardToHand(drawnCard);
		
		return true;
	}
	
	/**
	 * Gets the GameContext's board
	 * @return	The board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Gets the GameContext's deck
	 * @return	The deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * Gets the GameContext's current card
	 * @return	The current card
	 */
	public Card getCurrentCard() {
		return currentCard;
	}

	/**
	 * Sets the GameContext's current card
	 */
	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	/**
	 * Gets the GameContext's current player
	 * @return	The current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Gets the GameContext's list of players as a string
	 * @return	A list of players as a String
	 */
	public String getPlayersAsString() {
		String[] string = new String[players.size()];

		for (int i = 0; i < players.size(); i++) {
			string[i] = players.get(i).getName();
		}
		
		return String.join(", ", string);
	}
	
	/**
	 * Gets the GameContext's list of players
	 * @return	An ArrayList of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the current turn number
	 * @return	The turn number
	 */
	public int getTurnNo() {
		return turnNo;
	}

	public void donateCurrentCard(Player player) {
		commHistory.executeCommand(new DonateCommand(currentPlayer, player, currentCard));
	}

	public void undoTurn() {
		cyclePreviousPlayer();
		if (!deckStates.isEmpty()) {
			deck.restoreFromMemento(deckStates.pop());
		}
		commHistory.undoLast();
	}

	public int getUndoCount() {
		return commHistory.getUndoStackCount();
	}

	public void reset() {
		commHistory.clear();

		// Fresh hand for each player:
		for (Player player : players) {
			player.getHand().clear();
			
			for (int j = 0; j < 5; j++) {
				player.addCardToHand(new GeneralCardFactory().createRandomCard());
			}
		}
		
		// Reset board and deck:
		initializeBoard(boardWidth, boardHeight, treasureCount);
		initializeDeck(deckCount);
	}
	
	public void distributePoints(boolean villainWins) {
		if (villainWins) {
			for (Player player : players) {
				if (player.isVillain()) {
					player.addToScore(players.size());
				}
			}
		} else {
			for (Player player : players) {
				if (!player.isVillain()) {
					player.addToScore(1);
				}
			}
		}
	}

	public void reinitPlayers() {
		for (Player player : players) {
			player.setAsVillain(false);
			player.setUndo(true);
		}
		
	}
}
