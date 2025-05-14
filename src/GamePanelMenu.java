import javax.swing.*;
import java.awt.*;

public class GamePanelMenu extends JPanel {

private JPopupMenu popup;
public GamePanelMenu(Frame frame) {
    popup = new JPopupMenu();
    Icon exitIcon = new ImageIcon(getClass().getResource("/images/exit.png"));
    Icon helpIcon = new ImageIcon(getClass().getResource("/images/help.png"));


    StyledMenuItem exitItem = new StyledMenuItem("Exit To Main Menu", exitIcon);
    StyledMenuItem helpItem = new StyledMenuItem("Help", helpIcon);

    popup.add(exitItem);
    popup.add(helpItem);

    exitItem.addActionListener(e -> {
        frame.switchTo("menu");
    });

    helpItem.addActionListener(e -> {
        frame.switchTo("help");

    });
}

    public void showPopupMenu(Component parent, Frame frame) {
        popup.pack();
        Dimension size = popup.getPreferredSize();


        int centerX = frame.getWidth() / 2 - size.width / 2;
        int centerY = frame.getHeight() / 2 - size.height / 2;


        popup.show(frame, centerX, centerY);


    }




}
