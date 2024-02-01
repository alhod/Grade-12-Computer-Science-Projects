package Model;


// import Controller.*;
// import View.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Card {

    public static final int CARD_WIDTH = 80;
    public static final int CARD_HEIGHT = 125;

    public static final String BEAN_IMG_PATH = "Images/";
    
    int id;
    String type;
    static HashMap<String, ArrayList<ArrayList<Integer>>>card_type_to_sell = new HashMap<String, ArrayList<ArrayList<Integer>>>();
    JLabel image;
    int total_num_this_type_card;

    public Card(int id, String type, int total_num_this_type_card){
        setId(id);
        setType(type);
        setTotal_num_this_type_card(total_num_this_type_card); 
        setImage(getCardLabel(getType()));
    }

    // Invalid card
    public Card(){
        setId(-1);
        setType(null);
        setTotal_num_this_type_card(0);
        setImage(new JLabel());
    }

    public static JLabel getCardLabel(String type){
        JLabel image = new JLabel();
        ImageIcon img = new ImageIcon(BEAN_IMG_PATH+type+".png");
        Image newimg = img.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(newimg));
        return image;
    }

    public static Card getEmptyBeanFieldCard(){
        Card card = new Card();
        card.setType("EmptyBeanField");
        card.setImage(getCardLabel("EmptyBeanField"));
        return card;
    }

    public static Card getUnpurchasedBeanFieldCard(){
        Card card = new Card();
        card.setType("UnpurchasedBeanField");
        card.setImage(getCardLabel("UnpurchasedBeanField"));
        return card;
    }

    public static Card getDeckImage(){
        Card card = new Card();
        card.setType("deck");
        card.setImage(getCardLabel("deck"));
        return card;
    }

    public static Card getCardBack(){
        Card card = new Card();
        card.setType("Back");
        card.setImage(getCardLabel("Back"));
        return card;
    }

    public static int sell(String type, int quantity){
        // System.out.println("selling");
        // System.out.println(getCard_type_to_sell());
        // System.out.println("type: "+type);
        // System.out.println("quantity: "+quantity);

        if(quantity==0){
            return 0;
        }
        
        if(!getCard_type_to_sell().containsKey(type)){
            return -999;
        }
        
        // ind 0 --> number of this card
        // ind 1 --> coins it's sold for
        ArrayList<ArrayList<Integer>> sell_info = getCard_type_to_sell().get(type);
        
        if(0<=quantity&&quantity<sell_info.get(0).get(0)){
            return 0;
        }
        for(int i=0; i<sell_info.get(0).size()-1; i++){
            if(sell_info.get(0).get(i)<=quantity&&quantity<sell_info.get(0).get(i+1)){
                return sell_info.get(1).get(i);
            }
        }
        if(sell_info.get(0).get(sell_info.get(0).size()-1)<=quantity){
            return sell_info.get(1).get(sell_info.get(0).size()-1);
        }

        // invalid quantity value
        return -999;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JLabel getImage() {
        return image;
    }

    public void setImage(JLabel image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Card [id=" + id + ", type=" + type + ", image=" + image + "]";
    }

    public static HashMap<String, ArrayList<ArrayList<Integer>>> getCard_type_to_sell() {
        return card_type_to_sell;
    }

    public static void setCard_type_to_sell(HashMap<String, ArrayList<ArrayList<Integer>>> card_type_to_sell) {
        Card.card_type_to_sell = card_type_to_sell;
    }

    public int getTotal_num_this_type_card() {
        return total_num_this_type_card;
    }

    public void setTotal_num_this_type_card(int total_num_this_type_card) {
        this.total_num_this_type_card = total_num_this_type_card;
    }





    
    
}
