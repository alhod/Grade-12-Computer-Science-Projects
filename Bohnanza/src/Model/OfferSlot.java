package Model;

import Controller.*;
import View.*;
import java.util.ArrayList;

public class OfferSlot {

    ArrayList<Card> cards;
    String type;
    int size;

    OfferSlotGUI offerSlotGUI;

    public OfferSlot() {
        setCards(new ArrayList<Card>());
        setType(null);
        setSize();
    }

    // Adds Card to offer slot. This Card should be the same as the other Cards in
    // the ArrayList. Returns true if successful, returns false if not.
    public boolean pushCard(Card card) {

        if (getSize()==0) {
            getCards().add(card);
            setSize();
            setType(card.getType());
            return true;
        }

        if (getType().equals(card.getType())) {
            getCards().add(card);
            setSize();
            return true;
        }

        return false;
    }

    // Returns all Cards in ArrayList in this offer slot
    public ArrayList<Card> popEntireOfferSlot() {
        ArrayList<Card>ret = getCards();
        setCards(new ArrayList<Card>());
        setType(null);
        setSize();
        return ret;
    }

    public Card peak(){
        if(getSize()==0){
            return new Card();
        }

        return getCards().get(0);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return getCards().size();
    }

    public void setSize() {
        this.size=getCards().size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public OfferSlotGUI getOfferSlotGUI() {
        return offerSlotGUI;
    }

    public void setOfferSlotGUI(OfferSlotGUI offerSlotGUI) {
        this.offerSlotGUI = offerSlotGUI;
    }

    

}
