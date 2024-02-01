/*
 * Class: View
 * Contributors: 100% - Andrew Deng
 * Description: The main class for the View package. It's responsible for 
 * storing the main JFrame this feature is displayed on, as well as 
 * switching between the survey and results panels within this JFrame. 
 * Overall coordinates other classes in View package.
 */

// Parent package and imports
package Matching.View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import Util.*;
import Database.Model.*;

// Declare View class
public class View {

    // Main frame feature is displayed on
    JFrame frame;

    // Instances of survey and results panel
    SurveyPanel surveyPanel;
    ResultsPanel resultsPanel;

    // Holds current panel
    String curr_panel; // Either "Survey" or "Results"

    // Array of university programs
    ArrayList<Program> programs;

    // Constructor for View class
    public View(ArrayList<Program> programs) {

        // Sets/initializes attributes
        setPrograms(programs);
        setSurveyPanel(new SurveyPanel(0, 0, Util.FRAME_WIDTH, Util.FRAME_HEIGHT, getPrograms()));
        setResultsPanel(new ResultsPanel(0, 0, Util.FRAME_WIDTH, Util.FRAME_HEIGHT, getPrograms()));

        // Sets current panel to survey panel
        setCurr_panel("Survey");
    }

    // Creates JFrame that matching feature is displayed on
    public void createFrame() {

        // Initializes and sets the settings for the JFrame
        setFrame(new JFrame());
        getFrame().setSize(Util.FRAME_WIDTH, Util.FRAME_HEIGHT);
        getFrame().setResizable(false);
        getFrame().setLayout(null);
        getFrame().setVisible(true);

        // Adds the panels into the frame
        getFrame().add(getSurveyPanel());
        getFrame().add(getResultsPanel());

        // Sets live panel and repaints the frame to update it
        setLivePanel(getCurr_panel());
        getFrame().repaint();
    }

    // Sets the live panel to survey or results based on string
    public void setLivePanel(String new_live_panel) {

        // Depending on value of string, sets live panel to survey or results panel
        if (new_live_panel.equals("Survey")) {
            getSurveyPanel().setVisible(true);
            getResultsPanel().setVisible(false);
        } else if (new_live_panel.equals("Results")) {
            getResultsPanel().setVisible(true);
            getSurveyPanel().setVisible(false);
        } else {
            System.out.println("\n\nSET LIVE PANEL ERROR\n\n");
            return;
        }

        // Repaints JFrame and sets current panel
        getFrame().repaint();
        setCurr_panel(new_live_panel);
    }

    public static void setScrollPaneGUI(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setBackground(Util.C2);
        scrollPane.getHorizontalScrollBar().setBackground(Util.C2);

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
        scrollPane.setBackground(Util.C2);
        scrollPane.setForeground(Color.white);
        scrollPane.setBorder(Util.getBorder(Util.C5));
    }

    // Getter and setter methods
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public SurveyPanel getSurveyPanel() {
        return surveyPanel;
    }

    public void setSurveyPanel(SurveyPanel surveyPanel) {
        this.surveyPanel = surveyPanel;
    }

    public ResultsPanel getResultsPanel() {
        return resultsPanel;
    }

    public void setResultsPanel(ResultsPanel resultsPanel) {
        this.resultsPanel = resultsPanel;
    }

    public String getCurr_panel() {
        return curr_panel;
    }

    public void setCurr_panel(String curr_panel) {
        this.curr_panel = curr_panel;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

}
