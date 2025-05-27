import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic {
    private Clip clip;
    private boolean isMuted = false;
    private String currentPath;

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

    public void mute() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        isMuted = true;
    }

    public void unmute() {
        isMuted = false;
        play(currentPath);
    }

    public boolean isMuted() {
        return isMuted;
    }
}
