package Matching.Model;

import Database.Model.*;

public class ScoredProgram {
    
    double score;
    Program program;

    public ScoredProgram(Program program){
        setScore(0.0);
        setProgram(program);
    }

    public ScoredProgram(Program program, double score){
        setScore(score);
        setProgram(program);
    }

    public void incScore(double inc){
        setScore(getScore()+inc);
    }

    public static boolean compare(ScoredProgram p1, ScoredProgram p2){
        return compare(p1, p2, true);
    }

    public static boolean compare(ScoredProgram p1, ScoredProgram p2, boolean return_greater){
        if(p1.getScore()>=p2.getScore()){
            return return_greater;
        } else {
            return return_greater;
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    
}
