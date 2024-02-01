package Authentication.View;

//Zaid Rahman (%100)
//Date of submission: 15/12/2023
//ICS4U1 
//Student App
// This class creates the survey frame and GUI that the user can interact with/
// The user will view and enter their answers for each question.
// Input validation is used so the user cannot skip questions without answering them
// When the user completes all the questions, they will be prompted to the 
// profile frame where they can view their answers 
//Features:
// GUI Elements that are used:
// - scroll pane 
// Panel Control
// - Their are methods that manipulate the visibility of panels
//	showPanel1(), showPanel2(), hidePanel1(), hidePanel2()
// - Event handling 
// 		- Action Listeners for next button, back button, confirm butotn
// Major Skills 
// - Handles user interactions with key listeners 
// - Input validation
// - Arrays , iterating through them

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class SurveyFrame extends JFrame implements ActionListener {

	// create panels 
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel(); 

	private JPanel topPanel = new JPanel(); // create panel

	private JRadioButton ossltCompleted = new JRadioButton("Completed");
	
	private ButtonGroup group = new ButtonGroup(); // create button group
	
	// create buttons, labels, check box arrays and textfield arrays
	private JButton confirmButton = new JButton("Finish");
	private JLabel title = new JLabel("User Info Survey"); // create getters and setters for this
	private JLabel ossltLabel = new JLabel("Osslt Completion");
	private JLabel hoursLabel = new JLabel("Enter Community Hours Completed");
	private JLabel schoolLabel = new JLabel("School you currently attend");
	private JLabel markLabel = new JLabel("Course Marks");
	private JLabel courses = new JLabel("Click on current courses");
	private JLabel contactInfo = new JLabel("Enter Contact Information");
	private JLabel creditsComplete = new JLabel("Credits Earned");
	private JButton nextButton = new JButton("Next");
	private JCheckBox[] coursesArray = new JCheckBox[48];
	private JTextField[] markInput = new JTextField[6];

	// create text fields 
	private JTextField schoolTxt = new JTextField();
	private JTextField contact = new JTextField();
	private JTextField creditsTxt = new JTextField();
	private JTextField commHours = new JTextField();
	private JTextField userNameText = new JTextField();
	private JButton courseInfo = new JButton("?");
	private JPanel backgroundPanel = new JPanel();

	// create fields 
	private JLabel completeFields = new JLabel("Please complete all fields");

	private JScrollPane scrollPane = new JScrollPane(); // create scroll pane 

	private JButton backButton = new JButton("Back"); // create back button

	private JTextField userAverage = new JTextField(); // create text field

	private JPanel buttonPanel = new JPanel(); // create panel

	JScrollPane sp = new JScrollPane(buttonPanel); // create scroll pane, and add panel

	
	// array lists that will store courses and the their corresponding codes 
	ArrayList<String> courseList = new ArrayList<String>(); 
	ArrayList<String> courseCode = new ArrayList<String>();

	private int markIndex = 0;

	// method that will add courses 
	public void addingCourses(ArrayList<String> courseCode, ArrayList<String> courseList) {
		courseList.add("Advanced Functions");
		courseList.add("Biology");
		courseList.add("Chemistry");
		courseList.add("Physics");
		courseList.add("Technological Design");
		courseList.add("Dramatic Arts");
		courseList.add("Media Arts");
		courseList.add("Dance");
		courseList.add("Financial Accounting Principal");
		courseList.add("Geomatics");
		courseList.add("The environment and resource management");
		courseList.add("World Geography");
		courseList.add("Canadian and World Issues");
		courseList.add("Canada:History, Identity and Culture");
		courseList.add("World History");
		courseList.add("Analysing Current Economic Issues");
		courseList.add("Canadian and International Law");
		courseList.add("Canadian and World Politics");
		courseList.add("English");
		courseList.add("Studies in Literature");
		courseList.add("The Writer's Craft");
		courseList.add("Extended French");
		courseList.add("French Immersion");
		courseList.add("Core French");
		courseList.add("Nutrition and Health");
		courseList.add("Human Development throughout the Lifespan");
		courseList.add("Families in Canada");
		courseList.add("The World of Fashion");
		courseList.add("Challenge and Change in Society");
		courseList.add("World Cultures");
		courseList.add("Equity and Social Justice: From Theory to Practice");
		courseList.add("Philosophy: Questions and Theories");
		courseList.add("Computer Science");
		courseList.add("Interdisciplinary Studies");
		courseList.add("Classical Civilization");
		courseList.add("Calculus and Vectors");
		courseList.add("Mathematics of Data Management");
		courseList.add("First Nations, MÃ©tis and Inuit Governance in Canada");
		courseList.add("Contemporary Indigenous Issues and Perspectives in a Global Context");
		courseList.add("Recreation and Healthy Active Living Leadership");
		courseList.add("Introductory Kinesiology");
		courseList.add("Earth and Space Science");
		courseList.add("Science");
		courseList.add("Computer Engineering Technology");
		courseList.add("Communications Technology");
		courseList.add("Green Industries");
		courseList.add("Health Care");
		courseList.add("Manufacturing Engineering Technology");

		courseCode.add("MHF4U");
		courseCode.add("SBI4U");
		courseCode.add("SCH4U");
		courseCode.add("SPH4U");
		courseCode.add("TDJ4M");
		courseCode.add("ADA4M");
		courseCode.add("ASM4M");
		courseCode.add("ATC4M");
		courseCode.add("BAT4M");
		courseCode.add("CGO4M");
		courseCode.add("CGR4M");
		courseCode.add("CGU4M");
		courseCode.add("CGW4U");
		courseCode.add("CHI4U");
		courseCode.add("CHI4U");
		courseCode.add("CIA4U");
		courseCode.add("CLN4U");
		courseCode.add("CPW4U");
		courseCode.add("ENG4U");
		courseCode.add("ETS4U");
		courseCode.add("EWC4U");
		courseCode.add("FEF4U");
		courseCode.add("FIF4U");
		courseCode.add("FSF4U");
		courseCode.add("HFA4U");
		courseCode.add("HHG4M");
		courseCode.add("HHS4U");
		courseCode.add("HNB4M");
		courseCode.add("HSB4U");
		courseCode.add("HSC4M");
		courseCode.add("HSE4M");
		courseCode.add("HZT4U");
		courseCode.add("ICS4U");
		courseCode.add("IDC4U/IDP4U");
		courseCode.add("LVV4U");
		courseCode.add("MCV4U");
		courseCode.add("MDM4U");
		courseCode.add("NDG4M");
		courseCode.add("NDW4M");
		courseCode.add("PLF4M");
		courseCode.add("PSK4U");
		courseCode.add("SES4U");
		courseCode.add("SNC4M");
		courseCode.add("TDJ4M");
		courseCode.add("TEJ4M");
		courseCode.add("TGJ4M");
		courseCode.add("THJ4M");
		courseCode.add("TPJ4M");
		courseCode.add("TMJ4M");
	}

	// constructor 
	public SurveyFrame() {
		
		// create a line 
		LineBorder line = new LineBorder(Color.white, 3, true);
		
		courseInfo.addActionListener(this); // add action listeners

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		buttonPanel.setLayout(new GridLayout(0, 1));// set layout 

		addingCourses(courseCode, courseList); 
		backgroundPanel.setSize(1440, 900); // set size 

		Color color2 = new Color(56, 66, 59); // create color

		group.add(ossltCompleted); // add buttons to a group

		// set font and text color
		ossltCompleted.setFont(new Font("Arial", Font.BOLD, 17));
		ossltCompleted.setForeground(Color.white);

		// create background color, position, and background 
		Color colorBackground = new Color(53, 56, 49);

		topPanel.setBounds(0, 10, 1440, 50);
		topPanel.setBackground(color2); 

		// set position, editability, opaqueness, font and text color
		userNameText.setBounds(1300, 10, 100, 15);
		userNameText.setEditable(false);
		userNameText.setOpaque(false);
		userNameText.setFont(new Font("Arial", Font.ITALIC, 15));
		userNameText.setForeground(Color.white);

		// add fields 
		add(title);
		add(nextButton);
		add(confirmButton);

		add(topPanel); 

		backgroundPanel.setBackground(colorBackground);// set background of panel

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close
		setSize(1440, 800); // set size 
		setResizable(false); // resizability
		setLayout(null); // no layout

		// iterate through marks 
		for (int i = 0; i < markInput.length; i++) {

			// create an instance, set position, font and add 
			markInput[i] = new JTextField();

			markInput[i].setBounds(875, 140 + (i * 50), 250, 40);

			markInput[i].setFont(new Font("Arial", Font.ITALIC, 15));

			add(markInput[i]);

		}

		// set font , opaque, text color, background color, and set the text to the course list and code 
		for (int i = 0; i < coursesArray.length; i++) {
			coursesArray[i] = new JCheckBox(); // create an instance 

			
			coursesArray[i].setFont(new Font("Arial", Font.PLAIN, 15));

			coursesArray[i].setOpaque(false);

			coursesArray[i].setForeground(Color.black);

			buttonPanel.setBackground(Color.white);

			coursesArray[i].setText(courseList.get(i) + "," + courseCode.get(i) + "\n");

			buttonPanel.add(coursesArray[i]);

		}

		
		// https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java
		commHours.addKeyListener(new KeyAdapter() {
			@Override
			
			// only allow integer values 
			public void keyPressed(KeyEvent e) {
				String value = commHours.getText();
				int l = value.length();
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					commHours.setEditable(true);
				} else {
					commHours.setText("");
					JOptionPane.showMessageDialog(null, "Enter only numerical digits");
					commHours.setText("");

				}
			}

		});

		// iterate through marks 
		for (int currentMark = 0; currentMark < markInput.length; currentMark++) {
			final int index = currentMark;
			
			// method will only allow integer values 
			markInput[currentMark].addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					JTextField currentField = markInput[index];
					String value = currentField.getText();
					int l = value.length();
					if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						currentField.setEditable(true);
					} else { // input of anything else 
						currentField.setText(""); //set text 
						
						// display message 
						JOptionPane.showMessageDialog(null, "Enter only characters");
					}
				}
			});
		}
		
		// only allow characters 
		schoolTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = schoolTxt.getText();
				int l = value.length();
				
				// are not integers , rather characters 
				if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					schoolTxt.setEditable(true);
				} else {
					// set text to nothing and display a message 
					schoolTxt.setText("");
					JOptionPane.showMessageDialog(null, "Enter only characters");
					schoolTxt.setText("");

				}
			}

		});
		
		
		// only allow inputs of integers 
		creditsTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = creditsTxt.getText();
				
				// if input are numbers, also allow backspaces 
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9'  || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					creditsTxt.setEditable(true);
				} else {
					
					// set the text to nothing 
					// and display a message 
					creditsTxt.setText("");
					JOptionPane.showMessageDialog(null, "Enter only numerical digits");
					creditsTxt.setText("");

				}
			}

		});

		sp.setBounds(260, 150, 480, 580); // set position of scroll bar 


		// create visuals for scroll bar 
		sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {

				// customize colors 
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.LIGHT_GRAY;
			}
		});

		
		// create visuals for the scroll bar 
		sp.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				
				// customize colors 
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.LIGHT_GRAY;
			}
		});

		// set the position and opaqueness
		ossltCompleted.setBounds(925, 460, 300, 100);
		ossltCompleted.setOpaque(false);

		// set the position and text color
		title.setBounds(630, -60, 500, 200);
		title.setForeground(Color.white);
		
		// set the position , font and text color
		markLabel.setBounds(930, -30, 200, 300);
		markLabel.setFont(new Font("Arial", Font.BOLD, 18));
		markLabel.setForeground(Color.white);

		// set the position , font , and color text 
		courses.setBounds(370, -30, 340, 300);
		courses.setFont(new Font("Arial", Font.BOLD, 20));
		courses.setForeground(Color.white);


		// set the position and text color
		schoolLabel.setBounds(555, 50, 300, 300);
		schoolLabel.setForeground(Color.white);
		
		// set the font 
		schoolLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		// set the position and text color
		hoursLabel.setBounds(850, 550, 400, 40);
		hoursLabel.setForeground(Color.white);
		
		// set the position and text color
		ossltLabel.setBounds(900, 420, 300, 100);
		ossltLabel.setForeground(Color.white);

		// set the position, font and text color
		commHours.setBounds(875, 600, 250, 40);
		commHours.setFont(new Font("Arial", Font.BOLD, 18));
		commHours.setForeground(Color.black);
		
		// set position, and horizontal alignment of the text
		contactInfo.setBounds(570, 320, 300, 300); // Label
		contactInfo.setHorizontalAlignment(SwingConstants.CENTER);
		commHours.setHorizontalAlignment(SwingConstants.CENTER);
		schoolLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// this method will only allow integer values, reject any characters
		schoolLabel.addKeyListener(new KeyAdapter() { // add key listener 
			public void keyPressed(KeyEvent e) {

				char ch = e.getKeyChar(); // store into a character variable
				if (Character.isDigit(ch)) { 
				} else {// if there is anything besides an integer  
					// display text 
					JOptionPane.showMessageDialog(null, "Only numbers are allowed!");
					schoolLabel.setText(" ");
				}
			}
		});
		
		// set position, border, font color, and font 
		contact.setBounds(450, 500, 550, 40);
		contact.setBorder(new RoundedBorder(10, 10, Color.white));
		contact.setForeground(Color.black);
		contact.setFont(new Font("Arial", Font.ITALIC, 20));
		
		// set text color and font 
		contactInfo.setForeground(Color.white);
		contactInfo.setFont(new Font("Arial", Font.BOLD, 20));
		
		// set borders 
		contact.setBorder(new RoundedBorder(10, 10, Color.white));
		schoolTxt.setBorder(new RoundedBorder(10, 10, Color.white));

		// set the text color, position, font , and alignment of text 
		creditsComplete.setForeground(Color.white);
		creditsComplete.setBounds(505, 325, 400, 40);
		creditsComplete.setFont(new Font("Arial", Font.BOLD, 20));
		creditsComplete.setHorizontalAlignment(SwingConstants.CENTER);

		// set position, font, and text color
		creditsTxt.setBounds(450, 360, 550, 40);
		creditsTxt.setFont(new Font("Arial", Font.ITALIC, 20));
		creditsTxt.setForeground(Color.black);

		// set position, background and text color 
		confirmButton.setBounds(1200, 20, 100, 30);
		confirmButton.setBackground(color2);
		confirmButton.setForeground(Color.white);

		// set position, font, and text color
		schoolTxt.setBounds(450, 220, 550, 40);
		schoolTxt.setFont(new Font("Arial", Font.ITALIC, 20));
		schoolTxt.setForeground(Color.black);
		
		// set the the position of the input text to the middle of the textfield 
		schoolTxt.setHorizontalAlignment(SwingConstants.CENTER);
		creditsTxt.setHorizontalAlignment(SwingConstants.CENTER);
		contact.setHorizontalAlignment(SwingConstants.CENTER);

		// set background, border, position, and border 
		firstPanel.setBackground(color2);
		firstPanel.setBorder(line);
		firstPanel.setBounds(210, 90, 1000, 650);
		firstPanel.setBorder(new RoundedBorder(10, 10, Color.white));

		// set the background color, a border, position, and position of panel 
		secondPanel.setBackground(color2);
		secondPanel.setBorder(line);
		secondPanel.setBounds(210, 90, 1000, 650); 
		secondPanel.setBorder(new RoundedBorder(10, 10, Color.white));

		hoursLabel.setFont(new Font("Arial", Font.BOLD, 17)); // set font and size

		// set the font and size
		ossltLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		// set font 
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setBackground(Color.white); // set background 

		// set position, background, text color, font  and size for the back button
		backButton.setBounds(470, 780, 180, 65);
		backButton.setBackground(color2);
		backButton.setForeground(Color.white);
		backButton.setFont(new Font("Arial", Font.BOLD, 17));

		// set position, background, text color, font  and size for the next button
		nextButton.setBounds(1200, 20, 100, 30);
		nextButton.setBackground(color2);
		nextButton.setForeground(Color.white);
		nextButton.setFont(new Font("Arial", Font.BOLD, 17));

		// add fields 
		add(sp);
		add(userNameText);
		add(backButton);
		add(ossltCompleted);
		add(schoolTxt);
		add(creditsTxt);
		add(commHours);
		add(creditsComplete);
		add(contactInfo);
		add(contact);
		add(markLabel);
		add(courses);
		add(schoolLabel);
		add(hoursLabel);
		add(courseInfo);

		add(ossltLabel);
		add(firstPanel);
		add(secondPanel);

		add(backgroundPanel);

		backButton.setVisible(false);

	}

	// getters and setters 
	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public ArrayList<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<String> courseList) {
		this.courseList = courseList;
	}

	public ArrayList<String> getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(ArrayList<String> courseCode) {
		this.courseCode = courseCode;
	}

	public JTextField getUserNameText() {
		return userNameText;
	}

	public void setUserNameText(JTextField userNameText) {
		this.userNameText = userNameText;
	}

	// method that will hide the first panel
	public void hidePanel1() {
		getossltCompleted().setVisible(false);
		getfirstPanel().setVisible(false);
		getCommHours().setVisible(false);
		getHoursLabel().setVisible(false);
		getOssltLabel().setVisible(false);
		getNextButton().setVisible(false);
		getBackButton().setVisible(false);

		getSp().setVisible(false);

		for (int i = 0; i < getMarkInput().length; i++) {
			getMarkLabel().setVisible(false);
			for (int j = 0; j < markInput.length; j++) {
				markInput[j].setVisible(false);

			}
			getCourses().setVisible(false);
		}

		for (int i = 0; i < getCoursesArray().length; i++) {
			coursesArray[i].setVisible(false);

		}
	}




	// mtehod that will show the first panel 
	public void showPanel1() {
		getossltCompleted().setVisible(true);
		getfirstPanel().setVisible(true);
		getCommHours().setVisible(true);
		getHoursLabel().setVisible(true);
		getOssltLabel().setVisible(true);
		getSp().setVisible(true);
		getBackButton().setVisible(false);

		for (int i = 0; i < getMarkInput().length; i++) {
			getMarkLabel().setVisible(true);
			for (int j = 0; j < markInput.length; j++) {
				markInput[j].setVisible(true);

			}
			getCourses().setVisible(true);
		}

		for (int i = 0; i < getCoursesArray().length; i++) {
			coursesArray[i].setVisible(true);

		}

		getNextButton().setVisible(true);
	}

	// method that will hide the second panel when prompted
	public void hidePanel2() {
		getSchoolLabel().setVisible(false);
		getContact().setVisible(false);
		getsecondPanel().setVisible(false);
		getConfirmButton().setVisible(false);
		getContactInfo().setVisible(false);
		getcreditsComplete().setVisible(false);
		getSchoolTxt().setVisible(false);
		getcreditsTxt().setVisible(false);

	}

	// method that will show the second panel when the next button is clicked 
	public void showPanel2() {
		getSchoolLabel().setVisible(true);
		getContact().setVisible(true);
		getsecondPanel().setVisible(true);
		getConfirmButton().setVisible(true);
		getContactInfo().setVisible(true);
		getcreditsComplete().setVisible(true);
		getSchoolTxt().setVisible(true);
		getcreditsTxt().setVisible(true);
		getBackButton().setVisible(true);
		getConfirmButton().setVisible(true);

	}

	// getters and setters 
	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getBackgroundPanel() {
		return backgroundPanel;
	}

	public void setBackgroundPanel(JPanel backgroundPanel) {
		this.backgroundPanel = backgroundPanel;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public JPanel getfirstPanel() {
		return firstPanel;
	}

	public void setfirstPanel(JPanel firstPanel) {
		this.firstPanel = firstPanel;
	}

	public JLabel getMarkLabel() {
		return markLabel;
	}

	public void setMarkLabel(JLabel markLabel) {
		this.markLabel = markLabel;
	}

	public JLabel getCourses() {
		return courses;
	}

	public void setCourses(JLabel courses) {
		this.courses = courses;
	}

	public JTextField[] getMarkInput() {
		return markInput;
	}

	public void setMarkInput(JTextField[] markInput) {
		this.markInput = markInput;
	}

	public JLabel getCompleteFields() {
		return completeFields;
	}

	public void setCompleteFields(JLabel completeFields) {
		this.completeFields = completeFields;
	}

	public JCheckBox[] getCoursesArray() {
		return coursesArray;
	}

	public void setCoursesArray(JCheckBox[] coursesArray) {
		this.coursesArray = coursesArray;
	}

	public JButton getConfirmButton() {
		return confirmButton;
	}

	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
	}
// get title 
//	public void setTitle(JLabel title) {
//		this.title = title;
//	}

	public JLabel getOssltLabel() {
		return ossltLabel;
	}

	public void setOssltLabel(JLabel ossltLabel) {
		this.ossltLabel = ossltLabel;
	}

	public JLabel getHoursLabel() {
		return hoursLabel;
	}

	public void setHoursLabel(JLabel hoursLabel) {
		this.hoursLabel = hoursLabel;
	}

	public JLabel getSchoolLabel() {
		return schoolLabel;
	}

	public void setSchoolLabel(JLabel schoolLabel) {
		this.schoolLabel = schoolLabel;
	}

	public JPanel getsecondPanel() {
		return secondPanel;
	}

	public void setsecondPanel(JPanel secondPanel) {
		this.secondPanel = secondPanel;
	}

	public JTextField getContact() {
		return contact;
	}

	public void setContact(JTextField contact) {
		this.contact = contact;
	}

	public JTextField getcreditsTxt() {
		return creditsTxt;
	}

	public void setcreditsTxt(JTextField creditsTxt) {
		this.creditsTxt = creditsTxt;
	}

	public JLabel getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(JLabel contactInfo) {
		this.contactInfo = contactInfo;
	}

	public JLabel getcreditsComplete() {
		return creditsComplete;
	}

	public void setcreditsComplete(JLabel creditsComplete) {
		this.creditsComplete = creditsComplete;
	}

	public JTextField getCommHours() {
		return commHours;
	}

	public void setCommHours(JTextField commHours) {
		this.commHours = commHours;
	}

	public JTextField getSchoolTxt() {
		return schoolTxt;
	}

	public void setSchoolTxt(JTextField schoolTxt) {
		this.schoolTxt = schoolTxt;
	}

	public JRadioButton getossltCompleted() {
		return ossltCompleted;
	}

	public void setossltCompleted(JRadioButton ossltCompleted) {
		this.ossltCompleted = ossltCompleted;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	public JTextField getUserAverage() {
		return userAverage;
	}

	public void setUserAverage(JTextField userAverage) {
		this.userAverage = userAverage;
	}

	public JPanel getFirstPanel() {
		return firstPanel;
	}

	public void setFirstPanel(JPanel firstPanel) {
		this.firstPanel = firstPanel;
	}

	public JPanel getSecondPanel() {
		return secondPanel;
	}

	public void setSecondPanel(JPanel secondPanel) {
		this.secondPanel = secondPanel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JScrollPane getSp() {
		return sp;
	}

	public void setSp(JScrollPane sp) {
		this.sp = sp;
	}

	public JButton getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(JButton courseInfo) {
		this.courseInfo = courseInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}