package Map.Model;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import Map.Controller.MapController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//Object Class
//This class extends the DefaulyWaypoint super class that contains only the coordinate attribute
public class CustomWaypoint extends DefaultWaypoint {

	// child class attributes

	// Name of the university
	private String name;

	// Clickable button that brings up the information of the university
	private JButton button;

	// Child Class Constructor
	public CustomWaypoint(String name, GeoPosition coord, ImageIcon icon) {

		super(coord);

		this.name = name;

		// set the properties of the JButton
		button = new JButton();
		button.setSize(48, 48);
		button.setPreferredSize(new Dimension(48, 48));
		button.setIcon(icon);

		// Make it such that the button is backgroundless
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);

		// allows the user the interact using the button
		button.addMouseListener(new SwingWaypointMouseListener());

	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	private class SwingWaypointMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			JOptionPane.showMessageDialog(button, "You clicked on " + name);

			for (int i = 0; i < MapController.uniArrayName.length; i++) {

				if (name == MapController.uniArrayName[i]) {

					Desktop url = Desktop.getDesktop();

					try {

						url.browse(UniversityURLs.uniLinks[i]);

					} catch (IOException error) {

						System.out.println("URL error");

					} // end of the event for url1 button

				}
			}
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
}