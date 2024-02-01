/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class creates 3 instances of a playerGUI, and adds them into an arraylist
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PlayerGUIs extends GUI{
	
	//attributes
	private ArrayList<PlayerGUI> playerGUIs = new ArrayList<PlayerGUI>();

	//constructor
	PlayerGUIs(int x, int y, int w, int h){

		super(x,y,w,h);

		setLayout(null);
		
		// setBorder(new RoundedBorder(17));

		//sets the background to transparent
		setOpaque(false);
		
		

		//for each of the numbers of players (2) , create one instance of the playerGUI class
		for (int players = 0; players < 2; players++) {
			if(players == 0) {
				PlayerGUI playerGUI = new PlayerGUI(0,510,550,340, false);
				getPlayerGUIs().add(playerGUI);
				add(playerGUI); //add it to the label
			}else {
				PlayerGUI playerGUI = new PlayerGUI(0,0,550,340, true);
				playerGUIs.add(playerGUI); //do the same with the second player, but this time call the swap bounds method to put it at the top
				playerGUI.swapBounds();
				playerGUI.setBackground(Color.black);
				add(playerGUI);
			}
			
		}

		
		
	}

	//getters and setters
	public ArrayList<PlayerGUI> getPlayerGUIs() {
		return playerGUIs;
	}

	public void setPlayerGUIs(ArrayList<PlayerGUI> playerGUIs) {
		this.playerGUIs = playerGUIs;
	}
	
	

}
