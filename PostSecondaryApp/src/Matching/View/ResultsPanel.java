/*
 * Class: ResultsPanel
 * Contributors: 100% - Andrew Deng
 * Description: Responsible for the results screen, storing the scroll pane of 
 * sorted university programs, the title, and displaying the matched program 
 * panel. Overall manages the results frame GUI aspect.
 */

// Parent package and imports
package Matching.View;

import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;

import Util.*;
import Database.Model.*;

// Declaer ResultsPanel class
public class ResultsPanel extends JPanel {
    
    // Number of programs to recommend
    public static final int NUM_PROGRAMS_TO_RECOMMEND = 4;

    // Attributes (GUI and not) for ResultsPanel
    ArrayList<Program>programs;
    MatchedProgramPanel matchedProgramPanel;
    ArrayList<Program>sorted_programs;
    JLabel title;
    JScrollPane scrollPane;
    JList<String> list;
    int curr_panel=-1;
    JButton redo_survey_button;

    // Constructor for ResultsPanel class
    public ResultsPanel(int x, int y, int width, int height, ArrayList<Program>programs){

        // Settings for results panel
        setBounds(x, y, width, height);
        setLayout(null);
        setPrograms(programs);

        // Initialize GUI components
        initRedoSurveyButton();
        initTitle();
        initScrollPane();
        initMatchedProgramPanel();
        setBackground(Util.C1);
    }

    // Initialize matching program panel
    public void initMatchedProgramPanel(){

        // Settings for matched program panel
        final int MATCHED_PROGRAM_PANEL_VERT_PADDING = 10;
        final int MATCHED_PROGRAM_PANEL_HORI_PADDING = 10;
        final int MATCHED_PROGRAM_PANEL_WIDTH = getScrollPane().getX()-2*MATCHED_PROGRAM_PANEL_HORI_PADDING;
        final int MATCHED_PROGRAM_PANEL_HEIGHT = getHeight()-(getTitle().getY()+getTitle().getHeight())-6*MATCHED_PROGRAM_PANEL_VERT_PADDING;
        final int MATCHED_PROGRAM_PANEL_X = MATCHED_PROGRAM_PANEL_HORI_PADDING;
        final int MATCHED_PROGRAM_PANEL_Y = getTitle().getHeight()+getTitle().getY()+MATCHED_PROGRAM_PANEL_VERT_PADDING;

        // Sets settings and initializes matched program panel
        setMatchedProgramPanel(new MatchedProgramPanel(MATCHED_PROGRAM_PANEL_X, MATCHED_PROGRAM_PANEL_Y, MATCHED_PROGRAM_PANEL_WIDTH, MATCHED_PROGRAM_PANEL_HEIGHT, programs));
        getMatchedProgramPanel().setVisible(true);
        add(getMatchedProgramPanel());
    }

    // Initialize title label
    public void initTitle(){

        // Settings for title label
        final int TITLE_HORI_SHIFT = 20;
        final int TITLE_VERT_SHIFT = 10;
        final int TITLE_WIDTH = getWidth();
        final int TITLE_HEIGHT = 70;
        final int TITLE_X = getWidth()/2 - TITLE_WIDTH/2;
        final int TITLE_Y = TITLE_VERT_SHIFT;

        // Initialize title and set settings for title label
        setTitle(new JLabel("Your Survey Results!", SwingConstants.CENTER));
        getTitle().setForeground(Color.white);
        getTitle().setBorder(Util.getBorder(Util.C5));
        getTitle().setBackground(Util.C2);
        getTitle().setFont(Util.getFont(40, true, false));
        getTitle().setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        add(getTitle());
    }
    
    // Initialize the scroll pane
    public void initScrollPane(){

        // Settings for scroll pane
        final int SCROLL_PANE_HORI_PAD = 20;
        final int SCROLL_PANE_VERT_PAD = 10;
        final int SCROLL_PANE_WIDTH = 400;
        final int SCROLL_PANE_HEIGHT = getHeight()-(getTitle().getY()+getTitle().getHeight())-6*SCROLL_PANE_VERT_PAD;
        final int SCROLL_PANE_X = getWidth()-SCROLL_PANE_WIDTH-SCROLL_PANE_HORI_PAD;
        final int SCROLL_PANE_Y = getTitle().getY()+getTitle().getHeight()+SCROLL_PANE_VERT_PAD;
        
        // Remove scroll pane if exists
        if(getScrollPane()!=null){
            remove(getScrollPane());
        }

        // Initializes and sets settings for scroll pane
        setScrollPane(new JScrollPane(getList(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        getScrollPane().setBorder(Util.getBorder(Util.C5));
        View.setScrollPaneGUI(getScrollPane());
        getScrollPane().setBounds(SCROLL_PANE_X, SCROLL_PANE_Y, SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        add(getScrollPane());
    }

    // Initialize redo survey button
    public void initRedoSurveyButton(){

        // Redo survey button question
        final int REDO_SURVEY_BUTTON_HORI_PAD = 20;
        final int REDO_SURVEY_BUTTON_VERT_PAD = 20;
        final int REDO_SURVEY_BUTTON_WIDTH = 200;
        final int REDO_SURVEY_BUTTON_HEIGHT = 50;
        final int REDO_SURVEY_BUTTON_X = getWidth()-REDO_SURVEY_BUTTON_WIDTH-REDO_SURVEY_BUTTON_HORI_PAD;
        final int REDO_SURVEY_BUTTON_Y = REDO_SURVEY_BUTTON_VERT_PAD;

        // Initialize and set settings for redo survey button
        setRedo_survey_button(new JButton("Redo Survey"));
        getRedo_survey_button().setBackground(Util.C5);
        getRedo_survey_button().setFont(Util.getFont(20, true, false));
        getRedo_survey_button().setBounds(REDO_SURVEY_BUTTON_X, REDO_SURVEY_BUTTON_Y, REDO_SURVEY_BUTTON_WIDTH, REDO_SURVEY_BUTTON_HEIGHT);
        add(getRedo_survey_button());
    }

    // Generate results panel
    public void generateResultsPanel(ArrayList<Program> programs){

        // Get sorted programs
        setSorted_programs(programs);
        
        // Create array to store programs
        String[] programs_array = new String[getSorted_programs().size()];

        // Gets sorted programs string format
        for(int i=0; i<getSorted_programs().size(); i++){
            programs_array[i]=getSorted_programs().get(i).getUniversity()+": "+getSorted_programs().get(i).getName();
        }

        // Initializes and sets settings for JList
        setList(new JList<String>(programs_array));
        getList().setFixedCellHeight(20);
        getList().setBackground(Util.C2);
        getList().setBorder(new EmptyBorder(10,10, 10, 10));
        getList().setForeground(Color.white);
        getList().setFont(Util.getFont(12));

        // For better GUI effects
        getList().setCellRenderer(new SelectedListCellRenderer());

        // List selection listener to update panel displayed
        getList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e){
                // updateMatchedProgramPanel(e.getLastIndex());
                updateMatchedProgramPanel(getList().getSelectedIndex());
            }
        });

        // Initialize scroll pane, repaint, and update current panel
        initScrollPane();
        getScrollPane().repaint();
        updateMatchedProgramPanel(0);
    }

    // Sets the new current matched program panel
    public void updateMatchedProgramPanel(int new_curr_panel){
        getMatchedProgramPanel().update(getSorted_programs().get(new_curr_panel));
    }

    // For better background for the GUI in the JList
    // https://stackoverflow.com/questions/1576853/how-to-change-background-color-of-the-selected-item-in-jlist-dynamically
    public class SelectedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                c.setBackground(Util.C5);
            }
            return c;
        }
    }

    // Resets the results panel
    public void reset(){
        setList(null);
        getScrollPane().removeAll();
        setSorted_programs(null);
    }

    // Getter and setter methods
    public static int getNumProgramsToRecommend() {
        return NUM_PROGRAMS_TO_RECOMMEND;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }

    public ArrayList<Program> getSorted_programs() {
        return sorted_programs;
    }

    public void setSorted_programs(ArrayList<Program> sorted_programs) {
        this.sorted_programs = sorted_programs;
    }

    public int getCurr_panel() {
        return curr_panel;
    }

    public void setCurr_panel(int curr_panel) {
        this.curr_panel = curr_panel;
    }

    public JButton getRedo_survey_button() {
        return redo_survey_button;
    }

    public void setRedo_survey_button(JButton redo_survey_button) {
        this.redo_survey_button = redo_survey_button;
    }

    public MatchedProgramPanel getMatchedProgramPanel() {
        return matchedProgramPanel;
    }

    public void setMatchedProgramPanel(MatchedProgramPanel matchedProgramPanel) {
        this.matchedProgramPanel = matchedProgramPanel;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }


    

    

    
}
