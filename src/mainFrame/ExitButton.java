package mainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Represents an exit button for the application.
 * When clicked, it prompts the user with a confirmation dialog,
 * and closes the application window if confirmed.
 *
 * @author Zdeněk Vacek
 */
public class ExitButton extends JButton {

    /**
     * Constructs an ExitButton with predefined styling and action listener.
     * When clicked, it asks the user for confirmation before closing the frame.
     *
     * @param frame the JFrame to be closed upon confirmation.
     */
    public ExitButton(JFrame frame) {
        this.setText("EXIT");
        this.setPreferredSize(new Dimension(200, 50));
        this.setMaximumSize(new Dimension(200, 50));
        this.setFont(new Font("Impact", Font.BOLD, 25));
        this.setForeground(Color.RED);
        this.setContentAreaFilled(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Accept", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });
    }
}
