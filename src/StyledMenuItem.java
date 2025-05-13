import javax.swing.*;
import java.awt.*;

public class StyledMenuItem extends JMenuItem {

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
