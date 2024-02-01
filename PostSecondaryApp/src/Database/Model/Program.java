/*
 * Program class
 * Description:
 *  Template class used to represent a university program.
 *  Contains all the attributes and methods each university program is associated with. 
 *  University program data is read from the "programs.json" file.
 * Contributors:
 *  100% - Andrew
 */

package Database.Model;

import java.util.*;
import org.json.simple.*;

public class Program {

	// Attributes for each Program
    String name;
    String university;
    String degree;
    String ouac_program_code;
    String grade_range;
    String experiential_learning;
    String enrollment;
    String instruction_language;
    String notes;
    ArrayList<String> prerequisites;
    String address;
    String email;
    String link;

    // Constructor for a Program
    public Program(JSONObject program_json) {

        // Sets attributes according to information stored for this university program
        setName((String) program_json.get("name"));
        setUniversity((String) program_json.get("University"));
        setDegree((String) program_json.get("Degree"));
        setOuac_program_code((String) program_json.get("OUAC Program Code"));
        setGrade_range((String) program_json.get("Grade Range"));
        setExperiential_learning((String) program_json.get("Experiential Learning"));
        setEnrollment((String) program_json.get("Enrollment"));
        setInstruction_language((String) program_json.get("Instruction Language"));
        setNotes((String) program_json.get("Notes"));

        // Prerequisites must be handled separately, since it is stored in an array
        ArrayList<String> prerequisites = new ArrayList<>();
        JSONArray prerequisites_json = (JSONArray) program_json.get("prerequisites");
        for (int i = 0; i < prerequisites_json.size(); i++) {
            prerequisites.add((String) prerequisites_json.get(i));
        }
        setPrerequisites(prerequisites);

        setAddress((String) program_json.get("address"));
        setEmail((String) program_json.get("email"));
        setLink((String) program_json.get("link"));
    }

    // Formats and returns name, university, degree, ouac program name, grade range,
    // experiential learning, enrollment, instruction language, and notes as string
    public String getInfoString() {
        String info = "";
        info+=String.format("Program: %s\n\n", getName());
        info+=String.format("University: %s\n\n", getUniversity());
        info+=String.format("Degree: %s\n\n", getDegree());
        info+=String.format("OUAC Code: %s\n\n", getOuac_program_code());
        info+=String.format("Grade range: %s\n\n", getGrade_range());
        info+=String.format("Experiential learning: %s\n\n", getExperiential_learning());
        info+=String.format("Enrollment: %s\n\n", getEnrollment());
        info+=String.format("Instruction Language: %s\n\n", getInstruction_language());
        info+=String.format("Notes: %s\n\n", getNotes());
        return info;
    }

    // Formats and returns prerequisites as a string
    public String getPrerequisitesString(){
        String prerequisites = "";
        for(String prerequisite:getPrerequisites()){
            prerequisites+=String.format("%s\n", prerequisite);
        }
        return prerequisites;
    }

    // Formats and return contact information for program as st  ring
    public String getContactInfoString(){
        String contact_info = "";
        contact_info += String.format("%s\n", getAddress());
        contact_info += String.format("%s\n", getEmail());
        return contact_info;
    }

    // Getter and setter methods for attributes
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getOuac_program_code() {
        return ouac_program_code;
    }

    public void setOuac_program_code(String ouac_program_code) {
        this.ouac_program_code = ouac_program_code;
    }

    public String getGrade_range() {
        return grade_range;
    }

    public void setGrade_range(String grade_range) {
        this.grade_range = grade_range;
    }

    public String getExperiential_learning() {
        return experiential_learning;
    }

    public void setExperiential_learning(String experiential_learning) {
        this.experiential_learning = experiential_learning;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getInstruction_language() {
        return instruction_language;
    }

    public void setInstruction_language(String instruction_language) {
        this.instruction_language = instruction_language;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Program [name=" + name + ", university=" + university + ", degree=" + degree + ", ouac_program_code="
                + ouac_program_code + ", grade_range=" + grade_range + ", experiential_learning="
                + experiential_learning + ", enrollment=" + enrollment + ", instruction_language="
                + instruction_language + ", notes=" + notes + ", prerequisites=" + prerequisites + ", address="
                + address + ", email=" + email + ", link=" + link + "]";
    }

    
    //This method gets the location of the university program based on the university name 
    public String getLocation() {
    	
    	if (university == null) {
    		return "Unknown Location";
    	}
    	
    	//Central Ontario Universities 
    	String [] centralOntarioUniversities = {
    			"OCAD University",
    			"Ontario Tech University",
    			"Toronto Metropolitan University (formerly Ryerson University)",
    			"Trent University",
    			"University of Toronto",
    			"University of Toronto – Mississauga",
    			"University of Toronto – Scarborough",
    			"York University",
    			"York University – Glendon Campus",
    	};
    	
    	//Eastern Ontario Universities 
    	String [] easternOntarioUniversities = {
    			"Carleton University",
    			"Queen's University",
    			"Royal Military College of Canada",
    			"University of Ottawa",
    			"University of Ottawa – Saint Paul University",
    	};
    	
    	//Northern Ontario Universities 
    	String [] northernOntarioUniversities = {
    			"Algoma University",
    			"Lakehead University",
    			"Laurentian University",
    			"Nipissing University",
    	};
    	
    	//Southwestern Ontario Universities 
    	String [] southwesternOntarioUniversities = {
    			"Brock University",
    			"McMaster University",
    			"University of Guelph",
    			"University of Guelph–Humber",
    			"University of Waterloo",
    			"University of Waterloo – Renison University College",
    			"University of Waterloo – St. Jerome's University",
    			"University of Windsor",
    			"Western University",
    			"Western University – Huron University College",
    			"Western University – King's University College",
    			"Wilfrid Laurier University",
    			"Wilfrid Laurier University – Brantford Campus",
    			"Wilfrid Laurier University – Milton Campus",
    			
    	};
    	
    	//Checks where the university belongs to and returns location's name
    	if (Arrays.asList(centralOntarioUniversities).contains(university)) {
    		return "Central Ontario";
    	} else if (Arrays.asList(easternOntarioUniversities).contains(university)) {
    		return "Eastern Ontario";
    	} else if (Arrays.asList(northernOntarioUniversities).contains(university)) {
    		return "Northern Ontario";
    	} else if (Arrays.asList(southwesternOntarioUniversities).contains(university)) {
    		return "Southwestern Ontario";
    	} 
    	
		return "Unknown Location";

    }
    

}