/*
 * Class: Controller
 * Contributors: 100% - Andrew Deng
 * Description: Main controller class for menu screen. Responsible for 
 * initializing the instances of the controller classes for all other 
 * features, and manages buttons to access all features.
 */

// Parent package and imports
package Menu.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Authentication.Controller.*;
import Menu.View.*;

// Declare Controller class
public class Controller {

    // Contains static instance of itself so features are able to access all other
    // classes in the program (mainly in Matching feature)
    public static Controller controller;

    // Attributes to store instances of the controller class for all features
    Database.Controller.Controller databaseController;
    Authentication.Controller.Controller authenticationController;
    Matching.Controller.Controller matchingController;
    Map.Controller.Controller mapController;

    // Stores instance of view class
    View view;

    // Constructor for Controller class
    public Controller() {

        // Initializes and sets all attributes (including instances of other controller classes)
        setController(this);
        setAuthenticationController(new Authentication.Controller.Controller());
        setDatabaseController(new Database.Controller.Controller());
        setMatchingController(new Matching.Controller.Controller(getDatabaseController().getModel().getPrograms()));
        setMapController(new Map.Controller.Controller());
        setView(new View());

        // Adds action listeners to view object
        addViewActionListeners();
    }

    // Adds action listeners to buttons in view class
    public void addViewActionListeners() {

        // Adds action listener to login button so opens login frame
        getView().getLogin().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getAuthenticationController().createFrame();
            }
        });
        // Adds action listener to database button so opens database frame
        getView().getDatabase().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getDatabaseController().createFrame();
            }
        });
        // Adds action listener to matching button so opens matching frame
        getView().getMatching().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Checks to make sure user is logged in before they can access matching frame
                if (!getAuthenticationController().isLogged_in()) {
                    JOptionPane.showMessageDialog(null, "Please Log In First");
                    return;
                }
                getMatchingController().createFrame();
            }
        });
        // Adds action listener to map button so opens map frame
        getView().getMap().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getMapController().createFrame();
            }
        });
    }

    // Getter and setter methods
    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        Controller.controller = controller;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Database.Controller.Controller getDatabaseController() {
        return databaseController;
    }

    public void setDatabaseController(Database.Controller.Controller databaseController) {
        this.databaseController = databaseController;
    }

    public Authentication.Controller.Controller getAuthenticationController() {
        return authenticationController;
    }

    public void setAuthenticationController(Authentication.Controller.Controller authenticationController) {
        this.authenticationController = authenticationController;
    }

    public Matching.Controller.Controller getMatchingController() {
        return matchingController;
    }

    public void setMatchingController(Matching.Controller.Controller matchingController) {
        this.matchingController = matchingController;
    }

    public Map.Controller.Controller getMapController() {
        return mapController;
    }

    public void setMapController(Map.Controller.Controller mapController) {
        this.mapController = mapController;
    }

}
