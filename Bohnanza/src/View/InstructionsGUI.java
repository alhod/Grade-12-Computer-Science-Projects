/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This is the class that displays the phase number, the instructions for each 
 * phase, and the current player's turn
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class InstructionsGUI extends GUI {

//	initialization of the different components that will be used in this
//	class, and are accessed by other classes.
	private JLabel player_turn = new JLabel();
	private JLabel phase_title = new JLabel();
	private JTextArea instructions = new JTextArea();
	
	//constructor
	public InstructionsGUI(int x, int y, int w, int h) {
		super(x, y, w, h);
		setLayout(null);
		
		//other elements
		JLabel crop_duster = new JLabel(new ImageIcon(
				new ImageIcon("Images/CropDuster.png").getImage()
				.getScaledInstance(800, 450, Image.SCALE_DEFAULT)));//scales the image to 800x450
		JLayeredPane layers = new JLayeredPane();
				
		//visibility
		setOpaque(false); //makes the panel invisible
		instructions.setOpaque(false);
		
		//bound setting
		crop_duster.setBounds(920, -120, 800, 450);
		phase_title.setBounds(1150, 220, 320, 60);
		player_turn.setBounds(1195, 120, 250, 70);
		instructions.setBounds(1150, 300, 320, 465);
		layers.setBounds(0, 0, getWidth(), getHeight());// Set the bounds to cover the whole panel
		
		//properties the instructions text area.
		instructions.setWrapStyleWord(true);
		instructions.setLineWrap(true);	
		instructions.setEditable(false);
		instructions.setHighlighter(null);
		instructions.setBackground(null);
		
		//border setting, with rounded border implementation
		instructions.setBorder(new RoundedBorder(15));
		phase_title.setBorder(new RoundedBorder(15));
		
		//font setting
		player_turn.setFont(new Font("Monospaced", Font.BOLD, 30));
		phase_title.setFont(new Font("BOLD", Font.BOLD, 30));
		instructions.setFont(new Font("Dialog", Font.BOLD, 20));
		

		//adding elements
		layers.add(player_turn, JLayeredPane.PALETTE_LAYER); // sets the player turn label on top of the crop duster
		layers.add(crop_duster, JLayeredPane.DEFAULT_LAYER); // sets crop duster behind other components
		add(layers);
		add(phase_title);
		add(instructions);
	}

	//getters and setters
	public JLabel getPlayer_turn() {
		return player_turn;
	}

	public void setPlayer_turn(JLabel player_turn) {
		this.player_turn = player_turn;
	}

	public JLabel getPhase_title() {
		return phase_title;
	}

	public void setPhase_title(JLabel phase_title) {
		this.phase_title = phase_title;
	}

	public JTextArea getInstructions() {
		return instructions;
	}

	public void setInstructions(JTextArea instructions) {
		this.instructions = instructions;
	}
	
	
	
	
}