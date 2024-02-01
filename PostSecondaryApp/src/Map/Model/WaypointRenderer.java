package Map.Model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

public class WaypointRenderer extends WaypointPainter<CustomWaypoint> {

	// paint the button on the map on the correct location
	@Override
	public void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {

		for (CustomWaypoint waypoint : super.getWaypoints()) {

			// get the current position on the screen of the waypoint in the map
			Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());

			// Determine the rectangular area currently visible in the map viewer
			Rectangle rec = map.getViewportBounds();

			// Calculating the Position Relative to the Viewport
			int x = (int) ((int) point.getX() - rec.getX());
			int y = (int) ((int) point.getY() - rec.getY());

			// It centers the locations of the waypoint button
			waypoint.getButton().setLocation(x - waypoint.getButton().getWidth() / 2,
					y - waypoint.getButton().getHeight());

		}

	}

}
