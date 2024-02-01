/*
Date: Nov 25th
Course: ICS4U1-02
Name: Andrew Deng
Significant help: none
Description: This class initializes the gameplay sequence
*/

package Controller;

import Model.*;
import View.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class Gameplay {

    public static final int NUM_PHASES = 4;
    public static final int NUM_STARTING_CARDS = 5;

    public CardGUI tmp_card;  // For utility throughout class

    Controller controller;
    int num_players;
    int player_turn;
    int turn_num;
    int num_cards_planted;

    // ind x = phase x+1, phases from 1 to 5
    int phase_ind;
    CardGUI prev_selected;
    CardGUI curr_selected;

    boolean game_over=false;

    Clip clip;

    public Gameplay(Controller controller) {
        setController(controller);
    }

    // Actually starts the game.
    // I.e. Make everything visible, make sure action listeners work, etc.
    public void startGame() {
        // while(!game_over){
            // System.out.println("NEXT TURN");
        //     nextTurn();
        // }
        // sellAllBeanFields();
        // getController().createEndGame();
        startMusic();
        nextTurn();
    }


    //method that continously plays the music once the gamegui is opened.
	private void startMusic() {
		
		//try catch in order to avoid crashing if any errors occur
		try {
			
			//creates the file path 
			File path = new File("Sounds\\2.wav");

			//checks if the file path that was inputted exists
			if (path.exists()) {
				
				// adds the music file to the audio system object
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
				
				// assigns the music into the clip
				setClip(AudioSystem.getClip());
				
				//open the audio file
				getClip().open(audioInput);

                // https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f);
				
				//loops the audio forever
				getClip().loop(Clip.LOOP_CONTINUOUSLY);
				
				//plays the audio file
				getClip().start();
			} else { //if the file path doesn't exist, print to the console
				System.out.println("file not found");
			}
		} catch (Exception e) { //catch all errors
			System.out.println(e);
		}

	}


    // Update player_turn, player_turn_name, turn_num, phase_num, etc.
    public void nextTurn() {

        
        // Update attributes for next turn
        setPlayer_turn((getPlayer_turn() + 1) % getNum_players());
        setTurn_num(getTurn_num() + 1);
        // System.out.printf("player turn: %d\n", getPlayer_turn());
        // System.out.printf("turn num: %d\n", getTurn_num());
        setPhase_ind(0);
        setPrev_selected(new CardGUI(0, 0, new Card(), new JPanel()));
        setNum_cards_planted(0);

        // Player curr_player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        // System.out.printf("turn num2: %d\n", getTurn_num());

        startPhase();
    }

    // When no more Cards in Deck, call this method, which calls endGame() in
    // Controller
    public void endGame() {
        
        getController().getView().getGameGUI().setEnabled(false);

        ArrayList<Player> players = getController().getModel().getGameModel().getPlayers().getPlayers();

        setGame_over(true);
        sellAllBeanFields();

        getClip().stop();
        getController().createEndGame();
    }

    // ---------- ACTION METHODS ---------- //
    // The below methods are used to perform actions in the game (e.g. draw, sell,
    // etc.).
    // Each of these methods is responsible for making the necessary updates to the
    // visual components they deal with.

    public Card drawCard() {
        Deck deck = getController().getModel().getGameModel().getDeck();

        deck.getDeckGUI().getNumCards().setText(Integer.toString(deck.getSize()));

        if(isGame_over()){
            return new Card();
        }

        // Since deck has ran out of cards, end game is triggered
        if (deck.getSize() == 0) {
            endGame();
        }

        return deck.draw();
    }

    public boolean sellBeanField(int index) {
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        Field field_to_sell = player.getFields().getFields().get(index);
        return sellBeanField(player, field_to_sell);
    }

    public boolean sellBeanField(Player player, Field field){

        sellSound();

        // System.out.println("selling bean field: "+field);
        // System.out.println("type bean field: "+field.getType());
        // System.out.println("quantity bean field: "+field.getSize());

        player.setCoins(player.getCoins() + field.sellValue());
        // Update CoinsGUI, since they now have more money
        updateCoinsGUI(player.getCoins());

        ArrayList<Card> cards_to_discard = field.removeAllCardsOnField();
        // Update FieldGUI, since now removed all its cards
        updateFieldGUI(field, field.getSize(), Card.getEmptyBeanFieldCard());

        DiscardedCards discarded_cards = getController().getModel().getGameModel().getDiscardedCards();

        for (Card card : cards_to_discard) {
            discarded_cards.push(card);
        }
        // Update DiscardedCardsGUI, since more cards discarded
        updateDiscardedCardsGUI(discarded_cards.peak());


        return true;
    }

    public boolean buyBeanField() {

        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        
        if (player.getCoins() < 3) {
            return false;
        }

        Field field = player.getFields().getFields().get(2);

        // 3rd field already purchased
        if(field.getUsable()){
            return false;
        }

        // Set it to usable
        field.setUsable(true);

        // Update 3rd FieldGUI, since now it's purchased
        updateFieldGUI(2, 0, Card.getEmptyBeanFieldCard());

        player.setCoins(player.getCoins() - 3);
        // Update CoinsGUI since they have less coins now
        updateCoinsGUI(player.getCoins());

        return true;
    }

    public boolean plantOfferedBeanCards(int offerAreaIndex, int fieldIndex) {
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());

        Field field = player.getFields().getFields().get(fieldIndex);
        OfferSlot offerSlot = getController().getModel().getGameModel().getOfferArea().getOfferSlots().get(offerAreaIndex);
        return plantOfferedBeanCards(offerSlot, field);
    }

    public boolean plantOfferedBeanCards(OfferSlot offerSlot, Field field){
        cardSound();
        // if (!field.getType().equals(offerSlot.getType())) {
        //     return false;
        // }

        ArrayList<Card> cards_to_plant = offerSlot.popEntireOfferSlot();
        // Update OfferSlotGUI, since it no longer contains any cards
        updateOfferSlotGUI(offerSlot, new Card());

        for (Card card : cards_to_plant) {
            field.plantBean(card);
        }
        // Update FieldGUI, since it has more Cards planted in it now
        Card card = field.peak();
        // System.out.println(" PLANT OFFERED BEAN CARD "+card);
        // System.out.println(card.getId());
        // System.out.println(card.getType());
        // System.out.println(" DONE PLANT OFFERED BEAN CARD");

        updateFieldGUI(field, field.getSize(), field.peak());
        return true;
    }

    public void discardOfferArea() {
        OfferArea offerArea = getController().getModel().getGameModel().getOfferArea();
        DiscardedCards discarded_cards = getController().getModel().getGameModel().getDiscardedCards();

        for (int i = 0; i < 3; i++) {

            ArrayList<Card> to_discard = offerArea.getOfferSlots().get(i).popEntireOfferSlot();
            // Update OfferSlotGUI since it no longer has any cards in it

            for (Card card : to_discard) {
                discarded_cards.push(card);
            }

            updateOfferSlotGUI(offerArea.getOfferSlots().get(i), new Card());
        }

        // Update DiscardedCardsGUI since the top card that was discarded is now
        // different
        updateDiscardedCardsGUI(discarded_cards.peak());
    }

    public boolean plantBeanCardFromHand(int fieldIndex) {
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        Field field = player.getFields().getFields().get(fieldIndex);
        return plantBeanCardFromHand(field);
    }

    public boolean plantBeanCardFromHand(Field field) {
        cardSound();
        // Note can only plant the card at the front of the hand

        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        Hand hand = player.getHand();



        Card card = hand.peak();



        // Should be same as Card object in card
        card = hand.takeCard(0);
        // Update HandGUI since now missing one Card
        updateHandGUI(player, hand.getCards());


        field.plantBean(card);
        // Update FieldGUI since (one more) card is planted in it
        updateFieldGUI(field, field.getSize(), field.peak());
        
        return true;
    }

    public boolean discardCard(Card card){
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        Hand hand = player.getHand();
        ArrayList<Card> cards = hand.getCards();

        for(int i=0; i<cards.size(); i++){
            if(cards.get(i)==card){
                return discardCard(i);
            }
        }

        return false;
    }

    public boolean discardCard(int index) {
        cardSound();

        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        DiscardedCards discarded_cards = getController().getModel().getGameModel().getDiscardedCards();

        Hand hand = player.getHand();

        Card card = hand.takeCard(index);
        // Update HandGUI, since now missing one more Card
        updateHandGUI(player, hand.getCards());

        discarded_cards.push(card);
        // Update DiscardedCardsGUI, since (one more) card is planted in it
        updateDiscardedCardsGUI(discarded_cards.peak());

        return true;
    }

    public void sellAllBeanFields(){

        ArrayList<Player> players = getController().getModel().getGameModel().getPlayers().getPlayers();

        for(Player player:players){
            
            for(int i=0; i<3; i++){
                Field field = player.getFields().getFields().get(i);
                if(!field.getUsable()){
                    continue;
                }

                sellBeanField(player, field);
            }
        }
    }

    public void populateOfferArea() {

        OfferArea offer_area = getController().getModel().getGameModel().getOfferArea();
        DiscardedCards discarded_cards = getController().getModel().getGameModel().getDiscardedCards();


        for (int i=0; i<offer_area.getOfferSlots().size(); i++) {

            OfferSlot offerSlot = offer_area.getOfferSlots().get(i);

            Card card = drawCard();

            // Returned null card, game has ended
            if(card.getType()==null){
                return;
            }

            offerSlot.pushCard(card);
            while (discarded_cards.getSize() > 0 && discarded_cards.peak().getType().equals(card.getType())) {
                Card card2 = discarded_cards.pop();
                // Update DiscardedCardsGUI, since one less card on it
                updateDiscardedCardsGUI(discarded_cards.peak());

                offerSlot.pushCard(card2);
            }

            // Update OfferSlotGUI, since more cards on it
            updateOfferSlotGUI(i, offerSlot.peak());
        }
    }

    public void drawCardToHand(){
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        drawCardToHand(player);
    }

    public void drawCardToHand(Player player) {

        // Deck deck = getController().getModel().getGameModel().getDeck();

        Card card = drawCard();
        
        // Null type card means game has ended
        if(card.getType()==null){
            return;
        }

        // System.out.println("past draw");

        // Update DeckGUI since one less card

        player.getHand().pushCard(card);
        // Update HandGUI since one more card in hand
        updateHandGUI(player, player.getHand().getCards());
    }

    // Adds action listener to a CardGUI object whenever Card object is added to
    // player’s hand. Similar to action listeners for OfferSlotGUIs and FieldGUIs.
    // E.g. drawCardToHand() should call whatever in GUI, then take CardGUI object
    // that was added to hand, pass into addMouseListenerCard(Card obj). Similarly,
    // action listener should call processEvent().
    public void addMouseListenerCard(CardGUI cardGUI) {
        // System.out.println("####### ADDING MOUSE LISTENER");
        // System.out.println("id: "+cardGUI.getCard().getId());
        // System.out.println("cardGUI: "+cardGUI);
        // System.out.println("cardGUI's parent panel: "+cardGUI.getParent_panel());
        // System.out.println("type: "+cardGUI.getCard().getType());
        // System.out.println("##############################");

        cardGUI.getImage().addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                // System.out.println("\n\n\n\n");
                // System.out.println("CLICKED CARD GUI:");
                // System.out.println("id: "+cardGUI.getCard().getId());
                // System.out.println("type: "+cardGUI.getCard().getType());
                // System.out.println("parent_panel class: "+cardGUI.getParent_panel().getClass());
                setPrev_selected(getCurr_selected());
                setCurr_selected(cardGUI);
                processEvent();
            }
        });
    }

    public boolean validBuyBeanField(){
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        
        if(player.getCoins()<3){
            PopUp.runPopUp("Purchasing 3rd bean field costs 3 coins");
            return false;
        }
        return true;
    }

    public void buyBeanFieldAction(){
        if(validBuyBeanField()){
            buyBeanField();
        }
    }

    public boolean validSellField(JPanel parent_panel){
        if(parent_panel instanceof FieldGUI){
            
            Field field = ((FieldGUI)parent_panel).getField();

            if(field.getSize()==0){
                PopUp.runPopUp("Field is empty");
                return false;
            } else if(field.getSize()==1){
                ArrayList<Field> fields = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn()).getFields().getFields();
                
                for(Field a_field:fields){
                    if(a_field.getUsable()&&a_field.getSize()!=1){
                        PopUp.runPopUp("Attempting to sell field with only one bean planted in it, but all other usable fields do not all have 1 bean planted in them");
                        return false;
                    }
                }
            } else if(!field.getUsable()){
                PopUp.runPopUp("This field hasn't yet been purchased");
                return false;
            }
            return true;
        } else {
            PopUp.runPopUp("Card selected is not planted in a field");
            return false;
        }
    }

    public void addActionListenerSellBeanButton(JButton sell_button){
        sell_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                clickSound();

                if(getCurr_selected()==null){
                    PopUp.runPopUp("No field selected to sell");
                    resetSelected();
                    return;
                }

                CardGUI curr_selected = getCurr_selected();
                
                JPanel parent_panel = curr_selected.getParent_panel();


                if(!validSellField(parent_panel)){
                    resetSelected();
                    return;
                }

                Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());

                sellBeanField(player, ((FieldGUI)parent_panel).getField());
                resetSelected();
            }
        });
    }

    public void addActionListenerEndPhaseButton(JButton end_phase_button){
        end_phase_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clickSound();

                resetSelected();
                incPhase();
                startPhase();
            }
        });
    }

    public void incPhase(){
        setPhase_ind(getPhase_ind()+1);
    }

    public void startPhase(){

        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());

        switch(getPhase_ind()){
            case 0:
            player.phase1();
            break;
            case 1:
            player.phase2();
            break;
            case 2:
            player.phase3();
            break;
            case 3:
            player.phase4();
            break;
        }

        if(getPhase_ind()==NUM_PHASES){

            // Draw two cards to the back of the player's hand
            drawCardToHand();
            drawCardToHand();

            nextTurn();
            return;
        }

        updateInstructionsGUI();
    }

    public void resetSelected(){
        unbold(getPrev_selected());
        unbold(getCurr_selected());

        setPrev_selected(null);
        setCurr_selected(null);
    }

    // Processes logic across sequential actions. Must be very careful managing
    // action listeners attached to Card objects (Card objects in hand should have
    // action listener, otherwise shouldn’t. Parent component would have action
    // listener e.g. in offer slot or field instead). Shouldn’t be too difficult -
    // just whenever Card object drawn from deck to hand, add action listener to
    // CardGUI. When this Card is removed from hand (either discarded or planted),
    // remove action listener. (Note: After card removed from hand, never returns to
    // a player’s hand). Note processEvent() should handle the highlighting/bolded
    // bordering of the JPanels as well.
    public void processEvent() {
        // System.out.println("process event: "+System.currentTimeMillis());
        // System.out.println("curr and prev selected: "+getCurr_selected()+" -- "+getPrev_selected());
        // System.out.println("curr selected is of type: "+getCurr_selected().getClass());
        // System.out.println("curr selected parent panel is of type: "+getCurr_selected().getParent_panel().getClass());
        
        bold(getCurr_selected());

        // if(getCurr_selected().getParent_panel() instanceof FieldGUI){
            // System.out.println(" SELECTED BEAN FIELD ARSEUOTHAO SENUT\n\n\n\n\n\n");
            // System.out.println("usable: "+((FieldGUI)getCurr_selected().getParent_panel()).getField().getUsable());
        // }

        if((getCurr_selected().getParent_panel() instanceof FieldGUI)){
            
            // Check if this field belongs to this player
            Field field = ((FieldGUI)getCurr_selected().getParent_panel()).getField();
            Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
            ArrayList<Field> fields = player.getFields().getFields();

            boolean field_belongs_curr_player = false;
            for(Field a_field:fields){
                if(a_field==field){
                    field_belongs_curr_player=true;
                }
            }

            if(!field_belongs_curr_player){
                
                PopUp.runPopUp("Field doesn't belong to current player");
                resetSelected();
                return;
            }

            // Check if this is 3rd unpurchased bean field
            if(field.getUsable()==false){
                if(validBuyBeanField()){
                    buyBeanField();
                }

                resetSelected();
                return;
            }
        }

        if((getCurr_selected().getParent_panel() instanceof HandGUIScrollPanel)){
            
            Hand hand = ((HandGUIScrollPanel)getCurr_selected().getParent_panel()).getHandGUI().getHand();
            Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());

            if(player.getHand()!=hand){
                PopUp.runPopUp("Card doesn't belong to current player");
                resetSelected();
                return;
            }
        }


        if(getPrev_selected()==null){
            if(getPhase_ind()!=2){
                return;
            }
        }

        // Check universal actions

        // Check purchase 3rd bean field
        
        // System.out.println("action: phase ind: "+getPhase_ind());
        switch(getPhase_ind()){
            
            case 0:
            phase1Action(getPrev_selected(), getCurr_selected());
            break;
            case 1:
            phase2Action(getPrev_selected(), getCurr_selected());
            break;
            case 2:
            phase3Action(getPrev_selected(), getCurr_selected());
            break;
            case 3:
            phase4Action(getPrev_selected(), getCurr_selected());
            break;
        }

        resetSelected();
    }

    public void bold(CardGUI cardGUI){
        if(cardGUI==null){
            return;
        }

        cardGUI.boldBorder();
    }

    public void unbold(CardGUI cardGUI){
        if(cardGUI==null){
            return;
        }

        cardGUI.removeBorder();
    }

    public boolean validPhase1Action(CardGUI prev, CardGUI curr){
        // Plant bean from offer area
        // prev must be from an offer slot
        // curr must be from a field

        JPanel prev_parent = prev.getParent_panel();
        JPanel curr_parent = curr.getParent_panel();

        
        if(!(prev_parent instanceof OfferSlotGUI)||!(curr_parent instanceof FieldGUI)){
            PopUp.runPopUp("Invalid action performed in phase 1");
            return false;
        }

        Card prev_card = prev.getCard();
        Card curr_card = curr.getCard();

        if(!prev_card.getType().equals(curr_card.getType())){
            return false;
        }

        return true;
    }

    public void phase1Action(CardGUI prev, CardGUI curr){
        if(!validPhase4Action(prev, curr)){
            return;
        }

        // System.out.println("phase 1 plant offered bean cards");
        plantOfferedBeanCards(((OfferSlotGUI)prev.getParent_panel()).getOfferSlot(), ((FieldGUI)curr.getParent_panel()).getField());
    }

    public boolean validPhase2Action(CardGUI prev, CardGUI curr){
        // Plant 1 or 2 cards from hand
        // prev must be first card from hand
        // curr must be from field

        JPanel prev_parent = prev.getParent_panel();
        JPanel curr_parent = curr.getParent_panel();

        if(!(prev_parent instanceof HandGUIScrollPanel)||!(curr_parent instanceof FieldGUI)){
            PopUp.runPopUp("Invalid selection for planting a card from your hand");
            return false;
        }

        Card prev_card = ((CardGUI)prev).getCard();

        Hand hand = ((HandGUIScrollPanel)prev_parent).getHandGUI().getHand();
        Field field = ((FieldGUI)curr_parent).getField();

        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        if(player.getHand()!=hand){
            PopUp.runPopUp("Card selected to plant must be from the current player's hand");
            return false;
        }

        if(hand.getSize()==0){
            PopUp.runPopUp("There are no cards in the current player's hand");
            return false;
        }

        // System.out.println(hand.peak());
        // System.out.println(prev_card);
        if(hand.peak()!=prev_card){
            PopUp.runPopUp("Card selected to plant from hand isn't the front card of the hand");
            return false;
        }

        if(field.getSize()!=0){
            if(!field.getType().equals(prev_card.getType())){
                PopUp.runPopUp("The field type doesn't match the card type");
                return false;
            }
        }

        setNum_cards_planted(getNum_cards_planted()+1);

        return true;
    }

    public void phase2Action(CardGUI prev, CardGUI curr){
        if(!validPhase2Action(prev, curr)){
            return;
        }

        plantBeanCardFromHand(((FieldGUI)curr.getParent_panel()).getField());
    
        if(getNum_cards_planted()>=2){

            incPhase();
            startPhase();
        }
    }
    
    public boolean validPhase3Action(CardGUI prev, CardGUI curr){
        // Discard none or 1 card from hand
		// curr must be from Hand

		
        // JPanel prev_parent = prev.getParent_panel();
        JPanel curr_parent = curr.getParent_panel();

        if(!(curr_parent instanceof HandGUIScrollPanel)){
            PopUp.runPopUp("Invalid card selected to discard");
            return false;
        }

        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        Hand hand = ((HandGUIScrollPanel)curr_parent).getHandGUI().getHand();
        if(player.getHand()!=hand){
            PopUp.runPopUp("Can only discard a card from the current player's hand");
            return false;
        }

        return true;
    }

    public void phase3Action(CardGUI prev, CardGUI curr){
        if(!validPhase3Action(prev, curr)){
            return;
        }

        discardCard(curr.getCard());
        incPhase();
        startPhase();
    }

    public boolean validPhase4Action(CardGUI prev, CardGUI curr){
        // Plant bean from offer area
        // prev must be from an offer slot
        // curr must be from a field

        JPanel prev_parent = prev.getParent_panel();
        JPanel curr_parent = curr.getParent_panel();

        
        // System.out.printf("validating phase offer area action %s %s\n", prev_parent.getClass(), curr_parent.getClass());
        // System.out.printf("ids %d %d\n", prev.getCard().getId(), curr.getCard().getId());

        if(!(prev_parent instanceof OfferSlotGUI)||!(curr_parent instanceof FieldGUI)){
            PopUp.runPopUp("Invalid planting from offer area action");
            // System.out.println("failed validating offer slot gui + fieldgui classes phase 4 action");
            return false;
        }

        Card prev_card = prev.getCard();
        Card curr_card = curr.getCard();

        OfferSlot offer_slot = ((OfferSlotGUI)prev_parent).getOfferSlot();
        Field field = ((FieldGUI)curr_parent).getField();

        ArrayList<Field> fields = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn()).getFields().getFields();
        boolean field_belongs_current_player = false;
        for(Field a_field:fields){
            if(a_field==field){
                field_belongs_current_player=true;
            }
        }
        if(!field_belongs_current_player){
            PopUp.runPopUp("Field doesn't belong to current player");
            return false;
        }

        if(!field.getUsable()){
            boolean purchased_bean_field = buyBeanField();
            if(purchased_bean_field){
                return true;
            }
            PopUp.runPopUp("Don't have enough money to purchase bean field");
            return false;
        }

        if(offer_slot.getSize()==0){
            PopUp.runPopUp("There are no cards in the offer slot");
            return false;
        }

        if(field.getSize()==0){
            return true;
        }
        
        // offer slot and field both have cards in them
        if(!prev_card.getType().equals(curr_card.getType())){
            PopUp.runPopUp("Type of card from offer slot and field don't match");
            return false;
        }

        return true;
    }

    public void phase4Action(CardGUI prev, CardGUI curr){
        if(!validPhase4Action(prev, curr)){
            return;
        }

        // System.out.println("phase 4 plant offered bean cards");
        plantOfferedBeanCards(((OfferSlotGUI)prev.getParent_panel()).getOfferSlot(), ((FieldGUI)curr.getParent_panel()).getField());
    }

    public void updateCoinsGUI(int n){
        CoinsGUI coins_GUI = getController().getView().getGameGUI().getPlayerGUIs().getPlayerGUIs().get(getPlayer_turn())
                .getCoinsGUI();
        coins_GUI.getNumCoins().setText(Integer.toString(n));
    }

    public void updateFieldGUI(int field_index, int num_cards, Card card){
        
        Field field = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn()).getFields().getFields().get(field_index);

        updateFieldGUI(field, num_cards, card);
    }

    public void updateFieldGUI(Field field, int num_cards, Card card){
        FieldGUI field_GUI = field.getFieldGUI();
        // field_GUI.getCardGUI().getCard().setId(-1000);
        tmp_card = new CardGUI(0, 20, card, field_GUI);
        addMouseListenerCard(tmp_card);
        field_GUI.setCardGUI(tmp_card);
        field_GUI.getNumCardsLabel().setText(Integer.toString(num_cards));

        field_GUI.revalidate();
        field_GUI.repaint();
    }

    public void updateOfferSlotGUI(int offerSlotIndex, Card card){
        OfferSlot offer_slot = getController().getModel().getGameModel().getOfferArea().getOfferSlots().get(offerSlotIndex);
        updateOfferSlotGUI(offer_slot, card);
    }

    public void updateOfferSlotGUI(OfferSlot offer_slot,  Card card){
        // System.out.println("updating offer slot gui with card: "+card.getType());
        
        OfferSlotGUI offer_slot_GUI = offer_slot.getOfferSlotGUI();

        // offer_slot_GUI.remove(offer_slot_GUI.getCardGUI());
        tmp_card = new CardGUI(0, 20, card, (JPanel)offer_slot_GUI);
        addMouseListenerCard(tmp_card);
        offer_slot_GUI.setCardGUI(tmp_card);
        
        offer_slot_GUI.getNumCardLabel().setText(Integer.toString(offer_slot.getSize()));
        offer_slot_GUI.revalidate();
        // offer_slot_GUI.add(offer_slot_GUI.getCardGUI());

        offer_slot_GUI.repaint();
    }

    public void updateDiscardedCardsGUI(Card card){
        DiscardedCardsGUI discarded_cards_GUI = getController().getView().getGameGUI().getDiscardedCardsGUI();
        // discarded_cards_GUI.remove(discarded_cards_GUI.getCardGUI());
        tmp_card = new CardGUI(0, 0, card, (JPanel)discarded_cards_GUI);
        addMouseListenerCard(tmp_card);
        discarded_cards_GUI.setCardGUI(tmp_card);
        // discarded_cards_GUI.add(discarded_cards_GUI.getCardGUI());
    }

    public void updateHandGUI(Player player, ArrayList<Card> cards){
        HandGUI hand_GUI = player.getHand().getHandGUI();
        HandGUIScrollPanel hand_GUI_scroll_panel = hand_GUI.getHandGUIScrollPanel();

        // Clear existing card GUIs from the panel
        hand_GUI_scroll_panel.removeAll();
        hand_GUI_scroll_panel.revalidate();

        GridBagConstraints gbc = hand_GUI.getGridBagConstraints();

        for (Card card : cards) {
            
            tmp_card = new CardGUI(0, 0, card, (JPanel) hand_GUI_scroll_panel);
            addMouseListenerCard(tmp_card);

            hand_GUI_scroll_panel.add(tmp_card.getImage(), gbc);
        }

        hand_GUI_scroll_panel.revalidate();
        hand_GUI_scroll_panel.repaint();
        hand_GUI.revalidate();
        hand_GUI.repaint();
    }

    public void updateInstructionsGUI(){
        Player player = getController().getModel().getGameModel().getPlayers().getPlayers().get(getPlayer_turn());
        int phase_ind = getPhase_ind();

        Instructions instructions = getController().getModel().getGameModel().getInstructions();
        InstructionsGUI instructions_GUI = getController().getView().getGameGUI().getInstructionsGUI();

        instructions_GUI.getPlayer_turn().setText(player.getName());
        instructions_GUI.getPhase_title().setText("Phase: "+(phase_ind+1));
        instructions_GUI.getInstructions().setText(instructions.getPhase_instructions().get(phase_ind));
    }

    private void clickSound() {

        //try catch in order to avoid crashing if any errors occur
        try {

            File path = new File("Sounds/0.wav");

            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);

                // https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f); 

                clip.start();

            } else { //if the file path doesn't exist, print to the console
                System.out.println("file not found");
            }
        } catch (Exception e) { //catch all errors
            System.out.println(e);
        }

    }

    private void cardSound() {

        //try catch in order to avoid crashing if any errors occur
        try {

            File path = new File("Sounds/1.wav");

            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);

                // https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f); 

                clip.start();

            } else { //if the file path doesn't exist, print to the console
                System.out.println("file not found");
            }
        } catch (Exception e) { //catch all errors
            System.out.println(e);
        }

    }

    private void sellSound() {

        //try catch in order to avoid crashing if any errors occur
        try {

            File path = new File("Sounds/4.wav");

            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);

                // https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f); 

                clip.start();

            } else { //if the file path doesn't exist, print to the console
                System.out.println("file not found");
            }
        } catch (Exception e) { //catch all errors
            System.out.println(e);
        }

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getPlayer_turn() {
        return player_turn;
    }

    public void setPlayer_turn(int player_turn) {
        this.player_turn = player_turn;
    }

    public int getTurn_num() {
        return turn_num;
    }

    public void setTurn_num(int turn_num) {
        this.turn_num = turn_num;
    }

    public int getPhase_ind() {
        return phase_ind;
    }

    public void setPhase_ind(int phase_ind) {
        this.phase_ind = phase_ind;
    }

    public CardGUI getPrev_selected() {
        return prev_selected;
    }

    public void setPrev_selected(CardGUI prev_selected) {
        this.prev_selected = prev_selected;
    }

    public int getNum_players() {
        return num_players;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    public CardGUI getCurr_selected() {
        return curr_selected;
    }

    public void setCurr_selected(CardGUI curr_selected) {
        this.curr_selected = curr_selected;
    }

    public CardGUI getTmp_card() {
        return tmp_card;
    }

    public void setTmp_card(CardGUI tmp_card) {
        this.tmp_card = tmp_card;
    }

    public static int getNumPhases() {
        return NUM_PHASES;
    }

    public boolean isGame_over() {
        return game_over;
    }

    public void setGame_over(boolean game_over) {
        this.game_over = game_over;
    }

    public static int getNumStartingCards() {
        return NUM_STARTING_CARDS;
    }

    public int getNum_cards_planted() {
        return num_cards_planted;
    }

    public void setNum_cards_planted(int num_cards_planted) {
        this.num_cards_planted = num_cards_planted;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    

}
