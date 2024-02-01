/*
 * Class: SurveyPanel
 * Contributors: 100% - Andrew Deng
 * Description: The JPanel in which the survey screen is displayed on. 
 * Responsible for handling initialization for all survey question panels, 
 * organizing them, switching in between them, and storing the submit 
 * button (which retrieves user input for each question).
 */

// Parent package and imports
package Matching.View;

import Util.Util;
import Database.Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.chrono.JapaneseChronology;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.Model.Program;

// Declare SurveyPanel class
public class SurveyPanel extends JPanel {

    // Static final attributes for utility
    public static final String CITIES_PATH = "src\\Matching\\Model\\Cities.txt";
    public static final int NUM_SURVEY_QUESTIONS = 7;

    // Attributes to store for survey screen
    // Current survey question panel index
    int curr_ind = -1;

    // GUI components for screen
    JLabel title;
    JList<String> list;
    JScrollPane scrollPane;
    JButton submit_button;

    // Array of university programs
    ArrayList<Program> programs;

    // Array of survey question panel instances
    ArrayList<SurveyQuestionPanel> question_panels;

    // Constructor for SurveyPanel class
    public SurveyPanel(int x, int y, int width, int height, ArrayList<Program> programs) {

        // Sets/initializes attributes
        setQuestion_panels(new ArrayList<SurveyQuestionPanel>());
        setPrograms(programs);

        // Settings for panel
        setBounds(x, y, width, height);
        setLayout(null);
        setBackground(Util.C1);

        // Initialize GUI components
        initTitle();
        initScrollPane();
        initSubmitButton();

        // Update and reset panel before displaying
        update(0);
        reset();
    }

    // Initialize the title GUI
    public void initTitle() {

        // Settings for title label
        final int TITLE_HORI_PAD = 20;
        final int TITLE_VERT_PAD = 10;
        final int TITLE_WIDTH = getWidth() - 2 * TITLE_HORI_PAD;
        final int TITLE_HEIGHT = 75;
        final int TITLE_X = getWidth() / 2 - TITLE_WIDTH / 2;
        final int TITLE_Y = TITLE_VERT_PAD;

        // Initialize and set the settings for the title label
        setTitle(new JLabel("University Programs Matching Survey", SwingConstants.CENTER));
        getTitle().setFont(Util.getFont(35, true, false));
        getTitle().setForeground(Color.white);
        getTitle().setBackground(Util.C2);
        getTitle().setBorder(Util.getBorder(Util.C5));
        getTitle().setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

        // Add to panel
        add(getTitle());
    }

    // Initialize the scroll pane and question panel
    public void initScrollPane() {

        // Settings for scroll pane
        final int SCROLL_PANE_HORI_PAD = 10;
        final int SCROLL_PANE_VERT_PAD = 10;
        final int SCROLL_PANE_WIDTH = 200;
        final int SCROLL_PANE_HEIGHT = 600;
        final int SCROLL_PANE_X = getWidth() - SCROLL_PANE_WIDTH - 3 * SCROLL_PANE_HORI_PAD;
        final int SCROLL_PANE_Y = getTitle().getY() + getTitle().getHeight() + SCROLL_PANE_VERT_PAD;

        // Settintgs for survey question panel
        final int SURVEY_QUESTION_PANEL_HORI_PAD = 10;
        final int SURVEY_QUESTION_PANEL_VERT_PAD = 10;
        final int SURVEY_QUESTION_PANEL_WIDTH = getWidth() - (SCROLL_PANE_WIDTH + 3 * SCROLL_PANE_HORI_PAD)
                - 2 * SURVEY_QUESTION_PANEL_HORI_PAD;
        final int SURVEY_QUESTION_PANEL_HEIGHT = getHeight()
                - (getTitle().getY() + getTitle().getHeight() + 6 * SURVEY_QUESTION_PANEL_VERT_PAD);
        final int SURVEY_QUESTION_PANEL_X = SURVEY_QUESTION_PANEL_HORI_PAD;
        final int SURVEY_QUESTION_PANEL_Y = getTitle().getY() + getTitle().getHeight() + SURVEY_QUESTION_PANEL_VERT_PAD;

        // Create array of survey questions
        String[] survey_questions = new String[NUM_SURVEY_QUESTIONS];

        // Initialize survey question panel arraylist
        setQuestion_panels(new ArrayList<SurveyQuestionPanel>());

        // --------------------------------------------------------------
        // INITIALIZE AND ADD ALL SURVEY QUESTION PANEL INSTANCES TO
        // ARRAYLIST AND ARRAY
        // --------------------------------------------------------------
        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "Preferred Universities",
                "Programs from which universities would you prefer us recommend to you?", "JCheckBox", getAllUnis()));
        survey_questions[0] = "Preferred Universities";

        ArrayList<String> q2 = new ArrayList<>();
        q2.add("Yes");
        q2.add("No");
        q2.add("I'm fine either way");

        ArrayList<String> q7 = new ArrayList<>();
        q7.add("Yes");
        q7.add("No");

        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "Co-op/Internship Opportunities",
                "Some university programs offer co-op/internship opportunities that provide their students with work experience before graduating. Would you like us to recommend programs that offer such co-op/internship opportunities?",
                "JRadioButton", q2));
        survey_questions[1] = "Co-op/Internships";
        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "Keywords/Phrases",
                "If you have any keywords or phrases you would like us to search for in the name of the university programs, please type them below. Keywords/phrases should be separated by commas. (E.g. \"Computer Science, Software Engineering\")",
                "JTextArea", null));
        survey_questions[2] = "Keywords/Phrases";
        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "Grade Range",
                "If you would prefer us to search for programs that have an average grade that falls under a certain range, please provide the range below.",
                "JSpinner", null));
        survey_questions[3] = "Grade Range";
        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "University Rankings",
                "Generally speaking, you can count on higher ranked universities to offer high quality education and be riddled with resources and opportunities for you. How much do you care about the ranking of universities?\"",
                "JSlider", null));
        survey_questions[4] = "University Rankings";
        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "Preferred Cities",
                "If you're spending 4 or more years studying at a certain university, you better choose one that suits your needs. Which cities are you fine with studying in?",
                "JCheckBox", getCities()));
        survey_questions[5] = "Preferred Cities";
        getQuestion_panels().add(new SurveyQuestionPanel(SURVEY_QUESTION_PANEL_X, SURVEY_QUESTION_PANEL_Y,
                SURVEY_QUESTION_PANEL_WIDTH, SURVEY_QUESTION_PANEL_HEIGHT, "Prerequisites",
                "Our university program recommendations should be personalized to YOU. Would you like us to recommend programs with prerequisites that overlap with the courses you've taken?",
                "JRadioButton", q7));
        survey_questions[6] = "Prerequisites";
        // --------------------------------------------------------------
        // FINISHED INITIALIZ AND ADD ALL SURVEY QUESTION PANEL INSTANCES
        // TO ARRAYLIST AND ARRAY
        // --------------------------------------------------------------

        // Set all panels invisible but add them to panel
        for (SurveyQuestionPanel surveyQuestionPanel : getQuestion_panels()) {
            add(surveyQuestionPanel);
            surveyQuestionPanel.setVisible(false);
        }

        // Initialize and set settings for JList for survey questions
        setList(new JList<String>(survey_questions));
        getList().setFixedCellHeight(50);
        getList().setBorder(new EmptyBorder(10, 10, 10, 10));
        getList().setForeground(Color.white);
        getList().setBackground(Util.C2);
        getList().setCellRenderer(new SelectedListCellRenderer());
        getList().setFont(Util.getFont(12, true, true));

        // Add list selection listener so detect when a survey question is selected
        getList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                update(getList().getSelectedIndex());
            }
        });

        // Initialize, set settings, and add the JList to the scroll pane
        setScrollPane(new JScrollPane(getList(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        getScrollPane().setBorder(Util.getBorder(Util.C5));
        getScrollPane().setBounds(SCROLL_PANE_X, SCROLL_PANE_Y, SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);

        // Add scroll pane to panel
        add(getScrollPane());
    }

    // Initialize the submit button
    public void initSubmitButton() {

        // Settings for the submit button
        final int SUBMIT_BUTTON_HORI_PAD = 10;
        final int SUBMIT_BUTTON_VERT_PAD = 10;
        final int SUBMIT_BUTTON_WIDTH = getScrollPane().getWidth();
        final int SUBMIT_BUTTON_HEIGHT = getQuestion_panels().get(0).getHeight() + getQuestion_panels().get(0).getY()
                - (getScrollPane().getY() + getScrollPane().getHeight()) - SUBMIT_BUTTON_VERT_PAD;
        final int SUBMIT_BUTTON_X = getScrollPane().getX();
        final int SUBMIT_BUTTON_Y = getScrollPane().getHeight() + getScrollPane().getY() + SUBMIT_BUTTON_VERT_PAD;

        // Initialize and set the settings for the submit button
        setSubmit_button(new JButton("SUBMIT"));
        getSubmit_button().setBackground(Util.C5);
        getSubmit_button().setFont(Util.getFont(20, true, false));
        getSubmit_button().setBounds(SUBMIT_BUTTON_X, SUBMIT_BUTTON_Y, SUBMIT_BUTTON_WIDTH, SUBMIT_BUTTON_HEIGHT);

        // Add the submit button to the panel
        add(getSubmit_button());
    }

    // Get all universities across all programs we have
    public ArrayList<String> getAllUnis() {

        // Hash set of universities to get only unique universities
        HashSet<String> unis = new HashSet<String>();

        // Add univeristies to set
        for (Program program : getPrograms()) {
            unis.add(program.getUniversity());
        }

        // Initialize array list for univeristies
        ArrayList<String> unis_arr = new ArrayList<>();

        // Add strings from set to arraylist
        for (String uni : unis) {
            unis_arr.add(uni);
        }

        // Sort array list
        Collections.sort(unis_arr);

        // Return array list
        return unis_arr;
    }

    // Gets all unique cities across all programs we have
    public ArrayList<String> getCities() {

        // For storing cities
        ArrayList<String> ret = new ArrayList<>();

        // Try to get all cities from file
        try {

            // Read and iterate through file
            File myObj = new File(CITIES_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ret.add(data);
            }

            // Sort alphabetically
            Collections.sort(ret);
            myReader.close();

            // Return
            return ret;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    // Update the current survey question panel being displayed
    public void update(int new_curr_ind) {

        // Checks if previous one was displayed, if so make it invisible
        if (getCurr_ind() != -1) {
            getQuestion_panels().get(getCurr_ind()).setVisible(false);
        }

        // Set new current index and set current panel visible
        setCurr_ind(new_curr_ind);
        getQuestion_panels().get(getCurr_ind()).setVisible(true);
    }

    // Get all the user input in the survey
    public HashMap<Integer, ArrayList<String>> getSurveyPanelInfo() {

        // Hashmap to represent survey
        HashMap<Integer, ArrayList<String>> survey = new HashMap<>();

        // Iterate through survey questions and get inputs
        for (int i = 0; i < getQuestion_panels().size(); i++) {
            survey.put(i, getQuestion_panels().get(i).getInfo());
        }

        return survey;
    }

    // For making the selection of the JList look prettier
    // https://stackoverflow.com/questions/1576853/how-to-change-background-color-of-the-selected-item-in-jlist-dynamically
    public class SelectedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                c.setBackground(Util.C5);
            }
            return c;
        }
    }

    // Check if all survey questions have valid input
    // Returns true if all questions in the survey have valid input
    public boolean checkSubmittable() {
        for (int i = 0; i < getQuestion_panels().size(); i++) {

            // If a single one doesn't return false and display that survey question
            if (!getQuestion_panels().get(i).checkSubmittable()) {
                update(i);
                return false;
            }
        }
        return true;
    }

    // Resets all survey question panels
    public void reset() {
        for (SurveyQuestionPanel surveyQuestionPanel : getQuestion_panels()) {
            surveyQuestionPanel.reset();
        }
    }

    // Getter setter methods
    public static String getCitiesPath() {
        return CITIES_PATH;
    }

    public static int getNumSurveyQuestions() {
        return NUM_SURVEY_QUESTIONS;
    }

    public int getCurr_ind() {
        return curr_ind;
    }

    public void setCurr_ind(int curr_ind) {
        this.curr_ind = curr_ind;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JList<String> getList() {
        return list;
    }

    public void setList(JList<String> list) {
        this.list = list;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JButton getSubmit_button() {
        return submit_button;
    }

    public void setSubmit_button(JButton submit_button) {
        this.submit_button = submit_button;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    public ArrayList<SurveyQuestionPanel> getQuestion_panels() {
        return question_panels;
    }

    public void setQuestion_panels(ArrayList<SurveyQuestionPanel> question_panels) {
        this.question_panels = question_panels;
    }

}

