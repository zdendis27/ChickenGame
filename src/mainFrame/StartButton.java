package mainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A custom styled button used to start the game from the main menu.
 * Features a transparent red hover effect and switches to the game panel when clicked.
 *
 * @author Zdeněk Vacek
 */
public class StartButton extends JButton {

    private Color whiteTransparent = new Color(255, 255, 255, 100);
    private Color transparentRed = new Color(255, 0, 0, 100);

    /**
     * Constructs a new StartButton that switches to the "game" panel when clicked.
     *
     * @param frame the main frame of game.
     */
    public StartButton(Frame frame) {
        this.setText("START");
        this.setPreferredSize(new Dimension(400, 100));
        this.setMaximumSize(new Dimension(400, 100));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setForeground(Color.BLACK);
        setContentAreaFilled(false);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
        this.setFont(new Font("Impact", Font.BOLD, 100));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setContentAreaFilled(true);
                setBackground(transparentRed);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setContentAreaFilled(false);
            }
        });

        this.addActionListener(e -> frame.switchTo("game"));
    }
}
