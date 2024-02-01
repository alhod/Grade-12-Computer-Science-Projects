/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class simply pops up a message dialog that any class can create with 
 * the string they want disaplayed. once ok is clicked, the message box closes
 */
package View;

import Model.*;
import Controller.*;

import javax.swing.JOptionPane;

public class PopUp{
	
	//method that needs to be called with the string argument to open the message dialog
	public static void runPopUp(String text) {
        JOptionPane.showMessageDialog(null, text, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

}
