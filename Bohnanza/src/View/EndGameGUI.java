/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class is shown once the game ends, and prints the results and stats of the 
 * game. All images included were created by me in photoshop.
 */
package View;

import Model.*;
import Controller.*;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EndGameGUI extends JFrame {

	// attributes of the class
	private JLabel background = new JLabel();
	private JLabel player2_info = new JLabel("", SwingConstants.CENTER);;
	private JLabel player1_info = new JLabel("", SwingConstants.CENTER);;
	private JLabel title = new JLabel();;
	private JLabel stats = new JLabel("Game Coin Stats:", SwingConstants.CENTER);;
	private JButton quitButton = new JButton("Quit Game");;
	private JButton restartButton = new JButton("Restart Game");;
	private JLabel winner_label;

	public EndGameGUI(String p1, int c1, String p2, int c2) {

		// System.out.printf("end game: %s %d %s %d\n", p1, c1, p2, c2);

		// setting panel elements
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setResizable(false);
		setVisible(true);

		// plays the end game sound as soon as the frame is opened
		try {
			Sounds.playSounds(4);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// initializing all the components that will be used
		winner_label = new JLabel("Winner: " + findWinner(p1, c1, p2, c2), SwingConstants.CENTER);

		// sets the background depending on the winner
		setBackgroundToWinner(p1, c1, p2, c2);

		// bound setting
		background.setBounds(0, 0, 600, 465);
		title.setBounds(150, 1, 300, 100);
		stats.setBounds(210, 215, 180, 50);
		player1_info.setBounds(0, 260, 300, 80);
		player2_info.setBounds(300, 260, 284, 80);
		quitButton.setBounds(66, 350, 200, 60);
		restartButton.setBounds(332, 350, 200, 60);
		winner_label.setBounds(0, 140, 600, 50);

		// visual changes
		stats.setFont(new Font("Calibri", Font.BOLD, 20));
		title.setIcon(new ImageIcon(
				new ImageIcon("images/bohnanza.png").getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT)));
		// HTML formatting used to combine the player name and coins
		player1_info.setText(
				"<html><div style='text-align: center;'>" + "<span style='font-size: 30px; font-weight: bold;'>" + p1
						+ "</span><br>" + c1 + " Coins</div></html>");
		player2_info.setText(
				"<html><div style='text-align: center;'>" + "<span style='font-size: 30px; font-weight: bold;'>" + p2
						+ "</span><br>" + c2 + " Coins</div></html>");
		winner_label.setFont(new Font("Calibri", Font.ITALIC, 50));
		winner_label.setText("<html><div style='font-family: Calibri; font-style: italic;'>Winner: "
				+ "<span style='font-family: Monospaced;'>" + findWinner(p1, c1, p2, c2) + "</span></div></html>");
		quitButton.setFont(new Font("Monospaced", Font.BOLD, 20));
		quitButton.setContentAreaFilled(false);
		quitButton.setBorder(new RoundedBorder(17));
		restartButton.setFont(new Font("Monospaced", Font.BOLD, 20));
		restartButton.setContentAreaFilled(false);
		restartButton.setBorder(new RoundedBorder(17));

		// adding elements to background label, and adds background label to main panel
		add(background);
		background.add(title);
		background.add(stats);
		background.add(player1_info);
		background.add(player2_info);
		background.add(winner_label);
		background.add(restartButton);
		background.add(quitButton);

		// used to make sure everything is placed correctly on top of each other
		repaint();
	}

	private void setBackgroundToWinner(String p1, int c1, String p2, int c2) {

		if (findWinner(p1, c1, p2, c2).equalsIgnoreCase(p1))
			background.setIcon(new ImageIcon(
					new ImageIcon("Images/p1winner.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
		else if (findWinner(p1, c1, p2, c2).equalsIgnoreCase(p2))
			background.setIcon(new ImageIcon(
					new ImageIcon("Images/p2winner.png").getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH)));
		else
			background.setIcon(new ImageIcon(
					new ImageIcon("Images/tie.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
	}

	// method used to determine who the winner is
	private String findWinner(String p1, int c1, String p2, int c2) {
		// determines who is the winner based on the number of coins each person has
		return (c1 > c2) ? p1 : (c1 < c2) ? p2 : "No one!";
	}

	// getters and setters
	public JLabel getPlayer2_info() {
		return player2_info;
	}

	public void setPlayer2_info(JLabel player2_info) {
		this.player2_info = player2_info;
	}

	public JLabel getPlayer1_info() {
		return player1_info;
	}

	public void setPlayer1_info(JLabel player1_info) {
		this.player1_info = player1_info;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JLabel getStats() {
		return stats;
	}

	public void setStats(JLabel stats) {
		this.stats = stats;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quit) {
		this.quitButton = quit;
	}

	public JButton getRestartButton() {
		return restartButton;
	}

	public void setRestartButton(JButton restart) {
		this.restartButton = restart;
	}

	public JLabel getWinner_Label() {
		return winner_label;
	}

	public void setWinner_Label(JLabel winner_Label) {
		this.winner_label = winner_Label;
	}

	public void setBackground(JLabel background) {
		this.background = background;
	}

	public JLabel getWinner_label() {
		return winner_label;
	}

	public void setWinner_label(JLabel winner_label) {
		this.winner_label = winner_label;
	}

	// called getTheTitle and getTheBackground, as 
	//getTitle and getBakground collides with pre-existing JLabel functions.
	public JLabel getTheTitle() {
		return title;
	}

	public JLabel getTheBackground() {
		return background;
	}

}