/*
 * Class: Model
 * Contributor: 100% - Andrew Deng
 * Description: The main class in the Model package. Responsible for the backend 
 * representation of the matching feature, including scoring and sorting all the 
 * programs based on the survey input (is called between transition between 
 * survey and results panels)
 */

// Parent package and imports
package Matching.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Authentication.Model.UserDataBase;

import Database.Model.Program;

// Declaration of Model class
public class Model {

    // Static final attributes for convenience
    public static final int NUM_SURVEY_QUESTIONS = 7;
    public static final String UNIVERSITY_RANKINGS_PATH = "src\\Matching\\Model\\UniversityRankings.txt";
    public static final String COURSE_CODES_TO_NAME_PATH = "src\\Matching\\Model\\CourseCodesToName.txt";
    public static final double QUESTION_WEIGHT_SCALE = 1.0 / 20.0;

    // Arraylist containing all programs and university rankings
    ArrayList<Program> programs;
    ArrayList<String> university_rankings;

    // Initialize “programs” ArrayList. Parse through university programs stored in
    // database, initialize Program instances, add to “programs”.
    public Model(ArrayList<Program> programs) {
        setPrograms(programs);
        setUniversity_rankings(new ArrayList<>());
        getUniversityRankings();
    }

    // Get's the rankings of universities in Ontario (to be used for the survey)
    // https://www.w3schools.com/java/java_files_read.asp
    public void getUniversityRankings() {
        try {
            File myObj = new File(UNIVERSITY_RANKINGS_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                university_rankings.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Clone “programs” ArrayList and sort new ArrayList based on survey results.
    // Return this new ArrayList.
    public ArrayList<Program> generateMatchingResults(HashMap<Integer, ArrayList<String>> survey) {

        // Create new array list to store the programs in sorted order
        ArrayList<ScoredProgram> scored_programs = new ArrayList<>();

        // Make copy of original list of university programs
        for (Program program : programs) {
            scored_programs.add(new ScoredProgram(program, score(program, survey)));
        }

        // Sort and return the programs
        ArrayList<Program> sorted_programs = sortPrograms(scored_programs);
        return sorted_programs;
    }

    // Use bubble sort to sort the programs in descending order of scores
    public ArrayList<Program> sortPrograms(ArrayList<ScoredProgram> scored_programs) {

        // Bubble sorts the programs from the array list
        int i, j;
        ScoredProgram temp;
        boolean swapped;
        for (i = 0; i < scored_programs.size() - 1; i++) {
            swapped = false;
            for (j = 0; j < scored_programs.size() - i - 1; j++) {

                // Compare one program to another based on their score
                if (scored_programs.get(j).getScore() < scored_programs.get(j + 1).getScore()) {
                    temp = scored_programs.get(j);
                    scored_programs.set(j, scored_programs.get(j + 1));
                    scored_programs.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (swapped == false)
                break;
        }

        // Init and populate an arraylist for university programs and return it
        ArrayList<Program> sorted_programs = new ArrayList<>();
        for (ScoredProgram scored_program : scored_programs) {
            sorted_programs.add(scored_program.getProgram());
        }

        return sorted_programs;
    }

    // Score first question
    public double q0(Program program, ArrayList<String> unis) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(unis.get(0));

        // Check to see if university program belongs to is amongst universities checked
        // off
        String uni = program.getUniversity();
        for (int i = 1; i < unis.size(); i++) {
            if (uni.equals(unis.get(i))) {
                return question_weight * 15.0;
            }
        }
        return 0.0;
    }

    // Score second question
    public double q1(Program program, ArrayList<String> ans) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(ans.get(0));

        // Check for instances of "coop" or "internship" or "co-op" in the "experiential
        // learning" section for this program
        boolean has_coop = false;
        String experiential_learning = program.getExperiential_learning().toLowerCase();
        has_coop = has_coop || experiential_learning.contains("coop");
        has_coop = has_coop || experiential_learning.contains("internship");
        has_coop = has_coop || experiential_learning.contains("co-op");

        // If the above is true, depending on what user checked off of, increase the
        // score
        if (ans.get(1).equals("Yes")) {
            return question_weight * (has_coop ? 10.0 : 0.0);
        } else if (ans.get(0).equals("No")) {
            return question_weight * (!has_coop ? 10.0 : 0.0);
        } else {
            return question_weight * 5.0;
        }
    }

    // Score third question
    public double q2(Program program, ArrayList<String> txt) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(txt.get(0));

        // Get each keyword/phrase, remove spaces and set to lower case
        String[] arr = txt.get(1).split(",");
        ArrayList<String> keywords = new ArrayList<>();
        for (String key : arr) {
            keywords.add(key.replaceAll(" ", "").toLowerCase());
        }

        // Do same for name of program
        String name = program.getName().replaceAll(" ", "").toLowerCase();

        // Iterate through each keyword and search if program name contains this
        // keyword/phrase
        for (String keyword : keywords) {
            if (name.contains(keyword)) {
                return question_weight * 25.0;
            }
        }
        return 0.0;
    }

    // Score fourth question
    public double q3(Program program, ArrayList<String> txt) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(txt.get(0));

        // Get user inputted lower and upper bounds
        int lower_bound = Integer.valueOf(txt.get(1));
        int upper_bound = Integer.valueOf(txt.get(2));

        // Find integers in the grade range in program
        ArrayList<String> integers = findIntegers(program.getGrade_range());

        // If no grades provided, return 0.0
        if (integers.size() == 0) {
            return 0.0;
        }

        // First instance of integer is lower bound
        int program_lower_bound = Integer.valueOf(integers.get(0));

        // If this is only instance of integer, check if these program bounds fall
        // within user inputted bounds
        if (integers.size() == 1) {
            if (lower_bound <= program_lower_bound && program_lower_bound <= upper_bound) {
                return question_weight * 10.0;
            }
            return 0.0;
        }

        // Second instance of integer is upper bound
        int program_upper_bound = Integer.valueOf(integers.get(0));

        // Check to see if there exists intersection between ranges of user inputted
        // boundsn and program bounds
        if (lower_bound <= Math.max(program_lower_bound, program_upper_bound)
                && Math.min(program_lower_bound, program_upper_bound) <= upper_bound) {
            return question_weight * 10.0;
        }

        return 0.0;
    }

    // Finds all integers in a string
    ArrayList<String> findIntegers(String stringToSearch) {

        // Add all integers in string to an arraylist and return it
        // https://stackoverflow.com/questions/13440506/find-all-numbers-in-the-string
        Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher matcher = integerPattern.matcher(stringToSearch);

        ArrayList<String> integerList = new ArrayList<>();
        while (matcher.find()) {
            integerList.add(matcher.group());
        }

        return integerList;
    }

    // Score fifth question
    public double q4(Program program, ArrayList<String> num) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(num.get(0));

        // Get slider weight user inputted
        int slider_weight = Integer.valueOf(num.get(1));
        for (int i = 0; i < getUniversity_rankings().size(); i++) {

            // Finds the ranking of the university that the program belongs to, and based on
            // slider weight, inputs into formula
            if (program.getUniversity().contains(getUniversity_rankings().get(i))) {
                return question_weight * (25 - (i + 1)) * 1.5 * Math.log10(Math.min(slider_weight + 1, 10));
            }
        }

        // If can't find university program belongs to
        return question_weight * 14 * 1.5 * Math.log10(Math.max(slider_weight + 1, 10));
    }

    // Score sixth question
    public double q5(Program program, ArrayList<String> txt) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(txt.get(0));

        // Gets preferred cities of users, removes spaces and sets to all lower case
        ArrayList<String> preferred_cities = new ArrayList<>();
        for (int i = 1; i < txt.size(); i++) {
            preferred_cities.add(txt.get(i).replaceAll(" ", "").toLowerCase());
        }

        // Gets address of program, does same filtering
        String address = program.getAddress().replaceAll(" ", "").toLowerCase();

        // Check if city this program is in is amongst checked cities
        for (String city : preferred_cities) {
            if (address.contains(city)) {
                return question_weight * 15.0;
            }
        }
        return 0.0;
    }

    // Score seventh question
    public double q6(Program program, ArrayList<String> ans) {
        double question_weight = (QUESTION_WEIGHT_SCALE) * Double.valueOf(ans.get(0));

        // If user answered they don't care about prerequisite overlap
        if (ans.get(1).equals("No")) {
            return 0.0;
        }

        // Stores user's courses in string format
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<String> course_strs = new ArrayList<>();

        UserDataBase userDataBase = Menu.Controller.Controller.getController().getAuthenticationController()
                .getControllerFrame().getUserDataBase();
        int userCount = Integer.valueOf(userDataBase.retrieveUserCount());

        course_strs.add(userDataBase.retrieveCourse1(userCount));
        course_strs.add(userDataBase.retrieveCourse2(userCount));
        course_strs.add(userDataBase.retrieveCourse3(userCount));
        course_strs.add(userDataBase.retrieveCourse4(userCount));
        course_strs.add(userDataBase.retrieveCourse5(userCount));
        course_strs.add(userDataBase.retrieveCourse6(userCount));

        // Splits between course name and code
        for (String course : course_strs) {
            String[] tmp = course.split(",");
            courses.add(new Course(tmp[0].replaceAll(" ", "").toLowerCase(), tmp[1].replaceAll(" ", "").toLowerCase()));
        }

        // Gets program prerequisites
        ArrayList<String> prerequisites = program.getPrerequisites();

        int x = 0;

        // Iterates through user's courses
        for (Course course : courses) {

            // Iterates through prerequisite courses
            for (String prerequisite : prerequisites) {

                // Filters prerequisite string in same way and check if this course is found in
                // the prerequisite courses of the program
                String tmp = prerequisite.replaceAll(" ", "").toLowerCase();
                if (tmp.contains(course.getCode()) || tmp.contains(course.getName())) {
                    x++;
                }
            }
        }

        // Formula based on logarithms
        return question_weight
                * (Math.log10(x+1.0)/Math.log10(6.0)+1);
    }

    // Scores a program based on survey results
    public double score(Program program, HashMap<Integer, ArrayList<String>> survey) {

        double score = 0.0;

        // Depending on the question number, applies different scoring  method
        for (int i = 0; i < NUM_SURVEY_QUESTIONS; i++) {
            switch (i) {
                case 0:
                    score += q0(program, survey.get(i));
                    break;
                case 1:
                    score += q1(program, survey.get(i));
                    break;
                case 2:
                    score += q2(program, survey.get(i));
                    break;
                case 3:
                    score += q3(program, survey.get(i));
                    break;
                case 4:
                    score += q4(program, survey.get(i));
                    break;
                case 5:
                    score += q5(program, survey.get(i));
                    break;
                case 6:
                    score += q6(program, survey.get(i));
                    break;
            }
        }

        return score;
    }

    // Getter and setter methods
    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    public static int getNumSurveyQuestions() {
        return NUM_SURVEY_QUESTIONS;
    }

    public ArrayList<String> getUniversity_rankings() {
        return university_rankings;
    }

    public void setUniversity_rankings(ArrayList<String> university_rankings) {
        this.university_rankings = university_rankings;
    }

}

// Class used to represent a course
class Course {

    // Only contains name and code for that course
    String name;
    String code;

    // Constructor for Course class
    public Course(String name, String code) {
        setName(name);
        setCode(code);
    }

    // Getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}