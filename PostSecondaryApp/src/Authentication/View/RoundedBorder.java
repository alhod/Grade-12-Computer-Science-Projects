package Authentication.View;

//Zaid Rahman (%100)

//Date of submission: 15/12/2023
//ICS4U1 
//Student App
//This class creates rounded borders

//Features: 
// - Uses Graphics and Graphics2D 
//		- Changes size and shape of borders 
// - Able to change the border to opaque 
// Major Skills:
// - Java graphics 
// - Ability to change colors 
// - Changing borders 

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.border.Border;

// this class was developped from stackoverflow
// stackoverflow greatly helped with making this class 
public class RoundedBorder implements Border {
	// creates fields
	
	// create x and y radius 
    private int radiusX; 
    private int radiusY;
    private Color color; // create a color 

    // create constructor
    public RoundedBorder(int radiusX, int radiusY, Color color) {
        this.radiusX = radiusX; 
        this.radiusY = radiusY;
        this.color = color;
    }

    @Override
    
    // create a method that will change the color of the border 
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g; // cast graphics object to graphics2d
        g2.setColor(color);  // set the color of the graphics to a color that will be decided 
 
        // draw a rounded rectangle 
        g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radiusX, radiusY));
    }

    @Override
    
    // determine insets of the borders (creates padding around the border)
    public Insets getBorderInsets(Component c) {
        return new Insets(radiusY, radiusX, radiusY, radiusX);
    }

    @Override
    
    // boolean method to determine whether the border is opaque
    public boolean isBorderOpaque() {
        return true;
    }
}