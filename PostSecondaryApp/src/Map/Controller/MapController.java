package Map.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import com.fasterxml.jackson.databind.ObjectMapper;

import Map.Model.*;
import Map.View.MapFrame;
import Map.View.MapPanel;

public class MapController implements ActionListener, MouseListener {

	// variables
	static final String APIKEY = "CwNr77PV9IhvEwumH08gaq4dEhcJjC8j";

	// Object for geocoding
	GeocodeResponse locationObject;

	/// create an instance of the MapFrame
	MapFrame mapFrame = new MapFrame();

	// create an instance of the mapViewer
	JXMapViewer mapViewer = mapFrame.getMapPanel().getMapViewer();

	// custom marker painter
	WaypointPainter<CustomWaypoint> waypointPaint = new WaypointRenderer();
	RoutePainter routePainter;
	List<Painter<JXMapViewer>> paintersList = new ArrayList<Painter<JXMapViewer>>();
	CompoundPainter<JXMapViewer> compoundPainter;

	// Arrays to store the coordinate of all the universities in ontario
	public static GeoPosition[] uniArrayCoord = { new GeoPosition(46.5021, -84.2927), // Algoma University
			new GeoPosition(43.1189, -79.2475), // Brock University
			new GeoPosition(45.3831, -75.6976), // Carleton University
			new GeoPosition(48.4197, -89.2608), // Lakehead University
			new GeoPosition(46.4669, -80.9683), // Laurentian University
			new GeoPosition(43.2609, -79.9192), // McMaster University
			new GeoPosition(46.3373, -79.4883), // Nipissing University
			new GeoPosition(43.9448472, -78.8917028), // Ontario Tech University
			new GeoPosition(44.2244, -76.4951), // Queen's University
			new GeoPosition(44.2333, -76.467831462), // Royal Military College of Canada
			new GeoPosition(43.6546, -79.3748), // Ryerson University
			new GeoPosition(44.3575, -78.2903), // Trent University
			new GeoPosition(43.5249, -80.2270), // University of Guelph
			new GeoPosition(45.4215, -75.6919), // University of Ottawa
			new GeoPosition(43.6629, -79.3957), // University of Toronto
			new GeoPosition(43.4723, -80.5449), // University of Waterloo
			new GeoPosition(42.3048, -83.0665), // University of Windsor
			new GeoPosition(43.00833, -81.27250), // Western University
			new GeoPosition(43.4717, -80.5238), // Wilfrid Laurier University
			new GeoPosition(43.7735, -79.5019) // York University
	};

	// Store the names of all the unviersitys in ontario in an array
	public static String[] uniArrayName = { "Algoma University", "Brock University", "Carleton University",
			"Lakehead University", "Laurentian University", "McMaster University", "Nipissing University",
			"Ontario Tech University", "Queen's University", "Royal Military College of Canada", "Ryerson University",
			"Trent University", "University of Guelph", "University of Ottawa", "University of Toronto",
			"University of Waterloo", "University of Windsor", "Western University", "Wilfrid Laurier University",
			"York University", "show all" };

	// HashSet to hold the instance of the School way point object
	Set<CustomWaypoint> waypoints = new HashSet<CustomWaypoint>();

	// Store the locations of users home
	GeoPosition homeLocation;
	Boolean home = false;

	// Variables to store a temp location when user search for a place
	GeoPosition tempLocation;

	// A custom made interaction listener for the map included in the library
	// When a user presses and drags the mouse over the map viewer,
	// this class calculates how far the map should move based on the drag distance.
	// It allows panning by continuously updating the map's center point as the
	// mouse is dragged.
	// Upon releasing the mouse button, it stops the panning action and restores the
	// original cursor.
	PanMouseInputListener panMouseListener = new PanMouseInputListener(mapViewer);

	// Constructor
	public MapController() {

		// setup all the images for the universities icon
		Images.setImage();

		// setup all the links for universities
		UniversityURLs.setUpUniversityLink();

		// Interaction for the map

		// add the custom mouseListener to the map for clicking purposes
		mapViewer.addMouseListener(panMouseListener);

		// add the custom mouseListener to the map for dragging purposes
		mapViewer.addMouseMotionListener(panMouseListener);

		// allows the user to zoom in and out by using the mouse scrollWheel
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

		// Adding ActionListener to buttons, ComboBox
		mapFrame.getMapPanel().getMapTypeComboBox().addActionListener(this);
		mapFrame.getEnterButton().addActionListener(this);
		mapFrame.getClearButton().addActionListener(this);
		mapFrame.getSearchButton().addActionListener(this);
		mapFrame.getSearchButton().addMouseListener(this);
		mapFrame.getSearchTextField().addMouseListener(this);
		mapFrame.getSetHomeButton().addMouseListener(this);
		mapFrame.getHomeButton().addActionListener(this);
		mapFrame.getRefreshButton().addActionListener(this);
		mapFrame.getRouteButton().addActionListener(this);
		mapFrame.getInstructionButton().addActionListener(this);

		// Store all the locations of the universities in the hashSet
		setUpSchoolLocation();

		// allows custom markers to be added on the map
		waypointPaint.setWaypoints(waypoints);

		paintersList.add(waypointPaint);

		compoundPainter = new CompoundPainter<JXMapViewer>(paintersList);

		// make it so that the custom painter coded can draw the waypoint on the map
		mapViewer.setOverlayPainter(compoundPainter);

	}

	// This methods Create instances of CustomWaypoint for all the univeristy
	private void setUpSchoolLocation() {

		// adding all the waypoints to a Hashset
		// Note: there won't be repetitive item in a hashset
		for (int i = 0; i < uniArrayCoord.length; i++) {

			waypoints.add(new CustomWaypoint(uniArrayName[i], uniArrayCoord[i], Images.universityIcons[i]));

		}

	}

	// This method triggers when an action is performed on the gui
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		// when the MapTypeComboBox is triggered
		if (actionEvent.getSource() == mapFrame.getMapPanel().getMapTypeComboBox()) {

			// Get the selected item
			String selectedItem = (String) mapFrame.getMapPanel().getMapTypeComboBox().getSelectedItem();
			TileFactoryInfo mapTypeInfo;

			// Based on the selected item on the comboBox
			// Change the style of the map
			switch (selectedItem) {

			case "VirtualEarth":
				mapTypeInfo = new OSMTileFactoryInfo();
				mapViewer.setZoom(7);
				break;

			case "Hybrid":
				mapTypeInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
				mapViewer.setZoom(7);
				break;

			case "Satellite":
				mapTypeInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
				mapViewer.setZoom(7);
				break;

			default:
				mapTypeInfo = new OSMTileFactoryInfo();
				mapViewer.setZoom(7);
				break;
			}

			// Change the type of the map
			mapViewer.setTileFactory(new DefaultTileFactory(mapTypeInfo));

			// refresh the frame
			mapFrame.repaint();
			mapFrame.revalidate();

		}

		// When the enterButton is triggered
		if (actionEvent.getSource() == mapFrame.getEnterButton()) {

			universitySelection();

		}

		// When the clear button is triggered
		if (actionEvent.getSource() == mapFrame.getClearButton()) {

			clearSchoolWaypoint();

		}

		// When the refocusButton is triggered
		if (actionEvent.getSource() == mapFrame.getHomeButton()) {

			if (homeLocation != null) {

				mapViewer.setCenterPosition(homeLocation);
				mapViewer.setZoom(6);

				mapFrame.repaint();
				mapFrame.revalidate();
			}

		}

		// When the refresh button is triggered
		if (actionEvent.getSource() == mapFrame.getRefreshButton()) {

			// Remove every waypoint on the map and clear users home address
			for (CustomWaypoint currentWaypoint : waypoints) {
				mapViewer.remove(currentWaypoint.getButton());
			}

			//Clear all the location stored previously
			homeLocation = null;
			tempLocation = null;

			//remove the route painter
			if (routePainter != null) {
				paintersList.remove(routePainter);
				compoundPainter.removePainter(routePainter);
				routePainter.setTrack(null);
				routePainter = null;
			}

			//refresh the frame
			mapFrame.revalidate();
			mapFrame.repaint();

		}

		// When the routing button is triggered
		if (actionEvent.getSource() == mapFrame.getRouteButton()) {

			// Draw a direct route between 2 locations
			if (homeLocation != null && tempLocation != null) {
				List<GeoPosition> track = Arrays.asList(homeLocation, tempLocation);

				routePainter = new RoutePainter(track);

				paintersList.add(routePainter);
				compoundPainter = new CompoundPainter<JXMapViewer>(paintersList);
				mapViewer.setOverlayPainter(compoundPainter);

				System.out.println(routePainter.getClass());
			}

			else {

				JOptionPane.showMessageDialog(mapFrame, "Make sure you have a home address and a waypoint added",
						"Warnings", JOptionPane.WARNING_MESSAGE);
			}

		}

		//This buttons brings use to the frame that instructs them how to use it
		if (actionEvent.getSource() == mapFrame.getInstructionButton()) {
			
			//Create a new Frame
			JFrame instructionFrame = new JFrame();
			instructionFrame.setSize(1440, 800);
			instructionFrame.setLayout(null);
			instructionFrame.setResizable(false);
			instructionFrame.setTitle("INSTRUCTION");
			
			//add the instruction Image on the frame
			JLabel instructionImage = new JLabel(new ImageIcon("src/Map/MapImages/Instruction.png"));
			instructionImage.setBounds(20, -10, 1380, 780);
			instructionFrame.add(instructionImage);
			instructionFrame.setVisible(true);
		}
	}

	// Remove any schools university waypoint
	private void clearSchoolWaypoint() {

		int counter = 0;

		// Since Universitys Waypoint are always store first (20)
		// We can remove the first 19 waypoints in the hashset
		for (CustomWaypoint currentWaypoint : waypoints) {

			if (counter > 19)
				return;

			mapViewer.remove(currentWaypoint.getButton());
			counter++;
		}

		mapFrame.repaint();
		mapFrame.revalidate();

	}

	// This method displays the icon of the corresponding university selected in the
	// comboBox
	private void universitySelection() {

		waypointPaint.setWaypoints(waypoints);

		// obtain the item use selected
		String selectedUniversity = (String) mapFrame.getUniComboBox().getSelectedItem();

		// Display all the universitys on the map
		if (selectedUniversity == "show all") {

			for (CustomWaypoint currentWaypoint : waypoints) {

				mapViewer.add(currentWaypoint.getButton());

			}

		}

		// Display a specific university chosen by the user on the map
		else {

			for (CustomWaypoint currentWaypoint : waypoints) {

				if (currentWaypoint.getName().equals(selectedUniversity)) {

					mapViewer.add(currentWaypoint.getButton());
				}

			}

		}

		// refresh the frame
		mapFrame.repaint();
		mapFrame.revalidate();

	}

	// When a mouse click action is performed on the frame
	@Override
	public void mouseClicked(MouseEvent mouseClick) {

		// Remove the default text on the searchTextField
		if (mouseClick.getSource() == mapFrame.getSearchTextField()
				&& mapFrame.getSearchTextField().getText().equals("Enter a location: ")) {

			mapFrame.getSearchTextField().setText("");
			mapFrame.getControlPanel().repaint();
			mapFrame.getControlPanel().revalidate();

		}

		// When either setHome button or search Button is clicked
		// As they both take information from the searechTextField
		if (mouseClick.getSource() == mapFrame.getSetHomeButton()
				|| mouseClick.getSource() == mapFrame.getSearchButton()) {

			// Parse the information from the Json File
			JsonParser();

			if (locationObject.getResults().length != 0) {

				// If the set home button is clicked
				if (mouseClick.getSource() == mapFrame.getSetHomeButton()) {

					// remove the previos home if exist
					if (home = true) {

						for (CustomWaypoint currentWaypoint : waypoints) {

							if (currentWaypoint.getName().equals("Home")) {

								mapViewer.remove(currentWaypoint.getButton());
								waypoints.remove(currentWaypoint);
								mapFrame.revalidate();
								mapFrame.repaint();
								home = false;
								break;

							}
						}
					}

					// store the location of the users home obtained in the json
					homeLocation = new GeoPosition(
							locationObject.getResults()[0].getLocations()[0].getLatLng().getLat(),
							locationObject.getResults()[0].getLocations()[0].getLatLng().getLng());

					// add the location to the waypoint hashset
					waypoints.add(new CustomWaypoint("Home", homeLocation, new ImageIcon("MapImages/HomeIcon.png")));

					// set the new set of waypoint to the painter
					waypointPaint.setWaypoints(waypoints);
					paintersList.set(0, waypointPaint);
					compoundPainter = new CompoundPainter<JXMapViewer>(paintersList);

					// Set the overlay painter again as the waypoint hashset changed
					mapViewer.setOverlayPainter(compoundPainter);

					// Add the home button to the map
					for (CustomWaypoint currentWaypoint : waypoints) {

						if (currentWaypoint.getName().equals("Home")) {

							mapViewer.add(currentWaypoint.getButton());

						}

					}

					home = true;

					// Center the map at users home
					mapViewer.setCenterPosition(homeLocation);

				}

				else if (mouseClick.getSource() == mapFrame.getSearchButton()) {

					// store the location of the place users searching for
					tempLocation = new GeoPosition(
							locationObject.getResults()[0].getLocations()[0].getLatLng().getLat(),
							locationObject.getResults()[0].getLocations()[0].getLatLng().getLng());

					// Add the location to the hashset
					// Note: name of this temp waypoint would be the same as the user input from the
					// json
					waypoints.add(new CustomWaypoint(
							locationObject.getResults()[0].getProvidedLocation().getLocation().toString(), tempLocation,
							new ImageIcon("MapImages/WaypointIcon.png")));

					// set the new set of waypoint to the painter
					waypointPaint.setWaypoints(waypoints);
					paintersList.set(0, waypointPaint);
					compoundPainter = new CompoundPainter<JXMapViewer>(paintersList);

					// Set the overlay painter again as the waypoint hashset changed
					mapViewer.setOverlayPainter(compoundPainter);

					// Display an icon on the map of that location
					for (CustomWaypoint currentWaypoint : waypoints) {

						if (currentWaypoint.getName().equals(
								locationObject.getResults()[0].getProvidedLocation().getLocation().toString())) {

							mapViewer.add(currentWaypoint.getButton());

						}

					}

					mapViewer.setCenterPosition(tempLocation);

				}

				// Refresh the map
				mapViewer.setZoom(6);
				mapFrame.revalidate();
				mapFrame.repaint();

			}

		}

	}

	// Converting information from a json to information usable by java
	private void JsonParser() {

		try {

			// Create an instance of the URL Object Class and save the link
			String address = mapFrame.getSearchTextField().getText();

			// URL-encode the address
			String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());

			// Bounding box coordinates for Canada
			String boundingBox = "49.384358,-125.001650,60.008549,-102.502930";

			// Construct the URL
			String requestUrl = "https://www.mapquestapi.com/geocoding/v1/address?key=" + APIKEY + "&location="
					+ encodedAddress;
			// + "&boundingBox=" + boundingBox;

			URL urlObject = new URL(requestUrl);

			// Open the URL using classes from the HTTP client library
			HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();

			// Using the HTTP GET Request method according to the Map Quest API
			connection.setRequestMethod("GET");

			ObjectMapper objectMapper = new ObjectMapper();

			// Convert everything form the json into locationObjects with values stored in
			// attributes
			locationObject = objectMapper.readValue(new InputStreamReader(connection.getInputStream()),
					GeocodeResponse.class);

			// Check if the address is found
			if (locationObject.getInfo().getStatuscode() != 0) {

				JOptionPane.showMessageDialog(mapFrame,
						"This Address does not exist, " + "make sure you have the format correct", "Warnings",
						JOptionPane.WARNING_MESSAGE);
				return;

			}

			// Catches exception when a I/O operatio failed
		} catch (IOException e) {

			// e.printStackTrace();
			JOptionPane.showMessageDialog(mapFrame,
					"This Address does not exist, " + "make sure you have the format correct", "Warnings",
					JOptionPane.WARNING_MESSAGE);
			return;

		}

		// Set the text of the searchTextField to default again
		mapFrame.getSearchTextField().setText("Enter a location: ");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
