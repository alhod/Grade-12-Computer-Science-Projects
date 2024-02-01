/*Date: Nov 26th
Course: ICS4U1-02
Name: Jay, Andrew
Significant help: Andrew
Description: This class is used for AI
*/
/* 
 * Jay Yuan 
 * AI Algorithm Jay 80% , Andrew 20% 
 * Andrew did the algorithm which helped
 * 50% of model (OfferArea, Deck DiscaredCards)
 * i was supposed to do the others but i just focused on AI
 * Who does what:
Andrew:
Everything in Controller
50% of Model
Application
Jay:
AI algorithm
50% of model
After done AI, work on OfferArea, Deck, and DiscardedCards
Saheer:
50% of View
GameGUI
Stuart:
50% of View
MenuGUI (once done works on GameGUI)
Due Date: 2023-11-25
ICS4U1-02, Mr.Fernandes
Bohnanza
The AI Algorithm works like a player, if the person whos playing 
dosent have a friend they can have a AI friend to play with.
List of features: feature : Andrew, gameplay logic, decision making based on scoring 
Major Skills used: Andrew , algoritms to help scores make decisions
Areas of Concern: checking opponents hand , some logic isnt fully finished 
*/

package Model;
import Controller.*;
import View.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AI extends Player {
    // private int id; // which player 0 is player 1, 1 player 2
    // private String name;
    // private Gameplay gameplay; // Gameplay Class 
    private double scoreThresholdPhase1;
    private double scoreThresholdPhase2;
    private int fieldIndex;
    private int totalNumCards;
    private int totalNumThisTypeCard;
    private double scoreThresholdPhase3;
    private double scoreThresholdPhase4;
    // Card card;

    public AI(int id, String name, Gameplay gameplay) {
        super(id, name, gameplay);
        // this.id = id; 
        // this.name = name;
        // this.gameplay = gameplay;
    }

    // Setters and getters for attributes
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
    public Gameplay getGameplay() {
        return gameplay;
    }
    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }
    public void setScoreThresholdPhase1(double scoreThresholdPhase1) {
        this.scoreThresholdPhase1 = scoreThresholdPhase1;
    }
    public double getScoreThresholdPhase1() {
        return scoreThresholdPhase1;
    }
    public void setScoreThresholdPhase2(double scoreThresholdPhase2) {
        this.scoreThresholdPhase2 = scoreThresholdPhase2;
    }
    public double getScoreThresholdPhase2() {
        return scoreThresholdPhase2;
    }
    public void setFieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }
    public int getFieldIndex() {
        return fieldIndex;
    }
    public void setTotalNumCards(int totalNumCards) {
        this.totalNumCards = totalNumCards;
    }
    public int getTotalNumCards() {
        return totalNumCards;
    }
    public void setTotalNumThisTypeCard(int totalNumThisTypeCard) {
        this.totalNumThisTypeCard = totalNumThisTypeCard;
    }
    public int getTotalNumThisTypeCard() {
        return totalNumThisTypeCard;
    }
    public void setScoreThresholdPhase3(double scoreThresholdPhase3) {
        this.scoreThresholdPhase3 = scoreThresholdPhase3;
    }
    public double getScoreThresholdPhase3() {
        return scoreThresholdPhase3;
    }
    public void setScoreThresholdPhase4(double scoreThresholdPhase4) {
        this.scoreThresholdPhase4 = scoreThresholdPhase4;
    }
    public double getScoreThresholdPhase4() {
        return scoreThresholdPhase4;
    }
    

    //make set and get methods for every single attribute 
    //startTurn dosent need set and get methods 
    
    // public void startTurn() {
    	
    // 	gameplay.getPhase_ind();
       
    //             phase1();phase2();phase3();phase4();
               
    //     }

    /*
     * Methods from Gameplay class that AI access +Gameplay(Controller controller)
     * +void startGame() # Actually starts the game +void nextTurn() # Update player_turn,
     * turn_num, phase_num, etc.
     * 
     */


    public void checkSellFields(){
        int curr_player = getGameplay().getPlayer_turn();
        ArrayList<Field> fields = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getFields().getFields();

        for(int i=0; i<fields.size(); i++){
            if(fields.get(i).sellValue()>0){
                getGameplay().sellBeanField(i);
            }
        }

    }


    public void phase1() {
        // System.out.println("AI PHASE 1");

        if(getGameplay().getTurn_num()==0){
            getGameplay().incPhase();
            getGameplay().startPhase();
            return;
        }

        int curr_player = getGameplay().getPlayer_turn();

        double scoreThresholdPhase1 = 2.0;

        ArrayList<OfferSlot> offerSlots = gameplay.getController().getModel().getGameModel().getOfferArea().getOfferSlots();

        //

        // MAKE SO GOES THROUGH EACH OFFER SLOT, GIVES CORRESPONDING SCORE, SORTS THIS

        // 

        ArrayList<OfferSlotScoreAndInd> offerSlotsSorted = new ArrayList<>();
        for(int i=0; i<offerSlots.size(); i++){
            if(offerSlots.get(i).getCards().size()==0){
                continue;
            }
            offerSlotsSorted.add(new OfferSlotScoreAndInd(scorePhase1(offerSlots.get(i).getCards().get(0)), i));
        }
        Collections.sort(offerSlotsSorted, new OfferSlotComparator());


        for (OfferSlotScoreAndInd offerSlotScoreAndInd:offerSlotsSorted) {
            OfferSlot offerSlot = offerSlots.get(offerSlotScoreAndInd.getInd());
            String cardType = offerSlot.getType(); // Obtain card type from the OfferSlot
            int cardCount = offerSlot.getSize(); // Obtain card count from the OfferSlot

            ArrayList<Field> fields = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getFields().getFields();

            
            // See if one of our fields is same type as offerslot. If so, plant it there.
            boolean planted = false;
            for(Field field:fields){
                if(!field.getUsable()){
                    continue;
                }

                if(field.getType()!=null&&field.getType().equals(cardType)){
                    getGameplay().plantOfferedBeanCards(offerSlot, field);
                    planted=true;
                    break;
                }
            }
            if(planted){
                continue;
            }

            
            double finalScore = offerSlotScoreAndInd.getScore();

            if(finalScore<scoreThresholdPhase1){
                break;
            }

            // Score meets threshold, no field with this type of card
            for(Field field:fields){
                if(field.getUsable()&&field.getSize()==0){
                    getGameplay().plantOfferedBeanCards(offerSlot, field);
                    break;
                }
            }

            

            // if (finalScore >= scoreThresholdPhase1) {
            //     // boolean match = checkOfferSlotMatch(offerSlotIndex, cardType);

            //     if (match) {
            //         gameplay.plantOfferedBeanCards(offerSlotIndex, offerSlotIndex); // Plant cards if they match
            //     } else {
            //         gameplay.discardOfferArea(); // Discard cards to the discard pile if they don't match
            //     }
            // } else {
            //     gameplay.discardOfferArea(); // Discard cards to the discard pile if below score threshold
            // }
        }

        checkSellFields();
        getGameplay().incPhase();
        getGameplay().startPhase();
    }

    // private boolean checkOfferSlotMatch(int offerSlotIndex, String cardType) {
    //     Field field = gameplay.getController().getModel().getGameModel().getPlayers().getPlayers().get(getId()).getFields().getFields().get(offerSlotIndex);
    //     String fieldCardType = field.getType(); // Get the card type of the field, in figma OfferSlot has type as lowercase should be fine, hopefully...
    //     return fieldCardType.equals(cardType); // Compare the field's card type with the offer slot's card type
    // }

    private double scorePhase1(Card card) {
        
        double score = 0.0;

        // scoring method
        // 1. See how many of these cards are in our hand
        // 2. See how many of these cards are in our fields
        // 3. Check probability draw same type of card from deck
        // 4. See how many of these cards are in opponents fields
        // 5. See how many of these cards are in opponents hand

        int curr_player = getGameplay().getPlayer_turn();
        ArrayList<Card> hand = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getHand().getCards();
        ArrayList<Field> fields = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getFields().getFields();
        int num_this_type_card_remaining_in_deck = getGameplay().getController().getModel().getGameModel().getDeck().getNum_this_type_card_in_deck().get(card.getType());
        int num_cards_in_deck = getGameplay().getController().getModel().getGameModel().getDeck().getSize();
        ArrayList<Card> opp_hand = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get((curr_player+1)%2).getHand().getCards();
        ArrayList<Field> opp_fields = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get((curr_player+1)%2).getFields().getFields();
        
        // 1.
        for(Card a_card:hand){
            score+=0.5;
        }

        // 2.
        for(Field field:fields){
            if(field.getUsable()&&field.getType()!=null&&field.getType().equals(card.getType())){
                score+=1.0;
            }
        }

        // 3.
        score+=8.0*((double)num_this_type_card_remaining_in_deck/num_cards_in_deck);

        // 4.
        for(Card a_card:opp_hand){
            score+=0.2;
        }

        // 5.
        for(Field a_field:opp_fields){
            if(a_field.getUsable()&&a_field.getType()!=null&&a_field.getType().equals(card.getType())){
                score+=1.0;
            }
        }

        return score;
    }

    

    /*private double calculateFinalScorePhase1(double initialScore, String cardType) {
       
        return initialScore;
    }
    */
	    
     //+boolean plantBeanCardFromHand(int fieldIndex)
     
    /*
     *for Matching cards 1 and 2 
     *we can check the card id:int? check the fields cards and if any match with our index 0 and 1 (1st and 2nd cards)
     *there is no method in Gameplay class that checks the fields 
     *Iterate over first 2 cards in hand (for loop): 
     *phase2 worst phase
     *we can only plant the first 2 cards of our hand 
     *check if any cards in our hand matches with the first and second cards, if there is a match / just a pair increase score of 
          * that type of bean by 2 use to check (cardCount), if theres more than 2    
     */

    public void phase2() {
        // System.out.println("AI PHASE 2");

        // Technically this should be done at end of phase 1
        getGameplay().discardOfferArea();


        int curr_player = getGameplay().getPlayer_turn();

        double scoreThresholdPhase2 = 1.0;
        
        Hand hand = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getHand();
        ArrayList<Field> fields = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getFields().getFields();
        
        for(int i=0; i<2; i++){
            
            Card card = hand.peak();

            // Check if we already have field with this card
            boolean planted=false;
            for(Field field:fields){
                if(!field.getUsable()){
                    continue;
                }

                if(field.getType()!=null&&field.getType().equals(card.getType())){
                    getGameplay().plantBeanCardFromHand(field);
                    planted=true;
                    break;
                }
            }
            if(planted){
                continue;
            }

            double score = scorePhase2(card);
            if(score<scoreThresholdPhase2){
                break;
            }

            // No fields have card of this type, so to plant, must plant in empty purchased field
            for(Field field:fields){
                if(field.getUsable()&&field.getSize()==0){
                    getGameplay().plantBeanCardFromHand(field);
                    break;
                }
            }
        }


        checkSellFields();
        getGameplay().incPhase();
        getGameplay().startPhase();
    }

    // private boolean plantBeanCardFromHand(int fieldIndex, ArrayList<Double> scores, double scoreThresholdPhase2) {
    //     int maxScoreIndex = getMaxScoreIndex2(scores);
    //     if (maxScoreIndex >= 0 && scores.get(maxScoreIndex) > scoreThresholdPhase2) {
    //         return gameplay.plantBeanCardFromHand(maxScoreIndex, fieldIndex);
    //     }
    //     return false; // Return false if no card is planted
    // }

    private double scorePhase2(Card card) {

        double score = -0.7;

        // Scoring method:
        // 1. Increment the score by 0.7 for each card in the AIs hand of the same type
        // 2. Increase the score by 8 x probability of drawing a card of same type from deck
        // 3. If this is the first card in the AIs hand, increase the score by the score of the next card in hand (if there is one) divided by 5

        // Get total number of this type of card in your hand
        int cardCount = -1;
        for(Card a_card:hand.getCards()){
            if(a_card.getType().equals(card.getType())){
                cardCount++;
            }
        }
        int curr_player = getGameplay().getPlayer_turn();
        int num_this_type_card_remaining_in_deck = getGameplay().getController().getModel().getGameModel().getDeck().getNum_this_type_card_in_deck().get(card.getType());
        int num_cards_in_deck = getGameplay().getController().getModel().getGameModel().getDeck().getSize();
        Hand hand = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getHand();
        

        // 1.
        score+=0.7*((double)cardCount);

        // 2.
        score+=8.0*((double)num_this_type_card_remaining_in_deck/num_cards_in_deck);

        // 3.
        if(hand.getSize()>=1&&hand.getCards().get(0)==card){
            if(hand.getSize()>=2){
                score+=scorePhase2(hand.getCards().get(1));
            }
        }

        return score;
    }

    // private int getMaxScoreIndex2(ArrayList<Double> scores) {
    //     double maxScore = Double.MIN_VALUE;
    //     int maxIndex = -1;

    //     for (int i = 0; i < scores.size(); i++) {
    //         if (scores.get(i) > maxScore) {
    //             maxScore = scores.get(i);
    //             maxIndex = i;
    //         }
    //     }

    //     return maxIndex;
    // }

        	/*
        	 * 	Create an ArrayList of doubles. Each index of this array list corresponds to an index of a Card object in the AIs hand, and will store the score for that card.
        	 * 	Iterate over each card in the AIs hand (obviously if there are no cards this loop shouldn’t run)
        	 *	 	Determine score for the current card (double) that for loop is iterating over
        	 * 	Iterate over all the scores
        	 * 		Locate the maximum score and the index associated with this score
        	 * If the maximum score is less than the score threshold, return from this method
        	 * Discard the card in the AIs hand that is associated with this score (we know which card is associated since we store the index of the max score as well)
        	 * in phase 3 we discard a card from our hand 
        	 */
   
    public void phase3() {
        // System.out.println("AI PHASE 3");
        double scoreThresholdPhase3 = 4.0; // Score threshold for keeping the card

        ArrayList<Double> scores = new ArrayList<>();
        ArrayList<Card> hand = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(getId()).getHand().cards; // Get the AI's hand

        for (int i = 0; i < hand.size(); i++) {
            double score = scorePhase3(hand.get(i));
            scores.add(score); // Store scores for each card in the hand
        }

        int min_ind = 0;
        double min_score = Double.MAX_VALUE;

        for(int i=0; i<scores.size(); i++){
            if(scores.get(i)<min_score){
                min_score=scores.get(i);
                min_ind=i;
            }
        }

        if(scores.get(min_ind)<scoreThresholdPhase3){
            getGameplay().discardCard(min_ind);
        }

        // if (maxScoreIndex >= 0 && scores.get(maxScoreIndex) < scoreThresholdPhase3) {
        //     int aiHandSize = hand.size();
        //     if (aiHandSize > 0) {
        //         // Set the score of the last card in the AI's hand to 0.5
        //         scores.set(aiHandSize - 1, 0.5);
        //     }
        //     gameplay.discardCard(maxScoreIndex); //discard the card associated with the maximum score
        // }

        checkSellFields();
        getGameplay().incPhase();
        getGameplay().startPhase();
    }

    private double scorePhase3(Card card) {
        double score = 0.5;

        // Scoring method:
        //  1. Increase score by 0.5 for each card in hand with same type
        //  2. Increase score by 0.7 for each card in all our fields with same type
        //  3. Increase score by 8 * probability that this card is drawn again

        int curr_player = getGameplay().getPlayer_turn();
        Hand hand = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getHand();
        ArrayList<Field> fields = getGameplay().getController().getModel().getGameModel().getPlayers().getPlayers().get(curr_player).getFields().getFields();
        int num_this_type_card_remaining_in_deck = getGameplay().getController().getModel().getGameModel().getDeck().getNum_this_type_card_in_deck().get(card.getType());
        int num_cards_in_deck = getGameplay().getController().getModel().getGameModel().getDeck().getSize();

        // 1.
        for(Card a_card:hand.getCards()){
            if(a_card.getType().equals(card.getType())){
                score+=0.5;
            }
        }

        // 2.
        for(Field field:fields){
            if(field.getType()!=null&&field.getType().equals(card.getType())){
                score+=0.7;
            }
        }

        // 3.
        score+=4.0*((double)num_this_type_card_remaining_in_deck/num_cards_in_deck);

        return score;
    }

    // private int getMaxScoreIndex3(ArrayList<Double> scores) {
    //     double maxScore = Double.MIN_VALUE;
    //     int maxIndex = -1;

    //     for (int i = 0; i < scores.size(); i++) {
    //         if (scores.get(i) > maxScore) {
    //             maxScore = scores.get(i);
    //             maxIndex = i;
    //         }
    //     }

    //     return maxIndex;
    // }

    /*
     * Algorithm: Phase 4() Draw, plant, and offer beans
b void populateOfferArea(); draw 3 new cards from deck → check discard pile if it matches any of the draw cards add it continue until no match
r Iterate over all OfferSlots:
o If the current OfferSlot we’re iterating over contains a type of bean we already have planted in a field, plant the beans in this OfferSlot into that field.
o Create an ArrayList that associates an OfferSlot index with their corresponding score
o Iterate over all OfferSlots (again) that still contain beans:
o Determine the score for the current OfferSlot
o Add the OfferSlot index + score into the ArrayList, and sort the ArrayList descending order based on score
o Use some indicator that an OfferSlot is empty for score (e.g. -1. This may be unnecessary, as whether offer slot is considered determined based on comparison with score threshold, and will naturally ignore empty offer slots if score is -1)
o While we have available Fields, iterate through the ArrayList
o For the current index + score pair in iteration, if the score is less than the score threshold, break from this loop
o Add the cards from the OfferSlot associated with the index into the first available field
o
This should reduce the number of available fields by 1
Draw 2 cards and put them at the back of the AIs hand
     */
    
    public void phase4() {
        // System.out.println("AI PHASE 4");

        gameplay.populateOfferArea(); // Draw 3 new cards from the deck

        gameplay.drawCardToHand(); 
        gameplay.drawCardToHand();

        // Since phase 4 is basically same process as phase 1
        phase1();

        
        // double scoreThresholdPhase4 = 0.4; // Score threshold for planting


        // List<OfferSlot> offerSlots = gameplay.getController().getModel().getGameModel().getOfferArea().getOfferSlots();

        // for (int offerSlotIndex = 0; offerSlotIndex < offerSlots.size(); offerSlotIndex++) {
        //     OfferSlot offerSlot = offerSlots.get(offerSlotIndex);
        //     String cardType = offerSlot.getType(); // Obtain card type from the OfferSlot
        //     int cardCount = offerSlot.getSize(); // Obtain card count from the OfferSlot

        //     double score = scoreForPhase4(cardCount, cardType);

        //     if (score >= scoreThresholdPhase4) {
        //         gameplay.plantOfferedBeanCards(offerSlotIndex, offerSlotIndex); // Plant beans if score threshold is met
        //     } else {
        //         // Leave cards in the offer area if below score threshold
        //     }
        // }

        //draw the 2 cards into AI hands 
    }
    
    // private double scoreForPhase4(int cardCount, String cardType) {
    //     double score = 0.0;


    //     if (cardCount > 2) {
    //         score = 3.0; // Highest score for the card with the most cards
    //     } else if (cardCount == 2) {
    //         score = 2.0; // Lower score for cards with fewer cards
    //     } else {
    //         score = 1.0; // Lowest score for cards with a single card
    //     }

    //     return score;
    // 	}
    // }
}


class OfferSlotScoreAndInd {

    double score;
    int ind;

    public OfferSlotScoreAndInd(double score, int ind){
        setScore(score);
        setInd(ind);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }



    
}


// https://www.geeksforgeeks.org/how-to-sort-arraylist-using-comparator/
class OfferSlotComparator implements Comparator<OfferSlotScoreAndInd> { 
  
    // override the compare() method 
    public int compare(OfferSlotScoreAndInd s1, OfferSlotScoreAndInd s2) 
    { 
        if (s1.score==s2.score) 
            return 0; 
        else if (s1.score > s2.score) 
            return 1; 
        else
            return -1; 
    } 
} 