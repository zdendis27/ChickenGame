import javax.swing.*;
import java.awt.*;

public class GamePanelMenu extends JPanel {

private JPopupMenu popup;

public GamePanelMenu(Frame frame) {
    popup = new JPopupMenu();
    ImageIcon exitIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/exit.png", 48,48).getImage());
    ImageIcon helpIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/help.png", 48,48).getImage());
    ImageIcon shopIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/shop.png", 48,48).getImage());
    ImageIcon musicIcon = new ImageIcon(LoadIcons.loadResizedIcon("src/images/music.png", 48,48).getImage());


    StyledMenuItem exitItem = new StyledMenuItem("Exit To Main Menu", exitIcon);
    StyledMenuItem helpItem = new StyledMenuItem("Help", helpIcon);
    StyledMenuItem shopItem = new StyledMenuItem("Shop", shopIcon);
    StyledMenuItem musicItem = new StyledMenuItem("Mute/Unmute", musicIcon);

    popup.add(exitItem);
    popup.add(helpItem);
    popup.add(shopItem);
    popup.add(musicItem);

    exitItem.addActionListener(e -> {
        frame.switchTo("menu");
    });

    helpItem.addActionListener(e -> {
        frame.switchTo("help");


    });

    shopItem.addActionListener(e -> {
        frame.switchTo("shop");
    });
    musicItem.addActionListener(e -> {
        if(frame.getBackgroundMusic().isMuted() == true) {
            frame.getBackgroundMusic().unmute();
        }else{
            frame.getBackgroundMusic().mute();
        }

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
