/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class is used to display to the user the amount of coins that they have
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CoinsGUI extends GUI{

	//the attributes that will be used in the coinsGUI class
	private int coins;
	private JLabel numCoins = new JLabel();
	// private JLabel coinImage;
	CardGUI coinImage;

	//constructor
	public CoinsGUI(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		//sets the properties of the coinsGUI class
		setLayout(null);
		setVisible(true);
		
		//sets bounds and image of the coinsGUI class
		// coinImage.setIcon(new ImageIcon(new ImageIcon("images/Back.png")
		// 		.getImage().getScaledInstance(w,h-15,Image.SCALE_DEFAULT)));
		// coinImage.setBounds(0,10,w,h);
		coinImage = new CardGUI(0, 20, Card.getCardBack(), this);
		
		//sets the text positioning, text, and bounds of the coins label
		numCoins = new JLabel(Integer.toString(coins),SwingConstants.CENTER);
		numCoins.setBounds(0,0,w,20);
		
		//adds the attributes to the panel
		add(coinImage);
		add(numCoins);

		
	}
	
	//getters and setters
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public JLabel getNumCoins() {
		return numCoins;
	}


	public CardGUI getCoinImage() {
		return coinImage;
	}

	public void setCoinImage(CardGUI coinImage) {
		this.coinImage = coinImage;
		revalidate();
		repaint();
	}

	public void setNumCoins(JLabel numCoins) {
		this.numCoins = numCoins;
		revalidate();
		repaint();
	}
	

}
 