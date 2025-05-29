package other;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Handles background music playback for the application.
 * Supports looping playback, mute/unmute functionality, and error handling.
 *
 * Only one music track is played at a time.
 *
 * @author Zdeněk Vacek with help of Chat-GPT.
 */
public class BackgroundMusic {
    private Clip clip;
    private boolean isMuted = false;
    private String currentPath;

    /**
     * Plays the specified music file in a continuous loop.
     * If the music is muted, it will not play until unmuted.
     *
     * @param filePath the path to the .wav file to be played
     */
    public void play(String filePath) {
        currentPath = filePath;

        if (isMuted) return;

        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                System.out.println("Music file not found: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mutes the background music if it is currently playing.
     */
    public void mute() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        isMuted = true;
    }

    /**
     * Unmutes the music and resumes playback of the last played track.
     */
    public void unmute() {
        isMuted = false;
        play(currentPath);
    }

    /**
     * Returns whether the music is currently muted.
     *
     * @return true if muted, false otherwise
     */
    public boolean isMuted() {
        return isMuted;
    }
}
