/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This is the class that holds the back of a card, as an image,
 * and tells the players how many cards are remaining in the deck
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DeckGUI extends GUI{
	
	private int cards;
	// private JLabel back_of_card;
	private CardGUI cardGUI;
	private JLabel numCards;

	Deck deck;
	
	DeckGUI(int x, int y) {
		super(x, y, 80, 140);
		
		//visibility and layout changes
		setLayout(null);
		setVisible(true);
		
		//setting images
		// back_of_card = new JLabel(new ImageIcon(new ImageIcon("images/Back.png")
		// 		.getImage().getScaledInstance(w,h-20,Image.SCALE_DEFAULT))); //allows for a space of 20 pixels above the image, for the number count
		// setCardGUI(new CardGUI(0, 0, new Card(), this));
		setNumCards(new JLabel(Integer.toString(cards),SwingConstants.CENTER));
		
		//setting bounds
		// back_of_card.setBounds(0,10,w,h);
		getNumCards().setBounds(0,0,80,20);
		
		//adding elements
		// add(back_of_card);
		add(numCards);
		
	}

	//getters and setters
	public int getCards() {
		return cards;
	}

	public void setCards(int cards) {
		this.cards = cards;
	}

	public JLabel getNumCards() {
		return numCards;
	}

	public void setNumCards(JLabel numCards) {
		this.numCards = numCards;
	}

	public CardGUI getCardGUI() {
		return cardGUI;
	}

	public void setCardGUI(CardGUI cardGUI) {
		if(getCardGUI()!=null&&getCardGUI().getParent()==this){
			remove(getCardGUI());
		}
		
		this.cardGUI = cardGUI;
		getCardGUI().setLocation(0, 20);
		add(getCardGUI());
		revalidate();
		repaint();
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	
	
	

}
