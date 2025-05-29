package game;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JButton used in the game panel for consistent styling.
 *
 * @author Zdeněk Vacek
 */
public class GamePanelButtons extends JButton {

    /**
     * Constructs a custom game button with predefined styling and text.
     *
     * @param text The text to be displayed on the button.
     */
    public GamePanelButtons(String text) {
        this.setText(text);
        this.setPreferredSize(new Dimension(100, 50));
        this.setMaximumSize(new Dimension(100, 50));
        this.setFont(new Font("Impact", Font.BOLD, 16));
        this.setForeground(Color.RED);
        this.setContentAreaFilled(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
