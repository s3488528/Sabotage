# Sabotage
Assignment 1 for ISYS1083/1084

'Group C' Team:
Pardon Gumbo	s3611684
Shinsuke Ito	s3488528
Tianze Zhao		s3573724

SOLID Principles:
The single responsibility principle has been applied by the creation of multiple classes, such as the Player, Board, and Tile classes which all have separate and singular responsibilities.

The open/closed principle can be seen with the Connectable and Rotatable interfaces, as well as the Card abstract class. These classes are closed to modification as they have abstract methods whose definitions and parameters cannot be changed. However, they are open for extension because their functionality can be further defined in a concrete class.

The Liskov substitution principle can be seen with the Card class. All the properties of a Card class can also be held true for any of its subclasses. For example, a Card object is able to be a part of a Player's hand, as well as a Board's deck. This is also true for any PathCard, ActionCard, and is still true for any further subclassed classes such as a CornerCard or a HostageCard.

An example of the interface segregation principle in this project are the two PathCard interfaces: Connectable and Rotatable. These interfaces abstract away distinct properties of a PathCard, improving cohesion. This also means that any new cards that uses rotation / connectability can re-use these interfaces.

The dependency inversion principle is seen in the GameListener and the JavaFXGameListener. These two classes allow the high level code (GameController/GameContext) is able to remain separate from the low level code (Main). This segregation is achieved in two parts:
 - The GameListener interface exposes 'UI events' which can be called by the model
 - The JavaFXGameListener provides a JavaFX-specific implementation to handle the 'UI events'
This way, different UI implementations can still handle the same 'UI events' by creating a new class (Say AwtGameListener for an AWT implementation) without changing the model.