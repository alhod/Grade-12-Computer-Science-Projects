package Authentication.View;

//Zaid Rahman (%100)

//Date of submission: 15/12/2023
//ICS4U1 
//Student App
//This class displays the profile frame where the user can see their inputs from the survey information 
// It displays their courses, marks, osslt completion, community hours
// Their is also a profile part on the right hand of the GUI, where it displays
// the username, password and contact information
//Features: 
// - Button expansion
// 	- When button is clicked, it expands the size of the panel and displays extra information
// - Scroll pane 
// 	- Able to scroll through every grade 12 course 
// Scroll pane customized visuals
// 	- shows user information
// Major Skills 
//	- Action listeners 
//	- Clickable buttons to expand panel 
//	- Panel management 
//		- Panels are managed through grid layouts 

// import 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ProfileFrame extends JFrame implements ActionListener {

	private ImageIcon profile = new ImageIcon(); // create image icon
	private JLabel input = new JLabel(); // create label

	// create course and mark textfield arrays
	private JTextField[] coursesLabel = new JTextField[6];
	private JTextField[] marksLabel = new JTextField[6];

	// create panels
	private JPanel gradPanel = new JPanel();
	private JPanel markPanel = new JPanel();
	private JPanel topPanel = new JPanel();

	// create text fields
	private JTextField hours = new JTextField();
	private JTextField ossltLabel = new JTextField();

	// create panels
	private JPanel backgroundPanel = new JPanel();

	private JPanel profilePanel = new JPanel();

	// create labels
	private JLabel courseHeader = new JLabel("Course Name");

	private JLabel markHeader = new JLabel("Course Average");

	private JLabel gradHeader = new JLabel("Graduation Requirements");

	private JLabel ossltHeader = new JLabel("OSSLT Completion");

	private JLabel hoursHeader = new JLabel("Community Hours");

	private JLabel image = new JLabel();

	private JLabel profileHeader = new JLabel("Profile");

	// create text fields
	private JTextField displayUsername = new JTextField();

	private JTextField displayPassword = new JTextField();

	private JTextField displayContactInfo = new JTextField();

	private JTextField average = new JTextField();

	private RegisterFrame registerFrame = new RegisterFrame();
	// create buttons
	private JButton hidePassword = new JButton();
	private JButton viewUserInfo = new JButton("User Info ▼");
	private JButton expand = new JButton("View Courses ▼");

	// create array lists
	ArrayList<String> courseList = new ArrayList<String>();
	ArrayList<String> courseCode = new ArrayList<String>();

	// create panels
	private JPanel userInfoPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	// create scroll panels
	JScrollPane sp = new JScrollPane(buttonPanel);

	// create text fields d
	JTextField[] coursesArray = new JTextField[48];
	Color labelColor = new Color(53, 56, 49); // create label colors

	// button to go back to register frame
	private JButton registerButton = new JButton("Back to Log In");

	// method that will add courses and course lists
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
		courseList.add("First Nations, Métis and Inuit Governance in Canada");
		courseList.add("Contemporary Indigenous Issues and Perspectives in a Global Context");
		courseList.add("Recreation and Healthy Active Living Leadership");
		courseList.add("Introductory Kinesiology");
		courseList.add("Earth and Space Science");
		courseList.add("Science");
		courseList.add("Computer Engineering Technology");
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

	public ProfileFrame() {

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close

		// add action listener
		expand.addActionListener(this);
		viewUserInfo.addActionListener(this);
		registerButton.addActionListener(this);

		addingCourses(courseCode, courseList); // call method for course and code

		// create border
		Border border = BorderFactory.createLineBorder(Color.white, 3);

		// create color
		Color color2 = new Color(56, 66, 59);
		Color colorBackground = new Color(53, 56, 49);

		// create image icon
		ImageIcon profileIcon = new ImageIcon("images/profile.png");

		// create images and image icon
		Image scaleImage = profileIcon.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(scaleImage);
		JLabel label = new JLabel(icon);
		label.setBounds(1190, 80, 100, 100); // set position
		add(label); // add label

		// set border, position, background, text color
		viewUserInfo.setBorder(new RoundedBorder(10, 10, Color.white));
		viewUserInfo.setBounds(1175, 180, 150, 30);
		userInfoPanel.setBounds(1175, 223, 150, 300);
		viewUserInfo.setBackground(labelColor);
		viewUserInfo.setForeground(Color.white);
		viewUserInfo.setBorder(border);
		viewUserInfo.setBorder(new RoundedBorder(5, 5, Color.white));

		// add field to frame
		add(viewUserInfo);

		userInfoPanel.setLayout(new GridLayout(0, 1)); // set a grid layout

		// add fields to the panel
		userInfoPanel.add(displayUsername);
		userInfoPanel.add(displayPassword);
		userInfoPanel.add(displayContactInfo);

		// set the horizontal text allignment to the center of the texfield
		displayUsername.setHorizontalAlignment(SwingConstants.CENTER);
		displayPassword.setHorizontalAlignment(SwingConstants.CENTER);
		displayContactInfo.setHorizontalAlignment(SwingConstants.CENTER);

		// set editability to false
		// ensures user cannot change data
		displayUsername.setEditable(false);
		displayPassword.setEditable(false);
		displayContactInfo.setEditable(false);

		// set the position, and font size
		displayUsername.setBounds(1175, 180, 150, 30);
		displayUsername.setFont(new Font("Arial", Font.PLAIN, 15));

		// set text color,background and font of the username , password , and the
		// contact information
		displayUsername.setForeground(Color.white);
		displayUsername.setBackground(labelColor);

		displayPassword.setBounds(1190, 225, 100, 30);
		displayPassword.setForeground(Color.white);

		displayPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		displayPassword.setForeground(Color.white);
		displayPassword.setBackground(labelColor);

		displayContactInfo.setBounds(1190, 260, 100, 30);
		displayContactInfo.setFont(new Font("Arial", Font.PLAIN, 15));
		displayContactInfo.setForeground(Color.white);
		displayContactInfo.setBackground(labelColor);

		average.setFont(new Font("Arial", Font.ITALIC, 12));
		average.setForeground(Color.white);
		average.setBackground(labelColor);

		// set size of the background panel
		backgroundPanel.setSize(1440, 900);

		// set position and background color of the mark panel
		markPanel.setBounds(20, 65, 700, 720);
		markPanel.setBackground(Color.white);

//		coursePanel.setBounds(20, 30, 300, 800);
//		coursePanel.setBackground(Color.white);

		// set the position and background color of the profile panel
		profilePanel.setBounds(1145, 80, 200, 700);
		profilePanel.setBackground(color2);

		profilePanel.setBorder(border); // set the border

		// set the position and background color
		gradPanel.setBounds(750, 75, 300, 730);
		gradPanel.setBackground(color2);

		gradHeader.setBounds(770, 5, 400, 50); // set the position
		gradHeader.setForeground(Color.white); // set the text color

		profileHeader.setBounds(1210, 5, 400, 50); // set the position
		profileHeader.setForeground(Color.white); // set the text color

		// set the font of the title headers
		profileHeader.setFont(new Font("Arial", Font.BOLD, 22));
		gradHeader.setFont(new Font("Arial", Font.BOLD, 22));
		courseHeader.setFont(new Font("Arial", Font.BOLD, 22));
		markHeader.setFont(new Font("Arial", Font.BOLD, 22));

		// set position and add a border to each panel
		topPanel.setBounds(0, 0, 1423, 50);
		topPanel.setBorder(border);
		markPanel.setBorder(new RoundedBorder(10, 10, Color.white));
		gradPanel.setBorder(border);
		gradPanel.setBorder(new RoundedBorder(10, 10, Color.white));
		markPanel.setBorder(new RoundedBorder(10, 10, Color.white));
		profilePanel.setBorder(new RoundedBorder(10, 10, Color.white));

		// create a color for the top panel
		Color topColor = new Color(56, 66, 59);
		topPanel.setBackground(topColor); // add that colr

		// set position , text colors, size , and font for the headers
		courseHeader.setBounds(100, 5, 300, 50);
		courseHeader.setForeground(Color.white);
		markHeader.setForeground(Color.white);
		markHeader.setBounds(435, 5, 300, 50);

		ossltHeader.setBounds(820, 80, 200, 40);
		ossltHeader.setFont(new Font("Arial", Font.BOLD, 16));
		ossltHeader.setForeground(Color.white);
		ossltHeader.setBackground(color2);

		hoursHeader.setBounds(820, 180, 200, 40);
		hoursHeader.setFont(new Font("Arial", Font.BOLD, 16));
		hoursHeader.setForeground(Color.white);
		hoursHeader.setBackground(color2);

		ossltLabel.setBounds(770, 120, 260, 45);
		ossltLabel.setFont(new Font("Arial", Font.BOLD, 16));
		ossltLabel.setForeground(Color.white);
		ossltLabel.setBackground(labelColor);
		ossltLabel.setEditable(false);
		ossltLabel.setHorizontalAlignment(SwingConstants.CENTER);

		ossltLabel.setBorder(new RoundedBorder(10, 10, Color.white));

		hours.setBounds(770, 215, 260, 45); // set the position
		hours.setFont(new Font("Arial", Font.BOLD, 16)); // set font and size
		hours.setForeground(Color.white); // set text color
		hours.setBackground(labelColor); // set the background color
		hours.setBorder(new RoundedBorder(10, 10, Color.white)); // set the border
		hours.setHorizontalAlignment(SwingConstants.CENTER); // center the text field

		hours.setEditable(false); // set editability to false

		backgroundPanel.setBackground(colorBackground); // set the background color
		markPanel.setBackground(color2);// set the background color
//		coursePanel.setBackground(color2);

		// use a for loop to iterate through each mark and course
		// the size of the for loop will be the amount of course labels there are
		// since the marks and courses have the same length, you do not need
		// seperate for loops
		for (int i = 0; i < coursesLabel.length; i++) {
			// create instances
			coursesLabel[i] = new JTextField();
			marksLabel[i] = new JTextField();

			marksLabel[i].setHorizontalAlignment(JTextField.LEFT); // center

			// set editability
			marksLabel[i].setEditable(false);
			coursesLabel[i].setEditable(false);

			// set the positon, font , text color, background , and border size and it's
			// color
			coursesLabel[i].setBounds(40, 80 + (i * 125), 390, 75);

			marksLabel[i].setBounds(430, 80 + (i * 125), 250, 75);

			marksLabel[i].setFont(new Font("Arial", Font.PLAIN, 20));

			coursesLabel[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
			coursesLabel[i].setForeground(Color.white);
			marksLabel[i].setForeground(Color.white);

			coursesLabel[i].setBackground(labelColor);
			marksLabel[i].setBackground(labelColor);

			coursesLabel[i].setBorder(new RoundedBorder(10, 10, Color.white));
			marksLabel[i].setBorder(new RoundedBorder(10, 10, Color.white));

			// add them to the frame
			add(marksLabel[i]);
			add(coursesLabel[i]);

		}

		// set the border, background color, position, font, and font color of the
		// expand button
		expand.setBorder(new RoundedBorder(10, 10, Color.white));
		expand.setBackground(labelColor);
		expand.setBounds(810, 300, 150, 30);
		expand.setFont(new Font("Arial", Font.PLAIN, 15));
		expand.setForeground(Color.white);

		registerButton.setBorder(new RoundedBorder(10, 10, Color.white));
		registerButton.setBackground(labelColor);
		registerButton.setBounds(1175, 600, 150, 30);
		registerButton.setFont(new Font("Arial", Font.PLAIN, 15));
		registerButton.setForeground(Color.white);

		add(registerButton);// add the register button
		// set the position and size of the scroll pane
		sp.setBounds(770, 330, 260, 450);

		// set the scroll bar
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(sp);// add the scroll bar
		sp.add(buttonPanel); // add the button panel to the scroll bar

		// set its visibility to false
		sp.setVisible(false);
		userInfoPanel.setVisible(false);

		// add components to the frame
		add(gradHeader);
		add(courseHeader);
		add(markHeader);
		add(ossltHeader);
		add(hoursHeader);
		add(profileHeader);
		add(hours);
		add(topPanel);
		add(expand);
		add(hidePassword);
		add(userInfoPanel);
		add(image);
		add(ossltLabel);
		add(gradPanel);
		add(markPanel);
		add(profilePanel);
		add(backgroundPanel);

		// change the scroll bar visuals
		sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {

				// set the bar color to gray
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.LIGHT_GRAY;
			}
		});
		// change the horizontal scroll bar visuals
		sp.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {

				// set the bar color to gray
				this.thumbColor = Color.GRAY;
				this.trackColor = Color.LIGHT_GRAY;
			}
		});

		// Remove border
		sp.setBorder(null);
		setVisible(false); // set visibility to false
		setSize(1440, 850);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(expand)) { // if user clicks the expand button

			buttonPanel.setLayout(new GridLayout(0, 1)); // create a grid layout
			
			// test to see if they are the same size
			System.out.println("courseList size: " + courseList.size());
			System.out.println("courseCode size: " + courseCode.size());

			// iterate through the courses
			for (int i = 0; i < coursesArray.length-1; i++) {

				coursesArray[i] = new JTextField();// create an instance of a text field

				// set the text field of the courses to the corresponding name and code
				coursesArray[i].setText(courseList.get(i) + "," + courseCode.get(i) + "\n");

				// set font, text color, and editability
				coursesArray[i].setFont(new Font("Arial", Font.PLAIN, 13));
				coursesArray[i].setForeground(Color.black);
				coursesArray[i].setEditable(false);
				buttonPanel.add(coursesArray[i]); // add courses to button panel

			}

			sp.setViewportView(buttonPanel);
			sp.setVisible(!sp.isVisible());
		}

		if (e.getSource().equals(viewUserInfo)) {
			userInfoPanel.setVisible(!userInfoPanel.isVisible());
			average.setVisible(true);

		}

	}

	// getters and setters
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

	public JTextField[] getCoursesArray() {
		return coursesArray;
	}

	public void setCoursesArray(JTextField[] coursesArray) {
		this.coursesArray = coursesArray;
	}

	public Color getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

	public JLabel getGradHeader() {
		return gradHeader;
	}

	public void setGradHeader(JLabel gradHeader) {
		this.gradHeader = gradHeader;
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

	public ImageIcon getProfile() {
		return profile;
	}

	public void setProfile(ImageIcon profile) {
		this.profile = profile;
	}

	public JLabel getInput() {
		return input;
	}

	public void setInput(JLabel input) {
		this.input = input;
	}

	public JTextField[] getCoursesLabel() {
		return coursesLabel;
	}

	public void setCoursesLabel(JTextField[] coursesLabel) {
		this.coursesLabel = coursesLabel;
	}

	public JTextField[] getMarksLabel() {
		return marksLabel;
	}

	public void setMarksLabel(JTextField[] marksLabel) {
		this.marksLabel = marksLabel;
	}

	public JPanel getGradPanel() {
		return gradPanel;
	}

	public void setGradPanel(JPanel gradPanel) {
		this.gradPanel = gradPanel;
	}

	public JTextField getOssltLabel() {
		return ossltLabel;
	}

	public void setOssltLabel(JTextField ossltLabel) {
		this.ossltLabel = ossltLabel;
	}

	public JPanel getMarkPanel() {
		return markPanel;
	}

	public void setMarkPanel(JPanel markPanel) {
		this.markPanel = markPanel;
	}

	public JTextField getHours() {
		return hours;
	}

	public void setHours(JTextField hours) {
		this.hours = hours;
	}

	public JPanel getBackgroundPanel() {
		return backgroundPanel;
	}

	public void setBackgroundPanel(JPanel backgroundPanel) {
		this.backgroundPanel = backgroundPanel;
	}

	public JPanel getProfilePanel() {
		return profilePanel;
	}

	public void setProfilePanel(JPanel profilePanel) {
		this.profilePanel = profilePanel;
	}

	public JLabel getMarkHeader() {
		return markHeader;
	}

	public void setMarkHeader(JLabel markHeader) {
		this.markHeader = markHeader;
	}

	public JLabel getCourseHeader() {
		return courseHeader;
	}

	public void setCourseHeader(JLabel courseHeader) {
		this.courseHeader = courseHeader;
	}

	public JTextField getDisplayUsername() {
		return displayUsername;
	}

	public void setDisplayUsername(JTextField displayUsername) {
		this.displayUsername = displayUsername;
	}

	public JTextField getDisplayPassword() {
		return displayPassword;
	}

	public void setDisplayPassword(JTextField displayPassword) {
		this.displayPassword = displayPassword;
	}

	public JTextField getDisplayContactInfo() {
		return displayContactInfo;
	}

	public void setDisplayContactInfo(JTextField displayContactInfo) {
		this.displayContactInfo = displayContactInfo;
	}

	public JLabel getOssltHeader() {
		return ossltHeader;
	}

	public void setOssltHeader(JLabel ossltHeader) {
		this.ossltHeader = ossltHeader;
	}

	public JLabel getHoursHeader() {
		return hoursHeader;
	}

	public void setHoursHeader(JLabel hoursHeader) {
		this.hoursHeader = hoursHeader;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JLabel getProfileHeader() {
		return profileHeader;
	}

	public void setProfileHeader(JLabel profileHeader) {
		this.profileHeader = profileHeader;
	}

	public JTextField getAverage() {
		return average;
	}

	public void setAverage(JTextField average) {
		this.average = average;
	}

	public JButton getExpand() {
		return expand;
	}

	public void setExpand(JButton expand) {
		this.expand = expand;
	}

	public JButton getHidePassword() {
		return hidePassword;
	}

	public void setHidePassword(JButton hidePassword) {
		this.hidePassword = hidePassword;
	}

	public JButton getViewUserInfo() {
		return viewUserInfo;
	}

	public void setViewUserInfo(JButton viewUserInfo) {
		this.viewUserInfo = viewUserInfo;
	}

	public JPanel getUserInfoPanel() {
		return userInfoPanel;
	}

	public void setUserInfoPanel(JPanel userInfoPanel) {
		this.userInfoPanel = userInfoPanel;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public RegisterFrame getRegisterFrame() {
		return registerFrame;
	}

	public void setRegisterFrame(RegisterFrame registerFrame) {
		this.registerFrame = registerFrame;
	}
}
