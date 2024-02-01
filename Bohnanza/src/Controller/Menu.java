/*
Date: Nov 25th
Course: ICS4U1-02
Name: Saheer, Eshan
Significant help: none
Description: This class initializes menu
*/

package Controller;

import Model.*;
import View.*;
import Controller.*;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Popup;

public class Menu {

	MenuGUI menu;
    Controller controller;
    ArrayList<PlayerTemplate> playerTemplates;
    boolean player1IsAI;;
    boolean player2IsAI;

	Clip clip;

    public Menu(Controller controller) {
        this.controller = controller;
    }

    // Creates menu as JFrame and displays JFrame. Don’t forget to manage action
    // listeners here. Once start game button clicked, call createGame() from
    // Controller object.
    public void createMenu() {

    	menu = new MenuGUI();

		startMusic();

		setPlayer1IsAI(false);
		setPlayer2IsAI(false);

    	addActionListenersMenu();
    }

	//method that continously plays the music once the gamegui is opened.
	// copied from saheer's gamegui
	private void startMusic() {
		
		//try catch in order to avoid crashing if any errors occur
		try {

			File path = new File("Sounds/5.wav");
			
			if (path.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
				setClip(AudioSystem.getClip());
				
				getClip().open(audioInput);
				// https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f);
				getClip().loop(Clip.LOOP_CONTINUOUSLY);
				getClip().start();
				
			} else { //if the file path doesn't exist, print to the console
				System.out.println("file not found");
			}
		} catch (Exception e) { //catch all errors
			System.out.println(e);
		}

	}

    public void addActionListenersMenu(){
    	
    	getMenu().getStartButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clickSound();

				String p1 = menu.getPlayer1Name().getText();
				String p2 = menu.getPlayer2Name().getText();
				int p1_len = menu.getPlayer1Name().getText().length();
				int p2_len = menu.getPlayer2Name().getText().length();
				
				if(p1_len<1||p1_len>16){
					PopUp.runPopUp("Player 1 name length must be between 1 and 16");
					return;
				}
				if(p2_len<1||p2_len>16){
					PopUp.runPopUp("Player 2 name length must be between 1 and 16");
					return;
				}
				if(p1.equals(p2)){
					PopUp.runPopUp("Player 1 and player 2 must have different names");
					return;
				}

				playerTemplates = new ArrayList<PlayerTemplate>();
				
				// for (int i = 1; i <= 2; i++) {
				//     boolean isAI = (i == 1) ? player1IsAI : player2IsAI;
				//     int playerType = (isAI) ? 1 : 0;
				//     String playerName = (i == 1) ? menu.getPlayer1Name().getText() : menu.getPlayer2Name().getText();

				//     playerTemplates.add(new PlayerTemplate(playerType, playerName));
				// }

				playerTemplates.add(new PlayerTemplate((player1IsAI?1:0), menu.getPlayer1Name().getText()));
				playerTemplates.add(new PlayerTemplate((player2IsAI?1:0), menu.getPlayer2Name().getText()));

				// for(PlayerTemplate t:playerTemplates){
				// 	System.out.println(t.getType());
				// }
				
				getMenu().dispose();
				getClip().stop();
				getController().createGame();
			}
    		
    	});
    	
    	getMenu().getCloseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				clickSound();

				getMenu().dispose();
				
			}
    		
    	});
    	
    	getMenu().getHelpButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				clickSound();

				try {
					
				     String url ="https://drive.google.com/drive/folders/1gj9TXSfCujr"
				     		+ "BlEz9iNScDLFNmF2nhxgw?usp=drive_link";
	
				     Desktop dt = Desktop.getDesktop();
				     URI uri = new URI(url);
				     dt.browse(uri.resolve(uri));
				     
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
    		
    	});
    	
    	getMenu().getPlayer1SwitchGamemode().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				clickSound();

				// switching from AI mode to player 1 mode
				if (player1IsAI == true) {
					getMenu().getPlayerLabel1().setText("Player 1");
					getMenu().getPlayer1SwitchGamemode().setText("AI");
					player1IsAI = false;
				}else if (player1IsAI == false) {
					getMenu().getPlayerLabel1().setText("AI #1");
					getMenu().getPlayer1SwitchGamemode().setText("P1");
					player1IsAI = true;
				}

				// System.out.printf("player 1 is ai: %b\n", player1IsAI);
			}
    		
    	});
    	
    	getMenu().getPlayer2SwitchGamemode().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				clickSound();

				// switching from AI mode to player 1 mode
				if (player2IsAI == true) {
					getMenu().getPlayerLabel2().setText("Player 2");
					getMenu().getPlayer2SwitchGamemode().setText("AI");
					player2IsAI = false;
				}else if (player2IsAI == false) {
					getMenu().getPlayerLabel2().setText("AI #2");
					getMenu().getPlayer2SwitchGamemode().setText("P2");
					player2IsAI = true;
				}
			}
    		
    	});
    	
    	
    }

	public MenuGUI getMenu() {
		return menu;
	}

	public void setMenu(MenuGUI menu) {
		this.menu = menu;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public ArrayList<PlayerTemplate> getPlayerTemplates() {
		return playerTemplates;
	}

	public void setRet(ArrayList<PlayerTemplate> playerTemplates) {
		this.playerTemplates = playerTemplates;
	}

	public void setPlayerTemplates(ArrayList<PlayerTemplate> playerTemplates) {
		this.playerTemplates = playerTemplates;
	}

	public boolean isPlayer1IsAI() {
		return player1IsAI;
	}

	public void setPlayer1IsAI(boolean player1IsAI) {
		this.player1IsAI = player1IsAI;
	}

	public boolean isPlayer2IsAI() {
		return player2IsAI;
	}

	public void setPlayer2IsAI(boolean player2IsAI) {
		this.player2IsAI = player2IsAI;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	private void clickSound() {

        //try catch in order to avoid crashing if any errors occur
        try {

            File path = new File("Sounds/0.wav");

            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);

                // https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f); 

                clip.start();

            } else { //if the file path doesn't exist, print to the console
                System.out.println("file not found");
            }
        } catch (Exception e) { //catch all errors
            System.out.println(e);
        }

    }

	
}
