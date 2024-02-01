/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class is a panel that contains information about the card (Card attribute)
 * , and its properties
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")

//card gui class
public class CardGUI extends GUI{

	//all the attributes of the CardGUI class that will be used in the GUI
	private Card card;
	private int width = 80;
	private int height = 125;
	private JLabel image;
	private JPanel parent_panel;
	private Border blackline = BorderFactory.createLineBorder(Color.black);
	
	public CardGUI(int x, int y, Card card, JPanel parent_panel) {
		super(x, y, 80, 125); //defaults the width and height to 80x125
		
		//gets the image and places it onto the instance of the cardGUI
		setLayout(null);
		setCard(card);
		JLabel card_image = new JLabel();
		card_image.setIcon(card.getImage().getIcon());
		setImage(card_image);
		getImage().setBounds(0,0,80,125);
		setParent_panel(parent_panel);
		add(getImage());
	}
	
	//method that sets a border around the image of the card
	public void boldBorder() {
		image.setBorder(blackline);
	}
	
	//method that removes border from the image
	public void removeBorder() {
		image.setBorder(null);
	}

	//getters and setters
	public Card getCard() {
		return card;
	}

	public JPanel getParent_panel() {
		return parent_panel;
	}

	public void setParent_panel(JPanel parent_panel) {
		this.parent_panel = parent_panel;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

	public Border getBlackline() {
		return blackline;
	}

	public void setBlackline(Border blackline) {
		this.blackline = blackline;
	}
	
	
	
}