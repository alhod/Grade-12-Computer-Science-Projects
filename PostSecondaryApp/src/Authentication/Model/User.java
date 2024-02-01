package Authentication.Model;

//Zaid Rahman (%100)
//Date of submission: 15/12/2023
//ICS4U1 
//Student App
//This class creates users and stores them. You can use this class to 
// instantiate users. The controller can also access this class.
//
// Features:
// - Creates users 
// - holding multiple users 
// Major skills: 
// - Array lists 
// - counters 
// - storing and retrieving data
// - 

import java.util.ArrayList;
import Authentication.Controller.ControllerFrame;

public class User {
	
	UserDataBase userDataBase = new UserDataBase(); // create instance of data base class 
	// ControllerFrame controller = new ControllerFrame(); // create instance of controller 
	
	// create fields 
	private String username = new String();
	private String email = new String();
	private String password = new String();
	private String communityHours;
	private Double [] marks = new Double[6];
	
	String stringCounter = userDataBase.retrieveUserCount(); // store string counter
	int userCounter = Integer.parseInt(stringCounter); // convert to integer counter 
	
	
	// array lists that will store courses and the their corresponding codes 
		ArrayList<String> courseList = new ArrayList<String>(); 
		ArrayList<String> courseCode = new ArrayList<String>();

	
	// constructor 
	public User(String username, String email, String password, String communityHours, Double[] marks,
			int userId, int parseCounter) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.marks = marks;
		this.userCounter = parseCounter;
	}
	
	// method that the courses and codes to the array list 
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

	
	// getters and setters
	public String getusername() {
		return userDataBase.retrieveUserName(userCounter);
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return userDataBase.retrieveContactInfo(userCounter);
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return userDataBase.retrievePassword(userCounter);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCommunityHours() {
		return userDataBase.retrieveHours(userCounter);
	}
	public void setCommunityHours(String communityHours) {
		this.communityHours = communityHours;
	}
	
}
