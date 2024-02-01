// Menu GUI
// 100% done by Stuart
package View;

import Model.*;
import Controller.*;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuGUI extends JFrame 
//implements ActionListener 
{

	private JPanel panel = new JPanel();
	
	private JLabel title = new JLabel();	// bohnanza image

	private JButton startButton = new JButton("Start Game");
	private JButton closeButton = new JButton();
	private JButton player1SwitchGamemode = new JButton("AI");
	private JButton player2SwitchGamemode = new JButton("AI");
	
	private JLabel playerLabel1 = new JLabel("Player 1");
	private JTextField player1Name = new JTextField();
	private JLabel playerLabel2 = new JLabel("Player 2");
	private JTextField player2Name = new JTextField();
//	boolean player1IsAI = false;
//	boolean player2IsAI = false;
	
	private JButton helpButton = new JButton("Help");
	
	public MenuGUI() {
		
		panel.setSize(1600,900);
		panel.setVisible(true);
		panel.setLayout(null);
		this.add(panel);
		
		// Label above player1 text area
		playerLabel1.setBounds(720,393,100,50);
		playerLabel1.setFont(new Font("Calibri", Font.BOLD, 24));
		// learned how to center text from https://stackoverflow.com/questions/6810581/how-to-center-the-text-in-a-jlabel
		playerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel1.setForeground(new Color(0,0,0));
		panel.add(playerLabel1);
		
		// textArea for player 1
		player1Name.setText("");
		player1Name.setEditable(true);
		player1Name.setFont(new Font("Arial", Font.BOLD, 40));
		player1Name.setHorizontalAlignment(SwingConstants.CENTER);
		// settings for player1 textArea
		player1Name.setBounds(560,440,400,55);
		panel.add(player1Name);
		
		// Label above playey2 text area
		playerLabel2.setBounds(720,503,100,50);
		playerLabel2.setFont(new Font("Calibri", Font.BOLD, 24));
		playerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel2.setForeground(new Color(0,0,0));
		panel.add(playerLabel2);
		
		// textArea for player 2
		player2Name.setText("");
		player2Name.setEditable(true);
		player2Name.setFont(new Font("Arial", Font.BOLD, 40));
		player2Name.setHorizontalAlignment(SwingConstants.CENTER);
		// settings for player 2 text area
		player2Name.setBounds(560,550,400,55);
		panel.add(player2Name);
		
		// players' buttons to switch back and forth between ai and the player
		player1SwitchGamemode.setBounds(980,440,55,55);
		player1SwitchGamemode.setFont(new Font("Monospaced", Font.BOLD, 20));
		player1SwitchGamemode.setBorder(new RoundedBorder(15));
		player1SwitchGamemode.setContentAreaFilled(false);
//		player1SwitchGamemode.addActionListener(this);
		panel.add(player1SwitchGamemode);
		
		player2SwitchGamemode.setBounds(980,550,55,55);
		player2SwitchGamemode.setFont(new Font("Monospaced", Font.BOLD, 20));
		player2SwitchGamemode.setBorder(new RoundedBorder(15));
		player2SwitchGamemode.setContentAreaFilled(false);
//		player2SwitchGamemode.addActionListener(this);
		panel.add(player2SwitchGamemode);
		
		// start button to start the game
		startButton.setBounds(535, 650, 520, 100);
//		startButton.addActionListener(this);
		startButton.setFont(new Font("Monospaced", Font.BOLD, 30));
		startButton.setBorder(new RoundedBorder(15));
		startButton.setContentAreaFilled(false);
		panel.add(startButton);
		
		// close button to close the game
		ImageIcon closeIcon = new ImageIcon("images/closeButtonImage.png");
		Image scaledCloseIcon = closeIcon.getImage();
		Image newClosedIcon = scaledCloseIcon.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		closeIcon = new ImageIcon(newClosedIcon);
		closeButton.setIcon(closeIcon);
		closeButton.setBounds(1510, 30, 40, 40);
//		closeButton.addActionListener(this);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		panel.add(closeButton);
		
		// button that will open up a link to a doc
		helpButton = new JButton(new ImageIcon ("images/helpIcon.png"));
		helpButton.setBounds(30,30, 50, 50);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
//		helpButton.addActionListener(this);
		panel.add(helpButton);
		
		// title image
		ImageIcon titleIMG = new ImageIcon("images/bohnanza.png");
		Image scaledImage = titleIMG.getImage();
		Image newImage = scaledImage.getScaledInstance(700, 400, java.awt.Image.SCALE_SMOOTH);
		titleIMG = new ImageIcon(newImage);
		title.setIcon(titleIMG);
		title.setBounds(440, 10, 700, 400);
		panel.add(title);
		
		// background image
		JLabel background = new JLabel(new ImageIcon("images/menuBackground.png"));
		background.setSize(1600,900);
		panel.add(background);
		
		// settings for the JFrame
		this.setSize(1600,900);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Bohnanza Main Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	 public JLabel getTheTitle() {
	 	return this.title;
	 }

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(JButton closeButton) {
		this.closeButton = closeButton;
	}

	public JButton getPlayer1SwitchGamemode() {
		return player1SwitchGamemode;
	}

	public void setPlayer1SwitchGamemode(JButton player1SwitchGamemode) {
		this.player1SwitchGamemode = player1SwitchGamemode;
	}

	public JButton getPlayer2SwitchGamemode() {
		return player2SwitchGamemode;
	}

	public void setPlayer2SwitchGamemode(JButton player2SwitchGamemode) {
		this.player2SwitchGamemode = player2SwitchGamemode;
	}

	public JLabel getPlayerLabel1() {
		return playerLabel1;
	}

	public void setPlayerLabel1(JLabel playerLabel1) {
		this.playerLabel1 = playerLabel1;
	}

	public JTextField getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(JTextField player1Name) {
		this.player1Name = player1Name;
	}

	public JLabel getPlayerLabel2() {
		return playerLabel2;
	}

	public void setPlayerLabel2(JLabel playerLabel2) {
		this.playerLabel2 = playerLabel2;
	}

	public JTextField getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(JTextField player2Name) {
		this.player2Name = player2Name;
	}

	public JButton getHelpButton() {
		return helpButton;
	}

	public void setHelpButton(JButton helpButton) {
		this.helpButton = helpButton;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//		if (e.getSource() == startButton) {
//			
//			this.dispose();
////			new GameGUI();
//			String poopie = player1Name.getText();
//			System.out.println(poopie);
//			JLabel heheheha = new JLabel(player1Name.getText());
//		}
//		
//		if (e.getSource() == closeButton) {
//			this.dispose();
//		}
//		
//		if (e.getSource() == helpButton) {
//			
//			// https://stackoverflow.com/questions/10037644/opening-a-url-from-a-jbutton-in-simple-java-program
//			try {
//				
//			     String url ="https://monkeytype.com";
//
//			     Desktop dt = Desktop.getDesktop();
//			     URI uri = new URI(url);
//			     dt.browse(uri.resolve(uri));
//			     
//			} catch (URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		
//		if (e.getSource() == player1SwitchGamemode) {
//			
//			System.out.println(player1IsAI);
//			
//			// switching from AI mode to player 1 mode
//			if (player1IsAI == true) {
//				playerLabel1.setText("Player 1");
//				player1SwitchGamemode.setText("AI #1");
//				player1IsAI = false;
//			}
//			
//			System.out.println("2" + player1IsAI);
//			
//			// switching from player 1 mode to AI mode
//			if (player1IsAI == false) {
//				playerLabel1.setText("AI #1");
//				player1SwitchGamemode.setText("P1");
//				player1IsAI = true;
//			}
//
//			System.out.println("1 " + player1IsAI);
//			
//		}
//		
//		if (e.getSource() == player2SwitchGamemode) {
//			
//			System.out.println(player2IsAI);
//			
//			// switching from AI mode to player 2 mode
//			if (player2IsAI == true) {
//				playerLabel2.setText("Player 2");
//				player2SwitchGamemode.setText("AI #2");
//				player2IsAI = false;
////				playerLabel2.revalidate();
////				playerLabel2.repaint();
//			}
//			
//			// switching from player 2 mode to AI mode
//			if (player2IsAI == false) {
//				playerLabel2.setText("AI #2");
//				player2SwitchGamemode.setText("P2");
//				player2IsAI = true;
////				playerLabel2.revalidate();
////				playerLabel2.repaint();
////				player2SwitchGamemode.revalidate();
////				player2SwitchGamemode.repaint();
//			}
//
//			System.out.println("1 " + player2IsAI);
//		}
//		
//	}
}
