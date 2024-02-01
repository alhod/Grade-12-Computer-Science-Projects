package Model;

import View.*;

import java.util.*;

public class Field {
    
    Stack<Card> cards;
    boolean usable;
    String type;
    FieldGUI fieldGUI;

    public Field(boolean usable){
        setUsable(usable);
        setCards(new Stack<Card>());
        // setSize();
    }

    public void push(Card card){
        getCards().push(card);
        // setSize();
    }

    public Card pop(){
        Card ret = getCards().pop();
        // setSize();

        return ret;
    }

    public Card peak(){
        if(getSize()==0){
            return new Card();
        }

        Card ret = getCards().peek();
        return ret;
    }

    public boolean plantBean(Card card){
        if(getSize()==0){
            push(card);
            return true;
        }

        if(getType().equals(card.getType())){
            push(card);
            return true;
        }

        return false;
    }

    public boolean sellable(){
        return getSize()>1&&getUsable();
    }

    // To sell, first call this, then call removeAllCardsOnField
    public int sellValue(){
        return Card.sell(getType(), getSize());
    }
    
    public ArrayList<Card> removeAllCardsOnField(){
        ArrayList<Card> ret = new ArrayList<>();
        
        while(getSize()>0){
            ret.add(pop());
        }

        return ret;
    }

    public void purchaseField(){
        setUsable(true);
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }

    public boolean isUsable() {
        return usable;
    }

    public boolean getUsable(){
        return this.usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public int getSize() {
        return getCards().size();
    }

    // public void setSize(){
    //     setSize(getCards().size());
    // }

    // public void setSize(int size) {
    //     this.size = size;
    // }

    public String getType() {
        if(getSize()==0){
            return null;
        }

        return getCards().peek().getType();
    }

    public void setType(String type) {
        this.type = type;
    }

    public FieldGUI getFieldGUI() {
        return fieldGUI;
    }

    public void setFieldGUI(FieldGUI fieldGUI) {
        this.fieldGUI = fieldGUI;
    }

    

    

    

    


}
