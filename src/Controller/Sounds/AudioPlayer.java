package Controller.Sounds;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Generic audio player
 */
public class AudioPlayer {

	private static AudioPlayer instance;
	private Clip clip;

	/**
	 * Implementation of singleton pattern of audio player
	 * @return instance of audio player
	 */
	public static AudioPlayer getInstance() {
		if (instance == null)
			instance = new AudioPlayer();
		return instance;
	}

	private AudioPlayer() {

	}

	/**
	 * Play a file
	 * @param filename filename to be played
 	 */
	public void play(String filename) {

		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Stop current playing
	 */
	public void stop(){
		if (isClipRunning())
				clip.stop();
	}

	/**
	 * Check if clip is running
	 * @return true if clip is running
	 */
	public boolean isClipRunning(){
		return clip != null && clip.isRunning();
	}

	/**
	 * Get duration of actual clip in millisecond
	 * @return clip duration
	 */
	public int getClipDuration(){
		return (int) (clip.getMicrosecondLength() / 1000);
	}
}
