package Map.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Map.Controller.MapController;

@SuppressWarnings("serial")
public class MapFrame extends JFrame {

	// GUI elements

	// Font used across all the components
	private Font font = new Font("Helvetica", Font.PLAIN, 26);

	// panels
	private JPanel controlPanel;
	private MapPanel mapPanel = new MapPanel();

	// JComboBox
	private JComboBox<String> uniComboBox;

	// JTextField
	private JTextField searchTextField;

	// JButtons
	private JButton searchButton;
	private JButton enterButton;
	private JButton clearButton;
	private JButton setHomeButton;
	private JButton homeButton;
	private JButton refreshButton;
	private JButton routeButton;
	private JButton instructionButton;

	// Constructor
	public MapFrame() {

		// Sizing the JFrame
		setSize(1440, 800);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setTitle("Ontario Universities Map");

		// Set methods to design buttons, comboBox, textField panels on the frame
		setupPanel();

		setupComboBox();

		setupButton();

		setupTextField();

		// makes the frame visible
		setVisible(true);
	}

	private void setupPanel() {

		// setting up the size, color of the controlPanel on the mapFrame
		controlPanel = new JPanel();
		controlPanel.setBounds(1100, 0, 340, 900);
		controlPanel.setBackground(Color.decode("#38423B"));
		controlPanel.setLayout(null);
		add(controlPanel);

		mapPanel.setBounds(0, 0, 1100, 900);
		add(mapPanel);
	}

	// This methods setting up properties of all the button across the controlPanel
	// Name, Font, Background Color, Position of the Frame
	private void setupButton() {

		// For the Search button that confirms the place users are looking for
		searchButton = new JButton("Search");
		searchButton.setFont(font);
		searchButton.setBackground(Color.decode("#20FC8F"));
		searchButton.setBounds(170, 60, 160, 40);
		controlPanel.add(searchButton);

		// For the button that confirm the university users are looking for
		enterButton = new JButton("Enter");
		enterButton.setFont(font);
		enterButton.setBackground(Color.decode("#20FC8F"));
		enterButton.setBounds(170, 200, 160, 40);
		controlPanel.add(enterButton);

		// For the button that clear waypoints of university on the map
		clearButton = new JButton("Clear");
		clearButton.setFont(font);
		clearButton.setBackground(Color.RED);
		clearButton.setBounds(10, 200, 160, 40);
		controlPanel.add(clearButton);

		// For the button that save the home of the user on the map
		setHomeButton = new JButton("Set Home");
		setHomeButton.setFont(font);
		setHomeButton.setBounds(10, 60, 160, 40);
		setHomeButton.setBackground(Color.decode("#3F5E5A"));
		setHomeButton.setForeground(Color.white);
		controlPanel.add(setHomeButton);

		// For the button that refocus to the centre of the map
		homeButton = new JButton("Refocus");
		homeButton.setFont(font);
		homeButton.setBounds(120, 660, 200, 40);
		homeButton.setBackground(Color.decode("#3F5E5A"));
		homeButton.setForeground(Color.white);
		controlPanel.add(homeButton);

		// for the button that Remove everything on the map
		refreshButton = new JButton("Refresh");
		refreshButton.setFont(font);
		refreshButton.setBounds(120, 720, 200, 40);
		refreshButton.setBackground(Color.decode("#3F5E5A"));
		refreshButton.setForeground(Color.white);
		controlPanel.add(refreshButton);

		// for the button that generates a "route" on the map
		routeButton = new JButton("Route");
		routeButton.setFont(font);
		routeButton.setBounds(120, 600, 200, 40);
		routeButton.setBackground(Color.decode("#3F5E5A"));
		routeButton.setForeground(Color.white);
		controlPanel.add(routeButton);

		// For the button that brings u to the instruction frame
		instructionButton = new JButton("Instruction");
		instructionButton.setFont(font);
		instructionButton.setBounds(120, 540, 200, 40);
		instructionButton.setBackground(Color.decode("#3F5E5A"));
		instructionButton.setForeground(Color.white);
		controlPanel.add(instructionButton);

	}

	// This methods setting up properties of all the comboBox across the controlPanel
	// Name, Font, Background Color, Position of the Frame
	private void setupComboBox() {

		uniComboBox = new JComboBox<String>(MapController.uniArrayName);
		uniComboBox.setFont(font);
		uniComboBox.setBackground(Color.decode("#3F5E5A"));
		uniComboBox.setForeground(Color.WHITE);
		uniComboBox.setBounds(10, 150, 320, 50);
		controlPanel.add(uniComboBox);

	}

	// This methods setting up properties of all the textField across the controlPanel
	// Name, Font, Background Color, Position of the Frame
	private void setupTextField() {

		searchTextField = new JTextField();
		searchTextField.setText("Enter a location: ");
		searchTextField.setBounds(10, 10, 320, 50);
		searchTextField.setBackground(Color.decode("#3F5E5A"));
		searchTextField.setForeground(Color.WHITE);
		searchTextField.setFont(font);
		controlPanel.add(searchTextField);
	}

	// getters and setters
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public JPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public JComboBox<String> getUniComboBox() {
		return uniComboBox;
	}

	public void setUniComboBox(JComboBox<String> uniComboBox) {
		this.uniComboBox = uniComboBox;
	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public void setSearchTextField(JTextField searchTextField) {
		this.searchTextField = searchTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getEnterButton() {
		return enterButton;
	}

	public void setEnterButton(JButton enterButton) {
		this.enterButton = enterButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	public JButton getSetHomeButton() {
		return setHomeButton;
	}

	public void setSetHomeButton(JButton setHomeButton) {
		this.setHomeButton = setHomeButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JButton getRouteButton() {
		return routeButton;
	}

	public void setRouteButton(JButton routeButton) {
		this.routeButton = routeButton;
	}

	public JButton getInstructionButton() {
		return instructionButton;
	}

	public void setInstructionButton(JButton instructionButton) {
		this.instructionButton = instructionButton;
	}

}
