/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class just displays the image of the most recently discarded card. Very
 * empty, as it is primarily controlled by the controller using the CardGUI attribute.
 */
package View;

import Model.*;
import Controller.*;

@SuppressWarnings("serial")
public class DiscardedCardsGUI extends GUI{
	
	//card gui attribute for the discarded cards, modified and accessed by controller
	private CardGUI cardGUI;
	
	//constructor
	DiscardedCardsGUI(int x, int y) {
		super(x, y, 80, 125);
	}

	public CardGUI getCardGUI() {
		return cardGUI;
	}

	public void setCardGUI(CardGUI cardGUI) {
		if(getCardGUI()!=null&&getCardGUI().getParent()==this){
			remove(getCardGUI());
		}
		
		this.cardGUI = cardGUI;
		add(getCardGUI());
		revalidate();
		repaint();
	}
	
	

}
