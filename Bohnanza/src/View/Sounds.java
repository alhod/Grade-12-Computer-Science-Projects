/*
 * Date: Nov 25th
 * Course: ICS4U1-02
 * Name: Saheer, Eshan
 * Significant help: none
 * Description: This class contains 5 usable sounds that can be accessed by any class at any moment
 * and will play the sounds with the right argument
 */
package View;

import Model.*;
import Controller.*;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	/*
	 * playSounds(1): click (got from pixabay.com) playSounds(2): card flip (got
	 * from pixabay.com) playSounds(3): game music (got from youtube) playSounds(4):
	 * game win (got from pixabay.com) playSounds(5): cash register/sell card (got
	 * from pixabay.com)
	 */

	// the two attributes used to play sound on command
	private static AudioInputStream audioStream;
	private static Clip clip;

	// method that is called whenever sounds are requested to be played
	public static void playSounds(int num) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		num -= 1; // because the files start at sound 0, the user can still put sound 1 to
					// indicate the first sound file..
		// adds the chosen file to the audio system object
		audioStream = AudioSystem.getAudioInputStream(new File("Sounds/" + num + ".wav"));

		// assigns the chosen song into the clip
		clip = AudioSystem.getClip();

		// adds the selected audio to the clip
		clip.open(audioStream);

		// plays the added file
		clip.start();

	}

}
