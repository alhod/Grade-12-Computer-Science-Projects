package Model;

import Controller.*;
import TextFiles.*;
import View.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Instructions {

    static final String BREAK = "&&&";

    String title;
    ArrayList<String> phase_titles;
    ArrayList<String> phase_instructions;
    String player_turn;

    // Gets phase_title for each phase, and phase_instructions associated with that
    // phase from .txt file.
    public Instructions() {
        setPhase_titles(new ArrayList<String>());
        setPhase_instructions(new ArrayList<String>());
        initInstructions();
    }

    public void initInstructions(){

        // Get deck information
        // From here: https://www.w3schools.com/java/java_files_read.asp
        try {

            // Reads file
            // File file = new File("C:\\Users\\andar\\Grade12ComSci\\Bohnanza\\Model\\AllCards.txt");
            // System.out.println(Paths.get("").toAbsolutePath().toString()+"\\Bohnanza\\TextFiles\\Cards.txt");
            // From here: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
            File file = TextFiles.getInstructions();
            Scanner scanner = new Scanner(file);

            // Goes through every line
            while (scanner.hasNextLine()) {

                String phase = scanner.nextLine();

                String phase_instructions="";

                while(scanner.hasNextLine()){
                    
                    String next_line = scanner.nextLine();

                    if(next_line.equals(BREAK)){
                        break;
                    }

                    phase_instructions+=next_line+"\n";
                }

                getPhase_titles().add(phase);
                getPhase_instructions().add(phase_instructions);
            }

            // Closes file reader
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Instructions.txt access messed up");
            e.printStackTrace();
        }

        // displayInstructions();
    }

    public static String getBreak() {
        return BREAK;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getPhase_titles() {
        return phase_titles;
    }

    public void setPhase_titles(ArrayList<String> phase_titles) {
        this.phase_titles = phase_titles;
    }

    public ArrayList<String> getPhase_instructions() {
        return phase_instructions;
    }

    public void setPhase_instructions(ArrayList<String> phase_instructions) {
        this.phase_instructions = phase_instructions;
    }

    public String getPlayer_turn() {
        return player_turn;
    }

    public void setPlayer_turn(String player_turn) {
        this.player_turn = player_turn;
    }

    public void displayInstructions(){
        for(int i=0; i<getPhase_titles().size(); i++){
            System.out.println(getPhase_titles().get(i));
            System.out.println(getPhase_instructions().get(i));
        }
    }

    
}
