/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Stuart, Toy
 * Significant help: none
 * Description: This is the class that holds the offer area slots
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class OfferAreaGUI extends GUI {

	//attributes
	private ArrayList<OfferSlotGUI> offerSlotGUIs = new ArrayList<OfferSlotGUI>();
	
	//constructor
	public OfferAreaGUI(int x, int y) {
		super(x, y, 261, 145);

		//adds it tot the panel
		this.setSize(261,145);
		this.setVisible(true);
		this.setLayout(null);
		
		//loops that creates, and adds the offer slot.
		for (int i = 0; i < 3; i++) {
			OfferSlotGUI offerSlotGUI = new OfferSlotGUI(i*87,0);
			getOfferSlotGUIs().add(offerSlotGUI);
			add(offerSlotGUI);
		}
		// setBackground(Color.red);
	}

	//getters and setters
	public ArrayList<OfferSlotGUI> getOfferSlotGUIs() {
		return offerSlotGUIs;
	}

	public void setOfferSlotGUIs(ArrayList<OfferSlotGUI> offerSlotGUIs) {
		this.offerSlotGUIs = offerSlotGUIs;
	}	
	
}
