package Map.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import Map.Model.CustomWaypoint;
import Map.Model.WaypointRenderer;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {

	// Gui elements
	private String[] mapTypeArray = { "OpenStreetMap", "VirtualEarth", "Hybrid", "Satellite" };

	// ComboBox to allow use to choose different types of map
	private JComboBox<String> mapTypeComboBox;

	// font
	private Font font = new Font("Helvetica", Font.PLAIN, 26);
	
	// variables
	private JXMapViewer mapViewer = new JXMapViewer();

	// location for bur oak secondary school
	private GeoPosition defaultLocation = new GeoPosition(43.8971, -79.2786);

	// import the map from open street map
	private TileFactoryInfo openStreetMap = new OSMTileFactoryInfo();

	// Default style of the map
	private DefaultTileFactory defaultTileFactory = new DefaultTileFactory(openStreetMap);

	public MapPanel() {

		// Sizing the JFrame
		setSize(1100, 900);
		setLayout(null);

		setUpMapViewer();

		setUpComboBox();

		this.add(mapViewer);

	}


	private void setUpComboBox() {

		//setup the properties of the comboBox that changes the style of the map
		mapTypeComboBox = new JComboBox<String>(mapTypeArray);
		mapTypeComboBox.setFont(font);
		mapTypeComboBox.setOpaque(true);
		mapTypeComboBox.setBounds(840, 10, 250, 50);
		mapViewer.add(mapTypeComboBox);

	}

	private void setUpMapViewer() {

		// set the size for the map on the panel
		mapViewer.setSize(1100, 800);

		// set the default map style
		mapViewer.setTileFactory(defaultTileFactory);

		// set the default geo position of the map - bur oak secondary school
		mapViewer.setAddressLocation(defaultLocation);

		// set the default zoom level of the map
		mapViewer.setZoom(7);

		mapViewer.setLayout(null);

	}

	//getters and setters
	public String[] getMapTypeArray() {
		return mapTypeArray;
	}


	public void setMapTypeArray(String[] mapTypeArray) {
		this.mapTypeArray = mapTypeArray;
	}


	public JComboBox<String> getMapTypeComboBox() {
		return mapTypeComboBox;
	}


	public void setMapTypeComboBox(JComboBox<String> mapTypeComboBox) {
		this.mapTypeComboBox = mapTypeComboBox;
	}


	public Font getFont() {
		return font;
	}


	public void setFont(Font font) {
		this.font = font;
	}


	public JXMapViewer getMapViewer() {
		return mapViewer;
	}


	public void setMapViewer(JXMapViewer mapViewer) {
		this.mapViewer = mapViewer;
	}


	public GeoPosition getDefaultLocation() {
		return defaultLocation;
	}


	public void setDefaultLocation(GeoPosition defaultLocation) {
		this.defaultLocation = defaultLocation;
	}


	public TileFactoryInfo getOpenStreetMap() {
		return openStreetMap;
	}


	public void setOpenStreetMap(TileFactoryInfo openStreetMap) {
		this.openStreetMap = openStreetMap;
	}


	public DefaultTileFactory getDefaultTileFactory() {
		return defaultTileFactory;
	}


	public void setDefaultTileFactory(DefaultTileFactory defaultTileFactory) {
		this.defaultTileFactory = defaultTileFactory;
	}


	
}
