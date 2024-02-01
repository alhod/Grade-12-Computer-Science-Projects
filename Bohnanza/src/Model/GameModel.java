package Model;

import Controller.*;
import View.*;

import java.util.*;

public class GameModel {
    Gameplay gameplay;
    

    // Has-a components
    OfferArea offerArea;
    DiscardedCards discardedCards;
    Deck deck;
    Instructions instructions;
    Players players;


    public GameModel(ArrayList<PlayerTemplate>player_templates, Gameplay gameplay){
        setGameplay(gameplay);

        setOfferArea(new OfferArea());
        // setDiscardedCards(new DiscardedCards());
        setDeck(new Deck());
        setInstructions(new Instructions());
        setPlayers(new Players(player_templates, getGameplay()));
        setDiscardedCards(new DiscardedCards());
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public OfferArea getOfferArea() {
        return offerArea;
    }

    public void setOfferArea(OfferArea offerArea) {
        this.offerArea = offerArea;
    }

    public DiscardedCards getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(DiscardedCards discardedCards) {
        this.discardedCards = discardedCards;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    
}
