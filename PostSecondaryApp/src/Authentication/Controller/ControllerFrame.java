package Authentication.Controller;

//// Zaid Rahman (%100)
//Date of submission: 15/12/2023
//ICS4U1 
//Student App
//this class is responsible for handling 
//
//Features: 
// User registration and login
//	- allows registration for username and password
//	- input validation for registation and login
// Survey operation:
//  - Validates and processes uer input
//    - invalid input would result in warnings showing up
// 	  - Minimum course selectio and character and integer validations only 
//		for some questions 
//	- stores and retrieves user information through a survey interface for future use 
// 	- Completion status 
// Profile/User display
// 	- Displays user data after completing survey frame 
//Major Skills: 
// 	- File i/o 
//		-stores and retrieves data from text file
//	- manages user information such as username , passwords, marks, courses 
//		completion status, input validation
//	- uses data structures such as hashmap and array lists 
//	- compares hashmap with user input 
// Event Handling 
//	- Uses action listener and item listeners to interact with user input
//- action performed is used for checkbox selections and 
// replaces invalid text with "_" , when pressing the space bar 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import Authentication.Model.UserDataBase;
import Authentication.View.ProfileFrame;
import Authentication.View.RegisterFrame;
import Authentication.View.SurveyFrame;

//

public class ControllerFrame implements ActionListener, ItemListener {

	// create instances of other classes
	private UserDataBase userDataBase = new UserDataBase(); // for the user data
	private SurveyFrame surveyFrame = new SurveyFrame(); // survey
	private RegisterFrame registerFrame = new RegisterFrame(); // register/ login frame
	private ProfileFrame profile = new ProfileFrame(); // profile / user display frame
	private HashSet<String> selectedCourses = new HashSet<String>();
	
	// used to keep track of whether a button is clicked
	private String buttonStatus;
	private int userCounter; // keeps track of the user
	private int userCount;

	// used to store username and password, will be used when comparing user input
	// with previous credentials when logging in
	private HashMap<String, String> data = new HashMap<String, String>();

	private String storedUserName; // variable to store the user names
	private String storedPassword; // variable to store the passwords
	private int intCounter; // counter that parses to integer type
	private String stringCounter; // counter that is string type, used to convert to integer
	private boolean surveyCompleted;

	// Instance of controller class
	private Controller controller;

	// constructor
	public ControllerFrame(Controller controller) {

		// Sets the current instance of the Controller class
		setController(controller);

		registerFrame.setVisible(true); // always open the register frame
//		profile.setVisible(true);

		surveyCompleted = false; // set to false as survey is not completed yet
		storeData(); // call the store data method
		addActionListenersToButtons(); // method to add action listeners to buttons
	}

	// method to store data
	public void storeData() {
		String totalUsers = userDataBase.retrieveUserCount(); // Retrieve total user count

		int total = Integer.parseInt(totalUsers); // parse the total users to an integer type
		// Iterate through all users and store their credentials in the 'data' HashMap
		for (int i = 1; i <= total; i++) {

			String storedUserName = userDataBase.retrieveUserName(i);
			String storedPassword = userDataBase.retrievePassword(i);

			// Store the username and password in the 'data' HashMap
			data.put(storedUserName, storedPassword);
		}

	}

	// add action listeners to swing components
	public void addActionListenersToButtons() {

		// for register frame
		registerFrame.getUserName().addActionListener(this);
		registerFrame.getPassword().addActionListener(this);
		registerFrame.getRegister().addActionListener(this);
		registerFrame.getLogin().addActionListener(this);

		// iterate through each course label and set an action listener
		for (int i = 0; i < surveyFrame.getCoursesArray().length; i++) {
			surveyFrame.getCoursesArray()[i].addItemListener(this);
		}

		// for survey frame
		surveyFrame.getSchoolTxt().addActionListener(this);
		surveyFrame.getContact().addActionListener(this);
		surveyFrame.getcreditsTxt().addActionListener(this);
		surveyFrame.getCommHours().addActionListener(this);
		surveyFrame.getossltCompleted().addActionListener(this);
		surveyFrame.getConfirmButton().addActionListener(this);
		surveyFrame.getNextButton().addActionListener(this);
		surveyFrame.getBackButton().addActionListener(this);
		
		// for profile frame
		profile.getRegisterButton().addActionListener(this);
	}

	@Override

	public void actionPerformed(ActionEvent e) {

		// store the user name and passwords to a string variable
		String userNameInput = registerFrame.getUserName().getText();
		String passwordInput = registerFrame.getPassword().getText();

		// if user types a space for the username and password, replace it with "_"
		userNameInput = userNameInput.replace(" ", "_"); //
		passwordInput = passwordInput.replace(" ", "_");

		// if user clicks on login button
		if (e.getSource().equals(registerFrame.getLogin())) {

			// input validation : user cannot enter these fields
			if (userNameInput.equals("Enter username") || passwordInput.equals("Enter password")) {
				JOptionPane.showMessageDialog(null, "Invalid Login"); // display a message
				return;
			}

			// Check if the entered username exists and if the password matches
			boolean found = false;
			for (Map.Entry<String, String> entry : data.entrySet()) { // iterate through the hashmap
				String storedUserName = entry.getKey(); // set the hashmap key to the username
				String storedPassword = entry.getValue(); // set the secondary parameter to the password

				// if the user enters a username and password already stored in the database
				if (storedUserName.equals(userNameInput) && storedPassword.equals(passwordInput)) {
					found = true; // set found to true
					break; // break from the statement
				}
			}

			if (found) { // if found is true
				JOptionPane.showMessageDialog(null, "Valid Login"); // show valid login
				displayUserText(); // display the user text
				getController().setLogged_in(true);
				profile.setVisible(false);
				return;
			}

			else { // if found is false
				JOptionPane.showMessageDialog(null, "Invalid Login"); // display this
				return;
			}
		}

		// *** Register Frame *** //

		// gets value from file and stores it into a string variable
		stringCounter = userDataBase.retrieveUserCount();
		intCounter = Integer.parseInt(stringCounter); // converts from string to int variable

		// if counter is set to 0
		if (intCounter == 0) {
			storedUserName = null;
			storedPassword = null;
		}
		// if counter is greater than 0, retrieve username and password
		if (intCounter > 0) {
			storedUserName = userDataBase.retrieveUserName(intCounter);
			storedPassword = userDataBase.retrievePassword(intCounter);
		}

		// registering with a new account

		// if the username and password are not already found in database
		if (!(userNameInput.equals(storedUserName) && passwordInput.equals(storedPassword))) {

			// if username and password have a length of 3 or more
			if (userNameInput.length() > 3 && passwordInput.length() > 3
					&& e.getSource() == registerFrame.getRegister()) {

				// increment the counter
				intCounter++;

				userDataBase.storeUserCount(intCounter);// store the counter in the user data base

				// retrieve the user count and set it to a string variable
				String newstringCounter = userDataBase.retrieveUserCount(); // 1

				// convert string to int
				userCount = Integer.parseInt(newstringCounter); // 1

				// set register frame to not visible
				registerFrame.setVisible(false);
				surveyFrame.setVisible(true); // set survey frame to visible

				// store user credentials
				userDataBase.storeUserCredentials(userNameInput, passwordInput, userCount); // 1

				// testing phase
				System.out.println("User" + intCounter + " email: " + userDataBase.retrieveUserName(userCount));

				System.out.println("User" + intCounter + " password: " + userDataBase.retrievePassword(userCount));

			}

			// if the passwqord length and username are less than 3, show a message
			else if (passwordInput.length() <= 3) {
				JOptionPane.showMessageDialog(null, "Password must be 3 or more characters");

			} else if (userNameInput.length() <= 3) {
				JOptionPane.showMessageDialog(null, "Username must be 3 or more characters");

			}
		}

//		// rif username and password are already in data base 
//		if (userNameInput.equals(storedUserName) && passwordInput.equals(storedPassword)
//				&& e.getSource() == registerFrame.getRegister()) {
//			// do not open surveyframe and close register frame 
//			surveyFrame.setVisible(false);
//			registerFrame.setVisible(false);
//			registerFrame.getRegister().setBackground(Color.red);
//			JOptionPane.showMessageDialog(null, "Already registered with this account");
//			return;
//		}

		// *** Register Frame *** //

		// store user input in corresponding fields
		String currentSchool = surveyFrame.getSchoolTxt().getText();
		String contactInfo = surveyFrame.getContact().getText();
		String coursesAmount = surveyFrame.getcreditsTxt().getText();
		String hours = surveyFrame.getCommHours().getText();
		buttonStatus = surveyFrame.getossltCompleted().getText();
		surveyFrame.getUserNameText().setText(storedUserName);

		// show the first panel and hide the second panel
		surveyFrame.showPanel1();
		surveyFrame.hidePanel2();
		

		// if user clicks on next button
		if (e.getSource() == surveyFrame.getNextButton()) {

			// amount of courses that can be selected
			int selectedCourseCount = 0; // set to 0

			// check to see if the check boxes are selected
			for (JCheckBox currentButton : surveyFrame.getCoursesArray()) {

				// if they are then increment the course count
				if (currentButton.isSelected()) { //
					selectedCourseCount++;
				}
			}
			// Set your minimum required courses count
			int minimumCourses = 6;

			// if the selected courses dont meet the minimum course count
			if (selectedCourseCount != minimumCourses) {

				// show message dialog
				JOptionPane.showMessageDialog(null, "Please select " + minimumCourses + " courses.");
				return; // Prevent other actions if the minimum isn't met
			}

			// hold the marks
			ArrayList<String> marksList = new ArrayList<>();

			// iterate through the marks
			for (int i = 0; i < surveyFrame.getMarkInput().length; i++) {
				String markText = surveyFrame.getMarkInput()[i].getText().trim(); // gets rid of spaces

				marksList.add(markText);// text without space
				if (markText.isEmpty()) { // if text field is empty
					JOptionPane.showMessageDialog(null, "Must enter field"); // display message
					return; // no further actions
				}

				// convert from string to integer
				int convert = Integer.parseInt(markText);
				if (convert > 100) { // if the mark is more than 100
					JOptionPane.showMessageDialog(null, "Mark cannot be greater than 100");
					return;// no further action
				} else if (convert < 0) { // if mark is less than 100
					JOptionPane.showMessageDialog(null, "Mark cannot be less than 0 ");
					return; // no further action
				}
			}

			//

			// use enumeration to iterate through all the radio buttons
			Enumeration<AbstractButton> radioButtons = surveyFrame.getGroup().getElements();
			JRadioButton radioButton = null; // set it to null

			while (radioButtons.hasMoreElements()) { // get next radio button

				// if none are selected
				radioButton = (JRadioButton) radioButtons.nextElement();
				if (radioButton.isSelected()) { // if selected
					break; // no further action
				}
			}
			surveyFrame.showPanel2(); // show second panel
			surveyFrame.hidePanel1(); // hide first

		}
		

		// if the confirm button is clicked
		if (e.getSource() == surveyFrame.getConfirmButton()) {
			ArrayList<String> marksList = new ArrayList<>(); // create an array lists thtat stores all the marks

			// iterate through all the marks
			for (int i = 0; i < surveyFrame.getMarkInput().length; i++) {

				// trunucates spaces if user enters them
				String markText = surveyFrame.getMarkInput()[i].getText().trim(); // gets rid of spaces

				// add the new truncated text to the marks array
				marksList.add(markText);
				if (markText.isEmpty()) { // if the marks is empty
					JOptionPane.showMessageDialog(null, "Must enter field"); // display a message
					return; // no further actions
				} else if (markText.length() > 3) { // if mark is greater than 100
					JOptionPane.showMessageDialog(null, "Cannot have a mark greater than 100"); // display message
					return; // no further actions
				}
			}

			// use enumeration to store all buttons
			Enumeration<AbstractButton> radioButtons = surveyFrame.getGroup().getElements();
			JRadioButton radioButton = null; // set to null

			while (radioButtons.hasMoreElements()) { // use while to loop through

				// set the next button to the variable
				radioButton = (JRadioButton) radioButtons.nextElement();
				if (radioButton.isSelected()) { // if user clicks on the radio button
					buttonStatus = "Completed"; // set the text
					break;
				} else {
					buttonStatus = "Not Completed"; // set the text
				}
			}

			String schoolText = surveyFrame.getSchoolTxt().getText().trim(); // gets rid of spaces
			if (schoolText.isEmpty()) { // if the textfield is empty
				JOptionPane.showMessageDialog(null, "Must enter school attended"); // display a message
				return; // no further actions
			}

			String contactText = surveyFrame.getContact().getText().trim(); // gets rid of spaces
			if (contactText.isEmpty()) {// if text field is empty
				JOptionPane.showMessageDialog(null, "Must enter contact information");// if the textfield is empty
				return;// no further actions
			}

			String coursesAmountTxt = surveyFrame.getcreditsTxt().getText().trim(); // gets rid of spaces
			if (coursesAmountTxt.isEmpty()) { // if text field is empty
				JOptionPane.showMessageDialog(null, "Must enter amount of courses");// if the textfield is empty
				return;// no further actions
			}

			// all the conditions above must be entered to go to the user profile frame
			surveyFrame.getConfirmButton().setVisible(true);
			// if user clicks on the confirm button
			if (e.getSource() == surveyFrame.getConfirmButton()) {
				surveyCompleted = true; // set completion status to true
				surveyFrame.setVisible(false); // set the survey frame visibility to false
				profile.setVisible(true); // set the profile visibility to true

				// store the user input to the buffered writer method created
				userDataBase.storeUserSurvey(selectedCourses, marksList, radioButton, hours, currentSchool, contactInfo,
						coursesAmount, intCounter, buttonStatus);
				displayUserText(); // call method

			}
		}
		if (e.getSource() == registerFrame.getRegister()) { 
			registerFrame.setVisible(false);
			surveyFrame.setVisible(true);
		}
		if(e.getSource() == profile.getRegisterButton())	 {
			storeData();
			profile.setVisible(false);
			registerFrame.setVisible(true);
		}
	}



	// this method sets the text that the user previously inputted from the survey
	// frame
	public void displayUserText() {

		// set the label to the user inputted courses
		profile.getCoursesLabel()[0].setText(userDataBase.retrieveCourse1(userCount));
		profile.getCoursesLabel()[1].setText(userDataBase.retrieveCourse2(userCount));
		profile.getCoursesLabel()[2].setText(userDataBase.retrieveCourse3(userCount));
		profile.getCoursesLabel()[3].setText(userDataBase.retrieveCourse4(userCount));
		profile.getCoursesLabel()[4].setText(userDataBase.retrieveCourse5(userCount));
		profile.getCoursesLabel()[5].setText(userDataBase.retrieveCourse6(userCount));

		// set the marks to the user inputted marks
		profile.getMarksLabel()[0].setText("Mark = " + userDataBase.retrieveMark1(userCount) + "%");
		profile.getMarksLabel()[1].setText("Mark = " + userDataBase.retrieveMark2(userCount) + "%");
		profile.getMarksLabel()[2].setText("Mark = " + userDataBase.retrieveMark3(userCount) + "%");
		profile.getMarksLabel()[3].setText("Mark = " + userDataBase.retrieveMark4(userCount) + "%");
		profile.getMarksLabel()[4].setText("Mark = " + userDataBase.retrieveMark5(userCount) + "%");
		profile.getMarksLabel()[5].setText("Mark = " + userDataBase.retrieveMark6(userCount) + "%");

		// set the username, password, contact info, hours
		// , and osslt status inputs to the corresponding textfields
		profile.getDisplayUsername().setText(userDataBase.retrieveUserName(userCount));
		profile.getDisplayPassword().setText(userDataBase.retrievePassword(userCount));
		profile.getDisplayContactInfo().setText("Contact Info: " + userDataBase.retrieveContactInfo(userCount));
		profile.getHours().setText(userDataBase.retrieveHours(userCount) + " hours");
		profile.getOssltLabel().setText(userDataBase.retrieveOsslt(userCount));
		
		// this was originally used to print the average marks for all courses 
		// however i decided not to implement this 
//		for (int i = 0; i < userCounter; i++) {
//			String total1 = userDataBase.retrieveMark1(userCount);
//			String total2 = userDataBase.retrieveMark2(userCount);
//			String total3 = userDataBase.retrieveMark3(userCount);
//			String total4 = userDataBase.retrieveMark4(userCount);
//			String total5 = userDataBase.retrieveMark5(userCount);
//			String total6 = userDataBase.retrieveMark6(userCount);
//
//			int parseTotal1 = Integer.valueOf(total1);
//			int parseTotal2 = Integer.valueOf(total2);
//			int parseTotal3 = Integer.valueOf(total3);
//			int parseTotal4 = Integer.valueOf(total4);
//			int parseTotal5 = Integer.valueOf(total5);
//			int parseTotal6 = Integer.valueOf(total6);
//
//			int total = parseTotal1 + parseTotal2 + parseTotal3 + parseTotal4 + parseTotal5 + parseTotal6;
//
//			double average = total / 6;
//			String stringAverage = String.valueOf(average);
//
//			profile.getAverage().setText(userDataBase.retrieveCredits(userCount));
//			System.out.print("AVERAGE " + average);
//		}

		// test by printing user data (USER FOR TEST MATRIX )
		System.out.println("User" + intCounter + " course1: " + userDataBase.retrieveCourse1(userCount));
		System.out.println("User" + intCounter + " course2: " + userDataBase.retrieveCourse2(userCount));
		System.out.println("User" + intCounter + " course3: " + userDataBase.retrieveCourse3(userCount));
		System.out.println("User" + intCounter + " course4: " + userDataBase.retrieveCourse4(userCount));
		System.out.println("User" + intCounter + " course5: " + userDataBase.retrieveCourse5(userCount));
		System.out.println("User" + intCounter + " courses6: " + userDataBase.retrieveCourse6(userCount));

		System.out.println("User" + intCounter + " Mark1: " + userDataBase.retrieveMark1(userCount));
		System.out.println("User" + intCounter + " Marks2: " + userDataBase.retrieveMark2(userCount));
		System.out.println("User" + intCounter + " Marks3: " + userDataBase.retrieveMark3(userCount));
		System.out.println("User" + intCounter + " Marks4: " + userDataBase.retrieveMark4(userCount));
	
		System.out.println("User" + intCounter + " Marks5:  " + userDataBase.retrieveMark5(userCount));
		System.out.println("User" + intCounter + " Marks6: " + userDataBase.retrieveMark6(userCount));
	

		System.out.println("User" + intCounter + " hours: " + userDataBase.retrieveHours(userCount));
		System.out.println("User" + intCounter + " current school: " + userDataBase.retrieveSchool(userCount));
		System.out.println("User" + intCounter + " contact info: " + userDataBase.retrieveContactInfo(userCount));
		System.out.println("User" + intCounter + " credits earned: " + userDataBase.retrieveCredits(userCount));

		System.out.println("User" + intCounter + " osslt Completion: " + userDataBase.retrieveOsslt(userCount));}
	

	@Override
	
	// method that will keep track of buttons clicked in the array 
	public void itemStateChanged(ItemEvent e) {

		int stringCounter = 0;
		if (e.getStateChange() == ItemEvent.SELECTED) { // check to see whether buttons are selected 

			for (JCheckBox currentButton : surveyFrame.getCoursesArray()) { // iterate through the buttons 
				if (currentButton.isSelected()) { //if button is selected 
					stringCounter++; // increment counter 
					selectedCourses.add(currentButton.getText()); // add the button text 

				}

			}
		} else { 
			JCheckBox currentBox = (JCheckBox) e.getSource(); // if the button is selected 
			String text = currentBox.getText(); // create text variable; stores text 
			selectedCourses.remove(text); // remove 

		}
	}

	public UserDataBase getUserDataBase() {
		return userDataBase;
	}

	public void setUserDataBase(UserDataBase userDataBase) {
		this.userDataBase = userDataBase;
	}

	public SurveyFrame getSurveyFrame() {
		return surveyFrame;
	}

	public void setSurveyFrame(SurveyFrame surveyFrame) {
		this.surveyFrame = surveyFrame;
	}

	public RegisterFrame getRegisterFrame() {
		return registerFrame;
	}

	public void setRegisterFrame(RegisterFrame registerFrame) {
		this.registerFrame = registerFrame;
	}

	public ProfileFrame getProfile() {
		return profile;
	}

	public void setProfile(ProfileFrame profile) {
		this.profile = profile;
	}

	public HashSet<String> getSelectedCourses() {
		return selectedCourses;
	}

	public void setSelectedCourses(HashSet<String> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}

	public String getButtonStatus() {
		return buttonStatus;
	}

	public void setButtonStatus(String buttonStatus) {
		this.buttonStatus = buttonStatus;
	}

	public int getUserCounter() {
		return userCounter;
	}

	public void setUserCounter(int userCounter) {
		this.userCounter = userCounter;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	public String getStoredUserName() {
		return storedUserName;
	}

	public void setStoredUserName(String storedUserName) {
		this.storedUserName = storedUserName;
	}

	public String getStoredPassword() {
		return storedPassword;
	}

	public void setStoredPassword(String storedPassword) {
		this.storedPassword = storedPassword;
	}

	public int getIntCounter() {
		return intCounter;
	}

	public void setIntCounter(int intCounter) {
		this.intCounter = intCounter;
	}

	public String getStringCounter() {
		return stringCounter;
	}

	public void setStringCounter(String stringCounter) {
		this.stringCounter = stringCounter;
	}

	public boolean isSurveyCompleted() {
		return surveyCompleted;
	}

	public void setSurveyCompleted(boolean surveyCompleted) {
		this.surveyCompleted = surveyCompleted;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}