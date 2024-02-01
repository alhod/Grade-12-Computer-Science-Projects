/*
 * Database Controller Class
 * Description: This class connects the view and data. It manages the logic for 
 * handling user interactions and updating the display based on the selected criteria.
 */

package Database.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Database.Model.Model;
import Database.Model.Program;
import Database.View.View;

public class Controller {
	
	private Model model;
	private View view;
	
	//Constructor 
	public Controller() {

		setModel(new Model());
	
	}
	
	public void createFrame(){
		setView(new View());

		//Set up the listener for the search button
        getView().addSearchActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		 updateProgramList();
        		// 
        	}
        });
	}
	
	//This method updates the list of programs based on the combo boxes 
	private void updateProgramList() {
		
		//Get the selected filters 
		String selectedUniversity = view.getSelectedUniversity();
		String selectedLocation = view.getSelectedLocation();
		String selectedExperientialLearning = view.getSelectedExperientialLearning();
		String selectedSort = view.getSelectedSort();
		String selectedCategory = view.getSelectedCategory();
		
		//Start will all the programs
		ArrayList<Program> filteredPrograms = model.getPrograms();
		
		//Apply the university filter
		if (selectedUniversity != null && !selectedUniversity.isEmpty() && !selectedUniversity.equals("All Universities")) {
			filteredPrograms = model.filterProgramsByUniversity(filteredPrograms, selectedUniversity);
		} 
		
		//Apply the location filter 
		if (selectedLocation != null && !selectedLocation.isEmpty() && !selectedLocation.equals("All Of Ontario")) {
			filteredPrograms = model.filterProgramsByLocation(filteredPrograms, selectedLocation);
		}
		
		//Apply the experiential learning filter 
		if (selectedExperientialLearning != null & !selectedExperientialLearning.isEmpty() && !selectedExperientialLearning.equals("All Of The Above")) {
			List<String> keywords = List.of(selectedExperientialLearning);
			filteredPrograms = model.filterProgramsByExperientialLearning(filteredPrograms, keywords);
		}
		
		if (selectedCategory != null & !selectedCategory.isEmpty() && !selectedCategory.equals("All")) {
			List<String> keywords = List.of(selectedCategory);
			filteredPrograms = model.filterProgramsByCategory(filteredPrograms, keywords);
			
		}
		
		System.out.println(selectedSort);
		//Apply sorting 
		model.sortPrograms(filteredPrograms, selectedSort);
		
		//Update the view 
		Program [] programArray = filteredPrograms.toArray(new Program[0]);
		view.displayPrograms(programArray);
	}

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
	
	

}
