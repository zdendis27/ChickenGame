package game;

import javax.swing.*;
import java.awt.*;

public class GamePanelButtons extends JButton {

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
