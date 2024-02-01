package Authentication.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class RegisterFrame extends JFrame {

	// create fields 
	
	// create labels, textfields, buttons, and panels
	JLabel title = new JLabel("Welcome to MyPortal"); 
	JLabel userLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");

	JTextField userName = new JTextField("Enter username");
	JTextField password = new JTextField("Enter password");
	JTextField information = new JTextField("Welcome to MyPortal");
	JTextField instruction = new JTextField("Please log in or sign-up with your MyPortal username and password.");

	JButton register = new JButton("Sign Up");
	JButton login = new JButton("Login");

	JPanel backgroundPanel = new JPanel(); // 
 
	// constructor 
	public RegisterFrame() {

		showDefaultTextField();
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close 

		String hex = "#E1E1E1"; // hex code of the color scheme 
		this.getContentPane().setBackground(Color.getColor(hex)); // set the background to the hex 

		Color backgroundColor = new Color(255, 255, 255); // create a color 

		ImageIcon background = new ImageIcon("images/backgroundImage"); // set the background to an imported image 
		JLabel backgroundLabel = new JLabel(background); // set the background label

		// create colors 
		Color color2 = new Color(56, 66, 59);
		Color color3 = new Color(45, 45, 42);

		title.setForeground(color2);// set the text color 
		
		// set the background color 
		setBackground(color3); 
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close 
		setSize(1400, 800); // set size of frame 
		setResizable(false); // disable resizability
		setLayout(null); // set layout to null

		// set the position , font , and background color of the title 
		title.setBounds(475, 20, 1000, 100);
		title.setFont(new Font("Arial", Font.BOLD, 45));
		title.setBackground(Color.white);

		// set the position, font, and border color and size
		// of the username and passwords labels and textfields 
		userName.setBounds(475, 190, 450, 75);
		userName.setFont(new Font("Arial", Font.PLAIN, 20));
		userName.setBorder(new RoundedBorder(10, 10, Color.black));

		userLabel.setBounds(470, 100, 300, 100);
		userLabel.setFont(new Font("Arial", Font.BOLD, 25));

		password.setBounds(475, 325, 450, 75);
		password.setFont(new Font("Arial", Font.PLAIN, 20));

		passwordLabel.setBounds(470, 240, 300, 100);
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 25));

		// set the borders to null
		userName.setBorder(null);
		password.setBorder(null);

		// set then position, background colors, and border width and color 
		backgroundPanel.setBounds(200, 30, 1000, 700);
		backgroundPanel.setBackground(backgroundColor);
		backgroundPanel.setBorder(new RoundedBorder(50, 50, Color.white));

		// create borders 
		Border lineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK); // 1-pixel black line
		
		// border that will be in the shape of a line 
		Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 30, 0); 

		// add the borders to the textfields 
		password.setBorder(BorderFactory.createCompoundBorder(emptyBorder, lineBorder));
		userName.setBorder(BorderFactory.createCompoundBorder(emptyBorder, lineBorder));

		// set position, font, border, and background color and size of the login button 
		login.setBounds(470, 440, 450, 75);
		login.setFont(new Font("Arial", Font.BOLD, 25));
		login.setBorder(new RoundedBorder(10, 10, Color.black));
		login.setBackground(Color.white);

		// set position and color of the register button 
		register.setBounds(470, 560, 450, 75);
		register.setBackground(color2);

		// set the position, font, text color and border 
		information.setBounds(580, 650, 300, 40);
		information.setFont(new Font("Arial", Font.ITALIC, 20));
		information.setForeground(color2);
		information.setBorder(null);
		add(information); // add field 

		// set position, font size , font color, and border visibility
		instruction.setBounds(440, 700, 550, 20);
		instruction.setFont(new Font("Arial", Font.PLAIN, 18));
		instruction.setForeground(Color.black);
		instruction.setBorder(null);
		add(instruction); // add component 

		// set position, font size , font color, and border 
		register.setFont(new Font("Arial", Font.BOLD, 22));
		register.setForeground(Color.white);
		register.setBorder(new RoundedBorder(10, 10, Color.black));

		// add components 
		// add in this order components show properly 
		add(login);
		add(title);
		add(userName);
		add(userLabel);
		add(passwordLabel);
		add(password);
		add(register);
		add(backgroundPanel);
		add(backgroundLabel);
		setVisible(false); // set frame visibility to false 
	}


	// this method will automatically show text on the username and password 
	// textfields 
	public void showDefaultTextField() {

		
		userName.addFocusListener(new FocusListener() {
		
			public void focusGained(FocusEvent e) {
				// default text 
				if (userName.getText().equals("Enter username"))
					
					// when user clicks on text field
					// display this text 
					userName.setText("");
			}

	
			public void focusLost(FocusEvent e) {
			
				// trunucate 
				if (userName.getText().trim().isEmpty()) {
					
					userName.setText("Enter username"); // show text field 
				}
			}
		});

		// do the same for the password text field 
		password.addFocusListener(new FocusListener() {
		
			public void focusGained(FocusEvent e) {
				if (password.getText().equals("Enter password")) // default text field 
				
					password.setText(""); // when clicked set text field to 
			}


			public void focusLost(FocusEvent e) {
	
				if (password.getText().trim().isEmpty()) { 
					password.setText("Enter password"); // default text field 
				}
			}
		});

	}
	
	// getters and setters 

	public JLabel getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(JLabel userLabel) {
		this.userLabel = userLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JTextField getUserName() {
		return userName;
	}

	public void setUserName(JTextField userName) {
		this.userName = userName;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
	}

	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

	public JButton getRegister() {
		return register;
	}

	public void setRegister(JButton register) {
		this.register = register;
	}

}
