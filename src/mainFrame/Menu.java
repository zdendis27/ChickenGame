package mainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main menu panel of the game.
 *
 * The menu displays a background image and two buttons: Start and Exit.
 *
 * The panel is added to the main frame.
 *
 * @author Zdeněk Vacek
 */
public class Menu extends JPanel {

    /**
     * Constructs the main menu panel with background and buttons.
     *
     * @param frame Reference to the main game frame for navigation and exit.
     */
    public Menu(Frame frame) {
        ImageIcon backgroundImage = new ImageIcon("src/images/bq 2.png");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) {
            @Override
            public void layoutContainer(Container target) {
                super.layoutContainer(target);
            }
        });

        this.setOpaque(false);

        StartButton startButton = new StartButton(frame);
        ExitButton exitButton = new ExitButton(frame);

        this.add(Box.createVerticalStrut(350)); // Vizuální mezera odshora
        this.add(startButton);
        this.add(Box.createVerticalStrut(20));  // Mezery mezi tlačítky
        this.add(exitButton);
        this.add(Box.createVerticalGlue());     // Posune obsah vzhůru, pokud je místo
    }

    /**
     * Paints the background image of the menu.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon backgroundImage = new ImageIcon("src/images/bq 2.png");
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
