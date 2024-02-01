/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Stuart, Toy
 * Significant help: none
 * Description: This class contains the cards that the user owns, and it can be
 * controlled and scrolled through by the user
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class HandGUI extends GUI {

	Hand hand;

	//attributes of the handGUI class
	private HandGUIScrollPanel handGUIScrollPanel;
	private JScrollPane scroll = new JScrollPane();
	private GridBagConstraints gridBagConstraints = new GridBagConstraints();
	private ArrayList<CardGUI> cardGUIs = new ArrayList<CardGUI>();
	//private Hand hand;  //uncomment when needed
	
	@SuppressWarnings("static-access")
	public HandGUI(int x, int y, int width, int height) {
		super(x, y, width, height);

		setLayout(null);
		setSize(440,145);
		setVisible(true);

		//sets the insets
		gridBagConstraints.insets = new Insets(0,10,0,0);
		
		handGUIScrollPanel = new HandGUIScrollPanel(this);
		setHandGUIScrollPanel(handGUIScrollPanel);

		// JButton but = new JButton("aeou");
		// but.setBounds(0, 0, 500, 500);
		// scroll.add(but, gridBagConstraints);

		scroll = new JScrollPane(handGUIScrollPanel, scroll.VERTICAL_SCROLLBAR_NEVER, scroll.HORIZONTAL_SCROLLBAR_ALWAYS);
		// got from https://stackoverflow.com/questions/5371547/set-scrollbar-thickness
		scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0,15));
		scroll.setBounds(0,0,440,140);

		

		add(scroll);
		
	}

	//getters and setters
	public HandGUIScrollPanel getHandGUIScrollPanel() {
		return handGUIScrollPanel;
	}

	public void setHandGUIScrollPanel(HandGUIScrollPanel handGUIScrollPanel) {
		// if(getHandGUIScrollPanel()!=null&&getHandGUIScrollPanel().getParent()==this){
		// 	remove(getHandGUIScrollPanel());
		// }
		
		// System.out.println("hgsp 2"+handGUIScrollPanel);
		this.handGUIScrollPanel = handGUIScrollPanel;
		// add(getHandGUIScrollPanel());
		// System.out.println("hgsp 2.1"+handGUIScrollPanel);
		// repaint();
		// revalidate();
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public ArrayList<CardGUI> getCardGUIs() {
		return cardGUIs;
	}

	public void setCards(ArrayList<CardGUI> cardGUIs) {
		this.cardGUIs = cardGUIs;
	}

	public GridBagConstraints getGridBagConstraints() {
		return gridBagConstraints;
	}

	public void setGridBagConstraints(GridBagConstraints gridBagConstraints) {
		this.gridBagConstraints = gridBagConstraints;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

//	public Hand getHand() {
//		return hand;
//	}
//
//	public void setHand(Hand hand) {
//		this.hand = hand;
//	}

	
	
}