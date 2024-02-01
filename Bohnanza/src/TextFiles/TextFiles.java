package TextFiles;

import java.io.File;
import java.nio.file.Paths;

public class TextFiles {

    static final String DECK_PATH = "src\\TextFiles\\Deck.txt";
    static final String INSTRUCTIONS_PATH = "src\\TextFiles\\Instructions.txt";

    public static File getDeck(){
        return new File(DECK_PATH);
    }

    public static File getInstructions(){
        return new File(INSTRUCTIONS_PATH);
    }
}
