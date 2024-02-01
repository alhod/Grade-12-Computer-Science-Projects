package Authentication.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import Authentication.View.RegisterFrame;

// Zaid Rahman (%100)
// Date of submission: 15/12/2023
// ICS4U1 
// Student App
// This class is used to store the data for the student app. When 
// the methods are called from the controller, it will store the information into 
// a text file and when calling the retrieve methods you can use the data. 
// 
// Features:
// - buffered reader and buffered writer
// - permanent data storage 
//		- once data is in the text file, it will be permanently stored 
// 		- even if you exit the application
//	- Data manipulation
//		- output data to profile frame with different texts displaying 
//		  depending on user input 
// Major Skills: 
// - Reading and writing information to text file
// - handling user data 
// - manipulating user data (user input will change output when displayed on profile frame)
// - error handling 
//	 - try and catches, print the errors 
// - store survey results 
//  Text file structuring 
//  	- files are organized based on the order of the questions 
// User Count tracking 
// 		- Hold user count in files to manage different user data 								
public class UserDataBase {

	// variables that will be used to store and retrieve user data
	public String userName;
	public String password;
	public String courses;
	public String marks;
	public String hoursOutput;
	public String schoolOutput;
	public String contactOutput;
	public String amountCoursesOutput;
	private String skip;
	public String userCountReal;
	public String ossltStatus;
	public String credits;

	public static final String COUNTER_PATH = "src\\Authentication\\counter.txt";

	Scanner sc = new Scanner(System.in);
	RegisterFrame registerFrame = new RegisterFrame(); // create an instance of the register frame
	
	// this method will store the user counter for each iteration of the register button clicked
	public void storeUserCount(int userCount) { 
		BufferedWriter writer; // create a buffered writer
		try {
		writer = new BufferedWriter(new FileWriter(COUNTER_PATH)); // create a new file 
		writer.write(userCount + "\n"); // write the new counter 
		writer.close(); // close the writer to prevent it from continuously going on
		
		} catch (IOException e) { // catch any errors
			
			// TODO Auto-generated catch block
			e.printStackTrace(); //print error
		}
	}
	
	// this method will re trieve the user counter for each iteration of the register button clicked
	public String retrieveUserCount() {
		
		try {
			// read the text file that was made from the store user count method 
			BufferedReader reader = new BufferedReader(new FileReader(COUNTER_PATH));
			userCountReal = reader.readLine(); // set it to a variable
			reader.close(); // close the reader 
			 
			// 
		} catch (IOException e) { // catch any errors
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userCountReal; // return the value user count 
	}
	
	// create a method that stores username and password
	public void storeUserCredentials(String userNameInput, String passwordInput, int userCount) {
		BufferedWriter writer; // create buffered writer 
		try {
			// read the file created 
			writer = new BufferedWriter(new FileWriter("user" + userCount + "Credentials.txt"));
			
			// display the username and password in the text file
			writer.write(userNameInput + "\n"); 
			writer.write(passwordInput);
			writer.close(); // close to prevent leakage
			
			// catch any errors
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// method to retrieve username from text file 
	public String retrieveUserName(int userCount) {

		// read the user credentials 
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + (userCount) + "Credentials.txt"));
			userName = reader.readLine(); // reads username 
			reader.close(); // close to prevent leakage
			
		} catch (IOException e) { // catch any errors 
			// TODO Auto-generated catch block
			e.printStackTrace(); /// display error in console 
		}
		return userName; // return username 
	}

	// method to retrieve password from text file 
	public String retrievePassword(int userCount) {
		try {
			
			// reads the text file
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCount + "Credentials.txt"));
			String skip = reader.readLine();
			password = reader.readLine(); // read the password line
			reader.close(); // close to prevent leakage
		} catch (IOException e) { // catch any errors 
			// TODO Auto-generated catch block
			e.printStackTrace(); // print any errors in console
		}
		return password; // return password
	} 

	// create a method that will store the survey results
	public void storeUserSurvey(HashSet<String> selectedButtons, ArrayList<String> marksList, JRadioButton radioButton,
			String hours, String currentSchool, String contactInfo, String coursesAmount, int userCounter, String buttonStatus) {
		BufferedWriter writer;
		
		try {
			// create a text file
			writer = new BufferedWriter(new FileWriter("user" + userCounter + "MarksAndCourses.txt"));
			for (String courses : selectedButtons) { // enhanced for loop to iterate through all courses
				writer.write(courses); // display the courses into the text file
			}
			// enhanced for loop to iterate through all marks
			for (String marks : marksList) {
				writer.write(marks); // display marks into the text file
				writer.newLine(); // go to the next line
			}
			// display the rest of the fields into the text fields
			writer.write(hours);
			writer.newLine(); // go to the next line
			writer.write(currentSchool);
			writer.newLine(); // go to the next line
			writer.write(contactInfo);
			writer.newLine(); // go to the next line
			writer.write(coursesAmount);
			writer.newLine(); // go to the next line
			writer.write(buttonStatus);

			writer.close();
		} catch (IOException e) { // catch any errors 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// these methods will store the 6 courses inputted by the user in a text file
	public String retrieveCourse1(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			courses = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public String retrieveCourse2(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			courses = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public String retrieveCourse3(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			courses = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public String retrieveCourse4(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			courses = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public String retrieveCourse5(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			courses = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	public String retrieveCourse6(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			courses = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	// these methods will get the marks inputted by the user from the survey info frame 
	public String retrieveMark1(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			marks = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}

	public String retrieveMark2(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();

			marks = reader.readLine();

			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}

	public String retrieveMark3(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();

			marks = reader.readLine();

			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}

	public String retrieveMark4(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();

			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			marks = reader.readLine();

			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}

	public String retrieveMark5(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();

			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			marks = reader.readLine();

			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}

	public String retrieveMark6(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			marks = reader.readLine();

			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marks;
	}
	
	// this method will retrieve the community hours
	
	public String retrieveHours(int userCounter) {
		try {
			
			// store in text file
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
		
			// skip lines to get to the community hours line
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			
			hoursOutput = reader.readLine(); // store the community hours 
			reader.close(); // close to prevent leakage
		} catch (IOException e) { // catch any error 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoursOutput; // return hours 
	}

	// method that will retrieve the school you attend 
	public String retrieveSchool(int userCounter) {
		try {
			// create buffered reader
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
		
			// skip to the school line 
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			schoolOutput = reader.readLine(); // read that line and store it into a variable 
			reader.close(); // close to prevent leakage
		} catch (IOException e) { // catch any errors 
			// TODO Auto-generated catch block
			e.printStackTrace(); // print any errors 
		}
		return schoolOutput; // return school variable
	} 

	// method that will retrieve contact information 
	public String retrieveContactInfo(int userCounter) {
		try {
			// buffered reader that will read from the file 
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
		
			// skip lines 
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			contactOutput = reader.readLine(); // store the contact into a variable 
			reader.close(); // close to prevent leakage 
		} catch (IOException e) { // catch any errors 
			// TODO Auto-generated catch block
			e.printStackTrace(); // print any errors into console 
		}
		return contactOutput; // return contact info
	}

	// method that will retrieve the course credits 
	public String retrieveCredits(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			
			// skip lines 
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			credits = reader.readLine(); // store into a variable 
			reader.close(); //close reader 
		} catch (IOException e) { // catch errors 
			// TODO Auto-generated catch block
			e.printStackTrace(); // print errors 
		}
		return amountCoursesOutput; // return value 
	}
	
	// method that will retrieve osslt status 
	public String retrieveOsslt(int userCounter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("user" + userCounter + "MarksAndCourses.txt"));
			
			// skip lines
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			skip = reader.readLine();
			
			// read line and store into a variable 
			ossltStatus = reader.readLine();
			reader.close(); // close reader 
		} catch (IOException e) { // catch any errors 
			// TODO Auto-generated catch block
			e.printStackTrace(); // print any errors 
		}
		return ossltStatus;// return osslt status 
	}}

