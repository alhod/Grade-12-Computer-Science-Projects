package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class LaptopStoreSurveyFrame extends JFrame {

	// labels
    JLabel backgroundLabel = new JLabel(new ImageIcon("images/background.jfif"));
    JLabel titleLabel = new JLabel("Survey");

    // buttons
    JButton checkSurveyResultButton = new JButton("Check Survey Results");

    // menus
    JMenuBar menuBar = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenu surveyMenu = new JMenu("Survey");
    JMenu inventoryMenu = new JMenu("Inventory");
    JMenu educationMenu = new JMenu("Education");

    // panels
    JPanel surveyBoxPanel = new JPanel(); // New panel for the survey box
    JPanel questionPanel = new JPanel();
    JPanel questionPanelTop = new JPanel(); // New panel for the top three questions
    JPanel questionPanelBottom = new JPanel(); // New panel for the bottom two questions

    // radio buttons
    JRadioButton[] restrictionRadioButtonArray = new JRadioButton[5]; // Adjusted for 5 questions
    ButtonGroup restrictionButtonGroup = new ButtonGroup();
    ButtonGroup restrictionButtonGroupTop = new ButtonGroup();
    ButtonGroup restrictionButtonGroupBottom = new ButtonGroup();
    JPanel panel = new JPanel();  // new main panel

    // constructor method
    public LaptopStoreSurveyFrame() {

        // Set the color for the "Survey" box
        Color surveyBoxColor = Color.decode("#B3A4FF");

        // setup frame
        setSize(1440, 900);
        setLayout(null);
        setTitle("Laptop Warehouse Application");

        // Panel takes up entire screen
        Color panelColor = Color.decode("#EBE7FF");
        panel.setLayout(null);    
        panel.setBounds(0, 0, 1440, 1000);
        panel.setBackground(panelColor);
        add(panel);

        // add menu bar to JFrame
        menuBar.add(homeMenu);
        menuBar.add(surveyMenu);
        menuBar.add(inventoryMenu);
        menuBar.add(educationMenu);
        setJMenuBar(menuBar);

        // add titleLabel inside panel 
        titleLabel.setBounds(0, 0, 1440, 100); // Adjusted bounds for more space above
        titleLabel.setFont(new Font("Futura", Font.PLAIN, 36)); // Increased font size
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        // add surveyBoxPanel to panel
        surveyBoxPanel.setBounds(0, 0, 1440, 100); // Cover from left to right, adjust the height as needed
        surveyBoxPanel.setBackground(surveyBoxColor);
        surveyBoxPanel.setLayout(null);
        panel.add(surveyBoxPanel);

        // add nextButton
        checkSurveyResultButton.setBounds(1250, 750, 150, 50);
        panel.add(checkSurveyResultButton);

        questionPanel.setBounds(0, 100, 1000, 900);
        questionPanel.setLayout(null);
        questionPanel.setOpaque(false);  // makes invisible background
        panel.add(questionPanel);
        
        // add questionPanelTop to panel
        questionPanelTop.setBounds(0, 100, 1000, 300); // Adjusted height for top questions
        questionPanelTop.setLayout(new GridLayout(1, 3)); // Use GridLayout to arrange the panels horizontally
        questionPanelTop.setOpaque(false); // makes invisible background
        panel.add(questionPanelTop);

        // add questionPanelBottom to panel
        questionPanelBottom.setBounds(0, 400, 1000, 200); // Adjusted height for bottom questions
        questionPanelBottom.setLayout(new GridLayout(1, 2)); // Use GridLayout to arrange the panels horizontally
        questionPanelBottom.setOpaque(false); // makes invisible background
        panel.add(questionPanelBottom);

        
     // Add survey questions and options
        String[] questions = {
            "How much memory do you need?",
            "What is your budget range?",
            "How much storage do you need?",
            "What will be the use for the computer?",
            "Which operating system do you prefer?"
        };

        JPanel[] qPanels = new JPanel[questions.length]; 

        // Add radio buttons to questionPanel
        int yOffset = 20; // Initial vertical offset
        for (int index = 0; index < questions.length; index++) {
            JPanel qPanel = new JPanel();
            // qPanel.setSize(200, 150);
            
            JLabel questionLabel = new JLabel(questions[index]);
            questionLabel.setBounds(0, 0, qPanel.getWidth(), 30);
            qPanel.add(questionLabel);

            // Assuming 4 options for each question
            for (int option = 0; option < 4; option++) {
                JRadioButton optionRadioButton = new JRadioButton("Option " + (option + 1));
                int option_height = 20;
                optionRadioButton.setBounds(0, questionLabel.getHeight()+option_height*option, 120, option_height);
                restrictionButtonGroup.add(optionRadioButton);
                qPanel.add(optionRadioButton);
            }

            // yOffset += 80; // Adjust vertical spacing

            add(qPanel);
            qPanels[index]=qPanel;
        }


        int qPanelW = 200;
        int qPanelH = 150;
        qPanels[0].setBounds(0, 0, qPanelW, qPanelH);
        qPanels[1].setBounds(0, qPanels[1].getHeight(), qPanelW, qPanelH);

        

        setVisible(true);
    }

}