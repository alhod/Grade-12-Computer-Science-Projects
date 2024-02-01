/*
 * Database View Class 
 * Description: This class allows users to search and view information 
 * about various academic programs. It is the GUI for the database.
 */

package Database.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import Database.Model.Program;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JFrame {
	
    private JComboBox<String> universityComboBox;
    private JComboBox<String> locationComboBox;
    private JComboBox<String> experientialComboBox;
    private JComboBox<String> sortComboBox;
    private JButton searchButton;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    
    private JComboBox<String> categoryComboBox;
    
    public View() {
    	
        //Create the frame 
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(new Dimension(1440, 800)); 
        setResizable(false);
        
        //Font for the titled borders 
        Font titleFont = new Font("Ariel", Font.BOLD, 30);
        Color titleColor = Color.WHITE;
        
        //Font for search labels 
        Font labelFont = new Font("Ariel", Font.PLAIN, 23);
        Color labelColor = Color.WHITE;
        
        //Create the search panel 
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.decode("#2d2d2a"));
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Set the font for the "Find a Program" label
        TitledBorder findProgramBorder = BorderFactory.createTitledBorder("Find a Program");
        findProgramBorder.setTitleFont(titleFont);
        findProgramBorder.setTitleColor(titleColor);
        searchPanel.setBorder(findProgramBorder);
        
        //Create grid bag constraints
        //https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html 
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20); //Controls the spacing
        
        //University combo box
        universityComboBox = new JComboBox<>();
        
        universityComboBox.addItem("All Universities");
        universityComboBox.addItem("Algoma University");
        universityComboBox.addItem("Brock University");
        universityComboBox.addItem("Carleton University");
        universityComboBox.addItem("Lakehead University");
        universityComboBox.addItem("Laurentian University");
        universityComboBox.addItem("McMaster University");
        universityComboBox.addItem("Nipissing University");
        universityComboBox.addItem("OCAD University");
        universityComboBox.addItem("Ontario Tech University");
        universityComboBox.addItem("Queen's University");
        universityComboBox.addItem("Royal Military College of Canada");
        universityComboBox.addItem("Toronto Metropolitan University (formerly Ryerson University)");
        universityComboBox.addItem("Trent University");
        universityComboBox.addItem("Trent University – Durham Greater Toronto Area");
        universityComboBox.addItem("University of Guelph");
        universityComboBox.addItem("University of Guelph–Humber");
        universityComboBox.addItem("University of Ottawa");
        universityComboBox.addItem("University of Ottawa – Saint Paul University");
        universityComboBox.addItem("University of Toronto");
        universityComboBox.addItem("University of Toronto – Mississauga");
        universityComboBox.addItem("University of Toronto – Scarborough");
        universityComboBox.addItem("University of Waterloo");
        universityComboBox.addItem("University of Waterloo – Renison University College");
        universityComboBox.addItem("University of Waterloo – St. Jerome's University");
        universityComboBox.addItem("University of Windsor");
        universityComboBox.addItem("Western University");
        universityComboBox.addItem("Western University – Huron University College");
        universityComboBox.addItem("Western University – King's University College");
        universityComboBox.addItem("Wilfrid Laurier University");
        universityComboBox.addItem("Wilfrid Laurier University – Brantford Campus");
        universityComboBox.addItem("Wilfrid Laurier University – Milton Campus");
        universityComboBox.addItem("York University");
        universityComboBox.addItem("York University – Glendon Campus");
        
        searchPanel.add(createLabeledComponent("University", universityComboBox, labelFont), gbc);
        
        //Location combo box
        locationComboBox = new JComboBox<>();

        locationComboBox.addItem("All Of Ontario");
        locationComboBox.addItem("Central Ontario");
        locationComboBox.addItem("Eastern Ontario");
        locationComboBox.addItem("Northern Ontario");
        locationComboBox.addItem("Southwestern Ontario");
        
        searchPanel.add(createLabeledComponent("Location", locationComboBox, labelFont), gbc);
        
        //Experiential learning combo box
        experientialComboBox = new JComboBox<>();
        searchPanel.add(createLabeledComponent("Experiential Learning", experientialComboBox, labelFont), gbc);
        
        experientialComboBox.addItem("Co-op");
        experientialComboBox.addItem("Internship");
        experientialComboBox.addItem("Practicum");
        experientialComboBox.addItem("Not Available");
        experientialComboBox.addItem("All Of The Above");
        
        //Category combo box 
        categoryComboBox = new JComboBox<>();
        searchPanel.add(createLabeledComponent("Category", categoryComboBox, labelFont), gbc);
        
        categoryComboBox.addItem("All");
        categoryComboBox.addItem("Accounting");
        categoryComboBox.addItem("African Studies");
        categoryComboBox.addItem("Anthropology");
        categoryComboBox.addItem("Architecture");
        categoryComboBox.addItem("Biology");
        categoryComboBox.addItem("Business");
        categoryComboBox.addItem("Chemistry");
        categoryComboBox.addItem("Commerce");
        categoryComboBox.addItem("Computer Science");
        categoryComboBox.addItem("Economics");
        categoryComboBox.addItem("Engineering");
        categoryComboBox.addItem("English");
        categoryComboBox.addItem("Life Science");
        categoryComboBox.addItem("Mathematics");
        categoryComboBox.addItem("Music");  
        categoryComboBox.addItem("Nursing");
        categoryComboBox.addItem("Physics"); 
        categoryComboBox.addItem("Psychology");
        categoryComboBox.addItem("Sociology");

        //Sort combo box
        sortComboBox = new JComboBox<>();
        
        sortComboBox.addItem("Alphabetical (Default)");
//        sortComboBox.addItem("Price (Lowest to Highest)");
//        sortComboBox.addItem("Price (Highest to Lowest)");
        sortComboBox.addItem("Acceptance Rate (Lowest to Highest)");
        sortComboBox.addItem("Acceptance Rate (Highest to Lowest)");
        
        searchPanel.add(createLabeledComponent("Sort", sortComboBox, labelFont), gbc);
        
        //Search button
        searchButton = new JButton("Search All Programs");
        searchButton.setPreferredSize(new Dimension(250, 50));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.fill = GridBagConstraints.NONE;  
        gbc.anchor = GridBagConstraints.CENTER;
        searchPanel.add(searchButton, gbc);

        searchButton.setUI(new BasicButtonUI());
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setBackground(Color.decode("#72f590"));   
        searchButton.setForeground(Color.BLACK);
        
        //Results panel
        resultsPanel = new JPanel();
        resultsPanel.setBackground(Color.WHITE);
//        resultsPanel.setLayout(new GridLayout(3, 3, 5, 5)); 
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //Set the font for the "Search Results" label
        TitledBorder resultsBorder = BorderFactory.createTitledBorder("Search Results");
        resultsBorder.setTitleFont(titleFont);
        resultsPanel.setBorder(resultsBorder);
        
        //Create the JScrollPane for the results panel
        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(720, 600));

        add(scrollPane, BorderLayout.CENTER); //Add the scroll pane to the frame 
        
        //Add panels to the frame
        add(searchPanel, BorderLayout.EAST);
        add(scrollPane, BorderLayout.CENTER);
        
        //Display the window
        setVisible(true);
        
    }

	//This method creates labeled components 
	private Component createLabeledComponent(String labelText, JComponent component, Font labelFont) {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.decode("#2d2d2a"));
		
		JLabel label = new JLabel(labelText);
		label.setForeground(Color.white);
		
		label.setFont(labelFont);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0)); //Controls spacing
		
		//Add to panel 
		panel.add(label, BorderLayout.NORTH); 
		panel.add(component, BorderLayout.CENTER);
		
		return panel;

	}
	
	//This method adds the action listener to the search button 
	public void addSearchActionListener(ActionListener listener) {
		
		searchButton.addActionListener(listener);
		
	}
	
	//These methods get the selected item from the combo boxes 
	public String getSelectedUniversity() {
		
		return (String) universityComboBox.getSelectedItem();
		
	}
	
	public String getSelectedLocation() {
		
		return (String) locationComboBox.getSelectedItem();
		
	}
	
	public String getSelectedExperientialLearning() {
		
		return (String) experientialComboBox.getSelectedItem();
		
	}
	
	public String getSelectedSort() {
		
		return (String) sortComboBox.getSelectedItem();
		
	}
	
	public String getSelectedCategory() {
		
		return (String) categoryComboBox.getSelectedItem();
		
	}
	
	//This method displays the programs in the results panel
	public void displayPrograms(Program[] programArray) {
		
		resultsPanel.removeAll(); //Clear the panel
		
		JLabel resultsCountLabel = new JLabel(programArray.length + " Programs Found");
		resultsCountLabel.setFont(new Font("Arial", Font.BOLD, 20));
		resultsCountLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		resultsPanel.add(resultsCountLabel);
		
		for (Program program : programArray) {
			
			//https://www.javatpoint.com/how-to-change-font-size-in-html 
			//https://www.javatpoint.com/how-to-change-text-color-in-html 
			String programInfo = String.format("<html><font color= black><strong><font size= +1>Program: %s</font></strong></font><br> <font size= +1>University: %s<br>"
					+ "Degree: %s<br> OUAC Program Code: %s<br> Grade Range: %s<br> Experiential Learning: %s<br> Enrollment: %s<br> Instruction Language: %s<br>"
					+ "Notes: %s<br> Prerequisites: %s<br> Address: %s<br> Email: %s<br> Link: %s <br><br> ------------------------------------------- <br><br></font>", 
					program.getName(), program.getUniversity(), program.getDegree(), program.getOuac_program_code(), program.getGrade_range(), program.getExperiential_learning(),
					program.getEnrollment(), program.getInstruction_language(), program.getNotes(), program.getPrerequisites(), program.getAddress(), program.getEmail(), program.getLink());
			
			JLabel programLabel = new JLabel(programInfo);
			programLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			programLabel.setBackground(Color.BLACK);
			resultsPanel.add(programLabel);
	
		}
		
		//Update the UI
		resultsPanel.revalidate();
		resultsPanel.repaint();
		
	}

}
