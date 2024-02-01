/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Stuart, Toy
 * Significant help: none
 * Description: Jpanel so you can attach the scrollPanel to
 */
package View;

import Model.*;
import Controller.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class HandGUIScrollPanel extends JPanel {

	int id;
	static int num_objects = 0;

	//attributes
	private HandGUI handGUI;

	//contstructor
	public HandGUIScrollPanel(HandGUI handGui) {
		setId(num_objects++);
		//sets layout type
		setLayout(new GridBagLayout());
		setHandGUI(handGui);

		setVisible(true);
		setBackground(Color.red);
	}

	//getters and setters
	public HandGUI getHandGUI() {
		return handGUI;
	}

	public void setHandGUI(HandGUI handGUI) {
		this.handGUI = handGUI;
		revalidate();
		repaint();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	
}
