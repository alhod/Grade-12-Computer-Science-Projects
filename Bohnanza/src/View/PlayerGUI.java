/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This is the class that holds ONE of the player's interactables, like the hand gui,
 * fields, and etc
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//class
@SuppressWarnings("serial")
public class PlayerGUI extends GUI {

	// attricbutes
	private FieldGUIs fieldGUIs;
	private HandGUI handGUI;
	private CoinsGUI coinsGUI;
	private JLabel playerName;
	// private int y;

	// constructor
	public PlayerGUI(int x, int y, int w, int h, boolean top) {
		super(x, y, w, h);

		// setBackground(Color.green);

		// sets the panel properties
		setLayout(null);
		setOpaque(false); // makes the background invisible

		// sets the player name characteristics
		playerName = new JLabel("", SwingConstants.CENTER);
		playerName.setFont(new Font("Calibri", Font.BOLD, 30));

		if (top) {
			// y=160;
			// for some reason, when setting these same values into setLocation() which uses
			// (x,y)
			// arguments too, it doesn't work.
			// fieldGUIs.setY(y + 30);
			// fieldGUIs.setX(100);
			fieldGUIs = new FieldGUIs(100, 160 + 30);
			// coinsGUI.setY(35);
			// coinsGUI.setX(0);
			// coinsGUI.setBounds(0, 35, coinsGUI.getWidth(), coinsGUI.getHeight());
			coinsGUI = new CoinsGUI(0, 35, 80, 140);
			// handGUI.setY(35);
			// handGUI.setX(100);
			// handGUI.setBounds(100, 35, handGUI.getWidth(), handGUI.getHeight());
			handGUI = new HandGUI(100, 35, 440, 140);
			playerName.setBounds(0, 0, 550, 40);
		} else {
			// creates and adds the different elements of the playerGUI.
			fieldGUIs = new FieldGUIs(100, 0);// no need to put width and height, as it is hard coded.
			coinsGUI = new CoinsGUI(470, 160, 80, 140);
			handGUI = new HandGUI(0, 160, 440, 140);

			playerName.setBounds(0, 305, 550, 40);
		}
		add(fieldGUIs);

		add(coinsGUI);

		add(handGUI);

		add(playerName);

	}

	// method that is called when you want to make that specific instance of the
	// playerGUI
	// appear on the top (the other player)
	public void swapBounds() {

		// // y = coinsGUI.getY();
		// // y=160;
		// //for some reason, when setting these same values into setLocation() which
		// uses (x,y)
		// //arguments too, it doesn't work.
		// // fieldGUIs.setY(y + 30);
		// // fieldGUIs.setX(100);
		// fieldGUIs.setBounds(100, 160+30, fieldGUIs.getWidth(),
		// fieldGUIs.getHeight());
		// // coinsGUI.setY(35);
		// // coinsGUI.setX(0);
		// coinsGUI.setBounds(0, 35, coinsGUI.getWidth(), coinsGUI.getHeight());
		// // handGUI.setY(35);
		// // handGUI.setX(100);
		// handGUI.setBounds(100, 35, handGUI.getWidth(), handGUI.getHeight());
		// playerName.setLocation(0,0);

	}

	public FieldGUIs getFieldGUIs() {
		return fieldGUIs;
	}

	public void setFieldGUIs(FieldGUIs fieldGUIs) {
		this.fieldGUIs = fieldGUIs;
	}

	public HandGUI getHandGUI() {
		return handGUI;
	}

	public void setHandGUI(HandGUI handGUI) {
		this.handGUI = handGUI;
	}

	public CoinsGUI getCoinsGUI() {
		return coinsGUI;
	}

	public void setCoinsGUI(CoinsGUI coinsGUI) {
		this.coinsGUI = coinsGUI;
	}

	public JLabel getPlayerName() {
		return playerName;
	}

	public void setPlayerName(JLabel playerName) {
		this.playerName = playerName;
	}

	// public int getY() {
	// return y;
	// }

	// public void setY(int y) {
	// this.y = y;
	// }

}
