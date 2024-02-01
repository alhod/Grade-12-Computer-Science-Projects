/*
 * Class: MatchedProgramPanel
 * Contributor: 100% - Andrew Deng
 * Description: Responsible for display a specific recommended program in 
 * the results screen. Displays the name of the program, the general 
 * information for it, the prerequisities, the contact information, and 
 * a learn more button that links to the website.
 */

// Parent package and imports
package Matching.View;

import Util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;

import javax.swing.plaf.basic.BasicScrollBarUI;
import org.jxmapviewer.google.GoogleMapsTileFactoryInfo;

import Database.Model.*;

// Declare MatchedProgramPanel class
public class MatchedProgramPanel extends JPanel {

    // Static variables for matched program panel
    public static String UNIVERSITY_IMAGES_PATH = "src\\Matching\\UniversityImages\\";
    public static final int REG_TEXT_FONT_SIZE = 15;

    // Array of all programs
    ArrayList<Program> programs;

    // GUI components for panel
    Program program;
    JLabel title;
    JTextArea info_text_area;
    JScrollPane info_scroll_pane;
    JTextArea prerequisites_text_area;
    JScrollPane prerequisites_scroll_pane;
    JTextArea contact_text_area;
    JScrollPane contact_scroll_pane;
    JButton learn_more_button;
    JLabel image_label;

    // Maps a university to corresponding image
    HashMap<String, JLabel> university_to_image;

    // Constructor for MatchedProgramPanel class
    public MatchedProgramPanel(int x, int y, int width, int height, ArrayList<Program> programs) {
        setPrograms(programs);

        // Settings for GUI for panel
        setBounds(x, y, width, height);
        setBackground(Util.C2);
        setLayout(null);

        // Initialize GUI components
        initTitle();
        initInfoScrollPane();
        initImageLabel();
        initPrerequisitesScrollPane();
        initContactScrollPane();
        initLearnMoreButton();
    }

    // Initialize hashmap for university to image
    public void initUniversityToImage() {
        setUniversity_to_image(new HashMap<String, JLabel>());
        ArrayList<String> unis = getAllUnis();

        // Get each university, compress its string, and set image
        for (String uni : unis) {
            getUniversity_to_image().put(compress_uni_string(uni), getUniImage(uni));
        }
    }

    // Gets a university image in a JLabel
    public JLabel getUniImage(String uni) {

        // Compresses university name, initialize label, types of files
        uni = compress_uni_string(uni);
        JLabel image = new JLabel();
        String[] types = { ".png", ".jpg" };

        // Iterate through ecah type
        for (String type : types) {
            Path path = Paths.get(UNIVERSITY_IMAGES_PATH + uni + type);

            // Create path to university image, and if it exists, get and scale its image
            if (Files.exists(path)) {
                ImageIcon img = new ImageIcon(UNIVERSITY_IMAGES_PATH + uni + type);
                Image newimg = img.getImage().getScaledInstance(getImage_label().getWidth(),
                        getImage_label().getHeight(), java.awt.Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(newimg));
                return image;
            }
        }

        return new JLabel();
    }

    // Compresses university name
    public String compress_uni_string(String str) {

        // To remain consistent between image paths and names of universities
        str = str.replaceAll("[^a-zA-Z]", "");
        str = str.replaceAll("\\?", "");
        str = str.replaceAll("'", "");
        str = str.replaceAll(" ", "");
        str = str.toLowerCase();
        return str;
    }

    // Gets all universities
    public ArrayList<String> getAllUnis() {

        // Gets all unique universities
        HashSet<String> unis = new HashSet<String>();
        for (Program program : getPrograms()) {
            unis.add(program.getUniversity());
        }

        // Put all unique universities in array list and sort
        ArrayList<String> unis_arr = new ArrayList<>();
        for (String uni : unis) {
            unis_arr.add(uni);
        }
        Collections.sort(unis_arr);

        return unis_arr;
    }

    // Initialize the title
    public void initTitle() {

        // Set the settings for the title
        final int TITLE_HORI_PAD = 20;
        final int TITLE_VERT_PAD = 10;
        final int TITLE_WIDTH = getWidth() - 2 * TITLE_HORI_PAD;
        final int TITLE_HEIGHT = 75;
        final int TITLE_X = TITLE_HORI_PAD;
        final int TITLE_Y = TITLE_VERT_PAD;

        // Init and set the settings for the title label
        setTitle(new JLabel("", SwingConstants.CENTER));
        getTitle().setForeground(Color.white);
        getTitle().setForeground(Color.white);
        getTitle().setFont(Util.getFont(25, false, true));
        getTitle().setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        add(getTitle());
    }

    // Init the info scroll pane
    public void initInfoScrollPane() {

        // Settings for scroll pane
        final int INFO_SCROLL_PANE_HORI_PAD = 10;
        final int INFO_SCROLL_PANE_VERT_PAD = 10;
        final int INFO_SCROLL_PANE_WIDTH = getWidth() / 2 - 2 * INFO_SCROLL_PANE_HORI_PAD;
        final int INFO_SCROLL_PANE_HEIGHT = (int) (((double) getHeight() - (getTitle().getY() + getTitle().getHeight()))
                * (2.0 / 3.0) - 2.0 * INFO_SCROLL_PANE_VERT_PAD);
        final int INFO_SCROLL_PANE_X = getWidth() / 2 + INFO_SCROLL_PANE_HORI_PAD;
        final int INFO_SCROLL_PANE_Y = getTitle().getY() + getTitle().getHeight() + INFO_SCROLL_PANE_VERT_PAD;

        // Init and set settings for scroll pane
        setInfo_text_area(new JTextArea());
        getInfo_text_area().setEditable(false);
        getInfo_text_area().setLineWrap(true);
        getInfo_text_area().setWrapStyleWord(true);

        // Create inner padding in text area
        // https://stackoverflow.com/questions/8792651/how-can-i-add-padding-to-a-jtextfield
        getInfo_text_area().setBorder(BorderFactory.createCompoundBorder(
                getInfo_text_area().getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // More GUI settings
        getInfo_text_area().setBackground(Util.C2);
        getInfo_text_area().setForeground(Color.white);
        getInfo_text_area().setFont(Util.getFont(REG_TEXT_FONT_SIZE));

        // Init and set the scroll pane settings
        setInfo_scroll_pane(new JScrollPane(getInfo_text_area(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        View.setScrollPaneGUI(getInfo_scroll_pane());
        getInfo_scroll_pane().setBounds(INFO_SCROLL_PANE_X, INFO_SCROLL_PANE_Y, INFO_SCROLL_PANE_WIDTH,
                INFO_SCROLL_PANE_HEIGHT);
        add(getInfo_scroll_pane());
    }

    // Init the image label for universities
    public void initImageLabel() {

        // Settings for image label
        final int IMAGE_LABEL_HORI_PAD = 10;
        final int IMAGE_LABEL_VERT_PAD = 10;
        final int IMAGE_LABEL_WIDTH = getWidth() - 2 * IMAGE_LABEL_HORI_PAD
                - (getWidth() - getInfo_scroll_pane().getX());
        final int IMAGE_LABEL_HEIGHT = getInfo_scroll_pane().getHeight();
        final int IMAGE_LABEL_X = IMAGE_LABEL_HORI_PAD;
        final int IMAGE_LABEL_Y = getTitle().getY() + getTitle().getHeight() + IMAGE_LABEL_VERT_PAD;

        // Init and set the JLabel with image
        setImage_label(new JLabel(""));
        getImage_label().setBorder(Util.getBorder(Util.C5));
        getImage_label().setBounds(IMAGE_LABEL_X, IMAGE_LABEL_Y, IMAGE_LABEL_WIDTH, IMAGE_LABEL_HEIGHT);
        add(getImage_label());

        // Init the university to image hash map
        initUniversityToImage();
    }

    // Init the prerequisites scroll pane
    public void initPrerequisitesScrollPane() {

        // Settings for prerequisite scroll pane
        final int PREREQUISITES_SCROLL_PANE_HORI_PAD = 10;
        final int PREREQUISITES_SCROLL_PANE_VERT_PAD = 10;
        final int PREREQUISITES_SCROLL_PANE_WIDTH = getInfo_scroll_pane().getWidth();
        final int PREREQUISITES_SCROLL_PANE_HEIGHT = (int) (((double) getHeight()
                - (getTitle().getY() + getTitle().getHeight())) * (1.0 / 3.0)
                - 2.0 * PREREQUISITES_SCROLL_PANE_VERT_PAD);
        final int PREREQUISITES_SCROLL_PANE_X = getInfo_scroll_pane().getX();
        final int PREREQUISITES_SCROLL_PANE_Y = getInfo_scroll_pane().getY() + getInfo_scroll_pane().getHeight()
                + PREREQUISITES_SCROLL_PANE_VERT_PAD;

        // Init and set the prerequisite text area
        setPrerequisites_text_area(new JTextArea());
        getPrerequisites_text_area().setEditable(false);
        getPrerequisites_text_area().setLineWrap(true);
        getPrerequisites_text_area().setWrapStyleWord(true);

        // Add inner padding
        // https://stackoverflow.com/questions/8792651/how-can-i-add-padding-to-a-jtextfield
        getPrerequisites_text_area().setBorder(BorderFactory.createCompoundBorder(
                getPrerequisites_text_area().getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // More GUI settings
        getPrerequisites_text_area().setBackground(Util.C2);
        getPrerequisites_text_area().setForeground(Color.white);
        getPrerequisites_text_area().setFont(Util.getFont(REG_TEXT_FONT_SIZE));

        // Init and set the scroll pane
        setPrerequisites_scroll_pane(new JScrollPane(getPrerequisites_text_area(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        View.setScrollPaneGUI(getPrerequisites_scroll_pane());
        getPrerequisites_scroll_pane().setBounds(PREREQUISITES_SCROLL_PANE_X, PREREQUISITES_SCROLL_PANE_Y,
                PREREQUISITES_SCROLL_PANE_WIDTH, PREREQUISITES_SCROLL_PANE_HEIGHT);
        add(getPrerequisites_scroll_pane());
    }

    // Init the contact scroll pane
    public void initContactScrollPane() {

        // Init the settings for scroll pane
        final int CONTACT_SCROLL_PANE_HORI_PAD = 10;
        final int CONTACT_SCROLL_PANE_VERT_PAD = 10;
        final int CONTACT_SCROLL_PANE_WIDTH = getImage_label().getWidth();
        final int CONTACT_SCROLL_PANE_HEIGHT = 100;
        final int CONTACT_SCROLL_PANE_X = CONTACT_SCROLL_PANE_HORI_PAD;
        final int CONTACT_SCROLL_PANE_Y = getImage_label().getY() + getImage_label().getHeight()
                + CONTACT_SCROLL_PANE_VERT_PAD;

        // Init and set the text area
        setContact_text_area(new JTextArea());
        getContact_text_area().setEditable(false);
        getContact_text_area().setLineWrap(true);
        getContact_text_area().setWrapStyleWord(true);

        // Add inner padding in text area
        // https://stackoverflow.com/questions/8792651/how-can-i-add-padding-to-a-jtextfield
        getContact_text_area().setBorder(BorderFactory.createCompoundBorder(
                getContact_text_area().getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Set more GUI settings
        getContact_text_area().setBackground(Util.C2);
        getContact_text_area().setForeground(Color.white);
        getContact_text_area().setFont(Util.getFont(REG_TEXT_FONT_SIZE));

        // Init and set the scroll pane
        setContact_scroll_pane(new JScrollPane(getContact_text_area(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        View.setScrollPaneGUI(getContact_scroll_pane());
        getContact_scroll_pane().setBounds(CONTACT_SCROLL_PANE_X, CONTACT_SCROLL_PANE_Y, CONTACT_SCROLL_PANE_WIDTH,
                CONTACT_SCROLL_PANE_HEIGHT);
        add(getContact_scroll_pane());
    }

    // Init the learn more button
    public void initLearnMoreButton() {

        // Settings for learn more button
        final int LEARN_MORE_BUTTON_VERT_PAD = 10;
        final int LEARN_MORE_BUTTON_WIDTH = getContact_scroll_pane().getWidth();
        final int LEARN_MORE_BUTTON_HEIGHT = getPrerequisites_scroll_pane().getHeight()
                - getContact_scroll_pane().getHeight() - LEARN_MORE_BUTTON_VERT_PAD;
        final int LEARN_MORE_BUTTON_X = getContact_scroll_pane().getX();
        final int LEARN_MORE_BUTTON_Y = getContact_scroll_pane().getY() + getContact_scroll_pane().getHeight()
                + LEARN_MORE_BUTTON_VERT_PAD;

        // Init and set settings for learn more button
        setLearn_more_button(new JButton("LEARN MORE"));
        getLearn_more_button().setBackground(Util.C5);
        getLearn_more_button().setFont(Util.getFont(20, true, false));
        getLearn_more_button().setBounds(LEARN_MORE_BUTTON_X, LEARN_MORE_BUTTON_Y, LEARN_MORE_BUTTON_WIDTH,
                LEARN_MORE_BUTTON_HEIGHT);
        add(getLearn_more_button());
    }

    // Opens link to a program on Ontario Universities
    // https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Opens link to a program on Ontario Universities
    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update this panel to display new program
    public void update(Program program) {

        // Sets current program
        setProgram(program);

        // ---------------------------------------------------------------------
        // GOES THROUGH EACH GUI COMPONENT AND UPDATES ITS CONTENT BASED ON THE
        // NEW CURRENT PROGRAM
        // ---------------------------------------------------------------------
        getTitle().setText(getProgram().getName());
        getInfo_text_area().setText(getProgram().getInfoString());
        if (getUniversity_to_image().containsKey(compress_uni_string(program.getUniversity()))) {
            getImage_label()
                    .setIcon(getUniversity_to_image().get(compress_uni_string(program.getUniversity())).getIcon());
        } else {
            System.out.println("FAILED GETTING IMAGE");
            System.out.println(getUniversity_to_image());
            System.out.println(compress_uni_string(program.getUniversity()));
        }
        getPrerequisites_text_area().setText("Prerequisites:\n"+getProgram().getPrerequisitesString());
        getContact_text_area().setText("Contact Info:\n"+getProgram().getContactInfoString());
        if (getLearn_more_button().getActionListeners() != null) {
            ActionListener[] arr = getLearn_more_button().getActionListeners();
            for (ActionListener actionListener : arr) {
                getLearn_more_button().removeActionListener(actionListener);
            }
        }
        getLearn_more_button().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                URL url;
                try {
                    url = new URL(getProgram().getLink());
                    openWebpage(url);
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        // ---------------------------------------------------------------------
        // GOES THROUGH EACH GUI COMPONENT AND UPDATES ITS CONTENT BASED ON THE
        // NEW CURRENT PROGRAM
        // ---------------------------------------------------------------------
    }

    // Getters and setters
    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JTextArea getInfo_text_area() {
        return info_text_area;
    }

    public void setInfo_text_area(JTextArea info_text_area) {
        this.info_text_area = info_text_area;
    }

    public JScrollPane getInfo_scroll_pane() {
        return info_scroll_pane;
    }

    public void setInfo_scroll_pane(JScrollPane info_scroll_pane) {
        this.info_scroll_pane = info_scroll_pane;
    }

    public JTextArea getPrerequisites_text_area() {
        return prerequisites_text_area;
    }

    public void setPrerequisites_text_area(JTextArea prerequisites_text_area) {
        this.prerequisites_text_area = prerequisites_text_area;
    }

    public JScrollPane getPrerequisites_scroll_pane() {
        return prerequisites_scroll_pane;
    }

    public void setPrerequisites_scroll_pane(JScrollPane prerequisites_scroll_pane) {
        this.prerequisites_scroll_pane = prerequisites_scroll_pane;
    }

    public JTextArea getContact_text_area() {
        return contact_text_area;
    }

    public void setContact_text_area(JTextArea contact_text_area) {
        this.contact_text_area = contact_text_area;
    }

    public JButton getLearn_more_button() {
        return learn_more_button;
    }

    public void setLearn_more_button(JButton learn_more_button) {
        this.learn_more_button = learn_more_button;
    }

    public JLabel getImage_label() {
        return image_label;
    }

    public void setImage_label(JLabel image_label) {
        this.image_label = image_label;
    }

    public JScrollPane getContact_scroll_pane() {
        return contact_scroll_pane;
    }

    public void setContact_scroll_pane(JScrollPane contact_scroll_pane) {
        this.contact_scroll_pane = contact_scroll_pane;
    }

    public HashMap<String, JLabel> getUniversity_to_image() {
        return university_to_image;
    }

    public void setUniversity_to_image(HashMap<String, JLabel> university_to_image) {
        this.university_to_image = university_to_image;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

}
