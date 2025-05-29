package game;

import mainFrame.Frame;
import other.LoadIcons;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a custom popup menu used in the game panel.
 *
 * The menu includes options to navigate to different screens (Menu, Help, Shop)
 * and to mute/unmute background music.
 *
 * @author Zdeněk Vacek
 */
public class GamePanelMenu extends JPanel {

    private JPopupMenu popup;

    /**
     * Constructs the popup menu with styled menu items and action listeners.
     *
     * @param frame Reference to the main game frame for screen switching and audio control.
     */
    public GamePanelMenu(mainFrame.Frame frame) {
        popup = new JPopupMenu();
        ImageIcon exitIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/exit.png", 48, 48).getImage());
        ImageIcon helpIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/help.png", 48, 48).getImage());
        ImageIcon shopIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/shop.png", 48, 48).getImage());
        ImageIcon musicIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/music.png", 48, 48).getImage());

        StyledMenuItem exitItem = new StyledMenuItem("Exit To Main Menu", exitIcon);
        StyledMenuItem helpItem = new StyledMenuItem("Help", helpIcon);
        StyledMenuItem shopItem = new StyledMenuItem("Shop", shopIcon);
        StyledMenuItem musicItem = new StyledMenuItem("Mute/Unmute", musicIcon);

        popup.add(exitItem);
        popup.add(helpItem);
        popup.add(shopItem);
        popup.add(musicItem);

        exitItem.addActionListener(e -> frame.switchTo("menu"));
        helpItem.addActionListener(e -> frame.switchTo("help"));
        shopItem.addActionListener(e -> frame.switchTo("shop"));
        musicItem.addActionListener(e -> {
            if (frame.getBackgroundMusic().isMuted()) {
                frame.getBackgroundMusic().unmute();
            } else {
                frame.getBackgroundMusic().mute();
            }
        });
    }

    /**
     * Displays the popup menu centered on the given frame.
     *
     * @param parent The component to anchor the popup to.
     * @param frame The frame in which the popup is displayed.
     */
    public void showPopupMenu(Component parent, Frame frame) {
        popup.pack();
        Dimension size = popup.getPreferredSize();
        int centerX = frame.getWidth() / 2 - size.width / 2;
        int centerY = frame.getHeight() / 2 - size.height / 2;
        popup.show(frame, centerX, centerY);
    }
}
