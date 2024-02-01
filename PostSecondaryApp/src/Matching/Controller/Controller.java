/*
 * Class: Controller
 * Contributors: 100% - Andrew
 * Description: Responsible for initializing the main Model and View classes, 
 * managing the switches between the survey and results panels, adding and 
 * managing action listeners for "submit" and "redosurvey" buttons, and 
 * contains method for initializing the JFrame to display the matching 
 * feature in. Overall just manages model and view classes together.
 */

// Parent package and imports
package Matching.Controller;

import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Model.Program;
import Matching.Model.*;
import Matching.View.*;
import Util.Util;

// Declare class
public class Controller {

    // Instances of main Model and View classes
    Model model;
    View view;

    // Array of university programs
    ArrayList<Program> programs;

    // Constructor, requires array of university programs
    public Controller(ArrayList<Program> programs) {
        
        // Sets/initializes attributes
        setPrograms(programs);
        setModel(new Model(getPrograms()));
        setView(new View(getPrograms()));

        // Adds action listeners to submit and redo survey buttons
        addSubmitActionListener(getView().getSurveyPanel().getSubmit_button());
        addRedoSurveyButtonActionListener(getView().getResultsPanel().getRedo_survey_button());
    }

    // Creates survey frame
    public void createFrame() {
        getView().createFrame();
    }

    // Adds action listener to submit button in survey panel
    public void addSubmitActionListener(JButton but) {
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Checks that all survey panels have valid user input
                if (!getView().getSurveyPanel().checkSubmittable()) {
                    return;
                }

                // Generates results panel
                generateResultsPanel();
            }
        });
    }

    // Adds action listener to redo survey button in results panel
    public void addRedoSurveyButtonActionListener(JButton but) {
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Generates survey panel
                generateSurveyPanel();
            }
        });
    }

    // Generates the survey panel
    public void generateSurveyPanel() {

        // Sets the live panel to survey panel
        getView().setLivePanel("Survey");

        // Resets the results panel
        getView().getResultsPanel().reset();
    }

    // Generates the results panel
    public void generateResultsPanel() {

        // Gets survey inputs
        HashMap<Integer, ArrayList<String>> survey = getView().getSurveyPanel().getSurveyPanelInfo();

        // Sorts programs based on survey inputs
        ArrayList<Program> sorted_programs = getModel().generateMatchingResults(survey);

        // Generates results panel, passing in sorted programs
        getView().getResultsPanel().generateResultsPanel(sorted_programs);

        // Sets live panels
        getView().setLivePanel("Results");

        // Resets survey panel
        // getView().getSurveyPanel().reset();
    }

    // Setters and getters
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

}
