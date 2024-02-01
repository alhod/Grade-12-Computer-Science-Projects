package Model;

import Controller.*;
import View.*;

public class Player implements Turn {
    
    Gameplay gameplay;
    int id;
    String name;
    Hand hand;
    Fields fields;
    int coins;

    public Player(int id, String name, Gameplay gameplay){
        setId(id);
        setName(name);
        setGameplay(gameplay);
        setHand(new Hand());
        setFields(new Fields());
    }

    // public void startTurn(){
    //     System.out.printf("turn num p: %d\n", getGameplay().getTurn_num());
    //     System.out.println(getGameplay());
    //     // phase1();
    //     // System.out.printf("done phase 1\n");
    //     // // while(getGameplay().getPhase_ind()==0){
    //     //     // System.out.printf("phase ind: %d\n", getGameplay().getTurn_num());
    //     // //     // Since first turn of the game
    //     // //     if(getGameplay().getTurn_num()==0){  // Implement this in AI as well
    //     // //         break;
    //     // //     }
    //     //     // System.out.printf("turn num shouldn't be 1: %d\n", getGameplay().getTurn_num());
    //     // // }
    //     // getGameplay().nextPhase();
    //     System.out.printf("should be pahes ind 1: %d\n", getGameplay().getPhase_ind());
    //     // phase2();
    //     // // while(getGameplay().getPhase_ind()==1){}
    //     // getGameplay().nextPhase();
    //     // phase3();
    //     // // while(getGameplay().getPhase_ind()==2){}
    //     // getGameplay().nextPhase();
    //     // phase4();
    //     // // while(getGameplay().getPhase_ind()==3){}
    //     // getGameplay().nextPhase();
    // }

    public void phase1(){
        // System.out.println("start phase 1");
        if(getGameplay().getTurn_num()==0){
            getGameplay().incPhase();
            getGameplay().startPhase();
        }

    }

    public void phase2(){
        // System.out.println("start phase 2");
        // Technically end of phase 1
        getGameplay().discardOfferArea();
    }
    
    public void phase3(){
        // System.out.println("start phase 3");
        
    }

    public void phase4(){
        // System.out.println("start phase 4");
        getGameplay().populateOfferArea();
    }

    // public void phase5(){
        // System.out.println("start phase 5");
        
    // }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    
}
