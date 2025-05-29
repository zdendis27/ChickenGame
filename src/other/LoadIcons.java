package other;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for loading and resizing image icons from file paths.
 * Provides a static method to load an image and scale it to a given size.
 *
 * @Author: ChatGPT
 * @version 1.0
 */
public class LoadIcons {

    /**
     * Loads an image from the specified file path and resizes it to the given dimensions.
     *
     * @param path  the path to the image file
     * @param width the desired width of the resulting icon
     * @param height the desired height of the resulting icon
     * @return the resized {@code ImageIcon}, or {@code null} if loading fails
     */
    public static ImageIcon loadResizedIcon(String path, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
