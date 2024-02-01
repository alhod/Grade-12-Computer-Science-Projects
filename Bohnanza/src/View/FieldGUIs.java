/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class contains three fieldguis, and displays them with gaps
 * in an arraylist and adds then to the panel
 */
package View;

import Model.*;
import Controller.*;

import java.util.ArrayList;

import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class FieldGUIs extends GUI {

	//attributes of the class
	private ArrayList<FieldGUI> fieldGUIs = new ArrayList<FieldGUI>();
	// private JLayeredPane layers = new JLayeredPane(); //used to make sure nothing blocks another attribute
	
	//contructor
	public FieldGUIs(int x, int y) {
		super(x, y, 415, 145);
		
		//sets the bounds of the layered pane
		// layers.setBounds(0,0,415,145);
		
		//sets panel properties
		setOpaque(false);
		setLayout(null);
		setVisible(true);
		
		//this is the variable used to store the gap in between each of the fieldGUIs inside of
		//the fieldGUIs class
		int gap = 0; //gap set to  0 at first because the first field should start at the very left
		
		//a loop that creates a new field and sets its bounds according to the gap, and dds it to 
		//the layered pane
		for(int cards = 0; cards < 3; cards++) {

			FieldGUI fieldGUI = new FieldGUI(0 + gap, 0, 80, 145);
			getFieldGUIs().add(fieldGUI);
			add(fieldGUI);
			// layers.add(fieldGUIs.get(cards), JLayeredPane.DEFAULT_LAYER); 
			//this number represents a gap of 50 in between the cards, PLUS the width of each card
			//which is 80
			gap += 130;	
		}
		
		//add the layed pane with contains the fieldGUIs into this panel
		// add(layers);

	}

	//getters and setters
	public ArrayList<FieldGUI> getFieldGUIs() {
		return fieldGUIs;
	}

	public void setFieldGUIs(ArrayList<FieldGUI> fieldGUIs) {
		this.fieldGUIs = fieldGUIs;
	}
	

}
