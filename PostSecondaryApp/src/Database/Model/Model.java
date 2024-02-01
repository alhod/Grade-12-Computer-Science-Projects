/*
 * Database Model Class
 * Description: This class processes data related to the academic programs 
 * It handles the logic for loading, filtering, and sorting the data. 
 */

package Database.Model;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Model {
    
    ArrayList<Program>programs;

    public Model(){
        setPrograms(loadPrograms());
//        for(Program p : getPrograms()){
//            System.out.println(p);
//        }
    }

    public ArrayList<Program> loadPrograms(){

        // https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("src/Database/Model/programs.json"))
        {
            Object obj;
            obj = jsonParser.parse(reader);
 
            JSONArray programs_json_array = (JSONArray) obj;
            
            ArrayList<Program>programs = new ArrayList<>();
            
            // https://stackoverflow.com/questions/8963347/how-to-iterate-this-json-array-using-java-and-org-json-in-android
            for(int i=0; i<programs_json_array.size(); i++){
                programs.add(new Program((JSONObject)programs_json_array.get(i)));
            }

            return programs;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Program> getPrograms(){
        return this.programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    //This method filters the programs by university 
    public ArrayList<Program> filterProgramsByUniversity(ArrayList<Program> programs, String university) {
    	
    	//An array list to hold the filtered programs
    	ArrayList<Program> filteredPrograms = new ArrayList<>();
    	
    	//Iterate through the entire list of programs 
    	for (Program program : programs) {
    		
    		//Checks if the program's university matches the given university 
    		if (program.getUniversity().equalsIgnoreCase(university)) {
    			
    			//If there is a match, add to the filtered list 
    			filteredPrograms.add(program);
    		}
    	}
    	
    	return filteredPrograms;
    	
    }
    
    //This method filters the programs by location
    public ArrayList<Program> filterProgramsByLocation(ArrayList<Program> programs, String location) {
    	
    	ArrayList<Program> filteredPrograms = new ArrayList<>();
    	
    	for (Program program : programs) {
    		
    		if (program.getLocation().equalsIgnoreCase(location)) {
    			
    			filteredPrograms.add(program);
    		}
    		

    	}
    	
    	return filteredPrograms;
    }
    
    //This method filters the programs by experiential learning
    public ArrayList<Program> filterProgramsByExperientialLearning(ArrayList<Program> programs, List<String> keywords) {
    	
    	ArrayList<Program> filteredPrograms = new ArrayList<>();
    	
    	for (Program program : programs) {
    		
    		//Convert to lowercase for case sensitive purposes 
    		String experientialLearning = program.getExperiential_learning().toLowerCase();
    	
    		for (String keyword : keywords) {
    			
    			if (experientialLearning.contains(keyword.toLowerCase())) {
    				
    				//Add to the filtered list if there is a match 
    				filteredPrograms.add(program);
    				
    				//Break inner loop to avoid adding the same program 
    				break;
    			}
    		}
    	}
    	
    	return filteredPrograms;
    }
    
    //This method sorts the program given the criteria 
    //https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html 
    public void sortPrograms(ArrayList<Program> programList, String sortBy) {
    	switch (sortBy) {
    	
    	case "Alphabetical (Default)":
    		Collections.sort(programList, Comparator.comparing(Program :: getName));
    		break;
    		
    	case "Acceptance Rate (Lowest to Highest)":
    		Collections.sort(programList, Comparator.comparingDouble(p -> extractNumbers(p.getGrade_range())));
    		break;
    		
    	case "Acceptance Rate (Highest to Lowest)":
    		Collections.sort(programList, (p1, p2) -> Double.compare(extractNumbers(p2.getGrade_range()), extractNumbers(p1.getGrade_range())));
    		break;
  
    	}
    	
    	
    }
    
    //This method filters the programs based on the category selected 
    public ArrayList<Program> filterProgramsByCategory(ArrayList<Program> programs, List<String> keywords) {
    	
    	ArrayList<Program> filteredPrograms = new ArrayList<>();
    	
    	for (Program program : programs) {
    		
    		//Convert to lowercase for case sensitive purposes 
    		String name = program.getName().toLowerCase();
    	
    		for (String keyword : keywords) {
    			
    			if (name.contains(keyword.toLowerCase())) {
    				
    				//Add to the filtered list if there is a match 
    				filteredPrograms.add(program);
    				
    				//Break inner loop to avoid adding the same program 
    				break;
    			}
    		}
    	}
    	
    	return filteredPrograms;   	
    	
    }
    
//    //This method deals with situations where low, mid, high are used 
//    private double extractWords(String input) {
//    	
//    	//Convert to lowercase 
//    	String lowercaseInput = input.toLowerCase();
//    	
//    	//Assign numeric values if string contains low, mid, high 
//    	if (lowercaseInput.contains("low")) {
//    		return (extractNumbers(lowercaseInput) - 1);
//    		
//    	} else if (lowercaseInput.contains("mid")) {
//    		return extractNumbers(lowercaseInput); 
//    		
//    	} else if (lowercaseInput.contains("high")) {
//    		return (extractNumbers(lowercaseInput) + 1);
//    		
//    	} else { 
//    		return extractNumbers(input);
//    	}
//    }

    //This method extracts the numeric value from the grade range 
	private double extractNumbers(String input) {
		
		//Removes all characters from the string that are not digits 
		String numbersOnly = input.replaceAll("[^0-9.]", "");
		
		//Deals with ranges like 70%-80%
		String [] numbers = numbersOnly.split("-");
		
		try {
			return Double.parseDouble(numbers[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

}