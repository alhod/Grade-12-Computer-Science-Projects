package Model;

import java.util.*;

public class DiscardedCards {

    int size;
    Stack<Card> cards;

    public DiscardedCards(){
        setCards(new Stack<Card>());
        setSize(0);
    }

    public void push(Card card){
        getCards().push(card);
        setSize();
    }

    public Card pop(){
        if(getSize()==0){
            return new Card();
        }

        Card ret = getCards().pop();
        setSize();
        return ret;
    }

    public Card peak(){
        if(getSize()==0){
            return new Card();
        }

        return getCards().peek();
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }

    public int getSize() {
        return size;
    }

    public void setSize(){
        setSize(getCards().size());
    }

    public void setSize(int size) {
        this.size = size;
    }

    

    


}
