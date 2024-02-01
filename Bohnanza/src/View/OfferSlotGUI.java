/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Stuart, Toy
 * Significant help: none
 * Description: Each individual offer slot
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class OfferSlotGUI extends GUI {
	
	int num_card; // number of each card in the offer slot

	JLabel numCardLabel = new JLabel("0", SwingConstants.CENTER); 
	OfferSlot offerSlot;
	CardGUI cardGUI; //update
	// CardGUI cardGUI = new CardGUI(0,0, new Card(), null); //update

	public OfferSlotGUI(int x, int y) {
		super(x, y, 80, 145);
		
		numCardLabel = new JLabel(Integer.toString(num_card),SwingConstants.CENTER);
        numCardLabel.setBounds(0,0,80,20);

//		numCardLabel.setText(String.valueOf(num_card));
		// numCardLabel.setBounds(0,0,80,20);
		// numCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// cardGUI = new CardGUI(0, 0, Card.getEmptyBeanFieldCard(), cardGUI);
		// add(cardGUI);
		// cardGUI.setVisible(true);
		// revalidate();


		add(numCardLabel);
		// setBackground(Color.black);

		//adds it to the panel
		// this.add(cardGUI);
		// cardGUI.setLocation(0, 20);
		this.add(numCardLabel);
		this.setSize(80,145);
		this.setVisible(true);
		this.setLayout(null);
		
	}

	public int getNum_card() {
		return num_card;
	}

	public void setNum_card(int num_card) {
		this.num_card = num_card;
	}

	public JLabel getNumCardLabel() {
		return numCardLabel;
	}

	public void setNumCardLabel(JLabel numCardLabel) {
		this.numCardLabel = numCardLabel;
	}

//	public OfferSlot getOfferSlot() {
//		return offerSlot;
//	}
//
//	public void setOfferSlot(OfferSlot offerSlot) {
//		this.offerSlot = offerSlot;
//	}

	public CardGUI getCardGUI() {
		return cardGUI;
	}

	public void setCardGUI(CardGUI cardGUI) {
		// if(getCardGUI()!=null&&getCardGUI().getParent()==this){
		// 	remove(getCardGUI());
		// }
		// if(getCardGUI()!=null){
		// 	remove(getCardGUI());
		// }
		removeAll();
		add(getNumCardLabel());
		
		this.cardGUI = cardGUI;
		add(getCardGUI());
		getCardGUI().revalidate();
		getCardGUI().setVisible(true);
		
		
		// 😭😭😭😭😭😭😭😭😭😭😭
		getCardGUI().setBackground(Color.white);


		revalidate();
	}

	public OfferSlot getOfferSlot() {
		return offerSlot;
	}

	public void setOfferSlot(OfferSlot offerSlot) {
		this.offerSlot = offerSlot;
	}


	
}