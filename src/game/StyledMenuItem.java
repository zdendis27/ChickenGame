package game;

import javax.swing.*;
import java.awt.*;

/**
 * StyledMenuItem is a customized version of {@link JMenuItem} that applies consistent
 * styling for use in game menus. It uses a dark background, white text, and bold font.
 * The icon and text are horizontally aligned with spacing.
 *
 * @author  Zdeněk Vacek
 */
public class StyledMenuItem extends JMenuItem {

    /**
     * Constructs a styled menu item with specified text and icon.
     *
     * @param text The label of the menu item.
     * @param icon The icon to be displayed next to the text.
     */
    public StyledMenuItem(String text, Icon icon) {
        super(text, icon);

        setFont(new Font("Impact", Font.BOLD, 24));
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setIconTextGap(10);
    }
}

