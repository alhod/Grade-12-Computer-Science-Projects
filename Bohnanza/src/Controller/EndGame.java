/*
Date: Nov 25th
Course: ICS4U1-02
Name: Saheer, Eshan
Significant help: none
Description: This class initializes end game menu
*/

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import View.*;
import Model.*;

import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class EndGame {

    EndGameGUI endGameGUI;
    Controller controller;
    String p1;
    String p2; 
    int c1;
    int c2;

    public EndGame(Controller controller) {
        this.controller = controller;
    }

    public void createEndGame() {
        endGameSequence();
    }

    public void endGameSequence() {

        ArrayList<Player> players = getController().getModel().getGameModel().getPlayers().getPlayers();

        // for (int player = 0; player < 2; player++) {
        //     p1 = getController().getModel().getGameModel().getPlayers().getPlayers().get(player).getName();
        //     c1 = getController().getModel().getGameModel().getPlayers().getPlayers().get(player).getCoins();
        //     if (player == 1) {
        //         p2 = getController().getModel().getGameModel().getPlayers().getPlayers().get(player).getName();
        //         c2 = getController().getModel().getGameModel().getPlayers().getPlayers().get(player).getCoins();
        //     }

        //     System.out.printf("end game sequence: %s %d %s %d\n", p1, c1, p2, c2);
        // } //uncomment this when done

        p1 = players.get(0).getName();
        c1 = players.get(0).getCoins();
        p2 = players.get(1).getName();
        c2 = players.get(1).getCoins();

        endGameGUI = new EndGameGUI(p1,c1,p2,c2);
        addActionListenersEndGame();

    }

    public void addActionListenersEndGame() {

        endGameGUI.getQuitButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                clickSound();

                getController().getView().getGameGUI().dispose();
                endGameGUI.dispose();

            }

        });

        endGameGUI.getRestartButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                clickSound();

                getController().getView().getGameGUI().dispose();
                endGameGUI.dispose();
                controller.createMenu();

            }

        });

    }

    public EndGameGUI getEndGameGUI() {
        return endGameGUI;
    }

    public void setEndGameGUI(EndGameGUI endGameGUI) {
        this.endGameGUI = endGameGUI;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
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