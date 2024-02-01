/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This is the class where all the game action takes place. The instances of all
 * interactables are found here. Such as the buttons, and the playerGUIs.
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Font;
import java.io.File;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {

	//initialization of all the attributes that will be used in the GameGUI class
	JLabel title = new JLabel();
	OfferAreaGUI offerAreaGUI = new OfferAreaGUI(630, 350);
	DiscardedCardsGUI discardedCardsGUI = new DiscardedCardsGUI(960, 355);
	DeckGUI deckGUI = new DeckGUI(480, 355);
	PlayerGUIs playerGUIs = new PlayerGUIs(490, 0, 550, 900);
	JLabel background = new JLabel();
	JButton sell_beans_button = new JButton("Sell Beans");
	JButton end_phase_button = new JButton("End Phase");
	InstructionsGUI instructionsGUI = new InstructionsGUI(40, 25, 1500, 800);

	public GameGUI() {

		//as soon as the gameGUI is ran, music starts playing
		// startMusic();

		//setting the properties of the main frame
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setSize(1600, 900);
		setVisible(true);
		
		//sets bounds and image of the background label
		background.setIcon(new ImageIcon(
				new ImageIcon("Images/bgd.png").getImage().getScaledInstance(1600, 900, Image.SCALE_DEFAULT)));
		background.setBounds(0, 0, 1600, 900);

		//sets bounds and image of the title label
		title.setIcon(new ImageIcon(
				new ImageIcon("Images/bohnanza.png").getImage().getScaledInstance(360, 150, Image.SCALE_DEFAULT)));
		title.setBounds(70, 10, 360, 150);

		//sets the bounds and properties of the sell beans button
		sell_beans_button.setBounds(100, 600, 250, 80);
		sell_beans_button.setFont(new Font("Monospaced", Font.BOLD, 30));
		sell_beans_button.setBorder(new RoundedBorder(15));
		sell_beans_button.setContentAreaFilled(false);

		//sets the bounds and properties of the end phase button
		end_phase_button.setBounds(100, 700, 250, 80);
		end_phase_button.setFont(new Font("Monospaced", Font.BOLD, 30));
		end_phase_button.setBorder(new RoundedBorder(15));
		end_phase_button.setContentAreaFilled(false);

		// JButton jbutton = new JButton("pee");
		// jbutton.setBounds(0, 0, 200, 200);
		// jbutton.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent e){
        //         System.out.println("fuck fuck");
        //     }
        // });
		// background.add(jbutton);

		//adds everything to the background label
		background.add(offerAreaGUI);
		background.add(playerGUIs);
		background.add(deckGUI);
		background.add(discardedCardsGUI);
		background.add(title);
		background.add(sell_beans_button);
		background.add(end_phase_button);
		background.add(instructionsGUI);
		
		//add the background label which contains every attribute onto the frame
		add(background);
		
		//replaint the frame once everything is added
		repaint();

	}

	//getters and setters
	public void setTitle(JLabel title) {
		this.title = title;
	}

	public DiscardedCardsGUI getDiscardedCardsGUI() {
		return discardedCardsGUI;
	}

	public void setDiscardedCardsGUI(DiscardedCardsGUI discardedCardsGUI) {
		this.discardedCardsGUI = discardedCardsGUI;
	}

	public DeckGUI getDeckGUI() {
		return deckGUI;
	}

	public void setDeckGUI(DeckGUI deckGUI) {
		this.deckGUI = deckGUI;
	}

	public PlayerGUIs getPlayerGUIs() {
		return playerGUIs;
	}

	public void setPlayerGUIs(PlayerGUIs playerGUIs) {
		this.playerGUIs = playerGUIs;
	}

	public void setBackground(JLabel background) {
		this.background = background;
	}

	public JButton getSell_beans_button() {
		return sell_beans_button;
	}

	public void setSell_beans_button(JButton sell_beans_button) {
		this.sell_beans_button = sell_beans_button;
	}

	public JButton getEnd_phase_button() {
		return end_phase_button;
	}

	public void setEnd_phase_button(JButton end_phase_button) {
		this.end_phase_button = end_phase_button;
	}

	public InstructionsGUI getInstructionsGUI() {
		return instructionsGUI;
	}

	public void setInstructionsGUI(InstructionsGUI instructionsGUI) {
		this.instructionsGUI = instructionsGUI;
	}

	public OfferAreaGUI getOfferAreaGUI() {
		return offerAreaGUI;
	}

	public void setOfferAreaGUI(OfferAreaGUI offerAreaGUI) {
		this.offerAreaGUI = offerAreaGUI;
	}

	// these two getters have the word "the" so it doesn't interfere with existing
	// JLabel functions, as they dont word without it.
	public JLabel getTheTitle() {
		return title;
	}

	public JLabel getTheBackground() {
		return background;
	}

}
