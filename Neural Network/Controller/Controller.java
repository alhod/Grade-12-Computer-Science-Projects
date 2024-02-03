/*
 * Class: Controller
 * Description: Main class in the Controller package. Contains instances of View 
 * and Model classes. Handles interactions between them. Particularly the View 
 * components triggering the use of a neural network model, this class is 
 * responsible for that.
 */

// Packages and imports
package Controller;

import Application.Application;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import Model.*;
import View.*;

// Controller class
// Responsible for interactions between the View and Model classes. Operates the neural network. Instantiates both View and Model classes.
public class Controller {

    // The main View and Model classes
    View view;
    Model model;

    // String that represents the name of the json file storing the serialized model
    public static final String DEFAULT_SERIALIZED_JSON_FILE_NAME = "model.json";

    // Constructor for Controller class
    // Based on
    public Controller() {

        // Instantiate View and Model classes
        setView(new View());
        setModel(new Model());

        // Must add action listener for submit button in Controller scope since needs
        // access to model to infer
        getView().getTest_model_frame().getPrediction_panel().getSubmit_button().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Get the image the user drew
                Image image = getView().getTest_model_frame().getPrediction_panel().getDrawArea().getImage();

                // Updates the image section in GUI to show user image passed into nn
                display_input_image(image);

                // Get prediction matrix by passing image into model
                Matrix prediction = getModel().getNeural_network().inference(get_img_matrix(image));

                // Displays the probabilities generated by the model
                display_model_output(prediction);

                // Passes image into nn and updates label based on predicted digit
                getView().getTest_model_frame().getPrediction_panel().getLabel()
                        .setText(Integer.toString(getModel().getNeural_network().inverse_one_hot_encode_only_class(prediction)));
            }
        });

        // Add action listeners to buttons in model selection frame
        ArrayList<JButton> buts = getView().getModel_selection_frame().getButtons();
        
        // Iterate through each button
        for(JButton but:buts){

            // Add action listener to link to certain model
            but.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    // Dispose of model selection frame
                    getView().getModel_selection_frame().getFrame().dispose();
                    
                    // Load model and test frame
                    String name = but.getText()+".json";
                    getModel().load_neural_network(name);
                    getView().getTest_model_frame().initFrame();
                }
            });
        }

        // Checks whether the application is in training or GUI mode
        // If training mode, trains the neural network
        if (Application.APPLICATION_STATUS.equals("TRAIN")) {

        }

        // If GUI mode, displays the frame in View class
        else if (Application.APPLICATION_STATUS.equals("GUI")) {
            getView().getModel_selection_frame().initFrame();
        }

        // Otherwise invalid status
        else {
            System.out.println("INVALID APPLICATION_STATUS");
        }
    }

    // Displays an inputted image, scaled down then scaled up, to get an idea of
    // what the model sees
    public void display_input_image(Image image) {

        // Get width and height of the image
        int scale_width = getView().getTest_model_frame().getPixelated_image_panel().getPixelated_image().getWidth();
        int scale_height = getView().getTest_model_frame().getPixelated_image_panel().getPixelated_image().getHeight();

        // Gets image and scales it down then up
        image = image.getScaledInstance(28, 28, Image.SCALE_SMOOTH).getScaledInstance(scale_width, scale_height,
                Image.SCALE_SMOOTH);

        // Update the pixelated image
        getView().getTest_model_frame().getPixelated_image_panel().getPixelated_image().setIcon(new ImageIcon(image));
    }

    // Updates the "Confidence" section in the GUI
    // Should be 10 x 1
    public void display_model_output(Matrix preds){

        // Get output label arraylist
        ArrayList<JLabel> output_labels = getView().getTest_model_frame().getOutput_panel().getOutput_labels();

        // Iterate through output labels
        for(int i=0; i<output_labels.size(); i++){
            output_labels.get(i).setText(String.format("%d: ", i)+"%"+String.format("%.2f", preds.get(i).get(0)*100.0));
        }
    }

    // Gets the user's drawing as a BufferedImage
    public Matrix get_img_matrix(Image image) {

        // Scales down image
        image = image.getScaledInstance(28, 28, Image.SCALE_SMOOTH);

        // Converts to buffered image
        // https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
        BufferedImage buffered_image = new BufferedImage(image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D buffered_graphic = buffered_image.createGraphics();
        buffered_graphic.drawImage(image, 0, 0, null);
        buffered_graphic.dispose();

        // Creates return matrix
        Matrix ret = new Matrix(Model.SAMPLE_WIDTH * Model.SAMPLE_HEIGHT, 1);

        // Iterate through each pixel in the buffered image
        for (int i = 0; i < ret.numRows(); i++) {

            // Get the pixel value, 0 to 255
            int pixel_val = buffered_image.getRaster().getSample(i % Model.SAMPLE_WIDTH, i / Model.SAMPLE_HEIGHT, 0);

            // Set matrix at that cell to this value
            ret.get(i).set(0, (double) pixel_val);
        }

        // Return matrix
        return ret;
    }

    // Getter and setter methods
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
