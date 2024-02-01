package Model;

// import Controller.*;
// import View.*;
import TextFiles.*;
import View.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Deck {

    public static int total_num_cards;
    HashMap<String, Integer> num_this_type_card_in_deck;
    DeckGUI deckGUI;

    Stack<Card> cards;

    public Deck() {
        setNum_this_type_card_in_deck(new HashMap<String, Integer>());
        setTotal_num_cards(0);
        // setCard_type_to_quantity(new HashMap<String, Integer>());
        setCards(new Stack<Card>());
        initDeck();
    }

    // Gets all cards from .txt file, creates Card objects, puts Card objects in
    // ArrayList and shuffles them, puts them all into Stack
    public void initDeck() {

        ArrayList<Card> entire_deck = new ArrayList<Card>();

        // Get deck information
        // From here: https://www.w3schools.com/java/java_files_read.asp
        try {

            // Reads file
            // File file = new File("C:\\Users\\andar\\Grade12ComSci\\Bohnanza\\Model\\AllCards.txt");
            // System.out.println(Paths.get("").toAbsolutePath().toString()+"\\Bohnanza\\TextFiles\\Cards.txt");
            // From here: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
            // File file = TextFiles.getDeck();  // new File("src\\TextFiles\\Deck.txt");
            File file = new File("src\\TextFiles\\Deck.txt");
            Scanner scanner = new Scanner(file);

            // Goes through every line
            while (scanner.hasNextLine()) {

                // Gets type
                String type = scanner.nextLine();

                // Get quantity
                int quantity = Integer.valueOf(scanner.nextLine());

                ArrayList<Integer>num_card = new ArrayList<>();
                ArrayList<Integer>coin_worth = new ArrayList<>();

                for(int i=0; i<4; i++){
                    String conversion = scanner.nextLine();

                    int ind_split=1;
                    for(int j=0; j<conversion.length(); j++){
                        if(conversion.charAt(j)==' '){
                            ind_split=j;
                            break;
                        }
                    }

                    num_card.add(Integer.valueOf(conversion.substring(0, ind_split)));
                    coin_worth.add(Integer.valueOf(conversion.substring(ind_split+1, conversion.length())));
                }

                

                for(int i=0; i<quantity; i++){
                    entire_deck.add(new Card(getTotal_num_cards(), type, quantity));
                    setTotal_num_cards(getTotal_num_cards()+1);
                }

                getNum_this_type_card_in_deck().put(type, quantity);

                // getCard_type_to_quantity().put(type, quantity);
                ArrayList<ArrayList<Integer>>tmp = new ArrayList<>();
                tmp.add(num_card);
                tmp.add(coin_worth);
                Card.getCard_type_to_sell().put(type, tmp);
            }

            // Closes file reader
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cards.txt access messed up");
            e.printStackTrace();
        }

        // Shuffles deck
        Collections.shuffle(entire_deck);
        
        for(int i=0; i<entire_deck.size(); i++){
            cards.add(entire_deck.get(i));
        }

        displayDeck();
    }

    public Card draw(){
        if(getCards().size()==0){
            return new Card();
        }

        Card card = getCards().pop();

        getNum_this_type_card_in_deck().put(card.getType(), getNum_this_type_card_in_deck().get(card.getType())-1);

        return card;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }

    public static int getTotal_num_cards() {
        return total_num_cards;
    }

    public static void setTotal_num_cards(int total_num_cards) {
        Deck.total_num_cards = total_num_cards;
    }

    // public static HashMap<String, Integer> getCard_type_to_quantity() {
    //     return card_type_to_quantity;
    // }

    // public static void setCard_type_to_quantity(HashMap<String, Integer> card_type_to_quantity) {
    //     Deck.card_type_to_quantity = card_type_to_quantity;
    // }

    public void displayDeck(){
        // Display deck

        ArrayList<Card>tmp = new ArrayList<Card>();
        while(cards.size()>0){
            tmp.add(cards.pop());
        }

        for(int i=0; i<tmp.size(); i++){
            // System.out.println(tmp.get(i));
            cards.push(tmp.get(i));
        }
    }

    public int getSize(){
        return getCards().size();
    }

    public DeckGUI getDeckGUI() {
        return deckGUI;
    }

    public void setDeckGUI(DeckGUI deckGUI) {
        this.deckGUI = deckGUI;
    }

    public HashMap<String, Integer> getNum_this_type_card_in_deck() {
        return num_this_type_card_in_deck;
    }

    public void setNum_this_type_card_in_deck(HashMap<String, Integer> num_this_type_card_in_deck) {
        this.num_this_type_card_in_deck = num_this_type_card_in_deck;
    }


    

    

    
}
