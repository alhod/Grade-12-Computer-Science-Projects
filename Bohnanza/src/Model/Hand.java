package Model;

import java.util.*;

import View.*;

public class Hand {
    
    ArrayList<Card> cards;
    HandGUI handGUI;

    public Hand(){
        setCards(new ArrayList<Card>());
    }
    
    public boolean pushCard(Card card){
        getCards().add(card);
        return true;
    }

    public Card peak(){
        if(getCards().size()==0){
            return new Card();
        }

        // System.out.println("front of hand: "+getCards().get(0).getType());
        return getCards().get(0);
    }

    // Must call before takeCard
    public boolean takeable(int index){
        if(index<0)return false;
        return index<getCards().size();
    }

    public Card takeCard(int index){
        if(!takeable(index)){
            return new Card();
        }

        Card ret = getCards().get(index);
        getCards().remove(index);
        return ret;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public HandGUI getHandGUI() {
        return handGUI;
    }

    public void setHandGUI(HandGUI handGUI) {
        this.handGUI = handGUI;
    }

    public int getSize(){
        return getCards().size();
    }

    
}
