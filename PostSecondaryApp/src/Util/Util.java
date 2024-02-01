/*
 * Util class
 * Description:
 *  Contains static attributes and methods that are useful across the entire application
 *      I.e. Frame width and height, as well as Mono Lisa font that should be used everywhere
 * Contributors:
 *  100% - Andrew
 */

package Util;

import java.io.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Util {

    // Static final attributes for the colors used throughout program
    public static final Color C1 = Color.decode("#2D2D2A");
    public static final Color C2 = Color.decode("#353831");
    public static final Color C3 = Color.decode("#38423B");
    public static final Color C4 = Color.decode("#3F5E5A");
    public static final Color C5 = Color.decode("#20FC8F");

    // Width and height of JFrame across all features
    public static final int FRAME_WIDTH = 1440;
    public static final int FRAME_HEIGHT = 800;

    // Returns default font of specified size (no bold, no italic)
    public static Font getFont(int size) {
        return getFont(size, false, false);
    }

    // Returns font of specified size, but also bold or italic based on parameters
    public static Font getFont(int size, boolean bold, boolean italic) {

        // https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
        // Change path to font based on whether bolded or italicized
        Font font;

        try {
            //create the font to use. Specify the size!
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src\\Util\\Fonts\\MonoLisa" + (bold ? "Bold" : "") + (italic ? "Italic" : "") + ".otf")).deriveFont((float)size);
            // font = Font.createFont(Font.TRUETYPE_FONT, new File("src\\Util\\Fonts\\MonoLisa.otf")).deriveFont((float)size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);

            return font;
        } catch (IOException e) {
            System.out.println("\n\nFONT IMPORT ERROR !!!\n\n");
            e.printStackTrace();
        } catch(FontFormatException e) {
            System.out.println("\n\nFONT IMPORT ERROR !!!\n\n");
            e.printStackTrace();
        }

        return null;
    }

    // Returns a scaled image icon, given path to image and desired width and height to scale to
    public static ImageIcon getScaledImageIcon(String path, int width, int height){
        ImageIcon img = new ImageIcon(path);
        Image newimg = img.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    // Returns a Border object
    public static Border getBorder(Color color){
        return BorderFactory.createLineBorder(color);
    }
}
