/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This is the class that represents a singular field that a player
 * can add cards to.
 */
package View;

import Model.*;
import Controller.*;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class FieldGUI extends GUI{
	
	private int numCards;
	private CardGUI cardGUI;
	private JLabel numCardsLabel  = new JLabel(Integer.toString(numCards), SwingConstants.CENTER);
	private Field field;
	
	//constructor
	public FieldGUI(int x, int y, int w, int h) {
	    super(x, y, w, h);
	    
	    //sets the properties of the panel
	    setLayout(null);
	    setVisible(true);

	    //sets the bounds of the label and adds it to the panel
	    numCardsLabel.setBounds(0, 0, 80, 20);
	    add(numCardsLabel);
	}

	//getters and setters
	public int getNumCards() {
		return numCards;
	}

	//whenever a new value of cards is set, it updates the label that displays that number
	public void setNumCards(int numCards) {
		this.numCards = numCards;
		numCardsLabel.setText(Integer.toString(numCards));
	}

	public CardGUI getCardGUI() {
		return cardGUI;
	}

	public void setCardGUI(CardGUI cardGUI) {
		// if(getCardGUI()!=null&&getCardGUI().getParent()==this){
		// 	remove(getCardGUI());
		// }
		removeAll();
		add(getNumCardsLabel());
		
		// System.out.println("field - set card gui type: "+cardGUI.getCard().getType());
		// System.out.println("parent panel: "+cardGUI.getParent_panel());

		this.cardGUI = cardGUI;
		// getCardGUI().setLocation(0, 0);
		add(getCardGUI());
		revalidate();
		
	}

	public JLabel getNumCardsLabel() {
		return numCardsLabel;
	}

	public void setNumCardsLabel(JLabel numCardsLabel) {
		this.numCardsLabel = numCardsLabel;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	
	

}
