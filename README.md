# Sabotage
Assignment 1/2 for ISYS1083/1084

'Group C' Team:
Pardon Gumbo	s3611684
Shinsuke Ito	s3488528
Tianze Zhao		s3573724

---------------------------------------

SOLID Principles:
The single responsibility principle has been applied by the creation of multiple classes, such as the Player, Board, and Tile classes which all have separate and singular responsibilities.

The open/closed principle can be seen with the Connectable and Rotatable interfaces, as well as the Card abstract class. These classes are closed to modification as they have abstract methods whose definitions and parameters cannot be changed. However, they are open for extension because their functionality can be further defined in a concrete class.

The Liskov substitution principle can be seen with the Card class. All the properties of a Card class can also be held true for any of its subclasses. For example, a Card object is able to be a part of a Player's hand, as well as a Board's deck. This is also true for any PathCard, ActionCard, and is still true for any further subclassed classes such as a CornerCard or a HostageCard.

An example of the interface segregation principle in this project are the two PathCard interfaces: Connectable and Rotatable. These interfaces abstract away distinct properties of a PathCard, improving cohesion. This also means that any new cards that uses rotation / connectability can re-use these interfaces.

The dependency inversion principle is seen in the GameListener and the JavaFXGameListener. These two classes allow the high level code (GameController/GameContext) is able to remain separate from the low level code (Main). This segregation is achieved in two parts:
 - The GameListener interface exposes 'UI events' which can be called by the model
 - The JavaFXGameListener provides a JavaFX-specific implementation to handle the 'UI events'
This way, different UI implementations can still handle the same 'UI events' by creating a new class (Say AwtGameListener for an AWT implementation) without changing the model.

---------------------------------------

 + Creational Pattern 1 - Abstract Factory Pattern:
The abstract factory pattern can be seen in the creation of the Card classes as shown by the diagram. 

The AbstractCardFactory class is the abstract factory, while the GeneralCardFactory and PathCardFactory classes are the concrete implementations. The Card class and its concrete implementations make up the products of the pattern.

This pattern allows the client (GameContext) to have low coupling with the creation of card objects - GameContext does not deal directly with the concrete implementations. The main advantage of the abstract factory pattern is that there can be multiple types of factories (GeneralCardFactory, PathCardFactory) for a common theme (Card). Although our implementation only has two types of AbstractCardFactory, there can be many more.

 + Creational Pattern 2 - Singleton Pattern:
The GameContext class in our project uses a singleton pattern.

The GameContext class holds all the relevant information about the current game, such as the board, the players, and the deck.

As the GameContext is only required once per application, using the singleton pattern ensures that the GameContext class is only initialised once.

 + Structural Pattern 1 - Bridge Pattern:
The bridge pattern has been implemented in our application in the Card classes. The logic for  placing cards on a tile and using cards on a player have been split into a CardLogic class.

In the perspective of the bridge pattern, the Card class can be seen as the abstraction, meaning that its concrete implementations would be the refined abstractions. Similarly, the CardLogic interface would be the implementor and its concrete implementations, the concrete implementors.

Using this pattern allows the abstraction (Card) to remain separate from its implementation details (CardLogic). This reduces coupling, meaning that changes to either the abstraction or implementation have little effect on the other.

In using the bridge pattern, we can see that the adapter pattern has also been implemented. The Card class (adapter) essentially 'wraps' the CardLogic class (adaptee) by holding a reference to it and calling methods against it.

 + Structural Pattern 2 - Facade Pattern:
In order to prevent extensive interactions between the model and view, the GameController class implements a facade pattern.

The GameController (facade) is responsible for game initialisation and game logic, both of which must be initiated by the UI (client). The GameController allows this by only exposing the functions that Main needs. In doing so, the facade's underlying implementation details remain hidden from the client.

 + Behavioural Pattern 1 - Command Pattern:
To allow player turns to be undone, a command pattern was implemented in our application. 

The GameContext has a instance of CommandHistory, which is a class holding a stack of Commands. Whenever a player makes a turn (any move except undo), the GameContext adds a new Command into the stack of Commands and calls its execute() function. In order to undo these commands, the top-most Command is popped off the stack and its undo() function is called.

 + Behavioural Pattern 2 - Memento Pattern:
To preserve states so commands can be undone, the memento pattern was used. The attached diagram shows the memento pattern used in the game's deck.

Just before any player draws from a deck, the GameContext asks the Deck for a DeckMemento and pushes it onto a stack of DeckMementos. Anytime a player deicdes to undo, the GameContext pops the top-most DeckMemento from the stack and restored to the Deck.

Note that the memento pattern is also used for the player's active state, as well as the player's hand. These mementos are created when their corresponding command is executed, and restored when their corresponding command is undone.