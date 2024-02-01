/*
 * Class: SurveyQuestionPanel
 * Contributor: Andrew Deng
 * Description: Responsible for managing GUI components for each survey question. 
 * This includes the question itself, description for question, and whatever GUI 
 * component required to get the user input for this question. For check boxes, 
 * has cehck and remove all buttons.
 */

// Parent package and imports
package Matching.View;

import Util.Util;

import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Declare SurveyQuestionPanel class
public class SurveyQuestionPanel extends JPanel {

    // Question label, the type of the questions, and the array of components displayed on the panel
    JLabel question;
    JTextArea description_text_area;
    JScrollPane description_scroll_pane;
    String type;
    ArrayList<JComponent>inputs;
    boolean submittable;
    JSlider weight_slider;

    // Constructor for SurveyQuestionPanel class
    public SurveyQuestionPanel(int x, int y, int width, int height, String question, String description, String type, ArrayList<String>options){

        // Settings for question label
        final int QUESTION_LABEL_HORI_PAD = 20;
        final int QUESTION_LABEL_WIDTH = width-2*QUESTION_LABEL_HORI_PAD;
        final int QUESTION_LABEL_HEIGHT = 100;
        final int QUESTION_LABEL_X = width/2-QUESTION_LABEL_WIDTH/2;
        final int QUESTION_LABEL_Y = 10;
        
        // Set settings for this panel
        setBounds(x, y, width, height);
        setLayout(null);
        setType(type);
        setSubmittable(true);
        setInputs(new ArrayList<JComponent>());

        // Sets and adds the question label
        setQuestion(new JLabel(question, SwingConstants.CENTER));
        getQuestion().setForeground(Color.white);
        getQuestion().setFont(Util.getFont(25, false, true));
        getQuestion().setBounds(QUESTION_LABEL_X, QUESTION_LABEL_Y, QUESTION_LABEL_WIDTH, QUESTION_LABEL_HEIGHT);
        add(getQuestion());

        
        // Initialize description
        initDescription(description);

        // Depending on type of survey question, initializes certain type of GUI component
        switch(type){
            case "JTextArea":
                initTextArea();
                break;
            case "JRadioButton":
                initRadioButton(options);
                break;
            case "JCheckBox":
                initCheckBox(options);
                break;
            case "JSlider":
                initSlider();
                break;
            case "JSpinner":
                initSpinners();
                break;
        }

        // Initialize weight slider
        initWeightSlider();

        // GUI settings for this panel
        setBorder(Util.getBorder(Util.C5));
        setBackground(Util.C2);
    }

    // Initializes the description GUI
    public void initDescription(String description){

        // Settings for description GUI
        final int DESCRIPTION_SCROLL_PANE_HORI_PAD = 20;
        final int DESCRIPTION_SCROLL_PANE_VERT_PAD = 10;
        final int DESCRIPTION_SCROLL_PANE_HEIGHT = 50;
        final int DESCRIPTION_SCROLL_PANE_WIDTH = getQuestion().getWidth();
        final int DESCRIPTION_SCROLL_PANE_X = getQuestion().getX();
        final int DESCRIPTION_SCROLL_PANE_Y = getQuestion().getY()+getQuestion().getHeight()+DESCRIPTION_SCROLL_PANE_VERT_PAD;

        // Initializes and sets settings for description text area
        setDescription_text_area(new JTextArea());
        getDescription_text_area().setEditable(false);
        getDescription_text_area().setLineWrap(true);
        getDescription_text_area().setWrapStyleWord(true);

        // https://stackoverflow.com/questions/8792651/how-can-i-add-padding-to-a-jtextfield
        getDescription_text_area().setBorder(BorderFactory.createCompoundBorder(
        getDescription_text_area().getBorder(), 
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        getDescription_text_area().setForeground(Color.white);
        getDescription_text_area().setBackground(Util.C3);
        getDescription_text_area().setFont(Util.getFont(15));
        getDescription_text_area().setText(description);

        // Initializes and sets settings for scroll pane
        setDescription_scroll_pane(new JScrollPane(getDescription_text_area(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        getDescription_scroll_pane().setBackground(Util.C2);
        getDescription_scroll_pane().setBounds(DESCRIPTION_SCROLL_PANE_X, DESCRIPTION_SCROLL_PANE_Y, DESCRIPTION_SCROLL_PANE_WIDTH, DESCRIPTION_SCROLL_PANE_HEIGHT);
        add(getDescription_scroll_pane());
    }
    
    // Initializes weight slider for this question
    public void initWeightSlider(){

        // Settings for weight slider
        final int WEIGHT_SLIDER_MAX = 100;
        final int WEIGHT_SLIDER_MIN = 0;
        final int WEIGHT_SLIDER_HORI_PAD = 10;
        final int WEIGHT_SLIDER_VERT_PAD = 10;
        final int WEIGHT_SLIDER_WIDTH = 500;
        final int WEIGHT_SLIDER_HEIGHT = 50;
        final int WEIGHT_SLIDER_X = getWidth()-WEIGHT_SLIDER_WIDTH-WEIGHT_SLIDER_HORI_PAD;
        final int WEIGHT_SLIDER_Y = getHeight()-WEIGHT_SLIDER_HEIGHT-WEIGHT_SLIDER_VERT_PAD;
        
        // Initialize weight slider
        setWeight_slider(new JSlider(0, 100));
        
        // Create label table for labels for slider, and set their GUI settings
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        JLabel l1 = new JLabel("Weight Lightly");
        l1.setForeground(Color.white);
        l1.setBackground(Util.C5);
        l1.setFont(Util.getFont(20, true, false));
        JLabel l2 = new JLabel("Weight Heavily");
        l2.setForeground(Color.white);
        l2.setBackground(Util.C5);
        l2.setFont(Util.getFont(20, true, false));
        labelTable.put(WEIGHT_SLIDER_MIN, l1);
        labelTable.put(WEIGHT_SLIDER_MAX, l2);
        getWeight_slider().setLabelTable(labelTable);
        getWeight_slider().setPaintLabels(true); 
        
        // Set more settings for weight slider
        getWeight_slider().setBackground(Util.C2);
        getWeight_slider().setBounds(WEIGHT_SLIDER_X, WEIGHT_SLIDER_Y, WEIGHT_SLIDER_WIDTH, WEIGHT_SLIDER_HEIGHT);
        add(getWeight_slider());
    }

    // Initialize text area
    public void initTextArea(){

        // Settings for text area
        final int TEXT_AREA_HORI_PAD = 20;
        final int TEXT_AREA_WIDTH = getWidth()-2*TEXT_AREA_HORI_PAD;
        final int TEXT_AREA_HEIGHT = 100;
        final int TEXT_AREA_X = getWidth()/2-TEXT_AREA_WIDTH/2;
        final int TEXT_AREA_Y = (getHeight()-(getDescription_scroll_pane().getY()+getDescription_scroll_pane().getHeight()))/2-TEXT_AREA_HEIGHT/2;

        // Initialize and set settings for text area
        JTextArea text_area = new JTextArea();
        text_area.setEditable(true);
        text_area.setForeground(Color.white);
        text_area.setBackground(Util.C4);
        text_area.setLineWrap(true);
        text_area.setWrapStyleWord(true);
        text_area.setFont(Util.getFont(30, false, true));
        text_area.setBorder(new EmptyBorder(10,10, 10, 10));
        text_area.setAlignmentX(CENTER_ALIGNMENT);
        text_area.setBounds(TEXT_AREA_X, TEXT_AREA_Y, TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        inputs.add((JComponent)text_area);
        add(text_area);
    }

    // Initializes the survey question panels with radio buttons
    public void initRadioButton(ArrayList<String>options){

        // Settings for these radio buttons
        final int NUM_RADIO_BUTTONS = options.size();
        final int RADIO_BUTTON_HORI_PADDING = 20;
        final int RADIO_BUTTON_VERT_PADDING = 20;
        final int RADIO_BUTTON_WIDTH = (getWidth()-2*RADIO_BUTTON_HORI_PADDING);
        final int RADIO_BUTTON_HEIGHT = 50;
        final int RADIO_BUTTON_X = getWidth()/2-RADIO_BUTTON_WIDTH/2;
        final int RADIO_BUTTON_Y = (getHeight()-(getDescription_scroll_pane().getHeight()+getDescription_scroll_pane().getY()))/2-(NUM_RADIO_BUTTONS*RADIO_BUTTON_HEIGHT-(NUM_RADIO_BUTTONS-1)*RADIO_BUTTON_VERT_PADDING)/2;

        ButtonGroup group = new ButtonGroup();

        // Iterates through each option and initializes radio button accordingly
        for(int i=0; i<options.size(); i++){

            // Init and set settings for button
            JRadioButton but = new JRadioButton();
            but.setText(options.get(i));
            but.setBackground(Util.C2);
            but.setForeground(Color.white);
            but.setFont(Util.getFont(30, true, false));
            but.setBounds(RADIO_BUTTON_X, RADIO_BUTTON_Y+i*(RADIO_BUTTON_HEIGHT+RADIO_BUTTON_VERT_PADDING), RADIO_BUTTON_WIDTH, RADIO_BUTTON_HEIGHT);
            add(but);
            group.add(but);
            inputs.add((JComponent)but);

            // Default first selected
            if(i==0){
                but.setSelected(true);
            }
        }

    }

    // Initialize check boxes
    public void initCheckBox(ArrayList<String>options){

        // Settings for check boxes
        final int NUM_COLS = 3;
        final int CHECK_BOX_HORI_PADDING = 10;
        final int CHECK_BOX_VERT_PADDING = 10;
        final int CHECK_BOX_HORI_SHIFT = 20;
        final int CHECK_BOX_WIDTH = (getWidth()-2*CHECK_BOX_HORI_SHIFT-CHECK_BOX_HORI_PADDING*(NUM_COLS-1))/(NUM_COLS);
        final int CHECK_BOX_HEIGHT = 20;
        final int CHECK_BOX_X = CHECK_BOX_HORI_SHIFT;
        final int CHECK_BOX_Y = getDescription_scroll_pane().getY()+getDescription_scroll_pane().getHeight()+10;

        // Iterate through each options
        for(int i=0; i<options.size(); i++){

            // Initialize check box
            JCheckBox box = new JCheckBox();
            box.setText(options.get(i));
            box.setFont(Util.getFont(15));
            box.setBackground(Util.C2);
            box.setForeground(Color.white);
            
            // Settings for check box
            int x = CHECK_BOX_X+(CHECK_BOX_WIDTH+CHECK_BOX_HORI_PADDING)*(i%NUM_COLS);
            int y = CHECK_BOX_Y+(CHECK_BOX_HEIGHT+CHECK_BOX_VERT_PADDING)*(i/NUM_COLS);
            int width = CHECK_BOX_WIDTH;
            int height = CHECK_BOX_HEIGHT;
            box.setBounds(x, y, width, height);
            add(box);
            inputs.add((JComponent)box);
        }

        // Settings for check all and uncheck all buttons
        final int CHECK_ALL_BUTTON_HORI_PAD = 10;
        final int CHECK_ALL_BUTTON_VERT_PAD = 10;
        final int CHECK_ALL_BUTTON_WIDTH = 200;
        final int CHECK_ALL_BUTTON_HEIGHT = 50;
        final int CHECK_ALL_BUTTON_X = CHECK_ALL_BUTTON_HORI_PAD;
        final int CHECK_ALL_BUTTON_Y = getHeight()-CHECK_ALL_BUTTON_HEIGHT-CHECK_ALL_BUTTON_VERT_PAD;

        // Initialize check all button, and add settings
        JButton check_all_button = new JButton("CHECK ALL");
        check_all_button.setBackground(Util.C5);
        check_all_button.setFont(Util.getFont(20, true, false));
        check_all_button.setBounds(CHECK_ALL_BUTTON_X, CHECK_ALL_BUTTON_Y, CHECK_ALL_BUTTON_WIDTH, CHECK_ALL_BUTTON_HEIGHT);
        check_all_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for(JComponent com:getInputs()){

                    // Checks all check boxes
                    ((JCheckBox)com).setSelected(true);
                }
            }
        });
        add(check_all_button);

        // Initialize uncheck all button, and add settings
        JButton uncheck_all_button = new JButton("UNCHECK ALL");
        uncheck_all_button.setBackground(Util.C5);
        uncheck_all_button.setFont(Util.getFont(20, true, false));
        uncheck_all_button.setBounds(CHECK_ALL_BUTTON_X+CHECK_ALL_BUTTON_WIDTH+CHECK_ALL_BUTTON_HORI_PAD, CHECK_ALL_BUTTON_Y, CHECK_ALL_BUTTON_WIDTH, CHECK_ALL_BUTTON_HEIGHT);
        uncheck_all_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for(JComponent com:getInputs()){

                    // Unchecks all check boxes
                    ((JCheckBox)com).setSelected(false);
                }
            }
        });
        add(uncheck_all_button);
        
    }

    // Initialize slider for question panel
    public void initSlider(){

        // Slider settings
        final int SLIDER_MIN = 0;
        final int SLIDER_MAX = 10;
        final int SLIDER_WIDTH = (getWidth()-40);
        final int SLIDER_HEIGHT = 250;
        final int SLIDER_X = getWidth()/2-SLIDER_WIDTH/2;
        final int SLIDER_Y = getDescription_scroll_pane().getY()+getDescription_scroll_pane().getHeight()+20;

        // Initialize and set settings for slider
        JSlider slider = new JSlider(SLIDER_MIN, SLIDER_MAX);
        slider.setBounds(SLIDER_X, SLIDER_Y, SLIDER_WIDTH, SLIDER_HEIGHT);
        slider.setBackground(Util.C2);

        // Label table for slider, set their settings and add them to slider
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        JLabel l1 = new JLabel("Not at all");
        l1.setForeground(Color.white);
        l1.setBackground(Util.C5);
        l1.setFont(Util.getFont(20, true, false));
        JLabel l2 = new JLabel("Somewhat");
        l2.setForeground(Color.white);
        l2.setBackground(Util.C5);
        l2.setFont(Util.getFont(20, true, false));
        JLabel l3 = new JLabel("A LOT");
        l3.setForeground(Color.white);
        l3.setBackground(Util.C5);
        l3.setFont(Util.getFont(20, true, false));
        labelTable.put(SLIDER_MIN, l1);
        labelTable.put((SLIDER_MAX+SLIDER_MIN)/2, l2);
        labelTable.put(SLIDER_MAX, l3);
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true); 

        add(slider);
        inputs.add((JComponent)slider);
    }

    // Initializes spinners for survey question
    public void initSpinners(){

        // Settings for spinner
        final int NUM_SPINNERS = 2;
        final int SPINNER_MIN = 0;
        final int SPINNER_MAX = 100;
        final int SPINNER_HORI_PAD = 30;
        final int SPINNER_WIDTH = 150;
        final int SPINNER_HEIGHT = 150;
        final int SPINNER_X = getWidth()/2-(NUM_SPINNERS*SPINNER_WIDTH+(NUM_SPINNERS-1)*SPINNER_HORI_PAD)/2;
        final int SPINNER_Y = getDescription_scroll_pane().getHeight()+getDescription_scroll_pane().getY()+(getHeight()-(getDescription_scroll_pane().getY()+getDescription_scroll_pane().getHeight()))/2-SPINNER_HEIGHT/2-50;
        
        // Initialize the first spinner, add its settings
        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0, SPINNER_MIN, SPINNER_MAX, 1));
        spinner1.setBackground(Util.C2);
        spinner1.setForeground(Color.white);
        spinner1.setFont(Util.getFont(75));
        spinner1.setBorder(Util.getBorder(Util.C5));
        spinner1.setBounds(SPINNER_X, SPINNER_Y, SPINNER_WIDTH, SPINNER_HEIGHT);
        add(spinner1);
        inputs.add(spinner1);

        // Initialize the second spinner, add its settings
        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(0, SPINNER_MIN, SPINNER_MAX, 1));
        spinner2.setBackground(Util.C2);
        spinner2.setForeground(Color.white);
        spinner2.setFont(Util.getFont(75));
        spinner2.setBorder(Util.getBorder(Util.C5));
        spinner2.setBounds(SPINNER_X+SPINNER_HORI_PAD+SPINNER_WIDTH, SPINNER_Y, SPINNER_WIDTH, SPINNER_HEIGHT);
        add(spinner2);
        inputs.add(spinner2);
    }

    // Gets info from this question panel
    public ArrayList<String>getInfo(){

        // Create arraylist to store answers
        ArrayList<String>answers = new ArrayList<>();

        // Adds weight of question
        answers.add(String.valueOf(getWeight_slider().getValue()));

        // Iterates through components
        for(Component com : getInputs()){

            // Depending on type of question, gets component inputs in different way
            switch(getType()){
                case "JTextArea":
                    answers.add(((JTextArea)com).getText());
                    return answers;
                case "JRadioButton":
                    if(((JRadioButton)com).isSelected()){
                        answers.add(((JRadioButton)com).getText());
                    }
                    break;
                case "JCheckBox":
                    if(((JCheckBox)com).isSelected()){
                        answers.add(((JCheckBox)com).getText());
                    }
                    break;
                case "JSlider":
                    answers.add(Integer.toString(((JSlider)com).getValue()));
                    break;
                case "JSpinner":
                    answers.add(Integer.toString(((Integer)((JSpinner)com).getValue())));
                    break;
            }
        }
        return answers;
    }

    // Checks if this question panel is submittable
    public boolean checkSubmittable(){

        // Only for spinners
        if(!getType().equals("JSpinner")){
            return true;
        }
        
        // Gets spinners
        JSpinner spinner1 = (JSpinner)getInputs().get(0);
        JSpinner spinner2 = (JSpinner)getInputs().get(1);

        // Gets values from each spinner
        // https://stackoverflow.com/questions/15400781/how-to-get-int-value-from-spinner
        try {
            spinner1.commitEdit();
            spinner2.commitEdit();
        } catch ( java.text.ParseException e ) {
            System.out.println("\n\n\nSPINNER GET VALUE ERROR\n\n\n");
        }
        int val1 = (Integer) spinner1.getValue();
        int val2 = (Integer) spinner2.getValue();

        // Checks if lower bound > upper bound
        if(val1>val2){
            JOptionPane.showMessageDialog(null, "Grade range lower bound must be less than or equal to the upper bound");
            return false;
        }

        return true;
    }

    // Resets this panel
    public void reset(){
        for(int i=0; i<getInputs().size(); i++){
            Component com = getInputs().get(i);

            // Depending on type of question resets in different way
            switch(getType()){
                case "JTextArea":
                    JTextArea textArea = (JTextArea)com;
                    textArea.setText("");
                    break;
                case "JRadioButton":
                    JRadioButton radioButton = (JRadioButton)com;
                    radioButton.setSelected(i==0);
                    break;
                case "JCheckBox":
                    JCheckBox checkBox = (JCheckBox)com;
                    checkBox.setSelected(true);
                    break;
                case "JSlider":
                    JSlider slider = (JSlider)com;
                    slider.setValue(slider.getMinimum());
                    break;
                case "JSpinner":
                    JSpinner spinner = (JSpinner)com;
                    spinner.setValue(0);
                    break;
            }
        }

        getWeight_slider().setValue((getWeight_slider().getMaximum()+getWeight_slider().getMinimum())/2);
    }

    // Getter and setters
    public JLabel getQuestion() {
        return question;
    }

    public void setQuestion(JLabel question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<JComponent> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<JComponent> inputs) {
        this.inputs = inputs;
    }

    public boolean isSubmittable() {
        return submittable;
    }

    public void setSubmittable(boolean submittable) {
        this.submittable = submittable;
    }

    public JTextArea getDescription_text_area() {
        return description_text_area;
    }

    public void setDescription_text_area(JTextArea description_text_area) {
        this.description_text_area = description_text_area;
    }

    public JScrollPane getDescription_scroll_pane() {
        return description_scroll_pane;
    }

    public void setDescription_scroll_pane(JScrollPane description_scroll_pane) {
        this.description_scroll_pane = description_scroll_pane;
    }

    public JSlider getWeight_slider() {
        return weight_slider;
    }

    public void setWeight_slider(JSlider weight_slider) {
        this.weight_slider = weight_slider;
    }

    

    

    

    

    

    
}
