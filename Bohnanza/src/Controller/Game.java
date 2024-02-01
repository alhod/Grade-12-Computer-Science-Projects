/*
Date: Nov 25th
Course: ICS4U1-02
Name: Andrew Deng
Significant help: none
Description: This class initializes the game
*/

package Controller;

import Model.*;
import View.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game {

    Controller controller;
    Gameplay gameplay;

    CardGUI tmp_card;  // for convenience of initializing CardGUIs

    public Game(Controller controller) {

        // Get instance of Controller for this game
        this.controller = controller;

        setGameplay(new Gameplay(controller));
    }

    // Call startGameSequence() method. Call createGameModel and createGameGUI from
    // Model and View objects in Controller respectively. Remember to display the
    // GameGUI JFrame. Call addActionListenerGame(). Then call startGame().
    public void createGame() {

        ArrayList<PlayerTemplate> player_templates = retrievePlayerTemplates();

        initGameplay(player_templates.size());

        getController().getModel().createGameModel(player_templates, getController().getGameplay());
        getController().getView().createGameGUI();

        initGameGUI();

        getController().getView().getGameGUI().setVisible(true);

        startGameSequence();
        startGame();
    }

    // Creates and returns PlayerTemplate ArrayList from MainGUI component info
    public ArrayList<PlayerTemplate> retrievePlayerTemplates() {
        return getController().getMenu().getPlayerTemplates();
    }

    // Initializes gameplay settings
    public void initGameplay(int num_players) {

        // Set default gameplay values
        getGameplay().setNum_players(num_players);
        getGameplay().setPlayer_turn(-1);
        getGameplay().setTurn_num(-1);
        getGameplay().setPhase_ind(0);
        getGameplay().setPrev_selected(null);
        getGameplay().setCurr_selected(null);
        getGameplay().setGame_over(false);
    }

    // Initializes gui images
    public void initGameGUI(){

        // System.out.println("start init game gui");
        // HandGUIScrollPanel p = getController().getView().getGameGUI().getPlayerGUIs().getPlayerGUIs().get(0).getHandGUI().getHandGUIScrollPanel();
        // p.setBackground(Color.yellow);
        // p.repaint();
        // p.revalidate();

        // HandGUI h = p.getHandGUI();

        GameModel gameModel = getController().getModel().getGameModel();
        GameGUI gameGUI = getController().getView().getGameGUI();
        
        // Initialize default images for offer slots, fields, deck, discardedcards
        ArrayList<OfferSlot> offer_slots = gameModel.getOfferArea().getOfferSlots();
        ArrayList<OfferSlotGUI> offer_slot_GUIs = gameGUI.getOfferAreaGUI().getOfferSlotGUIs();
        for(int i=0; i<Math.min(offer_slots.size(), offer_slot_GUIs.size()); i++){
            // tmp_card = new CardGUI(0, 20, new Card(), offer_slot_GUIs.get(i));
            // getGameplay().addMouseListenerCard(tmp_card);
            // offer_slot_GUIs.get(i).setCardGUI(tmp_card);
            offer_slot_GUIs.get(i).setOfferSlot(offer_slots.get(i));
            offer_slots.get(i).setOfferSlotGUI(offer_slot_GUIs.get(i));
        }
        // p.setBackground(Color.blue);
        // p.repaint();
        // p.revalidate();
        
        ArrayList<Player> players = gameModel.getPlayers().getPlayers();
        ArrayList<PlayerGUI> player_GUIs = gameGUI.getPlayerGUIs().getPlayerGUIs();
        for(int i=0; i<Math.min(players.size(), player_GUIs.size()); i++){

            ArrayList<Field> fields = players.get(i).getFields().getFields();
            ArrayList<FieldGUI> field_GUIs = player_GUIs.get(i).getFieldGUIs().getFieldGUIs();

            
            for(int j=0; j<Math.min(fields.size(), field_GUIs.size()); j++){

                // System.out.println("adding field gui");
                
                if(fields.get(j).getUsable()) {
                    tmp_card = new CardGUI(0, 20, Card.getEmptyBeanFieldCard(), field_GUIs.get(j));
                } else {
                    tmp_card = new CardGUI(0, 20, Card.getUnpurchasedBeanFieldCard(), field_GUIs.get(j));
                }

                getGameplay().addMouseListenerCard(tmp_card);
                field_GUIs.get(j).setCardGUI(tmp_card);
                
                field_GUIs.get(j).setField(fields.get(j));
                fields.get(j).setFieldGUI(field_GUIs.get(j));
                
            }          
                
            Hand hand = players.get(i).getHand();
            HandGUI handGUI = player_GUIs.get(i).getHandGUI();
            hand.setHandGUI(handGUI);
            handGUI.setHand(hand);
            
        }

        player_GUIs.get(0).getPlayerName().setText(players.get(0).getName());
        player_GUIs.get(1).getPlayerName().setText(players.get(1).getName());

        // p.setBackground(Color.lightGray);
        // p.repaint();
        // p.revalidate();

        Deck deck = gameModel.getDeck();
        DeckGUI deck_GUI = gameGUI.getDeckGUI();
        deck_GUI.setCardGUI(new CardGUI(0, 15, Card.getDeckImage(), deck_GUI));
        deck.setDeckGUI(deck_GUI);
        deck_GUI.setDeck(deck);

        DiscardedCardsGUI discarded_cards_GUI = gameGUI.getDiscardedCardsGUI();
        discarded_cards_GUI.setCardGUI(new CardGUI(0, 0, Card.getCardBack(), discarded_cards_GUI));
        // System.out.println("end init game gui");


        getGameplay().addActionListenerSellBeanButton(gameGUI.getSell_beans_button());
        getGameplay().addActionListenerEndPhaseButton(gameGUI.getEnd_phase_button());

    }

    // Runs start game sequence (i.e. draws 5 cards for everyone, resets
    // player_turn, turn_num, etc.)
    public void startGameSequence() {

        ArrayList<Player> players = getController().getModel().getGameModel().getPlayers().getPlayers();
        
        for(int i=0; i<players.size(); i++){
            players.get(i).getHand().getHandGUI().getHandGUIScrollPanel().setBackground(Color.ORANGE);
            for(int j=0; j<getGameplay().NUM_STARTING_CARDS; j++){
                
                getGameplay().drawCardToHand(players.get(i));
            }
        }
    }

    // Calls gameplay.
    public void startGame() {
        getGameplay().startGame();
    }

    // Add action listener to all panels, being OfferSlotGUIs and FieldGUIs. Make so
    // when clicked, Gameplay.prev_selected is set to that JPanel, and that JPanel
    // has bolded border or some indicator. Can further use .getClass() on the
    // Gameplay.prev_selected object to identify validity/intention sequential user
    // clicks. Can make so after invalid sequence of clicks, the
    // Gameplay.prev_selected object is unbordered. Make all action listeners call
    // Gameplay.processEvent() method to incorporate logic across multiple actions.
    public void addActionListenersGame() {

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

}
