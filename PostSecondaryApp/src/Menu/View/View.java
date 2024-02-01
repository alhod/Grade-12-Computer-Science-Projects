/*
 * Class: View
 * Contributor: Andrew Deng
 * Description: Main class in View package for menu. Responsible for the 
 * frame for the main menu for the MyPortal app. Contais the JButtons 
 * that when clicked leads to the frames for the other features in the 
 * app.
 */

// Parent package and imports
package Menu.View;

import Util.*;

import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Declare View class
public class View {

    // Path to background for menu
    public static final String BACKGROUND_PATH = "src\\Menu\\Images\\Background.jpg";

    // GUI components for the menu GUI
    JFrame frame;
    JPanel panel;
    JLabel title;
    JLabel background;

    // Buttons corresponding to each feature in app
    JButton login;
    JButton database;
    JButton matching;
    JButton map;

    // Constructor for view class
    public View() {

        // Initialize and set the settings for the JFrame
        setFrame(new JFrame());
        getFrame().setLayout(null);
        getFrame().setSize(Util.FRAME_WIDTH, Util.FRAME_HEIGHT);
        getFrame().setVisible(true);
        getFrame().setResizable(false);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize and set the settings for the JPanel
        setPanel(new JPanel());
        getPanel().setLayout(null);
        getPanel().setBounds(0, 0, getFrame().getWidth(), getFrame().getHeight());
        getFrame().add(getPanel());
        initTitle();
        initButtons();
        initBackground();
    }

    // Initialize the title label
    public void initTitle() {

        // Settings for title label
        final int TITLE_HORI_PAD = 50;
        final int TITLE_WIDTH = getPanel().getWidth() - 2 * TITLE_HORI_PAD;
        final int TITLE_HEIGHT = 75;
        final int TITLE_X = getPanel().getWidth() / 2 - TITLE_WIDTH / 2;
        final int TITLE_Y = 30;

        // Initialize and set the settings for the title label
        setTitle(new JLabel("Welcome to MyPortal!", SwingConstants.CENTER));
        getTitle().setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        getTitle().setFont(Util.getFont(40, true, false));
        getTitle().setOpaque(true);
        getTitle().setForeground(Color.white);
        getTitle().setBackground(Util.C2);
        getTitle().setBorder(Util.getBorder(Util.C5));
        getPanel().add(getTitle());
    }

    // Initializes the background label for background image
    public void initBackground() {

        // Initialize and set settings for background label
        setBackground(new JLabel(""));
        getBackground().setBounds(0, 0, getPanel().getWidth(), getPanel().getHeight());

        // Create path to background image, and if it exists, get its image
        Path path = Paths.get(BACKGROUND_PATH);
        if (Files.exists(path)) {
            ImageIcon img = new ImageIcon(BACKGROUND_PATH);
            getBackground().setIcon(img);
        } else {
            System.out.println("Menu Background Import Error");
        }

        getPanel().add(getBackground());
    }

    // Initialize the buttons
    public void initButtons() {

        // Settings for buttons
        final int NUM_BUTS = 4;
        final int VERT_PAD = 20;
        final int WIDTH = 300;
        final int HEIGHT = 75;
        final int X = getFrame().getWidth() / 2 - WIDTH / 2;
        final int Y = getFrame().getHeight() / 2 - (NUM_BUTS * HEIGHT + (NUM_BUTS - 1) * VERT_PAD) / 2;

        // Initialize and set the settings for the login button
        setLogin(new JButton("LOGIN"));
        getLogin().setBackground(Util.C2);
        getLogin().setForeground(Color.white);
        getLogin().setBorder(Util.getBorder(Util.C5));
        getLogin().setFont(Util.getFont(30, true, false));
        getLogin().setBounds(X, Y, WIDTH, HEIGHT);
        getPanel().add(getLogin());
        
        // Initialize and set the settings for the database button
        setDatabase(new JButton("DATABASE"));
        getDatabase().setBackground(Util.C2);
        getDatabase().setForeground(Color.white);
        getDatabase().setBorder(Util.getBorder(Util.C5));
        getDatabase().setFont(Util.getFont(30, true, false));
        getDatabase().setBounds(X, Y + HEIGHT + VERT_PAD, WIDTH, HEIGHT);
        getPanel().add(getDatabase());
        
        // Initialize and set the settings for the matching button
        setMatching(new JButton("MATCHING"));
        getMatching().setBackground(Util.C2);
        getMatching().setForeground(Color.white);
        getMatching().setBorder(Util.getBorder(Util.C5));
        getMatching().setFont(Util.getFont(30, true, false));
        getMatching().setBounds(X, Y + 2 * (HEIGHT + VERT_PAD), WIDTH, HEIGHT);
        getPanel().add(getMatching());
        
        // Initialize and set the settings for the map button
        setMap(new JButton("MAP"));
        getMap().setBackground(Util.C2);
        getMap().setForeground(Color.white);
        getMap().setBorder(Util.getBorder(Util.C5));
        getMap().setFont(Util.getFont(30, true, false));
        getMap().setBounds(X, Y + 3 * (HEIGHT + VERT_PAD), WIDTH, HEIGHT);
        getPanel().add(getMap());
    }

    // Sets the scroll bar GUI settings with colors
    public void setScrollPaneGUI(JScrollPane scrollPane) {

        // Set background for scroll bars for scroll pane
        scrollPane.getVerticalScrollBar().setBackground(Util.C2);
        scrollPane.getHorizontalScrollBar().setBackground(Util.C2);

        // Sets scroll bar colors scroll pane
        // https://stackoverflow.com/questions/55454479/how-to-change-the-color-of-scrollbar-in-jscrollpane
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Util.C5; 
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Util.C5;
            }
        });

        // Set more settings
        scrollPane.setBackground(Util.C2);
        scrollPane.setForeground(Color.white);
        scrollPane.setBorder(Util.getBorder(Util.C5));
    }

    // Getters and setters
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton getLogin() {
        return login;
    }

    public void setLogin(JButton login) {
        this.login = login;
    }

    public JButton getDatabase() {
        return database;
    }

    public void setDatabase(JButton database) {
        this.database = database;
    }

    public JButton getMatching() {
        return matching;
    }

    public void setMatching(JButton matching) {
        this.matching = matching;
    }

    public JButton getMap() {
        return map;
    }

    public void setMap(JButton map) {
        this.map = map;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getBackground() {
        return background;
    }

    public void setBackground(JLabel background) {
        this.background = background;
    }

}
