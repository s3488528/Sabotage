package sabotage.core;

import java.util.Stack;

public class Deck extends Stack<Card> {

    public void set(Stack<Card> deck) {   
    	clear();
    	
    	for (Card card : deck) {
    		this.add(card);
    	}
    }
 
    public DeckMemento getCurrentAsMemento() {
        return new DeckMemento(this);
    }
 
    public void restoreFromMemento(DeckMemento memento) {
    	set(memento.getSavedState());
    }
    
    public static class DeckMemento {
        private Stack<Card> deck;

        public DeckMemento(Stack<Card> deck) {
        	this.deck = new Stack<Card>();
        	
        	for (Card card : deck) {
        		this.deck.add(card);
        	}
        }
 
        private Stack<Card> getSavedState() {
            return deck;
        }
    }
}
